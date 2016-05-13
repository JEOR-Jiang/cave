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
        Object object=this.getObjectBySQL(sql.toString(),param);
        if(object!=null){
            return (User)object;
        }else{
            return null;
        }
    }

    public User getUserByAccount(User user){
        List<User> users=super.createSqlQuery("select * from [user] where name=?",user.getName()).addEntity(User.class).list();
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    public List<User> findByUsergroup(UserGroup userGroup) throws Exception {
        String sql="select u.* from userGroup_ref r inner join [user] u on u.id=r.userId where groupId=?";
        return this.getListBySQL(sql,userGroup.getId());
    }

    public List<User> findByRole(Role role) throws Exception {
        return null;
    }

    public List<User> findByPermission(Permission permission) throws Exception {
        return null;
    }
}
