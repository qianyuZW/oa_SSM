package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.*;

import org.ppcirgo.oa.beans.model.PlanModel;
import org.springframework.stereotype.Repository;

import javax.xml.soap.Text;


@Repository
public interface PlanMapper {
    //添加周报
    @Insert(value = "insert into weekPlan(user_id,create_time,visited_store,create_day,comment,shipping_plan,development_store) values(#{userId},#{createTime},#{visitedStore},#{createDay},#{comment},#{shippingPlan},#{developmentStore})")
    int savePlan(@Param("userId") int userId,@Param("createTime") long createTime, @Param("visitedStore") String visitedStore, @Param("createDay") long createDay, @Param("comment") String comment, @Param("shippingPlan") String shippingPlan,@Param("developmentStore") String developmentStore);
    //根据用户id查周报
    @Select(value = "select * from weekPlan where user_id = #{userId}")
    PlanModel getPlanByUserId(@Param("userId") int userId);
    //修改周计划
    @Update(value =  "update weekPlan set modify_time = #{modifyTime}, modify_day=#{modifyDay}, visited_store=#{visitedStore},comment=#{comment},shipping_plan=#{shippingPlan},development_store=#{developmentStore} where user_id = #{userId}")
    int modifyPlan(@Param("userId") int userId, @Param("modifyTime") long modifyTime, @Param("modifyDay") long modifyDay, @Param("visitedStore") String visitedStore,@Param("comment") String comment,@Param("shippingPlan") String shippingPlan,@Param("developmentStore") String developmentStore);
    @Delete(value="delete from weekPlan where user_id = #{userId}")
    int deletePlanById(@Param("userId") int userId);
}
