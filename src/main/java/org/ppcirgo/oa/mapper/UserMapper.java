package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ppcirgo.oa.beans.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //根据id查找员工
    @Select(value = "select * from user as t where t.id = #{userId}")
    UserModel getUserById(@Param("userId") int userId);
    //注册员工
    @Insert(value = "insert into user(user_name,password,create_time,email,level) values(#{userName},#{password},#{createTime},#{email},#{level}) ")
    int saveUser(UserModel userModel);

}

public interface WeeklySchedule {
    //查询周计划
    @Select(value = "select * from user as t where t.date = #{userDate}")
    UserModel getWeeklyScheduleByDate(@Param("userDate") date userDate.getTime());

}