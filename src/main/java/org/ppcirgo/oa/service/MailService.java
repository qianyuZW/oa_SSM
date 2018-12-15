package org.ppcirgo.oa.service;


public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendInilneMail(String to, String subject, String content,String resPath,String resId);

    public void sendAttachmentMail(String to,String subject,String content,String filePath);


}

