package cave.server;

import cave.entity.User;

/**
 * Created by Jeor on 2016/1/28.
 */
public interface UserServer extends BaseServer<User>{
    /**
     *  用户登录
     *  @param user 登录的用户。（登录成功后填充此对象的所有属性）
     *  @return 是否登录成功
     *
     */
    public boolean login(User user)throws Exception;
    /**
     * 用户注册
     * @param user
     * @return  错误信息。（null为成功）
     * 检查规则：
     *  1.邮箱、手机、QQ号码必须有一个不为空
     *  2.邮箱、手机、QQ号码不能重复
     *  3.密码不能为空
     */
    public String register(User user)throws Exception;


}
