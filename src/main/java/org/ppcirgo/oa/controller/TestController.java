package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public Object getUsers(){
       return userMapper.getUserById(1);
    }
}
