package com.jeor.cave.job;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class ScheduleJobFactory {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private QuartzConfig quartzConfig;

    /**
     * 初始化任务
     *
     * @param job
     * @throws Exception
     */
    public void initJob(ScheduleJob job) throws Exception {
        if (job == null) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        if (!ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            log.info("删除任务{}", job.getJobName());
            this.deleteJob(scheduler, triggerKey);
        } else {
            log.info(scheduler + "添加任务: {}", JSONObject.toJSON(job));
            addJob(scheduler, triggerKey, job);
        }
    }

    /**
     * 添加定时任务job
     *
     * @param scheduler  调度器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    private void addJob(Scheduler scheduler, TriggerKey triggerKey, ScheduleJob job) throws Exception {

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            setJobExistTrigger(scheduler, trigger, triggerKey, job);
        } else {
            setJobNoExistTrigger(scheduler, trigger, triggerKey, job);
        }

    }

    /**
     * 如果触发器key不存在, 设置job
     *
     * @param scheduler  调度器
     * @param trigger    触发器
     * @param triggerKey 触发器key
     * @param job        任务
     * @throws Exception
     */
    private void setJobNoExistTrigger(Scheduler scheduler, CronTrigger trigger, TriggerKey triggerKey, ScheduleJob job) throws Exception {
        // Trigger已存在, 那么更新相应的定时设置
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }


    /**
     * 如果触发器key存在, 设置job
     *
     * @param scheduler  调度器
     * @param trigger    触发器
     * @param triggerKey 触发器key
     * @param job        任务
     */
    private void setJobExistTrigger(Scheduler scheduler, CronTrigger trigger, TriggerKey triggerKey, ScheduleJob job) throws Exception {

        Class<? extends Job> clz = job.isConcurrent() ? QuartzJobFactoryAllowConcurrentExecution.class
                : QuartzJobFactoryDisallowConcurrentExecution.class;

        JobDetail detail = JobBuilder.newJob(clz).withIdentity(job.getJobName(), job.getJobGroup()).build();
        detail.getJobDataMap().put("scheduleJob", job);

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
                .withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(detail, trigger);
    }


    /**
     * 删除job
     *
     * @param scheduler  调度器
     * @param triggerKey 触发器key
     * @throws Exception
     */
    private void deleteJob(Scheduler scheduler, TriggerKey triggerKey) throws Exception {
        if (triggerKey != null) {
            scheduler.unscheduleJob(triggerKey);
        }
    }

    @PostConstruct
    public void init() {
        List<ScheduleJob> jobs = quartzConfig.getJobs();
        log.info("任务初始化开始....");
        try {
            for (ScheduleJob job : jobs) {
                initJob(job);
            }
        } catch (Exception e) {
            log.error("任务初始化异常", e);
        }

    }


}
