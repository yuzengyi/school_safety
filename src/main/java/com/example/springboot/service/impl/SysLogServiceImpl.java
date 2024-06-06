package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.SysLog;
import com.example.springboot.mapper.SysLogMapper;
import com.example.springboot.service.ISysLogService;
import org.springframework.stereotype.Service;
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
