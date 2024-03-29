package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.MailModel;
import org.ppcirgo.oa.mapper.MailMapper;
import org.ppcirgo.oa.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Transactional
@Service
public class MailServiceImpl  implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailMapper mailMapper;

    MailModel emailModel=new MailModel();

/*
    发送简单邮件

 */
  public MailModel sendSimpleMail(String sender, String receiver, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(content);
      /* try {
            mailSender.send(message);
            logger.info("简单邮件已经发送");
        }catch (Exception e){
            logger.error("发送邮件发生异常",e);
        }*/
        return  emailModel;
    }

 /*   *
     * 发送嵌入静态资源（一般是图片）的邮件
     * @Param sender
     * @Param receiver
     * @Param content    邮件内容，需要包括一个静态资源id，比如：<img src=\"cid:resId01\">
     * @Param resPath    静态资源路径和文件名
     * @Param resId      静态资源id
     **/
    public MailModel  sendInilneMail(String sender,String receiver, String subject, String content,String resPath,String resId){
        MimeMessage message= mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource res=new FileSystemResource(new File(resPath));
            helper.addInline(resId,res);
            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件已经发生异常",e);
        }
        return  emailModel;
    }
  /*
     * 发送带附件的邮件
     * @param
     * @param
     * @param
     * @param
     */
   public MailModel  sendAttachmentMail(String sender,String receiver,String subject,String content,String filePath){

        MimeMessage message=mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file=new FileSystemResource(new File(filePath));
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            logger.info("带附件的邮件已经发送");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常",e);
        }
          return  emailModel;
    }

    @Override
    public int saveEmailRecord(MailModel mailModel) {
        return  mailMapper.saveEmailRecord(mailModel);
    }

    @Override
    public List<MailModel> getEmailRecordById(Integer id) {
        return mailMapper.getEmailRecordById(id);
    }

    @Override
    public  List<MailModel> getEmailRecordBySubject(String subject) {
       return  mailMapper.getEmailRecordBySubject(subject);
    }

    @Override
    public int updateEmailRecordBySender(String subject, String sender) {
        return mailMapper.updateEmailRecordBySender(subject,sender);
    }

    @Override
    public int updateStatusBySender(String status, String sender) {
        return mailMapper.updateStatusBySender(status,sender);
    }

    @Override
    public int deleteEmailRecordBySender(String sender) {
        return mailMapper.deleteEmailRecordBySender(sender);
    }

}
