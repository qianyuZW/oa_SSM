package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.ppcirgo.oa.beans.model.PlanModel;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanMapper {

    //根据plan_id查找周报
    @Select(value = "select * from week_plan as t where t.plan_id = #{planId}")
    PlanModel getPlanById(@Param("planId") int planId);
    //保存周报
    @Insert(value = "insert into week_plan(user_id,this_week_content,next_week_content,audit_opinion,audit_time,create_time,modify_time,level) values(#{userId},#{thisWeekContent},#{nextWeekContent},#{auditOpinion},#{auditTime},#{createTime},#{modifyTime},#{level}) ")
    int savePlan(PlanModel planModel);
    //根据planId和userId查找员工本周计划子及下周计划
    @Select(value = "select * from week_plam as t where t.plan_id = #{planId} and user_id = #{userId}")
    PlanModel getPlan(@Param("plan_id") int planId, @Param("user_id") int userId);
}
