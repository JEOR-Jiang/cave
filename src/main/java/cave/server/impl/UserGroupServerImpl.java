package cave.server.impl;

import cave.dao.PermissionDao;
import cave.dao.RoleDao;
import cave.dao.UserDao;
import cave.dao.UserGroupDao;
import cave.entity.User;
import cave.entity.UserGroup;
import cave.server.UserGroupServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Jeor on 2016/4/19.
 */
@Service
public class UserGroupServerImpl extends BaseServerImpl<UserGroup> implements UserGroupServer {

    @Resource
    private UserGroupDao userGroupDao;
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private PermissionDao permissionDao;
    /**
     * 需要填充的信息：
     *  1.用户组基本属性
     *  2.成员列表
     *  3.角色列表
     *  4.权限列表
     * @param entity 需要填充的对象
     * @throws Exception
     */
    public void findDetail(Object entity) throws Exception {
        UserGroup userGroup =(UserGroup) entity;
        userGroup=this.userGroupDao.get(UserGroup.class,userGroup.getId());
        userGroup.setUsers(this.userDao.findByUsergroup(userGroup));
        userGroup.setRoles(this.roleDao.findByUsergroup(userGroup));
        userGroup.setPermissions(this.permissionDao.findByUsergroup(userGroup));
        entity=userGroup;
    }

    /**
     * 修改关联关系
     * @param id
     * @param relationIds 顺序为：用户关系、角色关系
     * @throws Exception
     */
    public void reviseRelation(Integer id, Integer[]... relationIds) throws Exception {
        Integer[] userIds=relationIds[0];
        Integer[] roleIds=relationIds[1];
        this.userGroupDao.removeRelationUser(id);
        this.userGroupDao.removeRelationRole(id);
        this.userGroupDao.reviseRelationUser(id,userIds);
        this.userGroupDao.reviseRelationRole(id, roleIds);

    }

}














