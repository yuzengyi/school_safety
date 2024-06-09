package com.example.springboot.mapper;
//yzy
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.DangerSituation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface DangerSituationMapper extends BaseMapper<DangerSituation> {
    @Select("SELECT school as name, COUNT(*) AS value FROM danger_situation GROUP BY school")
    List<Map<String, Object>> getSchoolCount();
    @Select("SELECT address as name, COUNT(*) AS value FROM danger_situation WHERE alarm_id = #{alarmId} GROUP BY address")
    List<Map<String, Object>> getAddressCount(@Param("alarmId") Integer alarmId);
    @Select("SELECT name, COUNT(*) AS value FROM ( " +
            "SELECT " +
            "CASE " +
            "    WHEN QUARTER(create_time) = 1 THEN '第一季度' " +
            "    WHEN QUARTER(create_time) = 2 THEN '第二季度' " +
            "    WHEN QUARTER(create_time) = 3 THEN '第三季度' " +
            "    WHEN QUARTER(create_time) = 4 THEN '第四季度' " +
            "END AS name " +
            "FROM danger_situation " +
            "WHERE alarm_id = #{alarmId} " +
            ") AS subquery " +
            "GROUP BY name " +
            "ORDER BY FIELD(name, '第一季度', '第二季度', '第三季度', '第四季度')")
    List<Map<String, Object>> getTimeCount(@Param("alarmId") Integer alarmId);
    @Select("SELECT " +
            "MONTH(create_time) AS month, " +
            "COUNT(*) AS value " +
            "FROM danger_situation " +
            "WHERE alarm_id = #{alarmId} " +
            "GROUP BY MONTH(create_time) " +
            "ORDER BY MONTH(create_time)")
    List<Map<String, Object>> getMonthlyCount(@Param("alarmId") Integer alarmId);
    @Select("SELECT (DAYOFWEEK(create_time) + 5) % 7 + 1 AS day, COUNT(*) AS value " +
            "FROM danger_situation " +
            "WHERE YEARWEEK(create_time, 1) = YEARWEEK(CURDATE(), 1) AND alarm_id = #{alarmId} " +
            "GROUP BY day " +
            "ORDER BY day")
    List<Map<String, Object>> getWeeklyAlarmCountByAlarmId(@Param("alarmId") Integer alarmId);

}