
package org.ppcirgo.oa.beans.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttendanceModel {
    private Integer id;
    //private Integer employee_id;
    private  String e_name;
    private  String  start_time;
    private  String  end_time;
    private  String  date ;
    private  String status;
    private  String start_location;
    private  String start_longitude;
    private  String start_latitude;
    private  String end_location;
    private  String end_longitude;
    private  String end_latitude;
    private  String start_mile;
    private  String start_img;
    private  String end_mile;
    private  String end_img;
}
