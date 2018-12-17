package org.ppcirgo.oa.service;


import org.ppcirgo.oa.beans.model.MailModel;

public interface MailService {

    //发送简单邮件
    public void sendSimpleMail(String to, String subject, String content);
    //发送带图片的邮件
    public void sendInilneMail(String to, String subject, String content,String resPath,String resId);
    //发送带附件的邮件
    public void sendAttachmentMail(String to,String subject,String content,String filePath);

    //保存发邮件记录
    int saveEmailRecord(MailModel mailModel);

    //通过发送者查询邮件记录
    MailModel getEmailRecordBySender(String sender);


}

