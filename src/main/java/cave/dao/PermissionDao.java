package cave.dao;

import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
public interface PermissionDao extends BaseDao<Permission> {

    /**
     * 查询用户组的权限
     */
    public List<Permission> findByUsergroup(UserGroup userGroup)throws Exception;
    /**
     * 查询用户的权限
     */
    public List<Permission> findByUser(User user)throws Exception;
    /**
     * 查询角色的权限
     */
    public List<Permission> findByRole(Role role)throws Exception;

}
