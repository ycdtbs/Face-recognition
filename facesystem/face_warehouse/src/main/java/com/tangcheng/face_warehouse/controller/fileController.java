package com.tangcheng.face_warehouse.controller;

import com.tangcheng.face_warehouse.feign.faceFeignService;
import com.tangcheng.face_warehouse.model.adminEneity;
import com.tangcheng.face_warehouse.service.adminService;
import com.tangcheng.face_warehouse.service.fileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.Result;
import result.ResultCodeEnum;

import java.io.IOException;
import java.util.List;

@Api("文件操作相关操作")
@RestController
@RequestMapping("/file")
@CrossOrigin
public class fileController {
    @Autowired
    fileService fileService;
    @Autowired
    faceFeignService faceFeignService;
    @Autowired
    adminService adminService;
    @ApiOperation("获取到照片的base64")
    @PostMapping("/getimageBase64Code")
    public Result findAllAdmin(MultipartFile file) throws IOException {
        fileService.imageToBase64(file.getInputStream());
        return Result.ok();
    }

    @ApiOperation("加载引擎")
    @PostMapping("/registered_engine")
    public Result registeredEngine() throws IOException {
        faceFeignService.registeredEngine();
        return Result.ok();

    }

    @ApiOperation("加载人脸数据")
    @PostMapping("/loadface")
    public Result loadface() throws IOException {
        String endpoint = adminService.getConfigvalueByName("endpoint").getConfigValue();
        String accessKeyId = adminService.getConfigvalueByName("accessKeyId").getConfigValue();
        String accessKeySecret = adminService.getConfigvalueByName("accessKeySecret").getConfigValue();
        String bucket = adminService.getConfigvalueByName("bucket").getConfigValue();
        List<String> usernameList = adminService.findUsernameList();
        for (String s: usernameList
             ) {
            System.out.println(s+"人脸数据加载完成");
        }
        faceFeignService.loadface(endpoint,accessKeyId,accessKeySecret,bucket,usernameList);
        return Result.ok();

    }

}
