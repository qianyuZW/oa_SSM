package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ppcirgo.oa.beans.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //根据email查找员工
    @Select(value = "select * from user as t where t.email = #{email}")
    UserModel getUserByEmail(@Param("email") String email);
    //注册员工
    @Insert(value = "insert into user(user_name,password,create_time,email,level) values(#{userName},#{password},#{createTime},#{email},#{level}) ")
    int saveUser(UserModel userModel);

    //<<<<<<< HEAD
    //更新密码根据email
    @Update(value = "update user as t set t.password = #{password} where t.email = #{email}")
    int updatePasswordByEmail(String password,String email);

//=======


//>>>>>>> c8380a91d17713a62d50a8a22338cf13bbcfa86c
}
