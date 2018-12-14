package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;

    private Exception exception = new Exception();


    @GetMapping("/test")
    @ResponseBody
    public Object getUsers(){
        UserModel userModel = userMapper.getUserByEmail("");
        System.out.println(userModel);
        return userModel;
    }

    //测试500异常
    @GetMapping("/ex")
    public void ex(){
        int a=5/0;
    }

}
