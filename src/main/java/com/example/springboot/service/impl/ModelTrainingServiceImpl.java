package com.example.springboot.service.impl;
//yzy
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.ModelTraining;
import com.example.springboot.mapper.ModelTrainingMapper;
import com.example.springboot.service.IModelTrainingService;
import org.springframework.stereotype.Service;

@Service
public class ModelTrainingServiceImpl extends ServiceImpl<ModelTrainingMapper, ModelTraining> implements IModelTrainingService {

}
