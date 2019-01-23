package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.DailyModel;

import java.util.List;

public interface DailyService {
    int saveDailyRecord(DailyModel  dailyModel);

    String   getDailyById(Integer id);

    String   getDailyByName(String employee_name);

    String getContent2DayByName(String name,String time);
}

