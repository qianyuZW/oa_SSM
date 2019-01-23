
package org.ppcirgo.oa.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ppcirgo.oa.beans.model.DailyModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyMapper {

    //保存日报记录
    @Insert(value="insert into daily(employee_name,created_time,content)  values(#{employee_name},#{created_time},#{content})")
    int saveDailyRecord(DailyModel dailyModel);


    //根据id查找
    @Select(value="select * from daily where id=#{id}")
    String  getDailyById(@Param("id") Integer id);

    //根据employee_name查找
    @Select(value="select * from daily where employee_name=#{employee_name}")
    String  getDailyByName(@Param("employee_name") String  employee_name);


}

