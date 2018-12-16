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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
