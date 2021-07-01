package com.tangcheng.face_warehouse.Repository;

import com.tangcheng.face_warehouse.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface userinfoReponsitory extends JpaRepository<UserInfo,String> {
    @Modifying
    @Transactional
    @Query(value = "insert into user_info values (?1,?2,?3)",nativeQuery = true)
    public int addUserinfo(String name,String sex,String flag);

    public List<UserInfo> findAllByUserFlag(String userflag);
    @Modifying
    @Transactional
    @Query(value = "delete from user_info where user_name = ?1",nativeQuery = true)
    public int deluser(String userName);

    @Modifying
    @Transactional
    @Query(value = "select user_name from user_info",nativeQuery = true)
    public List<String> findUsernameList();


}
