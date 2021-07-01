package com.tangcheng.face_warehouse.Repository;

import com.tangcheng.face_warehouse.model.adminEneity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<adminEneity,Integer> {
    /**
     * 登录
     *
     * @param adminId
     * @param password
     * @return
     */
    public adminEneity findByAdminIdAndAdminPassword(Integer adminId, String password);

}