package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.EmployeeModel;
import org.ppcirgo.oa.service.EmployeeService;
import org.ppcirgo.oa.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private EmployeeService employeeService;

    //存储员工信息
    @RequestMapping(value="/saveEmployeeMessage",method = RequestMethod.POST)
    public Object saveEmployeeMessage(
            @RequestParam(value = "name", required = false) String  name,
            @RequestParam(value = "password", required = false) String  password,
            @RequestParam(value = "telephone", required = false) String  telephone,
            @RequestParam(value = "weixin", required = false) String  weixin,
            @RequestParam(value = "having_car", required = false) String  having_car,
            @RequestParam(value = "birth_date", required = false) String  birth_date,
            @RequestParam(value = "department", required = false) String  department,
            @RequestParam(value = "level", required = false) String  level,
            @RequestParam(value = "entry_date", required = false) String  entry_date,
            @RequestParam(value = "e_state", required = false) String  e_state,
            @RequestParam(value = "resign_date", required = false) String  resign_date
    ){
        EmployeeModel  employeeModel=new EmployeeModel();
        employeeModel.setName(name);
        employeeModel.setPassword(password);
        employeeModel.setTelephone(telephone);
        employeeModel.setWeixin(weixin);
        employeeModel.setHaving_car(having_car);
        employeeModel.setBirth_date(birth_date);
        employeeModel.setDepartment(department);
        employeeModel.setLevel(level);
        employeeModel.setE_state(entry_date);
        employeeModel.setE_state(e_state);
        employeeModel.setResign_date(resign_date);

        if(employeeService.saveEmployeeMessage(employeeModel)>0){
          //  System.out.println("employeeModel"+employeeService.saveEmployeeMessage(employeeModel));
            return new AJAXResult(MsgCode.success);
        }else
            return new AJAXResult(MsgCode.notexsit);

    }
  //根据员工姓名查询用户密码
    @RequestMapping(value="/getEmployeeMessageByName",method = RequestMethod.POST)
        public Object getEmployeeMessageByName(
               @RequestParam(value = "name",required = true) String name
    ){
          EmployeeModel employModel=employeeService.getEmployeeMessageByName(name);

          System.out.println("employModel=========="+employModel);

          if(employModel!=null){
              return new AJAXResult(MsgCode.success);
          }else
              return new AJAXResult(MsgCode.error);
        }
     @RequestMapping(value="/getPasswordByName",method = RequestMethod.POST)
       public Object getPasswordByName(
               @RequestParam(value="name",required = true) String name
               ){
               String employModel=employeeService.getPasswordByName(name);

         System.out.println("employModel=========="+employModel);
               if(employModel!=null){
                   return new AJAXResult(MsgCode.success);
               }else
                   return  new AJAXResult(MsgCode.notexsit);
         }

     //员工登录接口
     @RequestMapping(value="/employeeLogin",method = RequestMethod.POST)
       public Object employeeLogin(
               @RequestParam(value="name",required=true)  String name,
               @RequestParam(value = "password",required = true) String password
     ){
        EmployeeModel  employeeModel=employeeService.getEmployeeMessageByName(name);
         System.out.println("employModel=========="+employeeModel);

         if(employeeModel==null){
            return new AJAXResult(MsgCode.notexsit);
         }
         if(employeeModel!=null&&employeeModel.getPassword().equals(password)){
             return new AJAXResult(MsgCode.success);
         }else
             return new AJAXResult(MsgCode.error);
         }
}
