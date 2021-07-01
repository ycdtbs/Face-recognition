package com.tangcheng.face_warehouse.service.impl;

import com.tangcheng.face_warehouse.Repository.adminRepository;
import com.tangcheng.face_warehouse.Repository.configRepository;
import com.tangcheng.face_warehouse.Repository.userinfoReponsitory;
import com.tangcheng.face_warehouse.model.Config;
import com.tangcheng.face_warehouse.model.UserInfo;
import com.tangcheng.face_warehouse.model.adminEneity;
import com.tangcheng.face_warehouse.model.vo.ossset;
import com.tangcheng.face_warehouse.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminServiceImpl implements adminService {
    @Autowired
    adminRepository adminRepository;
    @Autowired
    configRepository configRepository;
    @Autowired
    userinfoReponsitory userinfoReponsitory;
    @Override
    public List<adminEneity> finAlladmin() {
        List<adminEneity> all = adminRepository.findAll();
        return all;
    }

    @Override
    public adminEneity login(Integer adminId, String password) {
        adminEneity adminEneity = adminRepository.findByAdminIdAndAdminPassword(adminId, password);
        return adminEneity;
    }

    @Override
    public adminEneity changeRoly(Integer adminId) {
        return null;
    }

    @Override
    public List<Config> getOssConfig(String ConfigTeam) {
        List<Config> distinctByConfigTeam = configRepository.findDistinctByConfigTeam(ConfigTeam);
        return distinctByConfigTeam;
    }

    @Override
    public void saveOssConfig(ossset ossset) {
        configRepository.updatevalue(ossset.getEndpoint(),"endpoint");
        configRepository.updatevalue(ossset.getAccessKeyId(),"accessKeyId");
        configRepository.updatevalue(ossset.getAccessKeySecret(),"accessKeySecret");
        configRepository.updatevalue(ossset.getBucket(),"bucket");

    }

    @Override
    public Config getConfigvalueByName(String ConfigName) {
        Config configByConfigName = configRepository.findConfigByConfigName(ConfigName);
        return configByConfigName;
    }

    @Override
    public void insertUser(UserInfo userInfo) {
        userinfoReponsitory.addUserinfo(userInfo.getUserName(),userInfo.getUserSex(),"0");
    }

    @Override
    public List<UserInfo> getUserList(String userflag) {
        List<UserInfo> allByUserFlag = userinfoReponsitory.findAllByUserFlag("0");
        return allByUserFlag;
    }

    @Override
    public int deluser(String userName) {
        int i = userinfoReponsitory.deluser(userName);
        return i;
    }

    @Override
    public List<String> findUsernameList() {
        List<String> usernameList = userinfoReponsitory.findUsernameList();
        return usernameList;
    }


}
