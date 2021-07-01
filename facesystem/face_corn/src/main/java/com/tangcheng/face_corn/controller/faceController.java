package com.tangcheng.face_corn.controller;

import com.tangcheng.face_corn.service.faceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import result.Result;
import utils.Base64Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class faceController {
    @Value("${face.dllPath}")
    private String dllPath;
    @Value("${face.appId}")
    String appId;
    @Value("${face.sdkKey}")
    String sdkKey;
    @Autowired
    faceService faceService;
    @ApiOperation("激活引擎")
    @PutMapping("/registered_engine")
    public void registeredEngine(){
        faceService.registeredEngine(appId,sdkKey,dllPath);
        System.out.println("激活结束");

    }
    @PutMapping("/loadface")
    public void loadface(@RequestParam(value = "endpoint") String endpoint,
                         @RequestParam(value = "accessKeyId") String accessKeyId,
                         @RequestParam(value = "accessKeySecret") String accessKeySecret,
                         @RequestParam(value = "bucketname") String bucketname,
                         @RequestParam(value = "userList") List<String> userList) throws IOException {

        faceService.loadFace( endpoint,  accessKeyId,  accessKeySecret,  bucketname,userList);

    }
    @PostMapping("/facesearch")
    public Result facesearch(@RequestParam(value = "imagebase64") String imagebase64,
                             @RequestParam(value = "userList") List<String> userList) throws IOException {

        byte[] bytes = Base64Util.base64ToImgByteArray(imagebase64);
        InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        String facesearch = faceService.facesearch(byteArrayInputStream, userList);
        return Result.ok().data("username",facesearch);
    }
    @GetMapping("/getsystemState")
    public String getsystemState(){
        String s = faceService.engineState();
        return s;
    }
    @GetMapping("/getredisfaceList")
    public Set<String> getredisfaceList(){
        Set<String> strings = faceService.getfaceList();
        return strings;
    }
    @PutMapping("/delface")
    public void delface(
                        @RequestParam(value = "userList") List<String> userList) throws IOException {
        faceService.delface(userList);
    }
}
