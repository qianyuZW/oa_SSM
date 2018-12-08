package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ppcirgo.oa.beans.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //根据id查找用户
    @Select(value = "select * from user as t where t.id = #{userId}")
    UserModel getUserById(@Param("userId") int userId);
}
