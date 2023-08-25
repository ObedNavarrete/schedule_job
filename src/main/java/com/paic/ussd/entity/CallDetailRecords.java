package com.paic.ussd.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "call_detail_records")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallDetailRecords {
    @NotNull
    private Timestamp recordDate;
    private Integer lSpc;
    private Integer lSsn;
    private Integer lRi;
    private Integer lGtI;
    private String lGtDigits;
    private Integer rSpc;
    private Integer rSsn;
    private Integer rRi;
    private Integer rGtI;
    private String rGtDigits;
    private String serviceCode;
    private Integer orNature;
    private Integer orPlan;
    private String orDigits;
    private Integer deNature;
    private Integer dePlan;
    private String deDigits;
    private Integer isdnNature;
    private Integer isdnPlan;
    private String msisdn;
    private Integer vlrNature;
    private Integer vlrPlan;
    private String vlrDigits;
    private String imsi;
    @NotNull
    private String status;
    @NotNull
    private String type;
    @NotNull
    private Timestamp tstamp;
    private BigDecimal localDialogId;
    private BigDecimal remoteDialogId;
    private BigDecimal dialogDuration;
    private String ussdString;
    @Id
    @NotNull
    private String id;

    public CallDetailRecords(String[] cdr) {
        this.recordDate = cdr[0] != null ? Timestamp.valueOf(cdr[0]) : null;
        this.lSpc = cdr[1] != null ? Integer.parseInt(cdr[1]) : null;
        this.lSsn = cdr[2] != null ? Integer.parseInt(cdr[2]) : null;
        this.lRi = cdr[3] != null ? Integer.parseInt(cdr[3]) : null;
        this.lGtI = cdr[4] != null ? Integer.parseInt(cdr[4]) : null;
        this.lGtDigits = cdr[5];
        this.rSpc = cdr[6] != null ? Integer.parseInt(cdr[6]) : null;
        this.rSsn = cdr[7] != null ? Integer.parseInt(cdr[7]) : null;
        this.rRi = cdr[8] != null ? Integer.parseInt(cdr[8]) : null;
        this.rGtI = cdr[9] != null ? Integer.parseInt(cdr[9]) : null;
        this.rGtDigits = cdr[10];
        this.serviceCode = cdr[11];
        this.orNature = cdr[12] != null ? Integer.parseInt(cdr[12]) : null;
        this.orPlan = cdr[13] != null ? Integer.parseInt(cdr[13]) : null;
        this.orDigits = cdr[14];
        this.deNature = cdr[15] != null ? Integer.parseInt(cdr[15]) : null;
        this.dePlan = cdr[16] != null ? Integer.parseInt(cdr[16]) : null;
        this.deDigits = cdr[17];
        this.isdnNature = cdr[18] != null ? Integer.parseInt(cdr[18]) : null;
        this.isdnPlan = cdr[19] != null ? Integer.parseInt(cdr[19]) : null;
        this.msisdn = cdr[20];
        this.vlrNature = cdr[21] != null ? Integer.parseInt(cdr[21]) : null;
        this.vlrPlan = cdr[22] != null ? Integer.parseInt(cdr[22]) : null;
        this.vlrDigits = cdr[23];
        this.imsi = cdr[24];
        this.status = cdr[25];
        this.type = cdr[26];
        this.tstamp = cdr[27] != null ? Timestamp.valueOf(cdr[27]) : null;
        this.localDialogId = cdr[28] != null ? new BigDecimal(cdr[28]) : null;
        this.remoteDialogId = cdr[29] != null ? new BigDecimal(cdr[29]) : null;
        this.dialogDuration = cdr[30] != null ? new BigDecimal(cdr[30]) : null;
        this.ussdString = cdr[31];
        this.id = cdr[32];
    }
}
