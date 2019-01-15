package org.ppcirgo.oa.controller;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.service.PlanService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import static org.ppcirgo.oa.utils.DateUtlis.currentTime;


@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/addPlans",method = RequestMethod.POST)
    public Object addPlans(
            @RequestParam(value = "userId",required = true) int userId,
            @RequestParam(value = "comment",required = true) String comment,
            @RequestParam(value = "developmentStore",required = true) String developmentStore,
            @RequestParam(value = "shippingPlan",required = true) String shippingPlan,
            @RequestParam(value = "visitedStore",required = true) String visitedStore


    ){
        PlanModel planModel = new PlanModel();
        planModel.setUserId(userId);
        planModel.setComment(comment);
        planModel.setDevelopmentStore(developmentStore);
        planModel.setShippingPlan(shippingPlan);
        planModel.setVisitedStore(visitedStore);
        planModel.setCreateTime(DateUtlis.currentTime((System.currentTimeMillis())));
        Long cuo=System.currentTimeMillis();
        Date date=new Date(cuo);
        planModel.setCreateDay(date.getDay());

        if (planService.addPlan(planModel)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }

    @RequestMapping(value = "/modifyPlans",method = RequestMethod.POST)
    public Object modifyPlans(
            @RequestParam(value = "userId",required = true) int userId,
            @RequestParam(value = "comment",required = true) String comment,
            @RequestParam(value = "developmentStore",required = true) String developmentStore,
            @RequestParam(value = "shippingPlan",required = true) String shippingPlan,
            @RequestParam(value = "visitedStore",required = true) String visitedStore


    ){

        PlanModel planModel = new PlanModel();
        planModel.setUserId(userId);
        planModel.setComment(comment);
        planModel.setDevelopmentStore(developmentStore);
        planModel.setShippingPlan(shippingPlan);
        planModel.setVisitedStore(visitedStore);
        planModel.setModifyTime(DateUtlis.currentTime((System.currentTimeMillis())));
        long cuo=System.currentTimeMillis();
        Date date=new Date(cuo);
        planModel.setModifyDay(date.getDay());
        if (planService.modifyPlan(planModel)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }

    @RequestMapping(value = "/deletePlans",method = RequestMethod.POST)
    public Object deletePlans(@ModelAttribute PlanModel planModel){

        if (planService.deletePlanById(planModel) > 0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }
}
