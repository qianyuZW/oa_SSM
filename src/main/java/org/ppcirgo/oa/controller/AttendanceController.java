
package org.ppcirgo.oa.controller;

import lombok.extern.slf4j.Slf4j;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.service.AttendanceService;
import org.ppcirgo.oa.service.EmployeeService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
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
    @RequestMapping(value = "/getStartTimeById", method = RequestMethod.POST)
    public Object getStartTimeById(
            @RequestParam(value = "id", required = false) Integer id
    ) {
       String attendanceModel =attendanceService.getStartTimeById(id);

       log.info("attendanceModel=========="+attendanceModel);

        if(attendanceModel!=null){
            return new AJAXResult(MsgCode.success);
        } else
           return new AJAXResult(MsgCode.notexsit);
    }

    //根据id获得结束打卡时间
    @RequestMapping(value = "/getEndTimeById", method = RequestMethod.POST)
    public Object getEndTimeById(
            @RequestParam(value = "id", required = false) Integer id
    ) {
      String attendanceModel = attendanceService.getEndTimeById(id);

        if(attendanceModel!=null){
            return new AJAXResult(MsgCode.success);
        } else
            return new AJAXResult(MsgCode.notexsit);
    }


    //保存打卡记录
    String defaultStatus = "未打卡";

    @RequestMapping(value = "/saveAttendanceRecord", method = RequestMethod.POST)
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
            log.info("attendanceModel======" + attendanceModel);
            return new AJAXResult(MsgCode.success);
        } else {
            return new AJAXResult(MsgCode.error);
        }
    }

    //根据id更新签退时间
    @RequestMapping(value = "/updateEndTimeAndStatus", method = RequestMethod.POST)
    public Object updateEndTimeAndStatus(
            @RequestParam(value = "end_time", required = false) String end_time,
            @RequestParam(value = "id", required =false) Integer id
    ) {
        int attendanceModel = attendanceService.updateEndTimeAndStatus(end_time, id);

        log.info("attendanceModel=========="+attendanceModel);

        if (attendanceModel>0) {
            return new AJAXResult(MsgCode.success);
        }else
            return new AJAXResult(MsgCode.notexsit);
    }

    //跟根据id更新签到时间
    @RequestMapping(value = "/updateStartTimeAndStatus", method = RequestMethod.POST)
    public Object updateStartTimeAndStatus(
            @RequestParam(value = "start_time", required = false) String start_time,
            @RequestParam(value = "id", required = false) Integer id
    ) {
       int attendanceModel = attendanceService.updateStartTimeAndStatus(start_time, id);
       //    int attendanceModel=attendanceService.updateStartTimeAndStatus(start_time,"打卡签到",uid);
       log.info("attendanceModel=========="+attendanceModel);
        if (attendanceModel>0) {
           return  new AJAXResult(MsgCode.success);
        }else
            return new AJAXResult(MsgCode.notexsit);
    }


    //根据员工id获得当月出勤率
    @RequestMapping(value = "/getWorkTimesByEmployeeId", method = RequestMethod.POST)
    public Object getWorkTimesByEmployeeId(
            @RequestParam(value = "employee_id", required = false) Integer employee_id
    ) {
       String attendanceModel= attendanceService.getWorkTimesByEmployeeId(employee_id);

        log.info("attendanceModel====="+attendanceModel);

        if (attendanceModel!=null) {
            return  new AJAXResult(MsgCode.success);
        }else
            return new AJAXResult(MsgCode.notexsit);
    }
    //根据时间和员工id获得里程数和
    @RequestMapping(value="/getSumsOfMiles",method = RequestMethod.POST)
    public Object getSumsOfMiles(
            @RequestParam(value = "employee_id", required = false) Integer employee_id,
            @RequestParam(value = "date1", required = false) String date1,
            @RequestParam(value = "date2", required = false) String date2
    ) {
        String attendanceModel = attendanceService.getSumsOfMiles(employee_id, date1, date2);
        log.info("employee_id========="+employee_id);
        log.info("date1================"+date1);
        log.info("date2================"+date2);
        log.info("attendanceModel==========="+attendanceModel);
        if (attendanceModel!=null) {
            return  new AJAXResult(MsgCode.success);
        }else
            return new AJAXResult(MsgCode.notexsit);
    }


    @Autowired
    private EmployeeService employeeService;
    //根据员工姓名获取员工id
    @GetMapping("/geteid")
    public Object getEID(
            @RequestParam(value = "name") String name
    ){
        return new AJAXResult(employeeService.getEid(name));
    }
}