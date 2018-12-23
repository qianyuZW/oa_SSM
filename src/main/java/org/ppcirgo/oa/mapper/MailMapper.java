package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.*;
import org.ppcirgo.oa.beans.model.MailModel;
import org.ppcirgo.oa.beans.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface MailMapper {


    //保存发邮件记录
    @Insert(value="insert into emailRecord (sender,receiver,time,subject,state) values(#{sender},#{receiver},#{time},#{subject},#{state})")
    int saveEmailRecord(MailModel mailModel);

    //根据发送者查找邮件内容
    @Select(value="select * from emailRecord where sender=#{sender}")
     List<MailModel>  getEmailRecordBySender(@Param("sender") String sender);

    //根据标题查询邮件内容
    @Select(value="select * from emailRecord where subject  like  '%${subject}%'")
     List<MailModel> getEmailRecordBySubject(@Param("subject") String subject);

    //根据发送者修改邮件主题
    @Update(value = " update emailRecord set subject=#{subject} where sender=#{sender} ")
    int updateEmailRecordBySender(@Param("subject") String subject, @Param("sender") String sender);

    //根据发送者删除邮件
    @Delete(value="delete from emailRecord where sender=#{sender}")
    int  deleteEmailRecordBySender(@Param("sender") String sender);


}
