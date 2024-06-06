package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_log")
public class Log {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String staffNumber;
    private String username;
    private String operation;
    private String method;
    private String params;
    private String ip;
    private String createDate;
    private Boolean isDelete;
}
