package org.ppcirgo.oa.beans.model;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString

public class PlanModel implements Serializable {
        private int userId;   //员工id
        private long createTime;//创建时间
        private long modifyTime;//修改时间
        private long createDay;//星期
        private long modifyDay;//星期
        private String comment; //备注
        private String visitedStore;//拜访门店
        private String developmentStore;//门店开发目标
        private String shippingPlan;//出货计划

}

