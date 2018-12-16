package org.ppcirgo.oa.controller;


import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.service.UserService;
import org.ppcirgo.oa.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//关于登录注册与前端的接口
@RestController
public class LoginAndRegistController {

    @Autowired
    private UserService userService;
    @Value("${oa.user.level}")
    private String defaultLevel;//默认的用户等级

    //用户登录验证   liuzhou 1214
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            HttpServletRequest request
    ){
        HttpSession session = request.getSession();
        UserModel userModel = userService.findUserByEmail(email);
        if (userModel==null){
            return new AJAXResult(MsgCode.notexsit);
        }
        if (userModel!=null && userModel.getPassword().equals(MD5Utils.encodeByMD5(password))){
            session.setAttribute("user",userModel);//缓存用户状态
            return new AJAXResult(MsgCode.success);
        }else {
            return new AJAXResult(MsgCode.error);//不匹配
        }

    }
    //忘记密码
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    public Object forgetPassword(@RequestParam(value = "email") String email){
        UserModel userModel = userService.findUserByEmail(email);
        if (userModel==null){
            return new AJAXResult(MsgCode.notexsit);
        }else {
            Map<String, String> map = MD5Utils.generatorPassword();
            String password  = (String) map.keySet().toArray()[0];//这是要发送给用户的随机密码明文
            //TODO  张敏  在这里发送 简单邮件 到指定邮箱email 发送内容自己组织模板 核心是密码明文


            userService.updatePasswordByemail(email,map.get(password));
            return new AJAXResult(MsgCode.success);
        }
    }


    //用户注册
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Object userRegist(
            @RequestParam(value = "username") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "ivt") String ivt
    ) {
        UserModel userModel = new UserModel();
        userModel.setCreateTime(System.currentTimeMillis());
        userModel.setEmail(email);
        userModel.setLevel(defaultLevel);
        userModel.setPassword(MD5Utils.encodeByMD5(password));
        userModel.setUserName(userName);
        System.out.println(userModel);

        if (!MsgCode.ivt.equals(ivt))
            return new AJAXResult(MsgCode.error);//企业未邀请
        if (userService.findUserByEmail(email)!=null)
            return new AJAXResult(MsgCode.isexsit);//邮箱已被注册
        if (userService.addUser(userModel)>0)
            return new AJAXResult(MsgCode.success);
        return new AJAXResult(MsgCode.error);//企业未邀请
    }

    //是否online
    public Object isOnL(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user=session.getAttribute("user");
        if (user!=null){
            UserModel userModel = (UserModel) user;
            return new AJAXResult(userModel);
        }else {
            return new AJAXResult(MsgCode.error);
        }

    }

}
