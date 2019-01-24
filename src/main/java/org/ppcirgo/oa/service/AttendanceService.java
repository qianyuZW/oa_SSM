package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.dto.MileDTO;
import org.ppcirgo.oa.beans.model.AttendanceModel;

import java.util.List;

public interface AttendanceService {

  //获得签到状态和记录打卡记录
  //  int AttendanceStatus(Integer id);
   //判断是否可以进行签到
   // int  attendanceTimesJudge(Integer id);

//   //根据id获取签到时间
//    String getStartTimeById (Integer id);
//    //根据uid获取签退时间
//    String  getEndTimeById(Integer id);
//
//    List<AttendanceModel> getAttendanceRecordById(Integer id);
//
//   //保存打卡记录
//    int saveAttendanceRecord(AttendanceModel attendanceModel);
//    //根据id更新打卡记录
//    int updateEndTimeAndStatus(String end_time,Integer id);
//    //跟根据id更新签到时间和状态
//    int updateStartTimeAndStatus(String start_time, Integer id);
//    //根据员工id获得每月的出勤率
//    String getWorkTimesByEmployeeId(Integer employee_id);
//    //查询任一员工在某个时间段内的里程数
//    String getSumsOfMiles(Integer employee_id, String date1, String date2);


 /**
  * **************************************刘周 2019 01 24********************************************
  */

 //保存签到状态
 int saveMorning(String name,String startTime,String date,String startLoc,String startLng,String startLat);
 //保存签退状态
 int saveEvening(String endTime,String endLoc,String endLng,String endLat,String eName,String date);
 //判断某人是否已经签到
 long getIsBiuBiu(String eName,String date);
 //统计某人本月的里程数
 String getMileSum(String eName, String likeDate);
 //保存签到里程
 int saveMorningMile(String name,String date,String mile,String mileImg);
 //保存签退里程
 int saveEveningMile(String name,String date,String mile,String mileImg);


}

