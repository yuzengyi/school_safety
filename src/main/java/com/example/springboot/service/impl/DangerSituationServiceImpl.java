package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.DangerSituation;
import com.example.springboot.mapper.DangerSituationMapper;
import com.example.springboot.service.IDangerSituationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DangerSituationServiceImpl extends ServiceImpl<DangerSituationMapper, DangerSituation> implements IDangerSituationService {
    private DangerSituationMapper dangerSituationMapper;

    public DangerSituationServiceImpl(DangerSituationMapper dangerSituationMapper) {
        this.dangerSituationMapper = dangerSituationMapper;
    }

    public List<Map<String, Object>> getSchoolCount() {
        return dangerSituationMapper.getSchoolCount();
    }
    public List<Map<String, Object>> getAddressCount() {
        return dangerSituationMapper.getAddressCount();
    }
    public List<Map<String, Object>> getTimeCount() {
        return dangerSituationMapper.getTimeCount();
    }
    @Override
    public List<Map<String, Object>> getMonthlyData() {
        List<Map<String, Object>> monthlyCounts = dangerSituationMapper.getMonthlyCount();
        List<Map<String, Object>> result = new ArrayList<>();

        int[] data = new int[12];
        for (Map<String, Object> count : monthlyCounts) {
            int month = (int) count.get("month");
            int value = ((Long) count.get("value")).intValue();
            data[month - 1] = value; // months are 1-based, array is 0-based
        }

        for (int i = 0; i < data.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("month", i + 1);
            map.put("value", data[i]);
            result.add(map);
        }

        return result;
    }
    @Override
    public List<Map<String, Object>> getWeeklyAlarmCount() {
        return dangerSituationMapper.getWeeklyAlarmCount();
    }
}
