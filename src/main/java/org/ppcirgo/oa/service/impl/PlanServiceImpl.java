package org.ppcirgo.oa.service.impl;
import org.ppcirgo.oa.beans.model.PlanModel;
import org.ppcirgo.oa.beans.model.UserModel;
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
    @Override
    public int addPlan(PlanModel planModel) {
        int planId = planModel.getPlanId();
        String thisWeekContent=planModel.getThisWeekContent();
        String nextWeekContent=planModel.getNextWeekContent();
        long createTime=planModel.getCreateTime();
        long modifyTime=planModel.getModifyTime();
        String auditOpinion = planModel.getAuditOpinion();
        long auditTime = planModel.getAuditTime();
        return planMapper.savePlan(planId,thisWeekContent,nextWeekContent,auditOpinion,auditTime,createTime,modifyTime);
    }
    @Override
    public int updatePlan(PlanModel planModel){
        int planId = planModel.getPlanId();
        String auditOpinion = planModel.getAuditOpinion();
        long auditTime = planModel.getAuditTime();
        return planMapper.updatePlan(planId,auditTime,auditOpinion);

    }
    @Override
    public PlanModel findPlanById(int planId) {
        return planMapper.getPlanById( planId);
    }
    public PlanModel getPlan(PlanModel planModel) {
        //再此处调用dao层  查询数据库返回查询结
        PlanModel plan = planMapper.getPlan(planModel.getPlanId(),planModel.getUserId());
        return plan;
     }
     public int deletePlanById(PlanModel planModel){
        return planMapper.deletePlanById(planModel.getPlanId());

     }


}
