package cave.dao.impl;

import cave.dao.RoleDao;
import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.RoleServer;
import cave.utils.PromptMessages;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    public List<Role> findByUsergroup(UserGroup userGroup) throws Exception {
        String sql="select r2.* from userGroupRole_ref r inner join [role] r2 on r2.id=r.roleId where groupId=?";
        return this.getListBySQL(sql,userGroup.getId());
    }

    public List<Role> findByUser(Integer style,User user) throws Exception {
        StringBuilder sql=new StringBuilder();
        switch (style){
            case 1:
                sql.append("select r2.* from userRole_ref r inner join [role] r2 on r2.id=r.roleId where r.userId=?;");
                return this.getListBySQL(sql.toString(),user.getId());
            case 2:
                sql.append("select r.* from userGroup_ref ugr inner join userGroupRole_ref ugrr on ugr.groupId=ugrr.groupId");
                sql.append(" inner join role r on r.id=ugrr.roleId where ugr.userId=?;");
                return this.getListBySQL(sql.toString(),user.getId());
            case 3:
                sql.append("select r.* from userGroup_ref ugr inner join userGroupRole_ref ugrr on ugr.groupId=ugrr.groupId");
                sql.append(" inner join role r on r.id=ugrr.roleId where ugr.userId=? union");
                sql.append(" select r2.* from userRole_ref r inner join [role] r2 on r2.id=r.roleId where r.userId=?");
                return this.getListBySQL(sql.toString(),user.getId(),user.getId());
            default:
                throw new IllegalArgumentException(PromptMessages.getMsg("user.rolefindByUserInvailOperation",style));
        }

    }

    public List<Role> findByPermission(Permission permission) throws Exception {
        String sql="select r2.* from rolePermission_ref r inner join [role] r2 on r2.id=r.roleId where r.permissionId=?";
        return this.getListBySQL(sql,permission.getId());
    }

    public void removeRelationUsergroup(Integer roleId) throws Exception {
        String sql="delete userGroupRole_ref where roleId=?";
        this.updateBySql(sql, roleId);
    }

    public void reviseRelationUsergroup(Integer roleId, Integer[] userGroupIds) throws Exception {
        String sql="insert into userGroupRole_ref(groupId,roleId) values(?,?)";
        for(Integer userGroupId:userGroupIds){
            this.updateBySql(sql,userGroupId,roleId);
        }
    }

    public void removeRelationUser(Integer roleId) throws Exception {
        String sql="delete userRole_ref where roleId=?";
        this.updateBySql(sql, roleId);
    }

    public void reviseRelationUser(Integer roleId, Integer[] userIds) throws Exception {
        String sql="insert into userRole_ref(userId,roleId) values(?,?)";
        for(Integer userId:userIds){
            this.updateBySql(sql,userId,roleId);
        }
    }
    public void removeRelationPermission(Integer roleId) throws Exception {
        String sql="delete rolePermission_ref where roleId=?";
        this.updateBySql(sql, roleId);
    }

    public void reviseRelationPermission(Integer roleId, Integer[] permissionIds) throws Exception {
        String sql="insert into rolePermission_ref(roleId,permissionId) values(?,?)";
        for(Integer permissionId:permissionIds){
            this.updateBySql(sql,roleId,permissionId);
        }
    }
}
