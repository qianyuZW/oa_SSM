
package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ppcirgo.oa.beans.dto.MileDTO;
import org.ppcirgo.oa.beans.model.AttendanceModel;
import org.ppcirgo.oa.beans.model.MailModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceMapper {

    //根据attendance_name获取签到时间start_time
    @Select(value="select start_time  from  attendance where id=#{id}")
   String  getStartTimeById(@Param("id") Integer id );


    //根据attendance_name获取签到时间end_time
    @Select(value="select end_time from  attendance where id=#{id}")
   String getEndTimeById(@Param("id") Integer id);

    //根据id查询考勤记录
    @Select(value="select *from attendance where id=#{id}")
    List<AttendanceModel> getAttendanceRecordById(@Param("id") Integer id);

    //保存打卡记录
    @Insert(value="insert into attendance(employee_id,attendance_name,start_time,end_time,DATE,STATUS,start_location,start_longitude,start_latitude,end_location,end_longitude,end_latitude,\n" +
            "start_mile,start_img,end_mile,end_img) values(#{employee_id},#{attendance_name},#{start_time},#{end_time},#{date},#{status},#{start_location},#{start_longitude},#{start_latitude},\n" +
            " #{end_location},#{end_longitude},#{end_latitude},#{start_mile},#{start_img},#{end_mile},#{end_img})")
    int saveAttendanceRecord(AttendanceModel attendanceModel);

   //根据id更新打卡的结束时间和状态
    @Update(value="update attendance set end_time=#{end_time} where id=#{id}")
    int  updateEndTimeAndStatus(@Param("end_time") String end_time,@Param("id") Integer id);

    //跟根据uid更新打卡的签到时间和状态
    @Update(value="update attendance set start_time=#{start_time} where id=#{id}")
    int updateStartTimeAndStatus(@Param("start_time") String start_time,@Param("id") Integer id);


    //根据员工id获得每月的出勤率
    @Select(value="select sum(status=\"打卡签退\") from attendance where employee_id=#{employee_id}")
    String getWorkTimesByEmployeeId(@Param("employee_id") Integer employee_id);

    //查询员工在某个时间段内的里程数
    @Select(value="select sum(end_mile-start_mile) as miles from attendance where employee_id=#{employee_id} and (date between #{date1} and #{date2})")
    String getSumsOfMiles(@Param("employee_id") Integer employee_id, @Param("date1") String date1, @Param("date2") String date2);

    /*--------------------------------------------刘周 2019 01 24--------------------------------------------------------*/
    //保存签到状态
    @Insert("insert into attendance(e_name,start_time,date,start_location,start_longitude,start_latitude) values(#{name},#{startTime},#{date},#{startLoc},#{startLng},#{startLat})")
    int saveMorning(String name,String startTime,String date,String startLoc,String startLng,String startLat);
    //保存签退状态
    @Update("update attendance set end_time=#{endTime},end_location=#{endLoc},end_longitude=#{endLng},end_latitude=#{endLat} where e_name=#{eName} and date=#{date}  ")
    int saveEvening(String endTime,String endLoc,String endLng,String endLat,String eName,String date);
    //判断某人是否已经签到
    @Select("select count(*) from attendance where e_name=#{eName} and date=#{date}")
    long getIsBiuBiu(String eName,String date);
    //统计某人本月的里程数
    @Select("select start_mile,end_mile from attendance where e_name=#{name} and date like  CONCAT('%',#{likeDate},'%') ")
    List<MileDTO> getMileSum(String eName,String likeDate);
    //保存签到里程
    @Update("update attendance set start_mile=#{mile},start_img=#{mileImg} where e_name=#{name} and date=#{date}")
    int saveMorningMile(String name,String date,String mile,String mileImg);
    //保存签退里程
    @Update("update attendance set end_mile=#{mile},end_img=#{mileImg} where e_name=#{name} and date=#{date}")
    int saveEveningMile(String name,String date,String mile,String mileImg);
    //获取某人当天打卡记录
    @Select("select * from attendance where e_name=#{eName} and date=#{date}")
    List<AttendanceModel> getRec(String eName,String date);
    /*--------------------------------------------刘周 2019 01 24--------------------------------------------------------*/
}
