package org.ppcirgo.oa.controller;


import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.service.UserService;
import org.ppcirgo.oa.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Value("oa.user.level")
    private String defaultLevel;//默认的用户等级

    //用户注册
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Object userRegist(
            @RequestParam(value = "username",required = true) String userName,
            @RequestParam(value = "password",required = true) String password,
            @RequestParam(value = "email",required = true) String email
    ) {
        UserModel userModel = new UserModel();
        userModel.setCreateTime(System.currentTimeMillis());
        userModel.setEmail(email);
        userModel.setLevel(defaultLevel);
        userModel.setPassword(MD5Utils.encodeByMD5(password));
        userModel.setUserName(userName);
        if (userService.addUser(userModel)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }

    //用户登陆测试案例
    @RequestMapping(value = "/authUser",method = RequestMethod.GET)
    @ResponseBody
    public Object authUser(HttpServletRequest request,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password){
        //Map map = new HashMap();
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);
        //此处调用service查询用户
        UserModel user = userService.getUser(userModel);
        if(user!=null){
            //查询到该用户，登陆成功   在此处可以对当前用户的会话进行保存，保持登陆状态（最开始一般是使用session保持的）
            request.getSession().setAttribute("session_user",user);
            return new AJAXResult("ok");
        }else{
            //查询不到，登陆失败
            return new AJAXResult("no");
        }
        //n new AJAXResult("");
    }



}
