package com.example.chienoki.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.chienoki.batch.dao.RssDao;

/**
 * @author amaomasashi
 *
 */
@EnableBatchProcessing
@Configuration
public class RssBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private RssDao rssDao;

    /**
     * @return
     */
    @Bean
    public Job job() {
        return jobs.get("articleImportJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    /**
     * @return
     */
    @Bean
    public Step step1() {
        return steps.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    rssDao.registerArticlesFromRss();
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
