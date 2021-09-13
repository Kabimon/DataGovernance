package com.webank.wedatasphere.dss.data.governance.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Classname HiveTblDetail
 * @Description TODO
 * @Date 2021/8/24 13:33
 * @Created by suyc
 */
@Data
public class HiveTblDetailInfo implements Serializable {
    private HiveTblBasicInfo basic;
    private List<HiveColumnInfo> columns;
    private List<HiveColumnInfo> partitionKeys;


    @Data
    public static class HiveTblBasicInfo extends HiveTblSimpleInfo {
        private  String store;
        private  String comment;
        private Set<String> labels;
        private Boolean isParTbl;
    }

    @Data
    public static class HiveColumnInfo {
        private  String name;
        private  String type;
        private  String guid;
        private  String comment;
    }

}
