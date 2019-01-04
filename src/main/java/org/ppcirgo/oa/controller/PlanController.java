package org.ppcirgo.oa.controller;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;


    @Value("${oa.week_plan.level}")
    private String defaultLevel;//默认的用户等级

    @RequestMapping(value = "/addPlans",method = RequestMethod.POST)
    public Object addPlans(
            @RequestParam(value = "planId",required = true) int planId,
            @RequestParam(value = "nextWeekContent",required = true) String nextWeekContent,
            @RequestParam(value = "thisWeekContent",required = true) String thisWeekContent,
            @RequestParam(value = "auditOpinion",required = true) String auditOpinion,
            @RequestParam(value = "proposer",required = true) String proposer


    ){
        PlanModel planModel = new PlanModel();
        planModel.setPlanId(planId);
        planModel.setThisWeekContent(thisWeekContent);
        planModel.setNextWeekContent(nextWeekContent);
        planModel.setCreateTime(System.currentTimeMillis());
        planModel.setAuditTime(System.currentTimeMillis());
        planModel.setModifyTime(System.currentTimeMillis());
        planModel.setAuditOpinion(auditOpinion);
        planModel.setProposer(proposer);
        planModel.setLevel(defaultLevel);

        if (planService.addPlan(planModel)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }

    @RequestMapping(value = "/auditPlans",method = RequestMethod.POST)
    public Object auditPlans(
            @RequestParam(value = "planId",required = true) int planId,
            @RequestParam(value = "auditOpinion",required = true) String auditOpinion


    ){

        PlanModel planModel2 = new PlanModel();
        planModel2.setPlanId(planId);
        planModel2.setAuditOpinion(auditOpinion);
        planModel2.setAuditTime(System.currentTimeMillis());

        if (planService.auditPlan(planModel2)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }


    @RequestMapping(value = "/deletePlans",method = RequestMethod.POST)
    public Object deletePlans(@ModelAttribute PlanModel planModel){
        int result = planService.deletePlanById(planModel);
        if (result > 0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);


    }

    @RequestMapping(value = "/modifyPlans",method = RequestMethod.POST)
    public Object modifyPlans(
            @RequestParam(value = "planId",required = true) int planId,
            @RequestParam(value = "thisWeekContent",required = true) String thisWeekContent,
            @RequestParam(value = "nextWeekContent",required = true) String nextWeekContent


    ){

        PlanModel planModel2 = new PlanModel();
        planModel2.setPlanId(planId);
        planModel2.setModifyTime(System.currentTimeMillis());
        planModel2.setThisWeekContent(thisWeekContent);
        planModel2.setNextWeekContent(nextWeekContent);

        if (planService.modifyPlan(planModel2)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }
}
