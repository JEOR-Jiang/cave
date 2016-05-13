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
     * 检查qq号码是否存在
     * @param operation 1-qq,2-email,3-phone
     * @param param
     * @return 存在返回true
     */
    public User findByParam(Integer operation,String param);

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
