package controller;

import cave.entity.User;
import cave.server.UserServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Jeor on 2016/1/28.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = {"develop"})
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class TestUserServer {
    static {
        System.setProperty("spring.profiles.active","develop");
    }

    @Resource
    private UserServer userServer;

    @Test
    public void testUserlogin()throws Exception {
        User user=new User();
        user.setName("admin");
        user.setPassword("123456");
        System.out.println(userServer);
        boolean flag=userServer.login(user);
        System.out.println("login success:"+flag);
    }

}
