package cave.dao;

import cave.entity.User;

/**
 * Created by Jeor on 2016/1/28.
 */
public interface UserDao {
    public User getUserByAccount(User user);
}
