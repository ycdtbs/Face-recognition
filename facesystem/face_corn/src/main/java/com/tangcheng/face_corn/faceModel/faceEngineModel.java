package com.tangcheng.face_corn.faceModel;

import com.arcsoft.face.FaceEngine;

public class faceEngineModel {
    private static FaceEngine faceEngine;
    private String dllPath;

    public static FaceEngine init(String dllPath){
        faceEngine = new FaceEngine(dllPath);
        return faceEngine;
    }
    public static FaceEngine getFaceEngine(){
        return faceEngine;
    }
}
