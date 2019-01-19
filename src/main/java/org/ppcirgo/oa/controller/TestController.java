
package org.ppcirgo.oa.controller;

import lombok.extern.slf4j.Slf4j;
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

import java.util.Date;

@Slf4j
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
        PlanModel planModel = planService.findPlanByUserId(2);
        System.out.println(planModel);
        return planModel;
    }

    /**
     * 测试 2019-01-18
     * @return
     */
    @GetMapping("/t")
    @ResponseBody
    public Object t(){
        return "ok123";
    }

    @GetMapping("/test")
    @ResponseBody
    public Object getUsers(){
        log.info("someone is testing...");
        UserModel userModel = userMapper.getUserByEmail("");
        System.out.println(userModel);
        return userModel;
    }
    @GetMapping("/getPlansByDay")
    @ResponseBody
    public Object getPlansByDay(){
        long cuo=System.currentTimeMillis();
        Date date=new Date(cuo);
        PlanModel planModel = planService.findPlanByDay(2,date.getDay());
        System.out.println(planModel);
        return planModel;
    }
    //测试500异常
    @GetMapping("/ex")
    public void ex(){
        int a=5/0;
    }

}
