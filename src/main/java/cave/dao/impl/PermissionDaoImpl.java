package cave.dao.impl;

import cave.dao.PermissionDao;
import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao {

    public List<Permission> findByUsergroup(UserGroup userGroup) throws Exception {
        return null;
    }

    public List<Permission> findByUser(User user) throws Exception {
        return null;
    }

    public List<Permission> findByRole(Role role) throws Exception {
        return null;
    }

}
