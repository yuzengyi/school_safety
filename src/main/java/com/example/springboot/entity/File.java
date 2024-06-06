package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("file")
public class File {
    @TableId(type= IdType.AUTO)
    private Integer fileId;
    private String fileNumber;
    private String schoolClientNumber;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String md5;
    private Boolean isDelete;
    private Boolean enable;
}
