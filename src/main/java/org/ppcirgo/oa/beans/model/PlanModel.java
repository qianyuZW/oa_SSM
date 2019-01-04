package org.ppcirgo.oa.beans.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString

public class PlanModel implements Serializable {
        private int planId;    //周计划id
        private int userId;   //用户id
        private String thisWeekContent; //本周计划
        private String nextWeekContent;//下周计划
        private String auditOpinion;//审核意见
        private long auditTime;//审核时间
        private long createTime;//创建时间
        private long modifyTime;//修改时间
        private String proposer; //计划提出者
        private String level;//用户等级，用作简单权限控制



        private int id;



}

