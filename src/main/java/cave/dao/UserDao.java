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
    /**
     * 检查qq号码是否存在
     * @param operation 1-qq,2-email,3-phone
     * @param param
     * @return 存在返回true
     */
    public User findByParam(Integer operation,String param);

    //---------------------------------------------------------用户组关联---------------------------------------------------------
    /**
     * 获取用户组的用户
     */
    public List<User> findByUsergroup(UserGroup userGroup)throws Exception;
    /**
     * 删除用户组关系
     */
    public void removeRelationUsergroup(Integer userId)throws Exception;
    /**
     *修改用户组关系
     */
    public void reviseRelationUsergroup(Integer userId, Integer[] userGroupIds) throws Exception ;
    //---------------------------------------------------------角色关联---------------------------------------------------------
    /**
     * 获取角色所属用户
     */
    public List<User> findByRole(Role role)throws Exception;
    /**
     * 删除角色关系
     */
    public void removeRelationRole(Integer userId)throws Exception;
    /**
     *修改角色关系
     */
    public void reviseRelationRole(Integer userId, Integer[] roleIds) throws Exception ;
    //---------------------------------------------------------权限关联---------------------------------------------------------
    /**
     * 获取权限所属的用户
     */
    public List<User> findByPermission(Permission permission)throws Exception;

}
