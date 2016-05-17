package cave.dao.impl;

import cave.dao.UserDao;
import cave.dao.UserGroupDao;
import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.utils.TransformData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeor on 2016/4/20.
 */
@Repository("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup> implements UserGroupDao {

    public List<UserGroup> findByUser(User user) throws Exception {
        String sql="select ug.* from userGroup ug inner join userGroup_ref ugr on ug.id=ugr.groupId where ugr.userId=?";
        List<UserGroup> userGroups=this.getListBySQL(sql,user.getId());
        return userGroups;
    }


    public void reviseRelationUser(Integer userGroupId, Integer[] userIds) throws Exception {
        String sql="insert into userGroup_ref(groupId,userId) values(?,?)";
        for(Integer userId:userIds){
            this.updateBySql(sql,userGroupId,userId);
        }
    }

    public void removeRelationUser(Integer userGroupId) throws Exception {
        String sql="delete userGroup_ref where groupId=?";
        this.updateBySql(sql, userGroupId);
    }

    public List<UserGroup> findByRole(Role role) throws Exception {
        String sql="select ug.* from userGroup ug inner join userGroupRole_ref ugr on ug.id=ugr.groupId where ugr.roleId=?;";
        return this.getListBySQL(sql,role.getId());
    }
    public void reviseRelationRole(Integer userGroupId, Integer[] roleIds) throws Exception {
        String sql="insert into userGroupRole_ref(groupId,roleId) values(?,?)";
        for(Integer role:roleIds){
            this.updateBySql(sql,userGroupId,role);
        }
    }

    public void removeRelationRole(Integer userGroupId) throws Exception {
        String sql="delete userGroupRole_ref where groupId=?";
        this.updateBySql(sql, userGroupId);
    }
    public List<UserGroup> findByPermission(Permission permission)throws Exception{
        String sql="select ug.* from userGroup ug inner join userGroupRole_ref ugr on ug.id=ugr.groupId" +
                " inner join rolePermission_ref rp on rp.roleId=ugr.roleId where rp.permissionId=?;";
        List<UserGroup> userGroups=this.getListBySQL(sql,permission.getId());
        return userGroups;
    }
}
