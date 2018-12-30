package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.AttendanceModel;

public interface AttendanceService {

  //获得签到状态和记录打卡记录
    int AttendanceStatus(Integer uid);
   //判断是否可以进行签到
    int  attendanceTimesJudge(Integer uid);
    //获得当前的工作状态
    int workingStatus(Integer uid);


   //根据uid获取签到时间
    String getStartTimeByUid (Integer uid);
    //根据uid获取签退时间
    String  getEndTimeByUid(Integer uid);

   //保存打卡记录
    int saveAttendanceRecord(AttendanceModel attendanceModel);
    ////根据id更新打卡记录
    int  updateAttendanceRecord(String end_time,Integer uid);


}
