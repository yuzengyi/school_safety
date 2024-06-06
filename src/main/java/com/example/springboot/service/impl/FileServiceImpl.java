package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.File;
import com.example.springboot.mapper.FileMapper;
import com.example.springboot.service.IFileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
