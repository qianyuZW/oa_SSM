package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.mapper.PlanMapper;
import org.ppcirgo.oa.mapper.UserMapper;
import org.ppcirgo.oa.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @Autowired
    private UserMapper userMapper;
    private PlanMapper planMapper;

    @Autowired
    private PlanService planService;

    private Exception exception = new Exception();
    @GetMapping("/getPlansByUserId")
    @ResponseBody
    public Object getPlansByUserId(){
        PlanModel planModel = planService.findPlanByUserId(1);
        System.out.println(planModel);
        return planModel;
    }
    /*
    @GetMapping("/test")
    @ResponseBody
    public Object getUsers(){
        UserModel userModel = userMapper.getUserByEmail("");
        System.out.println(userModel);
        return userModel;
    }
    @GetMapping("/plan")
    @ResponseBody
    public Object getPlans(){
        PlanModel planModel = planService.findPlanById(111);
        System.out.println(planModel);
        return planModel;
    }
    @GetMapping("/getPlansByUserId")
    @ResponseBody
    public Object getPlansByUserId(){
        PlanModel planModel = planService.findPlanByUserId(12);
        System.out.println(planModel);
        return planModel;
    }*/
    //测试500异常
    @GetMapping("/ex")
    public void ex(){
        int a=5/0;
    }

}
