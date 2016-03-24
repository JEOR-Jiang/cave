package cave.dao.impl;

import cave.dao.UserDao;
import cave.entity.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.apache.commons.dbcp.*;

import javax.annotation.Resource;

/**
 * Created by Jeor on 2016/1/28.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    private DriverManagerDataSource dataSource;

    public User getUserByAccount(User user){
        User dbuser = new User();
        dbuser.setPassword("123456");
        return dbuser;
    }

}
