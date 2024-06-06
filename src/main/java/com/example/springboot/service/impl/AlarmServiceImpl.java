package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Alarm;
import com.example.springboot.mapper.AlarmMapper;
import com.example.springboot.service.IAlarmService;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements IAlarmService {

}
