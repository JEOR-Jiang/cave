package cave.controller;

import cave.entity.User;
import cave.server.UserServer;
import cave.utils.PromptMessages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
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
    private String identityCode;//验证码
    @Resource
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
                modelAndView.addObject("", PromptMessages.getMsg("user.failedLogin", name));
            }
        }catch(Exception e){
            modelAndView.addObject("message",e.getMessage());
            modelAndView.setViewName("common/errorMessage");
        }
        return modelAndView;
    }

    /**
     * 注册
     *  逻辑：1.账号密码不能为空，2.账号有效判断；
     * @param model
     * @return
     */
    @RequestMapping
    public ModelAndView register(Model model,@RequestParam(value="account")String account,@RequestParam(value="password")String password){

    }


    private void checkUser() {
        if (user == null) {
            throw new InvalidParameterException(PromptMessages.getMsg("user.nullId"));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
