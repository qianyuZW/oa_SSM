
package org.ppcirgo.oa.service.impl;

import org.ppcirgo.oa.beans.model.DailyModel;
import org.ppcirgo.oa.mapper.DailyMapper;
import org.ppcirgo.oa.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
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
}

