package com.example.springboot.service;
//yzy
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.DangerSituation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface IDangerSituationService extends IService<DangerSituation> {
    List<Map<String, Object>> getSchoolCount();
    List<Map<String, Object>> getAddressCount();
    List<Map<String, Object>> getTimeCount();
    List<Map<String, Object>> getMonthlyData();
    List<Map<String, Object>> getWeeklyAlarmCount();

}
