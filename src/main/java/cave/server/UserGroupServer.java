package cave.server;


import cave.entity.UserGroup;
import cave.utils.Page;


/**
 * Created by Jeor on 2016/4/15.
 */
public interface UserGroupServer extends BaseServer<UserGroup>  {

    /**
     * 查询所有用户组(分页)
     */
    public Page<UserGroup> findByPage(Page<UserGroup> page)throws Exception;



}
