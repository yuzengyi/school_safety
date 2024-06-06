package com.example.springboot.controller;
//yzy
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.SchoolClient;
import com.example.springboot.service.ISchoolClientService;
import com.example.springboot.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/schoolClient")
public class SchoolClientController {
    @Resource
    private ISchoolClientService schoolClientService;

    //新增或者更新
    @PostMapping
    public Result save(@RequestBody SchoolClient schoolClient){
        schoolClientService.saveOrUpdate(schoolClient);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        schoolClientService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        schoolClientService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(schoolClientService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(schoolClientService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(required = false) String schoolName,
                               @RequestParam(required = false) String personName,
                           @RequestParam(required = false) String phone,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String address) {
        QueryWrapper<SchoolClient> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isAbsent(personName)) {
            queryWrapper.like("person_name", personName);
        }
        if (!StringUtil.isAbsent(phone)) {
            queryWrapper.like("phone", phone);
        }
        if (!StringUtil.isAbsent(email)) {
            queryWrapper.like("email", email);
        }
        if (!StringUtil.isAbsent(address)) {
            queryWrapper.like("address", address);
        }
        if (!StringUtil.isAbsent(schoolName)) {
            queryWrapper.like("school_name", schoolName);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(schoolClientService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
