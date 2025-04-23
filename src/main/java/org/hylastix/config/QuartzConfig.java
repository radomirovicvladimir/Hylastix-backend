package org.hylastix.config;

import org.springframework.beans.factory.annotation.Value;
import org.hylastix.job.ExportAndDeleteDeletedItemsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Value("${scheduler.deleted-items-cron}")
    private String cronExpression;

    @Bean
    public JobDetail exportDeletedItemsJobDetail() {
        return JobBuilder.newJob(ExportAndDeleteDeletedItemsJob.class)
                .withIdentity("exportDeletedItemsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger exportDeletedItemsTrigger(JobDetail exportDeletedItemsJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(exportDeletedItemsJobDetail)
                .withIdentity("exportDeletedItemsTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }
}
