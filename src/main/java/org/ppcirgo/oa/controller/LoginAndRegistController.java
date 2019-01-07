package org.ppcirgo.oa.controller;


import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.enums.UserStatus;
import org.ppcirgo.oa.beans.model.MailModel;
import org.ppcirgo.oa.beans.model.UserModel;
import org.ppcirgo.oa.service.MailService;
import org.ppcirgo.oa.service.UserService;
import org.ppcirgo.oa.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import static org.ppcirgo.oa.beans.consts.MsgCode.ivt;

//关于登录注册与前端的接口
@RestController
public class LoginAndRegistController {

    @Autowired
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailService mailService;
    @Value("${oa.user.level}")
    private String defaultLevel;//默认的用户等级

    //用户登录验证   liuzhou 1214
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            HttpServletRequest request
    ){
        HttpSession session = request.getSession();
        UserModel userModel = userService.findUserByEmail(email);
        if (userModel==null){
            return new AJAXResult(MsgCode.notexsit);
        }
        if (userModel!=null && userModel.getPassword().equals(MD5Utils.encodeByMD5(password))){
            session.setAttribute("user",userModel);//缓存用户状态
            userService.updateStatusByemail(email, UserStatus.online.toString());

            return new AJAXResult(MsgCode.success);
        }else {
            return new AJAXResult(MsgCode.error);//不匹配
        }

    }
    //忘记密码
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.GET)
    public Object forgetPassword(@RequestParam(value = "email",required = false) String email){

        UserModel userModel = userService.findUserByEmail(email);
        if (userModel==null){
            return new AJAXResult(MsgCode.notexsit);
        }else {
            Map<String, String> map = MD5Utils.generatorPassword();
            String password  = (String) map.keySet().toArray()[0];//这是要发送给用户的随机密码明文
            //TODO  张敏  在这里发送 简单邮件 到指定邮箱email 发送内容自己组织模板 核心是密码明文
                String sender="1534781927@163.com";
                String subject="密码重置";
                String content="验证密码为："+password+"为了您的账户安全，请及时重置新密码并登陆！";
            mailService.sendSimpleMail(sender,email,subject,content);
            MailModel mailModel=new MailModel();
            mailModel.setSender(sender);
            mailModel.setReceiver(email);
            mailModel.setSubject(subject);
            mailModel.setContent(content);
            mailModel.setPassword(password);
            mailService.saveEmailRecord(mailModel);
            userService.updatePasswordByemail(email,map.get(password));
            return new AJAXResult(MsgCode.success);
        }
    }


    //用户注册
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Object userRegist(
            @RequestParam(value = "username") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "ivt") String ivt
    ) {
        UserModel userModel = new UserModel();
        userModel.setCreateTime(System.currentTimeMillis());
        userModel.setEmail(email);
        userModel.setLevel(defaultLevel);
        userModel.setPassword(MD5Utils.encodeByMD5(password));
        userModel.setUserName(userName);
        System.out.println(userModel);

        if (!ivt.equals(ivt))
            return new AJAXResult(MsgCode.error);//企业未邀请
        if (userService.findUserByEmail(email)!=null)
            return new AJAXResult(MsgCode.isexsit);//邮箱已被注册
        if (userService.addUser(userModel)>0)
            return new AJAXResult(MsgCode.success);
        return new AJAXResult(MsgCode.error);//企业未邀请
    }

    //是否online
    @RequestMapping(value = "/isOnLine",method = RequestMethod.POST)

    public Object isOnL(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user=session.getAttribute("user");
        if (user!=null){
            UserModel userModel = (UserModel) user;
            userModel.setPassword(null);
            userModel.setLevel(null);
            userModel.setCreateTime(0);
            return new AJAXResult(userModel);
        }else {
            return new AJAXResult(MsgCode.error);
        }



    }

    //安全退出
    @PostMapping("/offline")
    public Object quit(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserModel user=(UserModel)session.getAttribute("user");
        if (user!=null){
            System.out.println("*************************"+user.getEmail());
            userService.updateStatusByemail(user.getEmail(), UserStatus.offline.toString());
            session.removeAttribute("user");
        }

        return new AJAXResult("not online");
    }

}
