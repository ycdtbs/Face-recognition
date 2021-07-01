package com.tangcheng.face_warehouse.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
  @Id
  private String userName;
  @Column(name = "user_sex")
  private String userSex;
  @Column(name = "user_flag")
  private String userFlag;


}
