package com.example.springboot.controller.dto;
//yzy
import lombok.Data;

import java.util.List;

/**
 * 接收前端登录请求的参数
 */
@Data
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String role;
}
