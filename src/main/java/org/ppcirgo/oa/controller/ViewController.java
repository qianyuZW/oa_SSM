package org.ppcirgo.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author:liuzhou
 * desc:有关页面的controller
 */
@Controller
public class ViewController {

    //登录页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    //注册页面
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String regist(){
        return "regist";
    }
}
