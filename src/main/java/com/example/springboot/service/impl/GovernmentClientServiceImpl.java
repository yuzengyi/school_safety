package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.GovernmentClient;
import com.example.springboot.mapper.GovernmentClientMapper;
import com.example.springboot.service.IGovernmentClientService;
import org.springframework.stereotype.Service;

@Service
public class GovernmentClientServiceImpl extends ServiceImpl<GovernmentClientMapper, GovernmentClient> implements IGovernmentClientService {
}
