package org.ppcirgo.oa.controller;


import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.service.UserService;
import org.ppcirgo.oa.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Value("oa.user.level")
    private String defaultLevel;//默认的用户等级

    //用户注册
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
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
    //用户登录验证
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    ){
        return new AJAXResult('1');
    }

}
