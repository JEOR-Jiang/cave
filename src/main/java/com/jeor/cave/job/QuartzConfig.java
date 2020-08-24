package com.jeor.cave.job;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "quartz")
public class QuartzConfig {

    private String schedulerName;

    private List<ScheduleJob> jobs = new ArrayList<>();
}
