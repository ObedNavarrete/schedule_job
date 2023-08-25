package com.paic.ussd.service;

public interface JobService {
    // Schedule que se ejecute cada 1 minuto
    //@Scheduled(cron = "0 0/1 * * * ?")
    void executeJob();
}
