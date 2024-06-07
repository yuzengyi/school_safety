package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.DangerSituation;
import com.example.springboot.mapper.DangerSituationMapper;
import com.example.springboot.service.IDangerSituationService;
import org.springframework.stereotype.Service;

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
}
