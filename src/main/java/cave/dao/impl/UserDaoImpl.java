package cave.dao.impl;

import cave.dao.UserDao;
import cave.entity.Permission;
import cave.entity.Role;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.utils.PromptMessages;
import org.springframework.stereotype.Repository;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by Jeor on 2016/1/28.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public User findByParam(Integer operation, String param) {
        StringBuilder sql=new StringBuilder();
        switch (operation){
            case 1:
                sql.append("select * from [user] where qqNumber=?");
                break;
            case 2:
                sql.append("select * from [user] where email=?");
                break;
            case 3:
                sql.append("select * from [user] where phone=?");
                break;
            default:
                throw new InvalidParameterException(PromptMessages.getMsg("user.findByParamInvailOperation",operation));
        }
        Object object=this.getObjectBySQL(sql.toString(), param);
        if(object!=null){
            return (User)object;
        }else{
            return null;
        }
    }

    public void removeRelationUsergroup(Integer userId) throws Exception {
        String sql="delete userGroup_ref where userId=?";
        this.updateBySql(sql, userId);
    }

    public void reviseRelationUsergroup(Integer userId, Integer[] userGroupIds) throws Exception {
        String sql="insert into userGroup_ref(groupId,userId) values(?,?)";
        for(Integer userGroupId:userGroupIds){
            this.updateBySql(sql,userGroupId,userId);
        }
    }

    public List<User> findByUsergroup(UserGroup userGroup) throws Exception {
        String sql="select u.* from userGroup_ref r inner join [user] u on u.id=r.userId where groupId=?";
        return this.getListBySQL(sql,userGroup.getId());
    }

    public List<User> findByRole(Role role) throws Exception {
        String sql="select * from [user] u inner join userRole_ref ur on u.id=ur.userId where ur.roleId=?;";
        return this.getListBySQL(sql,role.getId());
    }

    public void removeRelationRole(Integer userId) throws Exception {
        String sql="delete userRole_ref where userId=?";
        this.updateBySql(sql, userId);
    }

    public void reviseRelationRole(Integer userId, Integer[] roleIds) throws Exception {
        String sql="insert into userRole_ref(userId,roleId) values(?,?)";
        for(Integer role:roleIds){
            this.updateBySql(sql,userId,role);
        }
    }
    public List<User> findByPermission(Permission permission) throws Exception {
        String sql="select u.* from [user] u inner join userRole_ref ur on ur.userId=u.id" +
                " inner join rolePermission_ref rp on rp.roleId=ur.roleId where rp.permissionId=?;";
        return this.getListBySQL(sql,permission.getId());
    }

}
