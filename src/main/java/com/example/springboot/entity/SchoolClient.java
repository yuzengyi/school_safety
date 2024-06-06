package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("school_client")
public class SchoolClient {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String clientNumber;
    private String schoolName;
    private String personName;
    private String password;
    private String phone;
    private String role;
    private String nickname;
    private String email;
    private String address;
    private String avatarUrl;
    @TableLogic
    private Integer isDeleted;
}
