package cave.server.impl;

import cave.dao.PermissionDao;
import cave.dao.RoleDao;
import cave.dao.UserDao;
import cave.dao.UserGroupDao;
import cave.entity.Permission;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.UserServer;
import cave.utils.ParamtersUtil;
import cave.utils.PromptMessages;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import javax.annotation.Resource;
import java.security.InvalidParameterException;

/**
 * Created by Jeor on 2016/1/28.
 */
@Service("userServer")
public class UserServerImpl extends BaseServerImpl<User> implements UserServer {

    @Resource
    private UserDao userDao;
    @Resource
    private UserGroupDao userGroupDao;
    @Resource
    protected RoleDao roleDao;
    @Resource
    private PermissionDao permissionDao;



    public boolean login(User user)throws Exception{
        User dbUser= userDao.getUserByAccount(user);
        if(dbUser!=null&&user.getPassword().equals(dbUser.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    public String register(User user) throws Exception {
        if(ParamtersUtil.isNull(user.getQqNumber())&&ParamtersUtil.isNull(user.getEmail())&&ParamtersUtil.isNull(user.getPhone())){
            return PromptMessages.getMsg("user.registerEmptyAcount");
        }else if(ParamtersUtil.isNull(user.getPassword())){
            return PromptMessages.getMsg("user.registerEmptyPassword");
        }else{
            if(!ParamtersUtil.isNull(user.getQqNumber())){
                if(userDao.findByParam(1,user.getQqNumber())!=null){
                    return PromptMessages.getMsg("user.registerExistQQ");
                }
            }
            if(!ParamtersUtil.isNull(user.getEmail())){
                if(userDao.findByParam(2,user.getEmail())!=null){
                    return PromptMessages.getMsg("user.registerExistEmail");
                }
            }
            if(!ParamtersUtil.isNull(user.getPhone())){
                if(userDao.findByParam(3,user.getPhone())!=null){
                    return PromptMessages.getMsg("user.registerExistPhone");
                }
            }
        }
        userDao.save(user);
        return null;
    }

    public void findDetail(Object entity) throws Exception {
        User user;
        if(entity==null){
            throw new InvalidParameterException(PromptMessages.getMsg("user.nullId"));
        }else{
            user=(User)entity;
            if(user.getId()==null||user.getId()<1){
                throw new InvalidParameterException(PromptMessages.getMsg("user.nullId"));
            }
        }
        //本身属性
        user= userDao.get(User.class,user.getId());
        //用户组
        user.setUserGroups(userGroupDao.findByUser(user));
        //角色
        user.setRoles(roleDao.findByUser(user));
        //权限
        user.setPermissions(permissionDao.findByUser(user));
    }

    public void reviseRelation(Integer id, Integer[]... relationIds) throws Exception {

    }
}
