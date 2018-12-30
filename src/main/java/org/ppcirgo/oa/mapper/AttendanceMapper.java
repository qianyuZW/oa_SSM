package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.beans.model.MailModel;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceMapper {

    //根据attendance_name获取签到时间start_time
    @Select(value="select start_time  from  attendance1 where uid=#{uid}")
    String  getStartTimeByUid(@Param("uid") Integer uid );


    //根据attendance_name获取签到时间end_time
    @Select(value="select end_time from  attendance1 WHERE uid=#{uid}")
    String getEndTimeByUid(@Param("uid") Integer uid);

    //保存打卡记录
    @Insert(value="insert into attendance1(attendance_name,start_time,end_time,date,status) values(#{attendance_name},#{start_time},#{end_time},#{date},#{status})")
    int saveAttendanceRecord(AttendanceModel attendanceModel);

   //根据id更新打卡记录
    @Update(value="update attendance1 set end_time=#{end_time} where uid=#{uid}")
    int updateAttendanceRecord(@Param("end_time") String end_time,@Param("uid") Integer uid);

}