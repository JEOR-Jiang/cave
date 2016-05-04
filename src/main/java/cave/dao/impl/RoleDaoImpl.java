package cave.dao.impl;

import cave.dao.RoleDao;
import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.RoleServer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    public List<Role> findByUsergroup(UserGroup userGroup) throws Exception {
        String sql="select r2.* from userGroupRole_ref r inner join [role] r2 on r2.id=r.roleId where groupId=?";
        return this.getListBySQL(sql,userGroup.getId());
    }

    public List<Role> findByUser(User user) throws Exception {
        return null;
    }

    public List<Role> findByPermission(Permission permission) throws Exception {
        return null;
    }
}
