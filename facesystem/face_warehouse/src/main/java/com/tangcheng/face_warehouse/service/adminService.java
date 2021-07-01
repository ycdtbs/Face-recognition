package com.tangcheng.face_warehouse.service;

import com.tangcheng.face_warehouse.model.Config;
import com.tangcheng.face_warehouse.model.UserInfo;
import com.tangcheng.face_warehouse.model.adminEneity;
import com.tangcheng.face_warehouse.model.vo.ossset;
import org.springframework.stereotype.Service;

import java.util.List;


public interface adminService {
    /**
     * 查询所有的用户信息
     * @return
     */
    public List<adminEneity> finAlladmin();

    /**
     * 登录
     * @param adminId
     * @param password
     * @return
     */
    public adminEneity login(Integer adminId,String password );
    public adminEneity changeRoly(Integer adminId);
    public List<Config> getOssConfig(String ConfigTeam);
    public void saveOssConfig(ossset ossset);
    public Config getConfigvalueByName(String ConfigName);
    public void insertUser(UserInfo userInfo);
    public List<UserInfo> getUserList(String userflag);
    public int deluser(String userName);
    public List<String> findUsernameList();
}
