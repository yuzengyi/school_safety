package com.example.springboot;
//yzy
import com.example.springboot.controller.*;
import com.example.springboot.controller.dto.LoginDto;
import com.example.springboot.entity.GovernmentClient;
import com.example.springboot.entity.SaftyStaff;
import com.example.springboot.entity.SchoolClient;
import com.example.springboot.service.impl.GovernmentClientServiceImpl;
import com.example.springboot.service.impl.SaftyStaffServiceImpl;
import com.example.springboot.service.impl.SchoolClientServiceImpl;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringbootApplicationTests {
    @Test
    public void contextLoads() {
//        TotalSituationController.test();
    }

    @Autowired
    private LoginController loginController;


    @Test
    public void testLogin_valid () throws Exception {
        String username = "hxy";
        String password = "12345";
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        Object res = loginController.login(3, loginDto);
        System.out.println(res);
        assertNotNull(res);
    }

    @Test
    public void testLogin_invalid () {
        String username = "ccc";
        String password = "1235";
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        Object res = loginController.login(3, loginDto);
        System.out.println(res);
        assertNotNull(res);
    }

    @Test
    public void testRegisterValid () {
        String username = "";
        String password = "123456";
        String rePassword = "123456";
        if (!password.equals(rePassword)) {
            return;
        }
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        Object res =  loginController.register(3, loginDto);
        System.out.println(res);
        assertNotNull(res);

    }
    @Test
    public void testRegisterInvalid () {
        String username = "";
        String password = "123456";
        String rePassword = "123";
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        Object res = loginController.register(3, loginDto);
        System.out.println(res);
        assertNotNull(res);
    }


    @Autowired
    private SchoolClientController schoolClientController;

    @Autowired
    private SchoolClientServiceImpl schoolClientService;

    @Autowired
    private GovernmentClientController governmentClientController;

    @Autowired
    private GovernmentClientServiceImpl governmentClientService;

    @Autowired
    private SaftyStaffController saftyStaffController;

    @Autowired
    private SaftyStaffServiceImpl saftyStaffService;

    @Test
    public void testChangeInfoValid() {
        SchoolClient schoolClient = new SchoolClient();
//        schoolClient =

    }

    @Autowired
    private AlarmController alarmController;

    @Test
    public void testSetAlarmFrequencyValid() {
        Map<String, Integer> map = new HashMap<>();
        map.put("alarmFrequency", 1);
        Object res = alarmController.updateAlarmFrequency(1,  map);
        assertNotNull(res);
    }

    @Test
    public void testSetAlarmFrequencyInValid() {
        Map<String, Integer> map = new HashMap<>();
        map.put("alarmFrequency", 1);
        Object res = alarmController.updateAlarmFrequency(-1,  map);
        assertNotNull(res);
    }




}
