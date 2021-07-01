package com.tangcheng.face_warehouse.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "config")
@Entity
public class Config {
  @Id
  private String configName;
  @Column(name = "config_value")
  private String configValue;
  @Column(name = "config_team")
  private String configTeam;



}
