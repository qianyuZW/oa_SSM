package org.ppcirgo.oa.beans.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailModel {
    private Integer id;
    private String sender;
    private  String receiver;
    private String time;
    private  String  subject;
    private String state;

}
