package org.ppcirgo.oa.controller;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.model.MailModel;
import org.ppcirgo.oa.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    String  to="minzhang1534781927@163.com";
    @RequestMapping(value = "/sendSimpleEmail",method = RequestMethod.GET)
    public  Object  sendSimpleMail(){
         String subject="主题:简单邮件发送";
         String content="测试邮件发送";

        mailService.sendSimpleMail(to,subject,content);return new AJAXResult("sendSimpleEmail success");
    }


    @RequestMapping(value="/sendInlineMail",method =RequestMethod.GET)
    public Object sendInilneMail() {
        String subjct="主题：圣诞快乐";
        String content="<html><body>这是有图片的邮件：<img src=\\'cid:\" + rscId + \"\\' ></body></html>";
        String resPath="E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\1.png";
        String resId="neo006";

        mailService.sendInilneMail(to, subjct, content, resPath, resId);return new AJAXResult("sendInlineMail success");
    }


    @RequestMapping(value="sendAttachmentMail",method = RequestMethod.GET)
    public Object sendAttachmentMail(){
        String subject="重要通知";
        String content="有附件，请注意查收";
        String filePath="E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\Inform.txt";

        mailService.sendAttachmentMail(to,subject,content,filePath);return new AJAXResult("sendAttachmentMail success");
    }



    private String defaultState="1";//默认的发邮件状态

    @RequestMapping(value = "/addEmailRecord",method = RequestMethod.GET)
    public Object addEmailRecord(
            @RequestParam(value="from",required = false) String sender,
            @RequestParam(value="to",required = false )String receiver,
            @RequestParam(value="subject",required = false)  String subject
    ){
        MailModel emailModel=new MailModel();
        emailModel.setSender(sender);
        emailModel.setReceiver(receiver);
        emailModel.setSubject(subject);
        emailModel.setTime(BigInteger.valueOf(System.currentTimeMillis()));
        emailModel.setState(defaultState);
        System.out.println(emailModel);

        if(mailService.saveEmailRecord(emailModel)>0)
            return new AJAXResult(1);
        else
            return new AJAXResult(4009,0);
    }



}
