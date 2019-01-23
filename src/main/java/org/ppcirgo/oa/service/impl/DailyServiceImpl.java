
package org.ppcirgo.oa.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ppcirgo.oa.beans.model.DailyModel;
import org.ppcirgo.oa.mapper.DailyMapper;
import org.ppcirgo.oa.service.DailyService;
import org.ppcirgo.oa.utils.DateUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


@Transactional
@Service
@Slf4j
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyMapper dailyMapper;

    @Override
    public int saveDailyRecord(DailyModel dailyModel) {
        return dailyMapper.saveDailyRecord(dailyModel);
    }

    @Override
    public String getDailyById(Integer id) {
        return dailyMapper.getDailyById(id);
    }

    @Override
    public String getDailyByName(String employee_name) {
        return dailyMapper.getDailyByName(employee_name);
    }

    @Override
    public String getContent2DayByName(String name, String time) {
        if(StringUtils.isEmpty(time)){
            time=DateUtlis.getNowYMD();
        }
        log.info("someone is required Content：参数情况---name:"+name+"---time:"+time);
        return dailyMapper.getContent2DayByName(name,time);
    }

}

