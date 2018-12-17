package org.ppcirgo.oa.timedtask;

import org.ppcirgo.oa.beans.enums.UserStatus;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.servlet.ServletContext;
import java.util.List;

@Component
public class Business {

    @Autowired
    private UserService userService;
    @Autowired
    private ServletContext application;

    @Scheduled(fixedDelay = 1000*10)
    public void refreshOnlineNum(){

        List<UserModel> users = userService.getUserByStatus(UserStatus.online.toString());
        //ServletContext application = ContextLoader.getCurrentWebApplicationContext().getServletContext(); 失败
        application.setAttribute("online",users.size());
        System.out.println("在线人数-------》》》"+users.size());
    }
}
