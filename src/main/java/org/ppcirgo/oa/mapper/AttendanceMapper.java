/*
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
    @Insert(value="INSERT INTO attendance1(attendance_name,start_time,end_time,DATE,STATUS,location,start_mile,end_mile) " +
            "VALUES(#{attendance_name},#{start_time},#{end_time},#{date},#{status},#{location},#{start_mile},#{end_mile})")
    int saveAttendanceRecord(AttendanceModel attendanceModel);

   //根据id更新打卡的结束时间和状态
    @Update(value="update attendance1 set end_time=#{end_time},status=#{status} where uid=#{uid}")
    int updatetEndTimeAndStatus(@Param("end_time") String end_time,@Param("status") String status,@Param("uid") Integer uid);

    //跟根据uid更新打卡的签到时间和状态
    @Update(value="update attendance1 set start_time=#{start_time},status=#{status} where uid=#{uid}")
    int updateStartTimeAndStatus(@Param("start_time") String start_time,@Param("status") String status,@Param("uid") Integer uid);
}*/
