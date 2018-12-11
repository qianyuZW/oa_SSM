package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.mapper.UserMapper;
import org.ppcirgo.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserModel userModel) {
       return userMapper.saveUser(userModel);
    }
}