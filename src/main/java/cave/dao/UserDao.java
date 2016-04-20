package cave.dao;

import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;

import java.util.List;

/**
 * Created by Jeor on 2016/1/28.
 */
public interface UserDao extends BaseDao<User>{
    public User getUserByAccount(User user);

    /**
     * 获取用户组的用户
     */
    public List<User> findByUsergroup(UserGroup userGroup)throws Exception;
    /**
     * 获取角色所属用户
     */
    public List<User> findByRole(Role role)throws Exception;
    /**
     * 获取权限所属的用户
     */
    public List<User> findByPermission(Permission permission)throws Exception;
}
