package org.ppcirgo.oa.controller;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.MailModel;
import org.ppcirgo.oa.service.MailService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  author:zhangmin
 *  desc:有关邮件的controller
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;
    private String defaultStatus="1";//默认的发邮件状态
    /*
     简单邮件发送,并保存记录
      */
    @RequestMapping(value="/sendSimpleEmail",method = RequestMethod.GET)
    public Object sendSimpleMail(
            @RequestParam(value="sender",required = false) String sender,
            @RequestParam(value="receiver",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content
    ) {
            MailModel mailModel=mailService.sendSimpleMail(sender,receiver,subject,content);
            mailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
            mailModel.setStatus(defaultStatus);
         if(mailService.saveEmailRecord(mailModel)>0)
            return new AJAXResult(MsgCode.success);
         else
            return new AJAXResult(MsgCode.error);

    }
/*
          带有附件的邮件
         resPath规范： E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\1.png
 */

    @RequestMapping(value="/sendInlineMail",method =RequestMethod.GET)
    public Object sendInilneMail(
            @RequestParam(value="sender",required = false) String sender,
            @RequestParam(value="receiver",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content,
            @RequestParam(value="resPath",required = false)  String resPath,
           @RequestParam(value="resId",required = false)  String resId
    ) {

        MailModel mailModel=mailService.sendInilneMail(sender, receiver, subject, content,resPath,resId);
   //     return new AJAXResult("sendInlineMail success");
            mailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
            mailModel.setStatus(defaultStatus);

        if(mailService.saveEmailRecord(mailModel)>0)
            return new AJAXResult(MsgCode.success);
        else
            return new AJAXResult(MsgCode.error);
    }

    /*
           带有附件的邮件
            String filePath="E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\Inform.txt";
     */
    @RequestMapping(value="/sendAttachmentMail",method =RequestMethod.GET)
    public Object sendAttachmentMail(
            @RequestParam(value="sender",required = false) String sender,
            @RequestParam(value="receiver",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject,
            @RequestParam(value="content",required = false)  String content,
            @RequestParam(value="filePath",required = false)  String filePath
    ) {

        MailModel mailModel=mailService.sendAttachmentMail(sender, receiver, subject, content,filePath);
       // return new AJAXResult("sendInlineMail success");
        mailModel.setTime(DateUtlis.currentTime((System.currentTimeMillis())));
        mailModel.setStatus(defaultStatus);
        if(mailService.saveEmailRecord(mailModel)>0)
            return new AJAXResult(MsgCode.success);
        else
            return new AJAXResult(MsgCode.error);
    }


    /**
     * 增加邮件记录
     * @param sender
     * @param receiver
     * @param subject
     * @param content
     * @return
     */
/*
   @RequestMapping(value = "/addEmailRecord",method = RequestMethod.GET)
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
        emailModel.setState(defaultState);
         emailModel.setState(password);
        System.out.println("emailModel====="+emailModel);

        if(mailService.saveEmailRecord(emailModel)>0)
            return new AJAXResult(MsgCode.success);
        else
            return new AJAXResult(MsgCode.error);
    }
*/

    /**
     * 根据发送者查询发邮件记录
     * @param id
     * @param request
     * @return
     */
    @RequestMapping (value = "/getEmailRecordById",method = RequestMethod.GET)
    public  Object getEmailRecordById(
            @RequestParam(value="id",required = false) Integer id,
            HttpServletRequest request
    ){
            HttpSession session = request.getSession();
            List<MailModel> mailModel=mailService.getEmailRecordById(id);
            if(mailModel==null){
                return new AJAXResult(MsgCode.notexsit);
            }else{
                session.setAttribute("id",mailModel);
                return new AJAXResult(MsgCode.success);
            }
        }

     @RequestMapping(value = "/getEmailRecordBySubject",method = RequestMethod.GET)
    public Object getEmailRecordBySubject(
         @RequestParam(value="subject",required = false) String subject,
         HttpServletRequest request
     ){
        HttpSession session=request.getSession();
         List<MailModel> mailModel=mailService.getEmailRecordBySubject(subject);

        if(mailModel==null){
            return new AJAXResult(MsgCode.notexsit);
        }else{
            session.setAttribute("subject",mailModel);
            return  new AJAXResult(MsgCode.success);
        }
     }

     @RequestMapping(value="/updateEmailRecordBySender",method = RequestMethod.GET)
    public Object updateEmailRecordBySender(
            @RequestParam(value="subject",required=false)  String subject,
            @RequestParam(value="sender",required = false)  String sender,
            HttpServletRequest request
     ){
         HttpSession session=request.getSession();
         int mailModel=mailService.updateEmailRecordBySender(subject,sender);
          if(mailModel>0){
              session.setAttribute("subject",mailModel);
              session.setAttribute("sender",mailModel);
             return new AJAXResult(MsgCode.success);
          }else{
             return new AJAXResult(MsgCode.notexsit);
          }
     }

    //根据
    // 根据发送者修改发送邮件的state
    @RequestMapping(value="/updateStatusById",method = RequestMethod.GET)
    public Object  updateStatusById(
            @RequestParam(value="status",required = false)  String status,
            @RequestParam(value="sender",required = false)  String sender,
            HttpServletRequest request
    ){
        HttpSession session=request.getSession();
        int mailModel=mailService.updateStatusById(status,sender);
        if(mailModel>0){
            session.setAttribute("status",mailModel);
            session.setAttribute("sender",mailModel);
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.notexsit);
        }
    }
    @RequestMapping(value="/deleteEmailRecordBySender",method = RequestMethod.GET)
    public Object deleteEmailRecordBySender(
         @RequestParam(value="sender",required = false)  String sender,
         HttpServletRequest request
     ){
       HttpSession session=request.getSession();
        int mailModel=mailService.deleteEmailRecordBySender(sender);


         if(mailModel>0){
             session.setAttribute("sender",mailModel);
             return new AJAXResult(MsgCode.success);
         }else{
             return new AJAXResult(MsgCode.notexsit);
         }
    }



}


















