package com.example.springboot.controller;
//yzy
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    //文件上传接口
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam String clientNumber) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        com.example.springboot.entity.File dbFile = getFileByMd5(md5);
        //File dbFile = getFileByMd5(md5);
        if (dbFile != null) { // 文件已存在
            url = dbFile.getUrl();
            //由于文件已存在，所以删除刚才上传的重复文件
            uploadFile.delete();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "https://image.familystudy.cn/file/" + fileUUID;
        }
        // 存储数据库
        com.example.springboot.entity.File saveFile = new com.example.springboot.entity.File();
        //File saveFile = new File();
//        saveFile.setFileNumber(UUID.);
        String code = UUID.randomUUID().toString().substring(0, 8);
        saveFile.setFileNumber(code);
        saveFile.setSchoolClientNumber(clientNumber);
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return url;
    }

    //文件下载接口
    @GetMapping("/UUID/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @GetMapping("/id/{fileId}")
    public void downloadById(@PathVariable Integer fileId, HttpServletResponse response) throws IOException {
        com.example.springboot.entity.File file = fileMapper.selectById(fileId);
        String fileUUID = "";
        if (file != null) {
            String[] array = file.getUrl().split("/");
            fileUUID = array[array.length - 1];
        }
        System.out.print(fileUUID);
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    //通过文件的md5查询文件
    private com.example.springboot.entity.File getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<com.example.springboot.entity.File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<com.example.springboot.entity.File> fileList = fileMapper.selectList(queryWrapper);
        return fileList.size() == 0 ? null : fileList.get(0);
    }

//    @PostMapping("/update")
//    public Result update(@RequestBody com.example.springboot.entity.File file) {
//        return Result.success(fileMapper.updateById(file));
//    }

    @DeleteMapping("/{fileId}")
    public Result delete(@PathVariable Integer fileId) {
        com.example.springboot.entity.File file = fileMapper.selectById(fileId);
        file.setIsDelete(true);
        fileMapper.updateById(file);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<com.example.springboot.entity.File> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("file_id", ids);
        List<com.example.springboot.entity.File> files = fileMapper.selectList(queryWrapper);
        for (com.example.springboot.entity.File file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {

        QueryWrapper<com.example.springboot.entity.File> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderByDesc("file_id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
