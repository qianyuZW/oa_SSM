package org.ppcirgo.oa.beans.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailModel {
    private Integer id;
    private String sender;   //邮件发送者
    private  String receiver;  //邮件接收者
    private String time;      //邮件发送时间
    private  String  subject;   //邮件主题
    private String status;      //邮件发送状态  1：发送成功  0：发送失败
    private String content;
    private  String password;

}
