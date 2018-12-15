package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

    @Autowired
    private MailService mailService;

    String  to="minzhang1534781927@163.com";

    @RequestMapping(value = "/sendSimpleEmail",method = RequestMethod.POST)
    @ResponseBody
    public  Object  sendSimpleMail(){

        mailService.sendSimpleMail(to,"主题:简单邮件发送","测试邮件发送");

        return new AJAXResult("sendSimpleEmail success");
    }

    @RequestMapping(value="/sendInlineMail",method =RequestMethod.POST )
    @ResponseBody
    public Object sendInilneMail() {
        String content="<html><body>这是有图片的邮件：<img src=\\'cid:\" + rscId + \"\\' ></body></html>";
        String resPath="E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\1.png";
        String resId="neo006";
        mailService.sendInilneMail(to, "主题：圣诞快乐", content, resPath, resId);
        return new AJAXResult("sendInlineMail success");
    }


    @RequestMapping(value="sendAttachmentMail",method = RequestMethod.POST)
    @ResponseBody
    public Object sendAttachmentMail(){
        String content="有附件，请注意查收";
        String filePath="E:\\urchin-oa\\ppcirgo-oa-12.12\\picture\\Inform.txt";
        mailService.sendAttachmentMail(to,"重要通知",content,filePath);
        return new AJAXResult("sendAttachmentMail success");
    }



}
