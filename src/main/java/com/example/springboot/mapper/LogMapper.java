package com.example.springboot.mapper;
//yzy
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
}
