package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.mapper.UserMapper;
import org.ppcirgo.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserModel userModel) {
       return userMapper.saveUser(userModel);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public int updatePasswordByemail(String email,String password) {
        return userMapper.updatePasswordByEmail(password,email);
    }

    @Override
    public int updateStatusByemail(String email, String status) {
        return userMapper.updateStatusByEmail(status,email);
    }

    @Override
    public List<UserModel> getUserByStatus(String statsu) {
        return userMapper.getUserBystatus(statsu);
    }

}
