package com.example.springboot.controller;
//yzy
import com.example.springboot.common.Result;
import com.example.springboot.entity.SchoolStaff;
import com.example.springboot.service.ISchoolStaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/schoolStaff")
public class SchoolStaffController {

    @Resource
    private ISchoolStaffService schoolStaffService;

    @PostMapping
    public Result save(@RequestBody SchoolStaff schoolStaff){
        schoolStaffService.saveOrUpdate(schoolStaff);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        schoolStaffService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        schoolStaffService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll(){
        return Result.success(schoolStaffService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(schoolStaffService.getById(id));
    }

}
