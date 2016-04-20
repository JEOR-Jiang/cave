package unit;

import cave.dao.UserGroupDao;
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
import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDao {
    @Resource
    private UserGroupServer userGroupServer;
    @Test
    public void testDao()throws Exception{
        System.out.println("testDao start.");
        this.userGroupServer.findDetail(null);

    }


}
