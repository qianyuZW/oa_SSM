package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.*;

import org.ppcirgo.oa.beans.model.PlanModel;
import org.springframework.stereotype.Repository;




@Repository
public interface PlanMapper {
    //添加周报
    @Insert(value = "insert into week_plan(user_id,create_time,visited_store,comment,shipping_plan,development_store,create_day) \n"+
            "values(#{userId},#{createTime},#{visitedStore},#{comment},#{shippingPlan},#{developmentStore},#{createDay})")
    int savePlan(@Param("userId") int userId,@Param("createTime") String createTime, @Param("visitedStore") String visitedStore, @Param("comment") String comment, @Param("shippingPlan") String shippingPlan,@Param("developmentStore") String developmentStore,@Param("createDay") long createDay);
    //根据用户id查周报
    @Select(value = "select * from week_plan where user_id = #{userId}")
    PlanModel getPlanByUserId(@Param("userId") int userId);
    //修改周计划
    @Update(value =  "update week_plan set modify_time = #{modifyTime}, visited_store=#{visitedStore},comment=#{comment},\n"+
            "shipping_plan=#{shippingPlan},development_store=#{developmentStore},modify_Day=#{modifyDay} where user_id = #{userId}")
    int modifyPlan(@Param("userId") int userId, @Param("modifyTime") String modifyTime,  @Param("visitedStore") String visitedStore,@Param("comment") String comment,@Param("shippingPlan") String shippingPlan,@Param("developmentStore") String developmentStore,@Param("modifyDay") long modifyDay);
    //根据id删除周计划
    @Delete(value="delete from week_plan where user_id = #{userId}")
    int deletePlanById(@Param("userId") int userId);
    //根据日期查一周的周报
    @Select(value = "select * from week_plan where user_id = #{userId} and create_time >= #{begin} and create_time <= #{end}")
    PlanModel getPlanByCurrentDate(@Param("userId") int userId,@Param("begin") String begin,@Param("end") String end);
}
