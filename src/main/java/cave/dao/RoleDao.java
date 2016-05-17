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

    //---------------------------------------------------------用户组关联---------------------------------------------------------
    /**
     * 获取用户组的角色
     */
    public List<Role> findByUsergroup(UserGroup userGroup)throws Exception;
    /**
     * 删除用户组关系
     */
    public void removeRelationUsergroup(Integer roleId)throws Exception;
    /**
     *修改用户组关系
     */
    public void reviseRelationUsergroup(Integer roleId, Integer[] userGroupIds) throws Exception ;
    //---------------------------------------------------------用户关联---------------------------------------------------------
    /**
     * 获取用户的角色
     * @param style 获取角色方式：1-用户角色，2-用户组角色，3-两种都查询
     */
    public List<Role> findByUser(Integer style,User user)throws Exception;
    /**
     * 删除用户关系
     */
    public void removeRelationUser(Integer roleId)throws Exception;
    /**
     *修改用户关系
     */
    public void reviseRelationUser(Integer roleId, Integer[] userIds) throws Exception ;
    //---------------------------------------------------------权限关联---------------------------------------------------------
    /**
     * 获取权限的角色
     */
    public List<Role> findByPermission(Permission permission)throws Exception;
    /**
     * 删除权限关系
     */
    public void removeRelationPermission(Integer roleId)throws Exception;
    /**
     *修改权限关系
     */
    public void reviseRelationPermission(Integer roleId, Integer[] permissionIds) throws Exception ;





}
