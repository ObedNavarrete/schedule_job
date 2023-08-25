package com.paic.ussd.repository;

import com.paic.ussd.entity.CdrLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdrLogsRepository extends JpaRepository<CdrLogs, Integer> {
    CdrLogs findByNombre(String nombre);
}
