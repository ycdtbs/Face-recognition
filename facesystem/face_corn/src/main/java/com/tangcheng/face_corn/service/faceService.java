package com.tangcheng.face_corn.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface faceService {
    /**
     * 引擎注册
     * @param appId
     * @param sdkKey
     * @param dllPath
     */
    public void registeredEngine(String appId,String sdkKey,String dllPath);
    /**
     * 加载人脸数据
     */
    public void loadFace(String endpoint, String accessKeyId, String accessKeySecret, String bucketname,List<String> userList) throws IOException;

    public String facesearch(InputStream inputStream,List<String> userList) throws IOException;

    public String engineState();

    public Set<String> getfaceList();
    public void delface(List<String> userList) throws IOException;

}
