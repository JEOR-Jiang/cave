package com.jeor.cave.job;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jeor.cave.common.utils.SpringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 14:29
 */
@Slf4j
@Component
@Data
public class ScheduleJob implements Serializable {

    public static final String STATUS_RUNNING = "1";

    public static final String STATUS_NOT_RUNNING = "0";

    /**
     *  任务名称
     */
    private String jobName;

    /**
     *  任务分组
     */
    private String jobGroup;

    /**
     *  任务状态  是否启动任务
     */
    private String jobStatus;

    /**
     *  cron表达式
     */
    private String cronExpression;

    /**
     *  spring bean
     */
    private String jobClass;

    /**
     *  任务调用的方法名
     */
    private String methodName;

    /**
     *  任务调用的方法传入的参数, 统一使用String
     */
    private Map<String, String> parameters;

    /**
     *  任务是否有状态
     */
    private boolean isConcurrent;


    /**
     *  延迟
     */
    private Integer delay;


    /**
     *  重试次数
     */
    private Integer maxTimes;


    /**
     *   通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob   调度任务
     */
    public static void invokeMethod(ScheduleJob scheduleJob)  {

        Object object = null;

        Class clz;

        if (StringUtils.isNotBlank(scheduleJob.getJobClass())) {
            object = SpringUtils.getBean(scheduleJob.getJobClass());
        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]未启动成功, 请检查配置是否正确!!!");
            return;
        }
        clz = object.getClass();
        Method method = null;

        try {
            if (scheduleJob.getParameters() == null) {
                method = clz.getDeclaredMethod(scheduleJob.getMethodName());
                method.invoke(object);
            }else {
                method = clz.getDeclaredMethod(scheduleJob.getMethodName(), Map.class);
                method.invoke(object, scheduleJob.getParameters());
            }
        }catch (Exception e){
            log.error("job反射执行方法异常", e);
            if (e instanceof InvocationTargetException) {
                InvocationTargetException invokeException = (InvocationTargetException) e;
                throw new RuntimeException(invokeException.getTargetException());
            }
        }
    }
}