package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("school_staff")
public class SchoolStaff {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String schoolClientNumber;
    private String staffNumber;
}
