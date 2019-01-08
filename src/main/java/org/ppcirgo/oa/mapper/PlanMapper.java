package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.*;

import org.ppcirgo.oa.beans.model.PlanModel;
import org.springframework.stereotype.Repository;

import javax.xml.soap.Text;


@Repository
public interface PlanMapper {
    //添加周报
    @Insert(value = "insert into week_plan(user_id,create_time,visited_store,create_day,comment,shipping_plan,development_store) values(#{userId},#{createTime},#{visitedStore},#{createDay},#{comment},#{shippingPlan},#{developmentStore})")
    int savePlan(@Param("userId") int userId,@Param("createTime") long createTime, @Param("visitedStore") String visitedStore, @Param("createDay") long createDay, @Param("comment") String comment, @Param("shippingPlan") String shippingPlan,@Param("developmentStore") String developmentStore);
    //根据用户id查周报
    @Select(value = "select * from week_plan where user_id = #{userId}")
    PlanModel getPlanByUserId(@Param("userId") int userId);
    //修改周计划
    @Update(value =  "update week_plan set modify_time = #{modifyTime}, modify_day=#{modifyDay}, visited_store=#{visitedStore},comment=#{comment},shipping_plan=#{shippingPlan},development_store=#{developmentStore} where user_id = #{userId}")
    int modifyPlan(@Param("userId") int userId, @Param("modifyTime") long modifyTime, @Param("modifyDay") long modifyDay, @Param("visitedStore") String visitedStore,@Param("comment") String comment,@Param("shippingPlan") String shippingPlan,@Param("developmentStore") String developmentStore);
    //根据id删除周计划
    @Delete(value="delete from week_plan where user_id = #{userId}")
    int deletePlanById(@Param("userId") int userId);
    //根据星期几查一周的周报
    @Select(value = "select * from week_plan where user_id = #{userId} and create_day= #{createDay}")
    PlanModel getPlanByDay(@Param("userId") int userId,@Param("createDay")  long createDay);
}
