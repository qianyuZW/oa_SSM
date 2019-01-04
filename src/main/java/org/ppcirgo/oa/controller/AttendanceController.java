/*
package org.ppcirgo.oa.controller;

import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.service.AttendanceService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class AttendanceController {
 @Autowired
    private AttendanceService attendanceService;

//打卡状态
    @RequestMapping(value = "/attendance",method = RequestMethod.GET)
    public  Object attendance (
            @RequestParam(value="uid",required = false) Integer uid,
            HttpServletRequest request
    ){
         int  attendceModel = attendanceService.attendanceTimesJudge(uid);
        if(attendceModel>0){
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.notexsit);
        }
    }

//根据id获得开始打卡时间
   @RequestMapping(value="/getStartTimeByUid",method = RequestMethod.GET)
   public Object getStartTimeByUid(
          @RequestParam(value="uid",required=false)  Integer  uid,
          HttpServletRequest request
   ){
       HttpSession session=request.getSession();
       String attendanceModel= attendanceService.getStartTimeByUid(uid);

       if(attendanceModel!=null){
           session.setAttribute("uid",attendanceModel);
           return new AJAXResult(MsgCode.success);
       }else{
           return new AJAXResult(MsgCode.notexsit);
       }
   }

   //根据id获得结束打卡时间
    @RequestMapping(value="/getEndTimeByUid",method = RequestMethod.GET)
    public Object getEndTimeByUid(
            @RequestParam(value="uid",required = false) Integer  uid,
            HttpServletRequest request
    ){
        HttpSession session=request.getSession();
        String attendanceModel=attendanceService.getEndTimeByUid(uid);

        if(attendanceModel!=null){
            session.setAttribute("uid",attendanceModel);
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //保存打卡记录
    String defaultStatus="未打卡";
    @RequestMapping(value = "/saveAttendanceRecord",method = RequestMethod.GET)
    public  Object saveAttendanceRecord(
            @RequestParam(value="attendance_name",required = false) String attendance_name,
            @RequestParam(value="start_time",required = false )String start_time ,
            @RequestParam(value="end_time",required = false)  String end_time,
            @RequestParam(value = "location",required=false) String location,
            @RequestParam(value = "start_mile",required=false) String start_mile,
            @RequestParam(value="end_mile",required = false) String  end_mile

    ){
        AttendanceModel attendanceModel=new AttendanceModel();
        attendanceModel.setAttendance_name(attendance_name);
        attendanceModel.setStart_time(start_time);
        attendanceModel.setEnd_time(end_time);
        attendanceModel.setDate(DateUtlis.currentTime((System.currentTimeMillis())).substring(0,10));
        attendanceModel.setStatus(defaultStatus);
        attendanceModel.setLocation(location);
        attendanceModel.setStart_mile(start_mile);
        attendanceModel.setEnd_mile(end_mile);

        if(attendanceService.saveAttendanceRecord(attendanceModel)>0){
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //根据id更新打卡时间
    @RequestMapping(value="/updatetEndTimeAndStatus",method = RequestMethod.GET)
    public Object updatetEndTimeAndStatus(
        @RequestParam(value="end_time",required=false)  String end_time,
        @RequestParam(value="uid",required = false)  Integer uid,
        HttpServletRequest request
        ){
           HttpSession session=request.getSession();
       int attendanceModel=attendanceService.updatetEndTimeAndStatus(end_time,"打卡签退",uid);
        if(attendanceModel>0){
            session.setAttribute("end_time",attendanceModel);
            session.setAttribute("status",attendanceModel);
            session.setAttribute("uid",attendanceModel);
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //跟根据uid更新签到时间和状态
    @RequestMapping(value="/updateStartTimeAndStatus",method = RequestMethod.GET)
    public Object updateStartTimeAndStatus(
            @RequestParam(value="start_time",required=false)  String start_time,
            @RequestParam(value="uid",required = false)  Integer uid,
            HttpServletRequest request
    ){
        HttpSession session=request.getSession();

        int attendanceModel=attendanceService.updateStartTimeAndStatus(start_time,"打卡签到",uid);
        if(attendanceModel>0){
            session.setAttribute("start_time",attendanceModel);
            session.setAttribute("status",attendanceModel);
            session.setAttribute("uid",attendanceModel);
            return new AJAXResult(MsgCode.success);
        }else{
            return new AJAXResult(MsgCode.notexsit);
        }
    }
    }

*/
