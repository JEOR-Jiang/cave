package cave.server;

import cave.entity.User;

/**
 * Created by Jeor on 2016/1/28.
 */
public interface UserServer {
    public boolean login(User user)throws Exception;
}
