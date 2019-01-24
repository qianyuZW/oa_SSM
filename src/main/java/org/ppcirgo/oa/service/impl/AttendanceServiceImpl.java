
package org.ppcirgo.oa.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ppcirgo.oa.beans.dto.MileDTO;
import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.mapper.AttendanceMapper;
import org.ppcirgo.oa.service.AttendanceService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;


@Transactional
@Service
@Slf4j
public  class AttendanceServiceImpl implements AttendanceService {

  @Autowired
    private AttendanceMapper attendanceMapper;

//    @Override
//    public String getStartTimeById(Integer id) {
//        return attendanceMapper.getStartTimeById(id);
//    }
//
//   @Override
//    public String getEndTimeById(Integer id) {
//        return attendanceMapper.getEndTimeById(id);
//    }
//
//    @Override
//    public List<AttendanceModel> getAttendanceRecordById(Integer id) {
//        return attendanceMapper.getAttendanceRecordById(id);
//    }
//
//    @Override
//    public int saveAttendanceRecord(AttendanceModel attendanceModel) {
//        return attendanceMapper.saveAttendanceRecord(attendanceModel);
//    }
//
//   @Override
//    public  int updateEndTimeAndStatus(String end_time, Integer id) {
//        return attendanceMapper.updateEndTimeAndStatus(end_time,id);
//    }
//
//    @Override
//    public int updateStartTimeAndStatus(String start_time, Integer id) {
//        return attendanceMapper.updateStartTimeAndStatus(start_time,id);
//    }
//
//    @Override
//    public String getWorkTimesByEmployeeId(Integer employee_id) {
//        int year= Integer.parseInt(DateUtlis.currentTime((System.currentTimeMillis())).substring(0,4));
//        Calendar calendar= Calendar.getInstance();
//        //获得当前时间的月份，月份从0开始所以结果要加1
//        int month=calendar.get(Calendar.MONTH)+1;
//        double days=DateUtlis.getDaysByYearAndMonth(year,month);
//        double result1= Double.parseDouble(attendanceMapper.getWorkTimesByEmployeeId(employee_id))/days;
//        double result2=(Math.round(result1*100*1000)/1000.0);
//        String result3=result2+"%";
//        return result3;
//    }
//
//    @Override
//    public String getSumsOfMiles(Integer employee_id, String date1, String date2) {
//        return attendanceMapper.getSumsOfMiles(employee_id,date1,date2);
//    }



    //---------------------------------刘周 2019 01 24------------------------------------------------------

    @Override
    public int saveMorning(String name, String startTime, String date, String startLoc, String startLng, String startLat) {
        if (StringUtils.isEmpty(date)){
            date=DateUtlis.getNowYMD();
        }
        if (StringUtils.isEmpty(startTime)){
            startTime=DateUtlis.currentTime(null);
        }
        return attendanceMapper.saveMorning(name,startTime,date,startLoc,startLng,startLat);
    }

    @Override
    public int saveEvening(String endTime, String endLoc, String endLng, String endLat, String eName, String date) {
        if (StringUtils.isEmpty(date)){
            date=DateUtlis.getNowYMD();
        }
        if (StringUtils.isEmpty(endTime)){
            endTime=DateUtlis.currentTime(null);
        }
        return attendanceMapper.saveEvening(endTime,endLoc,endLng,endLat,eName,date);
    }

    @Override
    public long getIsBiuBiu(String eName, String date) {
        if (StringUtils.isEmpty(date)){
            date=DateUtlis.getNowYMD();
        }
        return attendanceMapper.getIsBiuBiu(eName,date);
    }

    @Override
    public String getMileSum(String eName, String likeDate) {
        if (StringUtils.isEmpty(likeDate)){
            likeDate=DateUtlis.getNowYMD();//2019-01-24
            likeDate=likeDate.substring(0,7);
            log.info("求解月里程数，参数："+likeDate);
        }
        List<MileDTO> mileSum = attendanceMapper.getMileSum(eName, likeDate);
        Integer sum = mileSum.stream()
                .map(t -> Integer.parseInt(t.getEndMile()) - Integer.parseInt(t.getStartMile()))
                .reduce(0, (s1, s2) -> s1 + s2);
        return sum+"";
    }

    @Override
    public int saveMorningMile(String name, String date, String mile, String mileImg) {
        if (StringUtils.isEmpty(date)){
            date=DateUtlis.getNowYMD();
        }
        return attendanceMapper.saveMorningMile(name,date,mile,mileImg);
    }

    @Override
    public int saveEveningMile(String name, String date, String mile, String mileImg) {
        if (StringUtils.isEmpty(date)){
            date=DateUtlis.getNowYMD();
        }
        return attendanceMapper.saveEveningMile(name,date,mile,mileImg);
    }

    @Override
    public AttendanceModel getRec(String name, String date) {
        if (StringUtils.isEmpty(date)){
            date=DateUtlis.getNowYMD();
        }
        return attendanceMapper.getRec(name,date);
    }


}
