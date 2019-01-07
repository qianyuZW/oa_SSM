
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

    /*//打卡状态
        @RequestMapping(value = "/attendance",method = RequestMethod.GET)
        public  Object attendance (
                @RequestParam(value="id",required = false) Integer id,
                HttpServletRequest request
        ){
             int  attendceModel = attendanceService.attendanceTimesJudge(id);
            if(attendceModel>0){
                return new AJAXResult(MsgCode.success);
            }else{
                return new AJAXResult(MsgCode.notexsit);
            }
        }
    */
//根据id获得开始打卡时间
    @RequestMapping(value = "/getStartTimeById", method = RequestMethod.GET)
    public Object getStartTimeById(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String attendanceModel = attendanceService.getStartTimeById(id);

        System.out.println("attendanceModel======" + attendanceModel);
        if (attendanceModel != null) {
            //     session.setAttribute("start_time",attendanceModel);
            session.setAttribute("uid", attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //根据id获得结束打卡时间
    @RequestMapping(value = "/getEndTimeById", method = RequestMethod.GET)
    public Object getEndTimeById(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String attendanceModel = attendanceService.getEndTimeById(id);
        System.out.println("attendanceModel======" + attendanceModel);
        if (attendanceModel != null) {
            session.setAttribute("uid", attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //保存打卡记录
    String defaultStatus = "未打卡";

    @RequestMapping(value = "/saveAttendanceRecord", method = RequestMethod.GET)
    public Object saveAttendanceRecord(
            @RequestParam(value = "employee_id", required = false) Integer employee_id,
            @RequestParam(value = "attendance_name", required = false) String attendance_name,
            @RequestParam(value = "start_time", required = false) String start_time,
            @RequestParam(value = "end_time", required = false) String end_time,
            @RequestParam(value = "start_location", required = false) String start_location,
            @RequestParam(value = "start_longitude", required = false) String start_longitude,
            @RequestParam(value = "start_latitude", required = false) String start_latitude,
            @RequestParam(value = "end_location", required = false) String end_location,
            @RequestParam(value = "end_longitude", required = false) String end_longitude,
            @RequestParam(value = "end_latitude", required = false) String end_latitude,
            @RequestParam(value = "start_mile", required = false) String start_mile,
            @RequestParam(value = "start_img", required = false) String start_img,
            @RequestParam(value = "end_mile", required = false) String end_mile,
            @RequestParam(value = "end_img", required = false) String end_img

    ) {
        AttendanceModel attendanceModel = new AttendanceModel();
        attendanceModel.setEmployee_id(employee_id);
        attendanceModel.setAttendance_name(attendance_name);
        attendanceModel.setStart_time(start_time);
        attendanceModel.setEnd_time(end_time);
        attendanceModel.setDate(DateUtlis.currentTime((System.currentTimeMillis())).substring(0, 10));
        attendanceModel.setStatus(defaultStatus);
        attendanceModel.setStart_location(start_location);
        attendanceModel.setStart_longitude(start_longitude);
        attendanceModel.setStart_latitude(start_latitude);
        attendanceModel.setEnd_location(end_location);
        attendanceModel.setEnd_longitude(end_longitude);
        attendanceModel.setEnd_latitude(end_latitude);
        attendanceModel.setStart_mile(start_mile);
        attendanceModel.setStart_img(start_img);
        attendanceModel.setEnd_mile(end_mile);
        attendanceModel.setEnd_img(end_img);

        if (attendanceService.saveAttendanceRecord(attendanceModel) > 0) {
            System.out.println("attendanceModel======" + attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //根据id更新签退时间
    @RequestMapping(value = "/updateEndTimeAndStatus", method = RequestMethod.GET)
    public Object updateEndTimeAndStatus(
            @RequestParam(value = "end_time", required = false) String end_time,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        int attendanceModel = attendanceService.updateEndTimeAndStatus(end_time, id);
        if (attendanceModel > 0) {
            session.setAttribute("end_time", attendanceModel);
            session.setAttribute("id", attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    //跟根据id更新签到时间
    @RequestMapping(value = "/updateStartTimeAndStatus", method = RequestMethod.GET)
    public Object updateStartTimeAndStatus(
            @RequestParam(value = "start_time", required = false) String start_time,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        int attendanceModel = attendanceService.updateStartTimeAndStatus(start_time, id);
        //   int attendanceModel=attendanceService.updateStartTimeAndStatus(start_time,"打卡签到",uid);
        if (attendanceModel > 0) {
            session.setAttribute("start_time", attendanceModel);
            //    session.setAttribute("status",attendanceModel);
            session.setAttribute("id", attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }
    }


    //根据员工id获得出勤率
    @RequestMapping(value = "/getWorkTimesByEmployeeId", method = RequestMethod.GET)
    public Object getWorkTimesByEmployeeId(
            @RequestParam(value = "employee_id", required = false) Integer employee_id,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
       String attendanceModel= attendanceService.getWorkTimesByEmployeeId(employee_id);
       System.out.println("attendanceModel=========="+attendanceModel);
        if (attendanceModel !=null) {
            session.setAttribute("employee_id", attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }
    }

    @RequestMapping(value="/getSumsOfMiles",method = RequestMethod.GET)
    public Object getSumsOfMiles(
            @RequestParam(value = "employee_id", required = false) Integer employee_id,
            @RequestParam(value = "date1", required = false) String date1,
            @RequestParam(value = "date2", required = false) String date2,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
       String attendanceModel = attendanceService.getSumsOfMiles(employee_id, date1, date2);
/*       System.out.println("employee_id========="+employee_id);
       System.out.println("date1================"+date1);
        System.out.println("date2================"+date2);
       System.out.println("attendanceModel==========="+attendanceModel);*/
        if (attendanceModel!=null) {
            session.setAttribute("start_time", attendanceModel);
            session.setAttribute("date1", attendanceModel);
            session.setAttribute("date2", attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.notexsit);
        }


    }
}