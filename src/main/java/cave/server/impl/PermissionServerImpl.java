package cave.server.impl;

import cave.dao.PermissionDao;
import cave.dao.RoleDao;
import cave.dao.UserDao;
import cave.dao.UserGroupDao;
import cave.entity.Permission;
import cave.server.PermissionServer;
import cave.utils.PromptMessages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * Created by Jeor on 2016/5/17.
 */
@Service("permissionServer")
public class PermissionServerImpl extends BaseServerImpl<Permission> implements PermissionServer {

    private static final Log log= LogFactory.getLog(PermissionServerImpl.class);
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserGroupDao userGroupDao;

    @Override
    public void findDetail(Object entity) throws Exception {
        Assert.isInstanceOf(Permission.class, entity);
        Permission permission =(Permission) entity;
        permission=permissionDao.get(Permission.class,permission.getId());
        permission.setRoles(roleDao.findByPermission(permission));
        permission.setUsers(userDao.findByPermission(permission));
        permission.setUserGroups(userGroupDao.findByPermission(permission));
    }

    @Override
    public void reviseRelation(Integer id, Integer[]... relationIds) throws Exception {
        Integer[] roleIds=relationIds[0];
        if(roleIds.length>0){
            permissionDao.removeRelationRole(id);
            permissionDao.reviseRelationRole(id,roleIds);
        }
    }
}


