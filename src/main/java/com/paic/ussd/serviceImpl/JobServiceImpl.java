package com.paic.ussd.serviceImpl;

import com.paic.ussd.entity.CallDetailRecords;
import com.paic.ussd.entity.CdrLogs;
import com.paic.ussd.repository.CallDetailRecordsRepository;
import com.paic.ussd.repository.CdrLogsRepository;
import com.paic.ussd.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class JobServiceImpl implements JobService {

    private final CallDetailRecordsRepository callDetailRecordsRepository;
    private final CdrLogsRepository cdrLogsRepository;

    @Value("${cdr.path}")
    private String cdrPath;

    @Scheduled(cron = "0 0/1 * * * ?")
    @Override
    public void executeJob() {
        System.out.println(cdrPath);
        File[] files = new File(cdrPath).listFiles();
        assert files != null;
        String[] fileNames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }

        List<String> archivosNoEncontrados = new ArrayList<>();

        for (String fileName : fileNames) {

            CdrLogs cdrLogs = new CdrLogs();
            cdrLogs = cdrLogsRepository.findByNombre(fileName);

            if (cdrLogs == null) {
                archivosNoEncontrados.add(fileName);
                log.info("Archivo no encontrado " + fileName);
            }
        }

        // Leer los archivos que no se encuentren en la tabla cdr_logs, dichos archivos son CDRs
        // cada linea del archivo es un CDR, cada CDR tiene campos separados por |
        // Cada CDR se debe guardar en la tabla call_detail_records

        CdrLogs cdrLogs = new CdrLogs();

        if (archivosNoEncontrados.isEmpty()) {
            log.info("No hay archivos nuevos");
            return;
        }

        for (String archivoNoEncontrado : archivosNoEncontrados) {
            log.info("Archivo no encontrado " + archivoNoEncontrado);
            Integer registrosExitosos = 0;
            Integer registrosFallidos = 0;

            cdrLogs.setNombre(archivoNoEncontrado);
            cdrLogs.setFecha(java.time.LocalDateTime.now());
            cdrLogs.setHoraInicio(java.time.LocalTime.now());

            File file = new File(cdrPath + '/' + archivoNoEncontrado);
            java.io.BufferedReader br = null;
            String line = "";
            String cvsSplitBy = "\\|";

            try {
                br = new java.io.BufferedReader(new java.io.FileReader(file));
                while ((line = br.readLine()) != null) {
                    String[] cdr = line.split(cvsSplitBy);

                    if (cdr.length == 33) {
                        cdr[0] = cdr[0].replace(',', '.');
                        cdr[27] = cdr[27].replace(',', '.');

                        for (int i = 0; i < cdr.length; i++) {
                            if (cdr[i].equals("")) {
                                cdr[i] = null;
                            }
                        }

                        try {
                            CallDetailRecords detalle = new CallDetailRecords(cdr);
                            callDetailRecordsRepository.save(detalle);
                            registrosExitosos++;
                            log.info("Registro exitoso " + registrosExitosos);
                        }catch (Exception e){
                            registrosFallidos++;
                            log.error("Registro fallido " + registrosFallidos);
                        }
                    } else {
                        log.error("Registro fallido " + registrosFallidos);
                        registrosFallidos++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            cdrLogs.setHoraFin(java.time.LocalTime.now());
            cdrLogs.setRegistrosExitosos(registrosExitosos);
            cdrLogs.setRegistrosFallidos(registrosFallidos);
            cdrLogsRepository.save(cdrLogs);
        }
    }
}
