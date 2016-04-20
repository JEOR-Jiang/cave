package cave.dao.impl;

import cave.dao.UserDao;
import cave.dao.UserGroupDao;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
@Repository("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup> implements UserGroupDao {

    public List<UserGroup> findByUser(User user) throws Exception {
        String sql="select ug.* from userGroup ug inner join userGroup_ref ugr on ug.id=ugr.groupId where ugr.userId=?";
        List<UserGroup> userGroups=this.getListBySQL(sql,user.getId());
        return userGroups;
    }

    public List<UserGroup> findByRole(Role role) throws Exception {
        return null;
    }
}
