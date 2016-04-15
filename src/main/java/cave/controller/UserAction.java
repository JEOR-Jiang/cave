package cave.controller;

import cave.entity.User;
import cave.server.UserServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView login(Model modle,@RequestParam(value = "name",required = true) String name,@RequestParam(value = "password",required = true) String password,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("modle = [" + modle + "], name = [" + name + "], password = [" + password + "]");
        try{
            checkUser();
            boolean success = userServer.login(user);
            if (success) {
                request.getSession().setAttribute("user",user);
                modelAndView.setViewName("cave/index");
            } else {
                modelAndView.setViewName("cave/user/login");
            }
        }catch(Exception e){
            modelAndView.addObject("message",e.getMessage());
            modelAndView.setViewName("common/errorMessage");
        }
        return modelAndView;
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
