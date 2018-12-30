package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.mapper.AttendanceMapper;
import org.ppcirgo.oa.service.AttendanceService;
import org.ppcirgo.oa.utils.DateUtlis;
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
    public int updateAttendanceRecord(String end_time, Integer uid) {
        return attendanceMapper.updateAttendanceRecord(end_time,uid);
    }

    /*
         获得签到状态和工作状态
    */
    public int AttendanceStatus(Integer uid){
    int status = 0;//默认不能进行签到
    int  workingStatus=0;  //默认的工作状态是未打卡
    //获取第一次打卡的时间
    String firstTime = attendanceMapper.getStartTimeByUid(uid);
   //获取第二次打卡的时间
    String secondTime = attendanceMapper.getEndTimeByUid(uid);

        if (firstTime == null && secondTime == null) {
              status=0;
             workingStatus=1;
         } else if (firstTime != null && secondTime == null) {
              status=1;
            workingStatus=1;
         }else if(firstTime != null && secondTime != null) {
              status=2;
            workingStatus=1;
         }else{
              status=3;
         }
            return status;
}

     /*
          判断是否可以进行签到
      */
    public int attendanceTimesJudge( Integer  uid){
        //通过status判断打卡的时间，1表示打卡的开始时间，2表示打卡的结束时间，3表示当天不能打卡，请第二天打卡，其它的状态打卡无效
         int status=AttendanceStatus(uid);
          if(status == 1){
              System.out.println("进行签到");
          }else if(status==2){
              System.out.println("进行签退");
          }else {
              System.out.println("打卡失败");
          }
          return 1;
    }
       /*
           获得当前的工作状态
     */
  @Override
    public int workingStatus(Integer uid) {
       int workingStatus= attendanceTimesJudge(uid);
        if(workingStatus==0){
            System.out.println("未打卡");
        }else if(workingStatus==1){
            System.out.println("已打卡");
        }else if(workingStatus==2){
            System.out.println("休假或请假");
        }
        return 1;
    }

}