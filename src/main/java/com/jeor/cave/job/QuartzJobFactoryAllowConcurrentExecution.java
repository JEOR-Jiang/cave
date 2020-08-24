package com.jeor.cave.job;

import org.quartz.*;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 14:27
 */
public class QuartzJobFactoryAllowConcurrentExecution implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        ScheduleJob.invokeMethod(scheduleJob);
    }
}
