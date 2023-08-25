package com.paic.ussd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UssdApplication {

    public static void main(String[] args) {
        SpringApplication.run(UssdApplication.class, args);
    }

}
