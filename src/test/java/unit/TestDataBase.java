package unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import cave.entity.*;

/**
 * Created by Jeor on 2016/2/18.
 */

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class TestDataBase {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Test
    public void testJdbc()throws Exception{
        System.out.println("测试数据库连接");
        List<User> list= hibernateTemplate.find("FROM User");
        System.out.println(list.size());
        if(list.size()>0){
            System.out.println(list.get(0).getName());
        }
        List<PmsMenu> list2= hibernateTemplate.find("FROM "+PmsMenu.class.getName());
        System.out.println(list2.size());
        if(list2.size()>0){
            System.out.println(list2.get(0).getName());
        }


    }
}
