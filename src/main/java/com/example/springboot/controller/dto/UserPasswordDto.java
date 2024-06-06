package com.example.springboot.controller.dto;
//yzy
import lombok.Data;

@Data
public class UserPasswordDto {
    private String username;
    private String password;
    private String newPassword;
}
