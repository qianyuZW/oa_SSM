package org.ppcirgo.oa.beans.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserModel implements Serializable {
    private int id;
    private String userName;//注册时间
    private long createTime;//用户名
    private String password;
    private String level;//用户等级，用作简单权限控制
    private String email;//注册邮箱
    private String status;//在线还是不在线
}
