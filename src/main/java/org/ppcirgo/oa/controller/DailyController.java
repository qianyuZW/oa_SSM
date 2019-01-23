
package org.ppcirgo.oa.controller;



import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.beans.consts.MsgCode;
import org.ppcirgo.oa.beans.model.DailyModel;
import org.ppcirgo.oa.service.DailyService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.Text;
import java.util.List;


@RestController
public class DailyController {
@Autowired
private DailyService dailyService;

    @RequestMapping(value="/saveDailyRecord",method = RequestMethod.POST)
    public Object saveDailyRecord(

            @RequestParam(value="employee_name" ,required = true) String employee_name,
            @RequestParam(value="content",required = true) String content
            ){
        DailyModel dailyModel=new DailyModel();
        dailyModel.setEmployee_name(employee_name);
        dailyModel.setCreated_time(DateUtlis.currentTime((System.currentTimeMillis())));
        dailyModel.setContent(content);

        System.out.print("dailyModel========="+dailyModel);

        if(dailyService.saveDailyRecord(dailyModel)>0){
            return new AJAXResult(MsgCode.success);
        }else
            return  new AJAXResult(MsgCode.error);
        }

        @RequestMapping(value="/getDailyRecordById",method = RequestMethod.POST)
        public Object getDailyRecordById(
                @RequestParam(value="id",required = true)  Integer id
        ){
           String dailyModel=dailyService.getDailyById(id);

            //System.out.print("dailyModel=========="+dailyModel);
            if(dailyModel!=null){
                return  new AJAXResult(MsgCode.success);
            }
            else
                return  new AJAXResult(MsgCode.notexsit);
        }
        @RequestMapping(value="/getDailyByName",method = RequestMethod.POST)
    public Object getDailyByName(
            @RequestParam(value="employee_name" ,required = true)  String employee_name
        ){
           String dailyModel=dailyService.getDailyByName(employee_name);
           System.out.print("dailyModel===="+dailyModel);
            if(dailyModel!=null){
                return  new AJAXResult(MsgCode.success);
            }
            else
                return  new AJAXResult(MsgCode.notexsit);
        }
}

