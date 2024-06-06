package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("safty_staff")
public class SaftyStaff {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String staffNumber;
    private String username;
    private String nickname;
    private String password;
    private String phone;
    private String grade;
    private String createTime;
    private String role;
    private String email;
    private String address;
    private String area;
    private String avatarUrl;
    @TableLogic
    private Integer isDeleted;
}
