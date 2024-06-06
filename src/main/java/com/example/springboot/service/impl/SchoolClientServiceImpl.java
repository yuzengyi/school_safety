package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.SchoolClient;
import com.example.springboot.mapper.SchoolClientMapper;
import com.example.springboot.service.ISchoolClientService;
import org.springframework.stereotype.Service;

@Service
public class SchoolClientServiceImpl extends ServiceImpl<SchoolClientMapper, SchoolClient> implements ISchoolClientService {
}
