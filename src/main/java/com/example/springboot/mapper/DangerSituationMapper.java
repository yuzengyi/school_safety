package com.example.springboot.mapper;
//yzy
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.DangerSituation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DangerSituationMapper extends BaseMapper<DangerSituation> {
    @Select("SELECT school as name, COUNT(*) AS value FROM danger_situation GROUP BY school")
    List<Map<String, Object>> getSchoolCount();
}
