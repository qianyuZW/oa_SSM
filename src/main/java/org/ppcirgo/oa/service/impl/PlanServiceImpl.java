package org.ppcirgo.oa.service.impl;
import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.mapper.PlanMapper;
import org.ppcirgo.oa.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;
    public int addPlan(PlanModel planModel){
        int userId=planModel.getUserId();
        long createTime=planModel.getCreateTime();
        String visitedStore=planModel.getVisitedStore();
        String comment=planModel.getComment();
        String shippingPlan=planModel.getShippingPlan();
        String developmentStore=planModel.getDevelopmentStore();
        long createDay=planModel.getCreateDay();
        return planMapper.savePlan(userId,createTime,visitedStore,comment,shippingPlan,developmentStore,createDay);
    }
    @Override
    public PlanModel findPlanByUserId(int userId) {
        return planMapper.getPlanByUserId(userId);
    }
    @Override
    public int modifyPlan(PlanModel planModel){
        int userId = planModel.getUserId();
        long modifyTime = planModel.getModifyTime();
        String comment = planModel.getComment();
        String shippingPlan = planModel.getShippingPlan();
        String visitedStore=planModel.getVisitedStore();
        String developmentStore=planModel.getDevelopmentStore();
        long modifyDay=planModel.getModifyDay();
        return planMapper.modifyPlan(userId,modifyTime,visitedStore,comment,shippingPlan,developmentStore,modifyDay);

    }
    @Override
    public int deletePlanById(PlanModel planModel){
        return planMapper.deletePlanById(planModel.getUserId());
    }

    @Override
    public PlanModel findPlanByDay(int userId,long weekDay) {
        return planMapper.getPlanByDay(userId,weekDay);
    }
}
