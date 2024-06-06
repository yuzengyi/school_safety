package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.SaftyStaff;
import com.example.springboot.mapper.SaftyStaffMapper;
import com.example.springboot.service.ISaftyStaffService;
import org.springframework.stereotype.Service;

@Service
public class SaftyStaffServiceImpl extends ServiceImpl<SaftyStaffMapper, SaftyStaff> implements ISaftyStaffService {
}
