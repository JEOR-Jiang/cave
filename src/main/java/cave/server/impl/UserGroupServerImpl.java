package cave.server.impl;

import cave.dao.UserGroupDao;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.UserGroupServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Jeor on 2016/4/19.
 */
@Service
public class UserGroupServerImpl extends BaseServerImpl<UserGroup> implements UserGroupServer {

    @Resource
    private UserGroupDao userGroupDao;
    public void reviseRelation(Integer id, Integer[]... relationIds) throws Exception {

    }

    public void findDetail(Object entity) throws Exception {
        UserGroup userGroup =(UserGroup) entity;
        User user =new User();
        user.setId(3);
        List<UserGroup> groupList= this.userGroupDao.findByUser(user);
        System.out.println("entity = [" + groupList + "]");
    }
}
