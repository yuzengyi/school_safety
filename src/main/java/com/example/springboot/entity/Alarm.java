package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("alarm")
public class Alarm {
    @TableId(type= IdType.AUTO)
    private Integer alarmId;
    private String name;
    private Integer frequency;
}
