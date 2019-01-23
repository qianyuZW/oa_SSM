package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.AttendanceModel;

import java.util.List;

public interface AttendanceService {

  //获得签到状态和记录打卡记录
  //  int AttendanceStatus(Integer id);
   //判断是否可以进行签到
   // int  attendanceTimesJudge(Integer id);

   //根据id获取签到时间
    String getStartTimeById (Integer id);
    //根据uid获取签退时间
    String  getEndTimeById(Integer id);

    List<AttendanceModel> getAttendanceRecordById(Integer id);

   //保存打卡记录
    int saveAttendanceRecord(AttendanceModel attendanceModel);
    //根据id更新打卡记录
    int updateEndTimeAndStatus(String end_time,Integer id);
    //跟根据id更新签到时间和状态
    int updateStartTimeAndStatus(String start_time, Integer id);
    //根据员工id获得每月的出勤率
    String getWorkTimesByEmployeeId(Integer employee_id);
    //查询任一员工在某个时间段内的里程数
    String getSumsOfMiles(Integer employee_id, String date1, String date2);

}

