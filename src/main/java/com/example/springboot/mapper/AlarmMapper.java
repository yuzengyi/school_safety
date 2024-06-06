package com.example.springboot.mapper;
//yzy
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Alarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {
}
