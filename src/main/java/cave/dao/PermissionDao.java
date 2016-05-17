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

    //---------------------------------------------------------用户组关联---------------------------------------------------------
    /**
     * 查询用户组的权限
     */
    public List<Permission> findByUsergroup(UserGroup userGroup)throws Exception;

    //---------------------------------------------------------用户关联---------------------------------------------------------
    /**
     * 查询用户的权限
     * @param style 获取角色方式：1-用户角色，2-用户组角色，3-两种都查询
     */
    public List<Permission> findByUser(Integer style,User user)throws Exception;
    //---------------------------------------------------------角色关联---------------------------------------------------------
    /**
     * 查询角色的权限
     */
    public List<Permission> findByRole(Role role)throws Exception;
    /**
     * 修改角色关系
     */
    public void reviseRelationRole(Integer permissionId ,Integer[] roleIds)throws Exception;
    /**
     * 删除角色关系
     */
    public void removeRelationRole(Integer permissionId)throws Exception;



}
