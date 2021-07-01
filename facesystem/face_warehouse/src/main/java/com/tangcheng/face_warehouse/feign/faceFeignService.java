package com.tangcheng.face_warehouse.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import result.Result;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
@FeignClient(value = "FACE-CORN")  //找 “CLOUD-PAYMENT-SERVICE” 微服务地址
public interface faceFeignService {
    @PutMapping("registered_engine")
    public void registeredEngine();

    @PutMapping("loadface")
    public void loadface(@RequestParam(value = "endpoint") String endpoint,
                         @RequestParam(value = "accessKeyId") String accessKeyId,
                         @RequestParam(value = "accessKeySecret") String accessKeySecret,
                         @RequestParam(value = "bucketname") String bucketname,
                         @RequestParam(value = "userList") List<String> userList);

    @PutMapping("/delface")
    public void delface(
                         @RequestParam(value = "userList") List<String> userList);
    @PostMapping("/facesearch")
    public Result facesearch(@RequestParam(value = "imagebase64") String imagebase64,
                             @RequestParam(value = "userList") List<String> userList);
    @GetMapping("/getsystemState")
    public String getsystemState();
    @GetMapping("/getredisfaceList")
    public Set<String> getredisfaceList();
}
