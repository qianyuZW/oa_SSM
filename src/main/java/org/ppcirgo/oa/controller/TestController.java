package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/test")
    public Object getUsers(){
        UserModel userModel = userMapper.getUserById(1);
        System.out.println(userModel);
       return userModel;
    }

    @GetMapping("/te")
    public String test(){
        return "index";
    }
}
