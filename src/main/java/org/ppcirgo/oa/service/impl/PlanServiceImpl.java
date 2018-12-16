package org.ppcirgo.oa.service.impl;
import org.ppcirgo.oa.beans.model.PlanModel;
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
        return planMapper.savePlan(planModel);
    }

    public PlanModel getPlan(PlanModel planModel) {
        //再此处调用dao层  查询数据库返回查询结果
        PlanModel plan = planMapper.getPlan(planModel.getPlanId(),planModel.getUserId());
        return plan;
     }


}
