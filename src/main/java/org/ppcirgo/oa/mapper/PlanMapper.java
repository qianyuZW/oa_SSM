package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.*;

import org.ppcirgo.oa.beans.model.PlanModel;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanMapper {

    //根据id查找周报
    @Select(value = "select * from week_plan where id = #{planId}")
    PlanModel getPlanById(@Param("planId") int planId);
    @Update(value =  "update week_plan set audit_opinion = #{auditOpinion}, audit_time = #{auditTime} where id = #{planId}")
    int updatePlan(@Param("planId") int planId, @Param("auditTime") long auditTime, @Param("auditOpinion") String auditOpinion);
    //保存周报
    @Insert(value = "insert into week_plan(id,this_week_content,next_week_content,audit_opinion,audit_time,create_time,modify_time) values(#{planId},#{thisWeekContent},#{nextWeekContent},#{auditOpinion},#{auditTime},#{createTime},#{modifyTime})")
    int savePlan(@Param("planId") int planId, @Param("thisWeekContent") String thisWeekContent,@Param("nextWeekContent") String nextWeekContent, @Param("auditOpinion") String auditOpinion, @Param("auditTime") long auditTime, @Param("createTime") long createTime,@Param("modifyTime") long modifyTime);
    //根据planId和userId查找员工本周计划子及下周计划
    @Select(value = "select * from week_plan where id = #{planId} and user_id = #{userId}")
    PlanModel getPlan(@Param("planId") int planId, @Param("userId") int userId);

    @Delete(value="delete from week_plan where id = #{planId}")
    int deletePlanById(@Param("planId") int planId);



}
