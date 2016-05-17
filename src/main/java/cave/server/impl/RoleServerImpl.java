package cave.server.impl;

import cave.dao.PermissionDao;
import cave.dao.RoleDao;
import cave.dao.UserDao;
import cave.dao.UserGroupDao;
import cave.entity.Role;
import cave.server.BaseServer;
import cave.server.RoleServer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by Jeor on 2016/5/17.
 */
@Service("roleServer")
public class RoleServerImpl extends BaseServerImpl<Role> implements RoleServer {

    @Resource
    private RoleDao roleDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserGroupDao userGroupDao;
    @Resource
    private PermissionDao permissionDao;

    @Override
    public void findDetail(Object entity) throws Exception {
        Assert.isInstanceOf(Role.class, entity);
        Role role=(Role)entity;
        role=this.roleDao.get(Role.class,role.getId());//本身属性
        role.setUserGroups(userGroupDao.findByRole(role));//用户组
        role.setUsers(userDao.findByRole(role));//用户
        role.setPermissions(permissionDao.findByRole(role));//权限
    }

    @Override
    public void reviseRelation(Integer id, Integer[]... relationIds) throws Exception {
        Integer[] usergroupIds=relationIds[0];
        Integer[] userIds=relationIds[1];
        Integer[] permissionIds=relationIds[2];
        if(usergroupIds.length>0){
            roleDao.removeRelationUsergroup(id);
            roleDao.reviseRelationUsergroup(id,usergroupIds);
        }
        if(userIds.length>0){
            roleDao.removeRelationUser(id);
            roleDao.reviseRelationUser(id, userIds);
        }
        if(permissionIds.length>0){
            roleDao.removeRelationPermission(id);
            roleDao.reviseRelationPermission(id, permissionIds);
        }
    }
}
