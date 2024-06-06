package com.example.springboot.controller;
//yzy
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Alarm;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.service.IAlarmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @Resource
    private IAlarmService alarmService;

    //新增或者更新
    @PostMapping
    public Result save(@RequestBody Alarm alarm){
        alarmService.saveOrUpdate(alarm);
        return Result.success();
    }

    @DeleteMapping("/{alarmId}")
    public Result deleteAlarm(@PathVariable Integer alarmId) {
        alarmService.removeById(alarmId);
        return Result.success();
    }

    @PostMapping("/update/{alarmId}")
    public Result updateAlarmFrequency(@PathVariable Integer alarmId,
                                       @RequestBody Map<String, Integer> map) {
        Alarm alarm = alarmService.getById(alarmId);
        Integer alarmFrequency = map.get("alarmFrequency");
        if (alarmFrequency < 0) {
            throw new ServiceException(Constants.CODE_400, "频率不能小于0");
        }
        alarm.setFrequency(map.get("alarmFrequency"));
        alarmService.updateById(alarm);
        return Result.success();
    }

    @GetMapping
    public Result findAll() { return Result.success(alarmService.list()); }

    @GetMapping("/{alarmId}")
    public Result findOne(@PathVariable Integer alarmId) {
        return Result.success(alarmService.getById(alarmId));
    }

}