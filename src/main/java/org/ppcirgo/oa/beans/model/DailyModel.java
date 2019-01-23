
package org.ppcirgo.oa.beans.model;

import lombok.Data;
import lombok.ToString;

import javax.xml.soap.Text;

@Data
@ToString
public class DailyModel {
    private Integer id;
    private String employee_name;
    private String created_time;
    private String content;


}

