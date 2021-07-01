package com.tangcheng.face_warehouse.model.vo;

import lombok.Data;
import lombok.NonNull;

@Data
public class login {
    @NonNull
    private Integer username;
    @NonNull
    private String password;
}
