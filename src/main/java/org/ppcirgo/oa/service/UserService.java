package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.UserModel;

import java.util.List;

public interface UserService {

    /**
     * author:liuzhou
     * date:2018-12-09
     */

     int addUser(UserModel userModel);//用户注册

     UserModel findUserByEmail(String email);//登录验证

     int updatePasswordByemail(String email ,String password);//根据邮箱修改用户密码

     int updateStatusByemail(String email,String status);//根据邮箱修改用户状态

     List<UserModel> getUserByStatus(String statsu);//根据状态获得用户
}

