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
        long createDay=planModel.getCreateDay();
        String visitedStore=planModel.getVisitedStore();
        String comment=planModel.getComment();
        String shippingPlan=planModel.getShippingPlan();
        String developmentStore=planModel.getDevelopmentStore();
        return planMapper.savePlan(userId,createTime,visitedStore,createDay,comment,shippingPlan,developmentStore);
    }
    @Override
    public PlanModel findPlanByUserId(int userId) {
        return planMapper.getPlanByUserId(userId);
    }
    @Override
    public int modifyPlan(PlanModel planModel){
        int userId = planModel.getUserId();
        long modifyTime = planModel.getModifyTime();
        long modifyDay=planModel.getModifyDay();
        String comment = planModel.getComment();
        String shippingPlan = planModel.getShippingPlan();
        String visitedStore=planModel.getVisitedStore();
        String developmentStore=planModel.getDevelopmentStore();
        return planMapper.modifyPlan(userId,modifyTime,modifyDay,visitedStore,comment,shippingPlan,developmentStore);

    }
    @Override
    public int deletePlanById(PlanModel planModel){
        return planMapper.deletePlanById(planModel.getUserId());

    }
}
