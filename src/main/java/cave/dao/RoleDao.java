package cave.dao;

import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
public interface RoleDao extends BaseDao<Role> {
    /**
     * 获取用户组的角色
     */
    public List<Role> findByUsergroup(UserGroup userGroup)throws Exception;
    /**
     * 获取用户的角色
     */
    public List<Role> findByUser(User user)throws Exception;
    /**
     * 获取权限的角色
     */
    public List<Role> findByPermission(Permission permission)throws Exception;





}
