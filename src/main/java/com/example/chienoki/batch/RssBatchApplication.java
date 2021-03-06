package com.example.chienoki.batch;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author amaomasashi
 *
 */
@SpringBootApplication
@EnableScheduling
public class RssBatchApplication {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Scheduled(cron = "* 0/30 * * * *", zone = "Asia/Tokyo")
    public void run() {
        System.out.println(" === Rss Batch started === ");
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(" === Rss Batch finished === ");
    }
}
