package com.example.springboot.controller;
//yzy
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.annotation.MyLog;
import com.example.springboot.common.Result;
import com.example.springboot.entity.SaftyStaff;
import com.example.springboot.entity.SchoolClient;
import com.example.springboot.service.ISaftyStaffService;
import com.example.springboot.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/saftyStaff")
public class SaftyStaffController {

    @Resource
    private ISaftyStaffService saftyStaffService;


    @GetMapping("/area")
    public Result getStaffsByArea(@RequestParam String area) {
        List<SaftyStaff> list = saftyStaffService.list(
                new QueryWrapper<SaftyStaff>()
                        .eq("area", area)
        );
        return Result.success(list);
    }

    //新增或者更新
    @PostMapping
    public Result save(@RequestBody SaftyStaff saftyStaff){
        saftyStaffService.saveOrUpdate(saftyStaff);
        return Result.success();
    }

    @GetMapping("/username/{username}")
    public Result getUserInfo(@PathVariable String username) {
        SaftyStaff saftyStaff = saftyStaffService.getOne(
                new QueryWrapper<SaftyStaff>()
                        .eq("username", username)
        );
        return Result.success(saftyStaff);
    }

    @MyLog(value = "删除安保人员")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        saftyStaffService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        saftyStaffService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() { return Result.success(saftyStaffService.list());}

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(saftyStaffService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(required = false) String personName,
                           @RequestParam(required = false) String phone,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String address) {
        QueryWrapper<SaftyStaff> queryWrapper = new QueryWrapper<>();
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
        queryWrapper.orderByDesc("id");
        return Result.success(saftyStaffService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
