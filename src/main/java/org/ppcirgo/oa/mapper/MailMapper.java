package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ppcirgo.oa.beans.model.MailModel;
import org.apache.ibatis.annotations.Insert;
import org.ppcirgo.oa.beans.model.UserModel;
import org.springframework.stereotype.Repository;


@Repository
public interface MailMapper {


    //保存发邮件记录
    @Insert(value="insert into emailRecord (sender,receiver,time,subject,state) values(#{sender},#{receiver},#{time},#{subject},#{state})")
    int saveEmailRecord(MailModel mailModel);

    @Select(value="select * from emailRecord where sender=#{sender}")
    MailModel getEmailRecordBySender(@Param("sender") String sender);


}
