package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ppcirgo.oa.beans.model.EmployeeModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

    //根据id添加员工信息
    @Insert(value="insert into employee(name,password,telephone,weixin,having_car,birth_date,department,level,entry_date,e_state,resign_date) values(#{name},#{password},#{telephone},#{weixin},#{having_car},#{birth_date},#{department},#{level},#{entry_date},#{e_state},#{resign_date})")
     int saveEmployeeMessage(EmployeeModel employeeModel);

    //通过员工姓名找到员工信息
    @Select(value="select *from employee where name=#{name}")
    EmployeeModel getEmployeeMessageByName(@Param("name") String name);

    //根据员工姓名查询密码
    @Select(value="select password from employee where name=#{name}")
    String getPasswordByName(@Param("name") String name);



}
