package com.jeor.cave.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 14:38
 */
@Slf4j
@Component
public class DemoTask {

    public void doTask(){
        log.info("is time do demo task.");
    }
}
