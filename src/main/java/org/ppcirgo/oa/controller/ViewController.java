package org.ppcirgo.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author:liuzhou
 * desc:有关页面的controller
 */
@Controller
public class ViewController {

    //登录注册页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "oalogin";
    }
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String regist(){
        return "oaregist";
    }

    //主页面
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "oaindex";
    }

    //考勤查询页面
    @RequestMapping(value = "/aquery",method = RequestMethod.GET)
    public String attQuery(){
        return "concrete/oa_att_query";
    }

    //网络布局页面
    @RequestMapping(value = "/netlayout",method = RequestMethod.GET)
    public String netLayout(){
        return "concrete/net_layout";
    }


}
