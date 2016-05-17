package cave.dao.impl;

import cave.dao.PermissionDao;
import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.utils.PromptMessages;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao {

    public List<Permission> findByUsergroup(UserGroup userGroup) throws Exception {
        String sql="select p.* from rolePermission_ref rp inner join permission p on p.id=rp.permissionId " +
                " inner join userGroupRole_ref ugr on rp.roleId=ugr.roleId where ugr.groupId=?";
        return this.getListBySQL(sql,userGroup.getId());
    }

    public List<Permission> findByUser(Integer style,User user) throws Exception {
        StringBuilder sql=new StringBuilder();
        switch (style){
            case 1:
                sql.append("select p.* from userRole_ref ur inner join rolePermission_ref rp on ur.roleId=rp.roleId");
                sql.append(" inner join permission p on p.id=rp.permissionId where ur.userId=?");
                return this.getListBySQL(sql.toString(),user.getId());
            case 2:
                sql.append("select p.* from userGroup_ref ugr inner join userGroupRole_ref ugrr on ugrr.groupId=ugr.groupId");
                sql.append(" inner join rolePermission_ref rp on rp.roleId=ugrr.roleId inner join permission p on p.id=rp.permissionId where ugr.userId=?");
                return this.getListBySQL(sql.toString(),user.getId());
            case 3:
                sql.append("select p.* from userRole_ref ur inner join rolePermission_ref rp on ur.roleId=rp.roleId");
                sql.append(" inner join permission p on p.id=rp.permissionId where ur.userId=? union");
                sql.append(" select p.* from userGroup_ref ugr inner join userGroupRole_ref ugrr on ugrr.groupId=ugr.groupId");
                sql.append(" inner join rolePermission_ref rp on rp.roleId=ugrr.roleId inner join permission p on p.id=rp.permissionId where ugr.userId=?");
                return this.getListBySQL(sql.toString(),user.getId(),user.getId());
            default:
                throw new IllegalArgumentException(PromptMessages.getMsg("user.rolefindByUserInvailOperation", style));
        }
    }

    public List<Permission> findByRole(Role role) throws Exception {
        String sql="select p.* from rolePermission_ref rp inner join permission p on p.id=rp.permissionId where rp.roleId=?;";
        return this.getListBySQL(sql,role.getId());
    }


    public void removeRelationRole(Integer permissionId) throws Exception {
        String sql="delete rolePermission_ref where permissionId=?";
        this.updateBySql(sql, permissionId);
    }

    public void reviseRelationRole(Integer permissionId, Integer[] roleIds) throws Exception {
        String sql="insert into rolePermission_ref(roleId,permissionId) values(?,?)";
        for(Integer roleId:roleIds){
            this.updateBySql(sql,roleId,permissionId);
        }
    }
}
