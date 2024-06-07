package com.example.springboot.controller;
//yzy
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Alarm;
import com.example.springboot.entity.DangerSituation;
import com.example.springboot.entity.SchoolClient;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.service.IAlarmService;
import com.example.springboot.service.IDangerSituationService;
import com.example.springboot.utils.StringUtil;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/dangerSituation")
public class DangerSituationController {
    @Resource
    private IDangerSituationService dangerSituationService;
    @Resource
    private IAlarmService alarmService;
    //新增或者更新
    @PostMapping
    public Result save(@RequestBody DangerSituation dangerSituation) {
        dangerSituationService.saveOrUpdate(dangerSituation);
        return Result.success();
    }
    @DeleteMapping("/{dangerId}")
    public Result delete(@PathVariable Integer dangerId) {
        dangerSituationService.removeById(dangerId);
        return Result.success();
    }
    @GetMapping("/reduce/{number}")
    public Result findReduceOne(@PathVariable Integer number) {
        QueryWrapper<DangerSituation> queryWrapper = new QueryWrapper<>();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -number);
        queryWrapper.like("create_time", dateFormat.format(cal.getTime()));
        return Result.success(dangerSituationService.count(queryWrapper));
    }

    @GetMapping("/count/{alarmId}")
    public Result getCount(@PathVariable Integer alarmId) {
           List<Map<String, Object>> res = dangerSituationService.getSchoolCount();
           return Result.success(res);
    }

    @GetMapping
    public Result findAll() {
        return Result.success(dangerSituationService.list());
    }
    @GetMapping("/total")
    public Result countTotal() {
        Integer countTotal = dangerSituationService.count();
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("count", countTotal);
        return Result.success(resultMap);
    }
    @GetMapping("/total/isSolve")
    public Result countSolvedTotal() {
        Integer countTotal = dangerSituationService.count(
                new QueryWrapper<DangerSituation>()
                        .eq("is_solved", 1)
        );
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("count", countTotal);
        return Result.success(resultMap);
    }
    @GetMapping("/total/isNotSolve")
    public Result countNotSolvedTotal() {
        Integer countTotal = dangerSituationService.count(
                new QueryWrapper<DangerSituation>()
                        .eq("is_solved", 0)
        );
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("count", countTotal);
        return Result.success(resultMap);
    }
    @GetMapping("/appeal/total")
    public Result countAppealTotal() {
        Integer countTotal = dangerSituationService.count(
                new QueryWrapper<DangerSituation>()
                        .eq("appeal_state", 1)
        );
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("count", countTotal);
        return Result.success(resultMap);
    }
    @GetMapping("/total/{alarmId}")
    public Result countByAlarmId(@PathVariable Integer alarmId) {
        Integer count = dangerSituationService.count(
                new QueryWrapper<DangerSituation>()
                        .eq("alarm_id", alarmId)
        );
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("count", count);
        System.out.println(count);
        return Result.success(resultMap);
    }
    @GetMapping("/appeal/page")
    public Result findAppealByPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String school,
                                     @RequestParam(required = false) String createTime,
                                     @RequestParam(required = false) String address,
                                     @RequestParam(required = false) String situation,
                                     @RequestParam(required = false) String appellant) {
        QueryWrapper<DangerSituation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school", school);
        if (!StringUtil.isAbsent(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        if (!StringUtil.isAbsent(address)) {
            queryWrapper.like("address", address);
        }
        if (!StringUtil.isAbsent(situation)) {
            queryWrapper.like("situation", situation);
        }
        if (!StringUtil.isAbsent(appellant)) {
            queryWrapper.like("appellant", appellant);
        }
        queryWrapper.eq("appeal_state", 1);
        return Result.success(dangerSituationService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }
    @PostMapping("/appeal/approve/{dangerId}")
    public Result approveAppeal(@PathVariable Integer dangerId) {
        DangerSituation dangerSituation = dangerSituationService.getById(dangerId);
        if (dangerSituation.getAppealState() == 1) {
            dangerSituation.setAppealState(2);
            dangerSituationService.updateById(dangerSituation);
            return Result.success();
        } else {
            throw new ServiceException(Constants.CODE_400, "已经同意");
        }
    }
    @GetMapping("/appeal/solved/page")
    public Result findSolvedAppealByPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String school,
                                     @RequestParam(required = false) String createTime,
                                     @RequestParam(required = false) String address,
                                     @RequestParam(required = false) String situation,
                                     @RequestParam(required = false) String person) {
        QueryWrapper<DangerSituation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school", school);
        if (!StringUtil.isAbsent(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        if (!StringUtil.isAbsent(address)) {
            queryWrapper.like("address", address);
        }
        if (!StringUtil.isAbsent(situation)) {
            queryWrapper.like("situation", situation);
        }
        if (!StringUtil.isAbsent(person)) {
            queryWrapper.like("solve_person", person);
        }
        queryWrapper.eq("appeal_state", 2);
        return Result.success(dangerSituationService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }
    @GetMapping("/solved/page")
    public Result findSolvedByPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                                   @RequestParam String school,
                              @RequestParam(required = false) String createTime,
                              @RequestParam(required = false) String address,
                              @RequestParam(required = false) String situation,
                              @RequestParam(required = false) Integer alarmId,
                              @RequestParam(required = false) String camera) {
        QueryWrapper<DangerSituation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_solved", 1);
        queryWrapper.eq("school", school);
        if (!StringUtil.isAbsent(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        if (!StringUtil.isAbsent(address)) {
            queryWrapper.like("address", address);
        }
        if (alarmId != null) {
            queryWrapper.eq("alarm_id", alarmId);
        }
        if (!StringUtil.isAbsent(situation)) {
            Integer id = alarmService.getOne(
                    new QueryWrapper<Alarm>()
                            .like("name", situation)
            ).getAlarmId();
            if (null != id) {
                queryWrapper.eq("alarm_id", id);
            }
        }
        if (!StringUtil.isAbsent(camera)) {
            queryWrapper.like("camera", camera);
        }
        return Result.success(dangerSituationService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }
    @PostMapping("/solve/{dangerId}")
    public Result solveById(@PathVariable Integer dangerId,
                            @RequestBody Map<String, String> map) {
        DangerSituation dangerSituation = dangerSituationService.getById(dangerId);
        dangerSituation.setIsSolved(true);
        dangerSituation.setSolvePerson(map.get("name"));
        dangerSituationService.updateById(dangerSituation);
        return Result.success();
    }
    @GetMapping("/page")
    public Result findByPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam String school,
                             @RequestParam(required = false) String createTime,
                             @RequestParam(required = false) String address,
                             @RequestParam(required = false) String situation,
                             @RequestParam(required = false) String camera) {
        QueryWrapper<DangerSituation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school", school);
        if (!StringUtil.isAbsent(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        if (!StringUtil.isAbsent(address)) {
            queryWrapper.like("address", address);
        }
        if (!StringUtil.isAbsent(situation)) {
            queryWrapper.like("situation", situation);
        }
        if (!StringUtil.isAbsent(camera)) {
            queryWrapper.like("camera", camera);
        }
        return Result.success(dangerSituationService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }
    @GetMapping("/unsolved/page")
    public Result findUnsolvedByPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize, @RequestParam String school,
                             @RequestParam(required = false) String createTime,
                             @RequestParam(required = false) String address,
                             @RequestParam(required = false) String situation,
                             @RequestParam(required = false) String camera) {
        QueryWrapper<DangerSituation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school", school);
        queryWrapper.eq("is_solved", 0);
        if (!StringUtil.isAbsent(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        if (!StringUtil.isAbsent(address)) {
            queryWrapper.like("address", address);
        }
        if (!StringUtil.isAbsent(situation)) {
            queryWrapper.like("situation", situation);
        }
        if (!StringUtil.isAbsent(camera)) {
            queryWrapper.like("camera", camera);
        }
        return Result.success(dangerSituationService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }
    @GetMapping("/{dangerId}")
    public Result findOne(@PathVariable Integer dangerId) {
        return Result.success(dangerSituationService.getById(dangerId));
    }
}
