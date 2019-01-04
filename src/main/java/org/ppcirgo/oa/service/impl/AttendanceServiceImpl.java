/*
package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.mapper.AttendanceMapper;
import org.ppcirgo.oa.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public  class AttendanceServiceImpl implements AttendanceService {

  @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public String getStartTimeByUid(Integer uid) {
        return attendanceMapper.getStartTimeByUid(uid);
    }

   @Override
    public String getEndTimeByUid(Integer uid) {
        return attendanceMapper.getEndTimeByUid(uid);
    }

    @Override
    public int saveAttendanceRecord(AttendanceModel attendanceModel) {
        return attendanceMapper.saveAttendanceRecord(attendanceModel);
    }

   @Override
    public int updatetEndTimeAndStatus(String end_time, String status,Integer uid) {
        return attendanceMapper.updatetEndTimeAndStatus(end_time,status,uid);
    }

    @Override
    public int updateStartTimeAndStatus(String start_time, String status, Integer uid) {
        return attendanceMapper.updateStartTimeAndStatus(start_time,status,uid);
    }

    */
/*
         获得签到状态
    *//*

    public int AttendanceStatus(Integer uid){
    int status = 0;//默认状态是未打卡
    //获取第一次打卡的时间
     String firstTime = attendanceMapper.getStartTimeByUid(uid);

   //获取第二次打卡的时间
     String secondTime = attendanceMapper.getEndTimeByUid(uid);

 */
/*   //当前日期
     String currentDate=DateUtlis.currentTime((System.currentTimeMillis())).substring(0,10);
    int result=(firstTime.substring(0,10)).compareTo(currentDate);*//*


        if (firstTime == null && secondTime == null) {
            status = 0; //未打卡
        }else if ((firstTime!= null) &&(secondTime == null)) {
            status = 1; //打卡签到
        }else if((firstTime != null) && (secondTime != null)){
            status=2;   //打卡签退
        }else{
            status=3;
        }
        return  status;
    }
    public int attendanceTimesJudge( Integer  uid){
        //通过status判断打卡的时间，0默认状态未打卡，1进行了第一次打卡，2进行了第二次打卡
         int status=AttendanceStatus(uid);
         String firstTime = attendanceMapper.getStartTimeByUid(uid);
        String secondTime = attendanceMapper.getEndTimeByUid(uid);
          if(status == 0){
              System.out.println("未打卡");
          }else if(status==1){
              //根据uid更新签到时间和状态
              updateStartTimeAndStatus(firstTime,"打卡签到",uid);
          }else if(status==2) {
              //根据uid更新签退时间和状态
              updatetEndTimeAndStatus(secondTime,"打卡签退",uid);
          }else{
              System.out.println("打卡失败");
          }
          return 1;
    }



}*/
