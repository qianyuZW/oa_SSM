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
    @Override
    public int addPlan(PlanModel planModel) {
        int planId = planModel.getPlanId();
        String thisWeekContent=planModel.getThisWeekContent();
        String nextWeekContent=planModel.getNextWeekContent();
        long createTime=planModel.getCreateTime();
        long modifyTime=planModel.getModifyTime();
        String auditOpinion = planModel.getAuditOpinion();
        long auditTime = planModel.getAuditTime();
        String proposer=planModel.getProposer();
        return planMapper.savePlan(planId,thisWeekContent,nextWeekContent,auditOpinion,auditTime,createTime,modifyTime,proposer);
    }
    @Override
    public int auditPlan(PlanModel planModel){
        int planId = planModel.getPlanId();
        String auditOpinion = planModel.getAuditOpinion();
        long auditTime = planModel.getAuditTime();
        return planMapper.auditPlan(planId,auditTime,auditOpinion);

    }
    @Override
    public PlanModel findPlanById(int planId) {
        return planMapper.getPlanById( planId);
    }
    
     @Override
     public int deletePlanById(PlanModel planModel){
        return planMapper.deletePlanById(planModel.getPlanId());

     }
    @Override
    public int modifyPlan(PlanModel planModel){
        int planId = planModel.getPlanId();
        long modifyTime = planModel.getModifyTime();
        String thisWeekContent = planModel.getThisWeekContent();
        String nextWeekContent = planModel.getNextWeekContent();
        return planMapper.modifyPlan(planId,modifyTime,thisWeekContent,nextWeekContent);

    }

}
