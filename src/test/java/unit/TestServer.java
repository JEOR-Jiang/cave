package unit;

import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.UserGroupServer;
import cave.server.UserServer;
import cave.utils.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Jeor on 2016/4/18.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class TestServer {
    @Resource
    private UserServer userServer;
    @Resource
    private UserGroupServer userGroupServer;
    @Test
    public void testServer()throws Exception{
        System.out.println("testServer start.");
        System.out.println(userServer);
        User user=this.userServer.findById(User.class,3);
        System.out.println("user:" + user.getName());

        Page page=new Page<User>();
        this.userServer.findByObjectPage(User.class, page);
        System.out.println(page.getResult());

        UserGroup userGroup =new UserGroup();
        userGroup.setName("123");
        userGroup.setParentId(0);
        this.userGroupServer.create(userGroup);
    }
}
