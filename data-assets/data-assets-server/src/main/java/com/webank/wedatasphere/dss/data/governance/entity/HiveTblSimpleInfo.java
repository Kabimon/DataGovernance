package com.webank.wedatasphere.dss.data.governance.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Classname HiveTblSimpleInfo
 * @Description TODO
 * @Date 2021/8/24 10:17
 * @Created by suyc
 */
@Data
public class HiveTblSimpleInfo {
    private String guid;
    private String name;
    private String qualifiedName;
    private String createTime;
    private String owner;
}
