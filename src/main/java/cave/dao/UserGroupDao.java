package cave.dao;

import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
public interface UserGroupDao extends BaseDao<UserGroup> {
    //---------------------------------------------------------用户关联---------------------------------------------------------
    /**
     * 获取用户所在管理组
     */
    public List<UserGroup> findByUser(User user)throws Exception;
    /**
     * 修改用户关系
     */
    public void reviseRelationUser(Integer userGroupId ,Integer[] userIds)throws Exception;
        /**
     * 删除用户关系
     */
    public void removeRelationUser(Integer userGroupId)throws Exception;

    //---------------------------------------------------------角色关联---------------------------------------------------------
    /**
     * 获取角色所在的管理组
     */
    public List<UserGroup> findByRole(Role role)throws Exception;
    /**
     * 修改角色关系
     */
    public void reviseRelationRole(Integer userGroupId ,Integer[] roleIds)throws Exception;
    /**
     * 删除角色关系
     */
    public void removeRelationRole(Integer userGroupId )throws Exception;

    //---------------------------------------------------------权限关联---------------------------------------------------------
    /**
     * 获取拥有权限的用户组
     */
    public List<UserGroup> findByPermission(Permission permission)throws Exception;
}
