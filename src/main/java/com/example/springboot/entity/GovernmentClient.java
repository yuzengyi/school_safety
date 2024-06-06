package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("government_client")
public class GovernmentClient {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String clientNumber;
    private String role;
    private String nickname;
    private String phone;
    private String email;
    private String address;
    private String avatarUrl;
    private String password;
    @TableLogic
    private Integer isDeleted;
}
