package org.ppcirgo.oa.beans.model;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString

public class PlanModel implements Serializable {
        private int userId;   //员工id
        private String createTime;//创建时间
        private String modifyTime;//修改时间
        private long createDay;//创建时间当前是星期几
        private long modifyDay;//修改时间当前是星期几
        private String comment; //备注
        private String visitedStore;//拜访门店
        private String developmentStore;//门店开发目标
        private String shippingPlan;//出货计划
        private String currentDate;  //存放前端给的日期
        private String begin;
        private String end;
}

