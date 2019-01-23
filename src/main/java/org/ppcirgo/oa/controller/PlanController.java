package org.ppcirgo.oa.controller;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.service.PlanService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    //根据给定日期返回日期所在的周的周计划
    @RequestMapping(value = "/getPlansByWeekDay",method = RequestMethod.POST)
    public Object getPlansByWeekDay(
            @RequestParam(value = "currentDate") String currentDate,
            @RequestParam(value = "userId") int userId

    ) throws ParseException {

        PlanModel planModel = new PlanModel();
        planModel.setUserId(userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(currentDate);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar_begin = Calendar.getInstance();
        Calendar calendar_end = Calendar.getInstance();
        calendar.setTime(date);
        calendar_begin.setTime(date);
        calendar_end.setTime(date);
        int arr[] = {7,1,2,3,4,5,6};
        int i = arr[calendar.get(calendar.DAY_OF_WEEK)-1];
        calendar_begin.add(Calendar.DATE,-i+1);
        Date begin = calendar_begin.getTime();
        calendar_end.add(Calendar.DATE,7-i);
        Date end = calendar_end.getTime();
        planModel.setBegin(simpleDateFormat.format(begin));
        planModel.setEnd(simpleDateFormat.format(end));
        planModel.setCurrentDate(currentDate);

        planModel = planService.findPlanByCurrentDate(userId,planModel.getBegin(),planModel.getEnd());
        System.out.println(planModel);
        return planModel;
    }
}
