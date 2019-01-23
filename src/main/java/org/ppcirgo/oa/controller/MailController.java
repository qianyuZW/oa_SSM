package org.ppcirgo.oa.controller;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.MailModel;
import org.ppcirgo.oa.service.MailService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 *  author:zhangmin
 *  desc:有关邮件的controller
 */
@RestController
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private   JavaMailSender mailSender;
    private String defaultStatus="1";//默认的发邮件状态
    /*
     简单邮件发送,并保存记录
      */
    @RequestMapping(value="/sendSimpleEmail",method = RequestMethod.POST)
    public Object sendSimpleMail(
            @RequestParam(value="sender",required = false) String sender,
            @RequestParam(value="receiver",required = false) String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(content);

        MailModel mailModel = mailService.sendSimpleMail(sender, receiver, subject, content);
        try {
            mailSender.send(message);
            mailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
            mailModel.setStatus(defaultStatus);
            mailService.saveEmailRecord(mailModel);
            return new AJAXResult(MsgCode.success);
        } catch (Exception e) {
            return new AJAXResult(MsgCode.error);

        }
    }
/*
          带有附件的邮件
         resPath规范： E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\1.png
 */
    @RequestMapping(value="/sendInlineMail",method =RequestMethod.POST)
    public Object sendInilneMail(
            @RequestParam(value="sender",required = false) String sender,
            @RequestParam(value="receiver",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content,
            @RequestParam(value="resPath",required = false)  String resPath,
          @RequestParam(value="resId",required = false)  String resId
    ) {

        MimeMessage message= mailSender.createMimeMessage();
        MailModel mailModel=mailService.sendInilneMail(sender, receiver, subject, content,resPath,resId);
         try{
             MimeMessageHelper helper=new MimeMessageHelper(message,true);
             helper.setFrom(sender);
             helper.setTo(receiver);
             helper.setSubject(subject);
             helper.setText(content,true);
             FileSystemResource res=new FileSystemResource(new File(resPath));
             helper.addInline(resId,res);
             mailSender.send(message);

             mailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
             mailModel.setStatus(defaultStatus);
             mailService.saveEmailRecord(mailModel);
             return new AJAXResult(MsgCode.success);
         } catch (MessagingException e) {
             return new AJAXResult(MsgCode.error);
         }
    }

    /*
           带有附件的邮件
            String filePath="E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\Inform.txt";
     */
    @RequestMapping(value="/sendAttachmentMail",method =RequestMethod.POST)
    public Object sendAttachmentMail(
            @RequestParam(value="sender",required = false) String sender,
            @RequestParam(value="receiver",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content,
           @RequestParam(value="filePath",required = false)  String filePath
    ) {
        MimeMessage message=mailSender.createMimeMessage();
        MailModel mailModel=mailService.sendAttachmentMail(sender, receiver, subject, content,filePath);
     try{
         MimeMessageHelper helper=new MimeMessageHelper(message,true);
         helper.setFrom(sender);
         helper.setTo(receiver);
         helper.setSubject(subject);
         helper.setText(content,true);
         FileSystemResource file=new FileSystemResource(new File(filePath));
         String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
         helper.addAttachment(fileName,file);
         mailSender.send(message);

         mailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
         mailModel.setStatus(defaultStatus);
         mailService.saveEmailRecord(mailModel);
         return new AJAXResult(MsgCode.success);
     } catch (MessagingException e) {
         return new AJAXResult(MsgCode.error);
     }
    }


    /**
     * 增加邮件记录
     * @param sender
     * @param receiver
     * @param subject
     * @param content
     * @return
     */

 /*  @RequestMapping(value = "/addEmailRecord",method = RequestMethod.POST)
    public Object addEmailRecord(
            @RequestParam(value="from",required = false) String sender,
            @RequestParam(value="to",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content,
            @RequestParam(value="password",required = false)  String password
    ){
        MailModel emailModel=new MailModel();
        emailModel.setSender(sender);
        emailModel.setReceiver(receiver);
        emailModel.setSubject(subject);
        emailModel.setSubject(content);
        emailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
        emailModel.setStatus(defaultStatus);
        //emailModel.setState(password);
        System.out.println("emailModel====="+emailModel);

        if(mailService.saveEmailRecord(emailModel)>0)
            return new AJAXResult(MsgCode.success);
        else
            return new AJAXResult(MsgCode.error);
    }*/


    /**
     * 根据发送者查询发邮件记录
     * @param id
     * @return
     */
    @RequestMapping (value = "/getEmailRecordById",method = RequestMethod.POST)
    public  Object getEmailRecordById(
            @RequestParam(value="id",required =false) Integer id
    ){
            String mailModel= String.valueOf(mailService.getEmailRecordById(id));
            //System.out.println("mailModel========"+mailModel);
            if(mailModel!="[]"){
                return new AJAXResult(MsgCode.success);
            }else{
                return new AJAXResult(MsgCode.notexsit);
            }
        }

     @RequestMapping(value = "/getEmailRecordBySubject",method = RequestMethod.POST)
    public Object getEmailRecordBySubject(
         @RequestParam(value="subject",required = false) String subject
     ){
        String mailModel= String.valueOf(mailService.getEmailRecordBySubject(subject));
        // System.out.println("mailModel========"+mailModel);
        if(mailModel!="[]"){
            return new AJAXResult(MsgCode.success);
        }else{
            return  new AJAXResult(MsgCode.notexsit);
        }
     }

     @RequestMapping(value="/updateEmailRecordBySender",method = RequestMethod.POST)
    public Object updateEmailRecordBySender(
            @RequestParam(value="subject",required=false)  String subject,
            @RequestParam(value="sender",required = false)  String sender
     ){
         int mailModel=mailService.updateEmailRecordBySender(subject,sender);

         //System.out.println("mailModel====="+mailModel);

          if(mailModel>0){
             return new AJAXResult(MsgCode.success);
          }else{
             return new AJAXResult(MsgCode.error);
          }
     }

    // 根据发送者修改发送邮件的state
    @RequestMapping(value="/updateStatusBySender",method = RequestMethod.POST)
    public Object  updateStatusBySender(
            @RequestParam(value="status",required = false)  String status,
            @RequestParam(value="sender",required = false)  String sender
    ){
        int mailModel=mailService.updateStatusBySender(status,sender);

        //System.out.println("mailModel====="+mailModel);

        if(mailModel>0){
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.error);
        }
    }
    @RequestMapping(value="/deleteEmailRecordBySender",method = RequestMethod.POST)
    public Object deleteEmailRecordBySender(
         @RequestParam(value="sender",required = false)  String sender
     ){
        int mailModel=mailService.deleteEmailRecordBySender(sender);
        System.out.println("mailModel========"+mailModel);

         if(mailModel>0){
             return new AJAXResult(MsgCode.success);
         }else{
             return new AJAXResult(MsgCode.error);
         }
    }
    
}


















