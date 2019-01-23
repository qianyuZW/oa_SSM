package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.EmployeeModel;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeeService {

    int saveEmployeeMessage(EmployeeModel employeeModel);

    EmployeeModel getEmployeeMessageByName(String name);

    String getPasswordByName(String name);

    public int getEid(String name);
}
