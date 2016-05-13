package unit;

import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.UserGroupServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Jeor on 2016/4/20.
 */

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class TestDao {
    @Resource
    private UserGroupServer userGroupServer;
    @Test
    public void testDao()throws Exception{
        System.out.println("testDao start.");
        UserGroup usergroup=new UserGroup();
        usergroup.setId(1);
        this.userGroupServer.findDetail(usergroup);

    }


}
