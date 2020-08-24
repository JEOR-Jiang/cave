package com.jeor.cave.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 14:28
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution  implements Job {


    private final Logger log = LoggerFactory.getLogger(QuartzJobFactoryDisallowConcurrentExecution.class);

    private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(4);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap dataMap = context.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) dataMap.get("scheduleJob");
        try {
            ScheduleJob.invokeMethod(scheduleJob);
        }catch (RuntimeException e){
            Integer delay = scheduleJob.getDelay();
            Integer maxTimes = scheduleJob.getMaxTimes();
            int times = 0;
            while ( delay != null && maxTimes != null && delay > 0 && maxTimes > 0 && times < maxTimes) {
                times ++;
                int delayTime = delay;
                for (int i = 0; i < times; i++) {
                    delayTime += i * delay;
                }
                log.error("任务:[{}]执行失败, 正在进行第{}次重试", scheduleJob.getJobName(), times);
                executor.schedule(() -> ScheduleJob.invokeMethod(scheduleJob), delayTime, TimeUnit.MINUTES);
            }
            log.error("任务: [{}]执行失败!", scheduleJob.getJobName(), e);
        }

    }
}
