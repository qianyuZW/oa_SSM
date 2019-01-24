
package org.ppcirgo.oa.beans.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttendanceModel {
    private Integer id;
    //private Integer employee_id;
    private  String eName;
    private  String  startTime;
    private  String  endTime;
    private  String  date ;
    private  String status;
    private  String startLocation;
    private  String startLongitude;
    private  String startLatitude;
    private  String endLocation;
    private  String endLongitude;
    private  String endLatitude;
    private  String startMile;
    private  String startImg;
    private  String endMile;
    private  String endImg;
}
