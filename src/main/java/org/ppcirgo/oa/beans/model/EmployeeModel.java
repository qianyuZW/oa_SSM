package org.ppcirgo.oa.beans.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeModel {
    private Integer id;  //员工id
    private String name;  //员工姓名
    private  String password;//员工登录密码
    private String telephone;//员工电话
    private  String weixin;//员工微信
    private  String having_car;//是否有车
    private String birth_date;// 出生日期
    private String department;//所属部门
    private  String level;//  等级
    private String entry_date;// 入职时间
    private  String e_state;//  状态
    private String resign_date;//离职时间

}
