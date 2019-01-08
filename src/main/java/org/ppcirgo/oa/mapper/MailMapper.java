
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
    @Insert(value="insert into email_record (sender,receiver,time,subject,status,content,password)  values(#{sender},#{receiver},#{time},#{subject},#{status},#{content},#{password})")
    int saveEmailRecord(MailModel mailModel);

    //根据发送者查找邮件内容
    @Select(value="select * from email_record where id=#{id}")
     List<MailModel>  getEmailRecordById(@Param("id") Integer id);

    //根据标题查询邮件内容
    @Select(value="select * from email_record where subject  like  '%${subject}%'")
     List<MailModel> getEmailRecordBySubject(@Param("subject") String subject);

    //根据发送者修改邮件主题
    @Update(value = " update email_record set subject=#{subject} where sender=#{sender} ")
    int updateEmailRecordBySender(@Param("subject") String subject, @Param("sender") String sender);


    //根据发送者改变发送邮件的state
    @Update(value=" update email_record set status=#{status} where sender=#{sender}")
    int updateStatusBySender(@Param("status") String status  ,@Param("sender") String sender);

    //根据发送者删除邮件
    @Delete(value="delete from email_record where sender=#{sender}")
    int  deleteEmailRecordBySender(@Param("sender") String sender);

}

