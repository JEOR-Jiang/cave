package cave.dao;

import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
public interface UserGroupDao extends BaseDao<UserGroup> {
    /**
     * 获取用户所在管理组
     */
    public List<UserGroup> findByUser(User user)throws Exception;
    /**
     * 获取角色所在的管理组
     */
    public List<UserGroup> findByRole(Role role)throws Exception;

}
