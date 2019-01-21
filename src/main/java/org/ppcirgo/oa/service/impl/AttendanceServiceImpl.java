
package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.mapper.AttendanceMapper;
import org.ppcirgo.oa.service.AttendanceService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;


@Transactional
@Service
public  class AttendanceServiceImpl implements AttendanceService {

  @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public String getStartTimeById(Integer id) {
        return attendanceMapper.getStartTimeById(id);
    }

   @Override
    public String getEndTimeById(Integer id) {
        return attendanceMapper.getEndTimeById(id);
    }

    @Override
    public List<AttendanceModel> getAttendanceRecordById(Integer id) {
        return attendanceMapper.getAttendanceRecordById(id);
    }

    @Override
    public int saveAttendanceRecord(AttendanceModel attendanceModel) {
        return attendanceMapper.saveAttendanceRecord(attendanceModel);
    }

   @Override
    public  int updateEndTimeAndStatus(String end_time, Integer id) {
        return attendanceMapper.updateEndTimeAndStatus(end_time,id);
    }

    @Override
    public int updateStartTimeAndStatus(String start_time, Integer id) {
        return attendanceMapper.updateStartTimeAndStatus(start_time,id);
    }

    @Override
    public String getWorkTimesByEmployeeId(Integer employee_id) {
        int year= Integer.parseInt(DateUtlis.currentTime((System.currentTimeMillis())).substring(0,4));
        Calendar calendar= Calendar.getInstance();
        //获得当前时间的月份，月份从0开始所以结果要加1
        int month=calendar.get(Calendar.MONTH)+1;
        double days=DateUtlis.getDaysByYearAndMonth(year,month);
        double result1= Double.parseDouble(attendanceMapper.getWorkTimesByEmployeeId(employee_id)) /days;
        double result2=(Math.round(result1*100*1000)/1000.0);
        String result3=result2+"%";
        return result3;
    }

    @Override
    public String getSumsOfMiles(Integer employee_id, String date1, String date2) {
        return attendanceMapper.getSumsOfMiles(employee_id,date1,date2);
    }




    /*  *//*
         获得签到状态
    *//*

    public int AttendanceStatus(Integer id){
    int status = 0;//默认状态是未打卡
    //获取第一次打卡的时间
     String firstTime = attendanceMapper.getStartTimeById(id);

   //获取第二次打卡的时间
     String secondTime = attendanceMapper.getEndTimeById(id);


   /*当前日期
     String currentDate= DateUtlis.currentTime((System.currentTimeMillis())).substring(0,10);
    int result=(firstTime.substring(0,10)).compareTo(currentDate);
*//*

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
    public int attendanceTimesJudge( Integer  id){
        //通过status判断打卡的时间，0默认状态未打卡，1进行了第一次打卡，2进行了第二次打卡
         int status=AttendanceStatus(id);
         String firstTime = attendanceMapper.getStartTimeById(id);
        String secondTime = attendanceMapper.getEndTimeById(id);
          if(status == 0){
              System.out.println("未打卡");
          }else if(status==1){
              //根据uid更新签到时间和状态
              updateStartTimeAndStatus(firstTime,"打卡签到",id);
          }else if(status==2) {
              //根据uid更新签退时间和状态
              updatetEndTimeAndStatus(secondTime,"打卡签退",id);
          }else{
              System.out.println("打卡失败");
          }
          return 1;
    }*/



}
