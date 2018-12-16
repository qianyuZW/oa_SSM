package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.UserModel;

public interface UserService {

    /**
     * author:liuzhou
     * date:2018-12-09
     */
    int addUser(UserModel userModel);//用户注册

    UserModel findUserByEmail(String email);//登录验证

    int updatePasswordByemail(String email ,String password);//根据邮箱修改用户密码
}

