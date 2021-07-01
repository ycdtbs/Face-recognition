package com.tangcheng.face_warehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;
import com.tangcheng.face_warehouse.service.fileService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import utils.Base64Util;

import java.io.*;

@Service
public class fileServiceImpl implements fileService {
    @Override
    public String imageToBase64(InputStream inputStream) throws IOException {
        byte[] bytes = Base64Util.toByteArray(inputStream);
        String encode = Base64Util.encode(bytes);
        return encode;
    }




}
