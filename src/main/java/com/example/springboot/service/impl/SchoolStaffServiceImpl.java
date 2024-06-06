package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.SchoolStaff;
import com.example.springboot.mapper.SchoolStaffMapper;
import com.example.springboot.service.ISchoolStaffService;
import org.springframework.stereotype.Service;

@Service
public class SchoolStaffServiceImpl extends ServiceImpl<SchoolStaffMapper, SchoolStaff> implements ISchoolStaffService {
}
