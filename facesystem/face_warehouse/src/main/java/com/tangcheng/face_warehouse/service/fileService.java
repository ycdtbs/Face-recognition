package com.tangcheng.face_warehouse.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public interface fileService {
    public String imageToBase64(InputStream inputStream) throws IOException;

}
