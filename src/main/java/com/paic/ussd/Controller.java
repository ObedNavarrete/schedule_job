package com.paic.ussd;

import com.paic.ussd.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final JobService jobService;

    @GetMapping("/executeJob")
    public void executeJob() {
        jobService.executeJob();
    }
}
