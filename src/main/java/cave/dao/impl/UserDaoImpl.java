package cave.dao.impl;

import cave.dao.BaseDaoImpl;
import cave.dao.UserDao;
import cave.entity.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.apache.commons.dbcp.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jeor on 2016/1/28.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Resource
    private DriverManagerDataSource dataSource;

    public User getUserByAccount(User user){
        List<User> users=super.createSqlQuery("select * from [user] where name=?",user.getName()).addEntity(User.class).list();
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

}
