package com.paic.ussd.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "cdr_logs")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "cdr_logs_id_seq", sequenceName = "cdr_logs_id_seq", allocationSize = 1)
public class CdrLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cdr_logs_id_seq")
    private Integer id;

    private String nombre;
    private LocalDateTime fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer registrosExitosos;
    private Integer registrosFallidos;
}
