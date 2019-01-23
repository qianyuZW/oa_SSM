package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.EmployeeModel;
import org.ppcirgo.oa.mapper.EmployeeMapper;
import org.ppcirgo.oa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public int saveEmployeeMessage(EmployeeModel employeeModel) {
        return employeeMapper.saveEmployeeMessage(employeeModel);
    }

    @Override
    public EmployeeModel getEmployeeMessageByName(String name) {
        return employeeMapper.getEmployeeMessageByName(name);
    }

    @Override
    public String getPasswordByName(String name) {
        return employeeMapper.getPasswordByName(name);
    }

    public int getEid(String name){
        return employeeMapper.getEId(name);
    }
}
