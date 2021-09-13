package com.webank.wedatasphere.dss.data.governance.dao;

import com.webank.wedatasphere.dss.data.governance.entity.PartInfo;
import com.webank.wedatasphere.dss.data.governance.entity.TableInfo;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:李嘉玮
 */
//
@Mapper
public interface MetaInfoMapper {
    Integer getTableStorage() throws SQLException;
    List<TableInfo> getTop10Table() throws SQLException;
    int getTableInfo(@Param("dbName") String dbName,@Param("tableName") String tableName,@Param("isPartTable") Boolean isPartTable) throws  SQLException;

    List<PartInfo> getPartInfo(@Param("dbName") String dbName, @Param("tableName") String tableName) throws SQLException;



}
