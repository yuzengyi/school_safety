package com.example.springboot.controller;
//yzy
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDto;
import com.example.springboot.controller.dto.UserDto;
import com.example.springboot.controller.dto.UserPasswordDto;
import com.example.springboot.entity.GovernmentClient;
import com.example.springboot.entity.SaftyStaff;
import com.example.springboot.entity.SchoolClient;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.service.impl.GovernmentClientServiceImpl;
import com.example.springboot.service.impl.SaftyStaffServiceImpl;
import com.example.springboot.service.impl.SchoolClientServiceImpl;
import com.example.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class LoginController {
    @Resource
    private SchoolClientServiceImpl schoolClientService;
    @Resource
    private GovernmentClientServiceImpl governmentClientService;
    @Resource
    private SaftyStaffServiceImpl saftyStaffService;

    @PostMapping("/register/{role}")
    public Result register(@PathVariable Integer role, @RequestBody LoginDto registerDto) {
        if (Objects.equals(registerDto.getUsername(), "") ||
                Objects.equals(registerDto.getPassword(), ""))
        {throw new ServiceException(Constants.CODE_400, "用户名或密码为空");}
        if(role == 1) {

            SchoolClient schoolClient = new SchoolClient();
            if (null != schoolClientService.getOne(
                    new QueryWrapper<SchoolClient>()
                            .eq("client_number", registerDto.getUsername())
            )) {
                throw new ServiceException(Constants.CODE_400, "已存在该用户");
            }
            schoolClient.setClientNumber(registerDto.getUsername());
            schoolClient.setPassword(registerDto.getPassword());
            schoolClientService.save(schoolClient);
            return Result.success();
        } else if(role == 2) {
            GovernmentClient governmentClient = new GovernmentClient();
            if (null != governmentClientService.getOne(
                    new QueryWrapper<GovernmentClient>()
                            .eq("client_number", registerDto.getUsername())
            )) {
                throw new ServiceException(Constants.CODE_400, "已存在该用户");
            }
            governmentClient.setClientNumber(registerDto.getUsername());
            governmentClient.setPassword(registerDto.getPassword());
            governmentClientService.save(governmentClient);
            return Result.success();
        } else if (role == 3) {
            SaftyStaff saftyStaff = new SaftyStaff();
            if (null != saftyStaffService.getOne(
                    new QueryWrapper<SaftyStaff>()
                            .eq("username", registerDto.getUsername())
            )) {
                throw new ServiceException(Constants.CODE_400, "已存在该用户");
            }
            saftyStaff.setUsername(registerDto.getUsername());
            saftyStaff.setPassword(registerDto.getPassword());
            saftyStaffService.save(saftyStaff);
            return Result.success();
        } else {
            throw new ServiceException(Constants.CODE_400, "角色选择错误");
        }
    }


//    @GetMapping("/username/{username}")

    @PostMapping("/password/{role}")
    public Result changePwd(@PathVariable Integer role, @RequestBody UserPasswordDto userPasswordDto) {
        if (role == 1) {
            SchoolClient client = schoolClientService.getOne(
                    new QueryWrapper<SchoolClient>()
                            .eq("client_number", userPasswordDto.getUsername())
                            .eq("password", userPasswordDto.getPassword())
            );
            if (null != client) {
                client.setPassword(userPasswordDto.getNewPassword());
                schoolClientService.update(client, new QueryWrapper<SchoolClient>()
                        .eq("client_number", userPasswordDto.getUsername()));
                return Result.success();
            } else {
                throw new ServiceException(Constants.CODE_400, "修改失败");
            }
        } else if (role == 2) {
            GovernmentClient client = governmentClientService.getOne(
                    new QueryWrapper<GovernmentClient>()
                            .eq("client_number", userPasswordDto.getUsername())
                            .eq("password", userPasswordDto.getPassword())
            );
            if (null != client) {
                client.setPassword(userPasswordDto.getNewPassword());
                governmentClientService.update(client, new QueryWrapper<GovernmentClient>()
                        .eq("client_number", userPasswordDto.getUsername()));
                return Result.success();
            } else {
                throw new ServiceException(Constants.CODE_400, "修改失败");
            }
        } else if (role == 3) {
            SaftyStaff client = saftyStaffService.getOne(
                    new QueryWrapper<SaftyStaff>()
                            .eq("username", userPasswordDto.getUsername())
                            .eq("password", userPasswordDto.getPassword())
            );
            if (null != client) {
                client.setPassword(userPasswordDto.getNewPassword());
                saftyStaffService.update(client, new QueryWrapper<SaftyStaff>()
                        .eq("username", userPasswordDto.getUsername()));
                return Result.success();
            } else {
                throw new ServiceException(Constants.CODE_400, "修改失败");
            }
        } else {
            return Result.error();
        }
    }

    @PostMapping("/login/{role}")
    public Result login(@PathVariable Integer role, @RequestBody LoginDto loginDto) {
        UserDto userDto = new UserDto();
        if (role == 1) {
            SchoolClient client = schoolClientService.getOne(
                    new QueryWrapper<SchoolClient>()
                            .eq("client_number", loginDto.getUsername())
                            .eq("password", loginDto.getPassword())
            );
            if (null != client) {
                BeanUtil.copyProperties(client, userDto, true);
                userDto.setUsername(client.getClientNumber());
                String token = TokenUtils.genToken(client.getId().toString(), client.getPassword());
                userDto.setToken(token);
                return Result.success(userDto);
            } else {
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }
        } else if (role == 2) {
            GovernmentClient client = governmentClientService.getOne(
                    new QueryWrapper<GovernmentClient>()
                            .eq("client_number", loginDto.getUsername())
                            .eq("password", loginDto.getPassword())
            );
            if (null != client) {
                BeanUtil.copyProperties(client, userDto, true);
                userDto.setUsername(client.getClientNumber());
                String token = TokenUtils.genToken(client.getId().toString(), client.getPassword());
                userDto.setToken(token);
                return Result.success(userDto);
            } else {
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }
        } else if (role == 3) {
            SaftyStaff client = saftyStaffService.getOne(
                    new QueryWrapper<SaftyStaff>()
                            .eq("username", loginDto.getUsername())
                            .eq("password", loginDto.getPassword())
            );
            if (null != client) {
                BeanUtil.copyProperties(client, userDto, true);

                String token = TokenUtils.genToken(client.getId().toString(), client.getPassword());
                userDto.setToken(token);
                return Result.success(userDto);
            } else {
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }
        } else {
            return Result.error();
        }
    }
}
