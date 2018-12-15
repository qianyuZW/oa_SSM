package org.ppcirgo.oa.controller;


import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.service.UserService;
import org.ppcirgo.oa.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

@RestController
public class UserController {
    @Autowired
    private ServletContext application;

    //在线人数反馈
    @GetMapping("/getOnlineCount")
    private Object getOnlineCount(){
        return new AJAXResult(application.getAttribute("online")+"");
    }
    //查询在线的人
    private Object getOnlinePeople(){
        return new AJAXResult("");
    }

}
