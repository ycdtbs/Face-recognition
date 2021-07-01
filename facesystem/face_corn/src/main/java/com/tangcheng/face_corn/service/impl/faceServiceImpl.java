package com.tangcheng.face_corn.service.impl;

import com.alibaba.fastjson.JSON;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.tangcheng.face_corn.faceModel.faceEngineModel;
import com.tangcheng.face_corn.service.faceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.Base64Util;
import utils.OssUtil;

import static com.arcsoft.face.toolkit.ImageFactory.getGrayData;
import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class faceServiceImpl implements faceService {
    @Autowired

    private RedisTemplate redisTemplate;
    @Override
    public void registeredEngine(String appId,String sdkKey,String dllPath) {
        System.out.println("appId" + appId);
        System.out.println("sdkKey" + sdkKey);
        System.out.println("dllPath" + dllPath);

        FaceEngine faceEngine = faceEngineModel.init(dllPath);
        //激活引擎
        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }
        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        System.out.println("激活成功");
    }

    @Override
    public void loadFace(String endpoint, String accessKeyId, String accessKeySecret, String bucketname, List<String> userList) throws IOException {
        // 下载人脸照片

        for (int i = 0;i<userList.size();i++){
            String itemUser = userList.get(i);
            InputStream inputStream = OssUtil.downloadFiletoStream(endpoint, accessKeyId, accessKeySecret, bucketname, itemUser);
            ImageInfo imageInfo = getRGBData(inputStream);
            FaceEngine faceEngine = faceEngineModel.getFaceEngine();

            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            int code1 = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
            System.out.println(code1);
            //特征提取
            FaceFeature faceFeature = new FaceFeature();
            int code2 = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
            System.out.println(code2);
            redisTemplate.opsForValue().set(itemUser, Base64Util.encode(faceFeature.getFeatureData()));
        }
    }

    @Override
    public String facesearch(InputStream inputStream,List<String> userList) throws IOException {
        ImageInfo imageInfo = getRGBData(inputStream);
        FaceEngine faceEngine = faceEngineModel.getFaceEngine();

        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        int code1 = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        System.out.println(code1);
        //特征提取
        // 传来的脸
        FaceFeature targetFaceFeature = new FaceFeature();
        faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), targetFaceFeature);

        for (int index = 0 ;index < userList.size();index ++){
            String key = userList.get(index);
            String objstring = String.valueOf(redisTemplate.opsForValue().get(key));
            byte[] bytes = Base64Util.base64ToImgByteArray(objstring);
            FaceFeature sourceFaceFeature = new FaceFeature();
            sourceFaceFeature.setFeatureData(bytes);
            FaceSimilar faceSimilar = new FaceSimilar();
            int errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
            float score = faceSimilar.getScore();
            if (score >=0.8){
                return key;
            }
        }

        return null;
    }

    @Override
    public String engineState() {
        FaceEngine faceEngine = faceEngineModel.getFaceEngine();
        if (faceEngine == null){
            return "0";
        }else {
            return "1";
        }
    }

    @Override
    public Set<String> getfaceList() {
        // *号 必须要加，否则无法模糊查询
        // 获取所有的key
        Set keys = redisTemplate.keys("*");
        System.out.println("查到的"+keys);
        return  keys;
    }

    @Override
    public void delface(List<String> userList) throws IOException {
        for (int i = 0;i<userList.size();i++){
            redisTemplate.delete(userList.get(i));

        }
    }
}
