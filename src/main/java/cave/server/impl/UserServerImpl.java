package cave.server.impl;

import cave.dao.UserDao;
import cave.entity.User;
import cave.server.UserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jeor on 2016/1/28.
 */
@Service("userServer")
public class UserServerImpl implements UserServer {

    @Resource
    private UserDao userDao;

    public boolean login(User user)throws Exception{
        User dbUser= userDao.getUserByAccount(user);
        if(user.getPassword().equals(dbUser.getPassword())){
            return true;
        }else {
            return false;
        }
    }
}
