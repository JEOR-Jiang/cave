package cave.server;

import cave.entity.Role;
import cave.utils.Page;



/**
 * Created by Jeor on 2016/4/18.
 */
public interface RoleServer extends BaseServer<Role>{
    /**
     * 查询所有角色(分页)
     */
    public Page<RoleServer> findByPage(Page<RoleServer> page)throws Exception;

}
