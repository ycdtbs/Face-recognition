package com.tangcheng.face_warehouse.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Api(tags = "登录用户信息")
@Data
@Table(name = "warehouse_admin")
@Entity
public class adminEneity {
    @ApiModelProperty("管理员ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @ApiModelProperty("管理员密码")
    @Column(name = "admin_password")
    private String adminPassword;
    @ApiModelProperty("管理员权限")
    @Column(name = "admin_role",columnDefinition = "INT(2) default 1")
    private Integer adminRole;
    @ApiModelProperty("创建日期")
    @Column(name = "admin_createtime")
    @CreationTimestamp
    private Date adminCreatetime;
}
