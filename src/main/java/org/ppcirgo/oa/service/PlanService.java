package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.PlanModel;

public interface PlanService {
    /**
     * author:zhangwei
     * date:2018-12-16
     * @param planModel
     */

    int addPlan(PlanModel planModel);
    PlanModel findPlanByUserId(int userId);
    int modifyPlan(PlanModel planModel);
    int deletePlanById(PlanModel planModel);
    PlanModel findPlanByCurrentDate(int userId,String begin,String end);
}
