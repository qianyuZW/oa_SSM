package org.ppcirgo.oa.service;

import org.ppcirgo.oa.beans.model.DailyModel;

import java.util.List;

public interface DailyService {
    int saveDailyRecord(DailyModel  dailyModel);

    String   getDailyById(Integer id);
}
