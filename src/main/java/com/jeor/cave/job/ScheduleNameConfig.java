package com.jeor.cave.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleNameConfig {

    @Autowired
    private QuartzConfig quartzConfig;


    public SchedulerFactoryBeanCustomizer customizer(){
        return (schedulerFactoryBean) ->{
            schedulerFactoryBean.setSchedulerName(quartzConfig.getSchedulerName());
        };
    }


}
