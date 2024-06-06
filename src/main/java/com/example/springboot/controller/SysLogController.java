package com.example.springboot.controller;
//yzy
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.mapper.SysLogMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.example.springboot.service.ISysLogService;
import com.example.springboot.entity.SysLog;

import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
@RequestMapping("/sysLog")
public class SysLogController {

    @Resource
    private ISysLogService sysLogService;

    @Resource
    private SysLogMapper sysLogMapper;

    //新增或者更新
    @PostMapping
    public Result save(@RequestBody SysLog sysLog){  //@RequestBody用来接收前端的数据，再传递给后端
        sysLogService.saveOrUpdate(sysLog);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        SysLog sysLog = sysLogMapper.selectById(id);
        sysLog.setIsDelete(true);
        sysLogMapper.updateById(sysLog);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<SysLog> sysLogs = sysLogMapper.selectList(queryWrapper);
        for (SysLog sysLog : sysLogs) {
            sysLog.setIsDelete(true);
            sysLogMapper.updateById(sysLog);
        }
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(sysLogService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(sysLogService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String operation,
                           @RequestParam(defaultValue = "") String createDate) {

        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.eq("is_delete", false);
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }
        if(!"".equals(operation)){
            queryWrapper.like("operation", operation);
        }
        if(!"".equals(createDate)){
            queryWrapper.like("create_date", createDate);
        }
        return Result.success(sysLogService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<SysLog> list = sysLogService.list();
        //通过工具类创建writer 写出到磁盘路径
        // ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息。xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username", "操作人员");
        writer.addHeaderAlias("operation", "操作");
        writer.addHeaderAlias("method", "方法名");
        writer.addHeaderAlias("params", "参数");
        writer.addHeaderAlias("ip", "ip地址");
        writer.addHeaderAlias("create_date", "操作时间");

        // 一次性写出List内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        //设置浏览器相应的格式
        response.setContentType("application/vnd.openxmlfornats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("日志信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

}


