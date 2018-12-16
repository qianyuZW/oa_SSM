package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


public class PlanController {

    @Autowired
    private PlanService planService;

    @Value("oa.week_plan.level")
    private String defaultLevel;//默认的用户等级

    @RequestMapping(value = "/weekPlan",method = RequestMethod.POST)
    public Object userPlan(
            @RequestParam(value = "nextWeekContent",required = true) String nextWeekContent,
            @RequestParam(value = "thisWeekContent",required = true) String thisWeekContent,
            @RequestParam(value = "auditOpinion",required = true) String auditOpinion


    ){
        PlanModel planModel = new PlanModel();
        planModel.setThisWeekContent(thisWeekContent);
        planModel.setNextWeekContent(nextWeekContent);
        planModel.setCreateTime(System.currentTimeMillis());
        planModel.setAuditTime(System.currentTimeMillis());
        planModel.setModifyTime(System.currentTimeMillis());
        planModel.setAuditOpinion(auditOpinion);
        planModel.setLevel(defaultLevel);

        if (planService.addPlan(planModel)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }

}
