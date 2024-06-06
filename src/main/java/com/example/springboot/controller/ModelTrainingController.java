package com.example.springboot.controller;
//yzy
import com.example.springboot.common.Result;
import com.example.springboot.entity.ModelTraining;
import com.example.springboot.service.IModelTrainingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/modelTraining")
public class ModelTrainingController {

    @Resource
    private IModelTrainingService modelTrainingService;

    @PostMapping
    public Result save(@RequestBody ModelTraining modelTraining) {
        modelTrainingService.saveOrUpdate(modelTraining);
        return Result.success();
    }

    @DeleteMapping("/{modelId}")
    public Result delete(@PathVariable Integer modelId){
        modelTrainingService.removeById(modelId);
        return Result.success();
    }

    @GetMapping
    public Result findAll(){
        return Result.success(modelTrainingService.list());
    }

    @GetMapping("/{modelId}")
    public Result findOne(@PathVariable Integer modelId) {
        return Result.success(modelTrainingService.getById(modelId));
    }
}
