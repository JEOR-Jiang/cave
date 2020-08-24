package com.jeor.cave.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static ApplicationContext getContext(){
        return context;
    }

    public static <T> T getBean(Class<T> clz){
        return (T)context.getBean(clz);
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }



}
