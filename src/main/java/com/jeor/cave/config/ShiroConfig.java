package com.jeor.cave.config;

import com.jeor.cave.shiro.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 14:40
 */
@Configuration
public class ShiroConfig {

    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorizedurl");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/doLogin", "anon");;
        map.put("/swagger/**", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/doc.html","anon");


        map.put("/v2/api-docs", "anon");

        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

}
