package cave.controller;

import cave.entity.User;
import cave.server.UserServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.InvalidParameterException;

/**
 * 用户controller
 * Created by Jeor on 2016/1/28.
 */
//@Controller()
//@RequestMapping(value = "user")
@Controller
@RequestMapping("user")
public class UserAction {
    private User user;
    private UserServer userServer;

    @RequestMapping("login")
    public String login(@RequestParam(value = "name",required = false,defaultValue = "404") String name,Model modle) throws Exception {
        checkUser();
        modle.addAttribute("name",name);
        boolean success = userServer.login(user);
        if (success) {
            return "common/error404";
        } else {
            return "b";
        }
    }

    public String one(){
        return "abc";
    }
    @RequestMapping(value = "tt")
    public String two(){
        return "two";
    }

    private void checkUser() {
        if (user == null) {
            throw new InvalidParameterException("user null.check the paramters");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
