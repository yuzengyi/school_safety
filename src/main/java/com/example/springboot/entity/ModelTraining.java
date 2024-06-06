package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("model_training")
public class ModelTraining {
    @TableId(type= IdType.AUTO)
    private Integer modelId;
    private Integer alarmId;
    private String desc;
    private String method;
    private String dataset;
    private String model;
    private String proportion;
}
