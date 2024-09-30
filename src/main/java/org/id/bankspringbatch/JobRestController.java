package org.id.bankspringbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JobRestController {
    private final JobLauncher jobLauncher;
    private final Job job;
    private final BankTransactionItemAnalyticsProcessor analyticsProcessor;

    @GetMapping("/startJob")
    public BatchStatus load() throws Exception {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution execution = jobLauncher.run(job, jobParameters);
        while (execution.isRunning()) {
            System.out.println("........");
        }
        return execution.getStatus();

    }

    @GetMapping("/analytics")
    public Map<String, Double> analytics() {
        Map<String, Double> map = new HashMap<>();
        map.put("totalCredit", analyticsProcessor.getTotalCredit());
        map.put("totalDebit", analyticsProcessor.getTotalDebit());
        return map;
    }
}
