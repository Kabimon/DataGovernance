package com.webank.wedatasphere.dss.data.governance.restful;

import com.webank.wedatasphere.dss.data.governance.entity.*;
import com.webank.wedatasphere.dss.data.governance.service.AssetService;
import com.webank.wedatasphere.dss.data.governance.service.WorkspaceInfoService;
import com.webank.wedatasphere.linkis.server.Message;
import org.apache.atlas.model.lineage.AtlasLineageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Classname DSSDataGovernanceAssetRestful
 * @Description TODO
 * @Date 2021/8/19 13:53
 * @Created by suyc
 */
@Path("/dss/data/governance/asset")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class DSSDataGovernanceAssetRestful {
    private static final Logger logger = LoggerFactory.getLogger(DSSDataGovernanceAssetRestful.class);

    private static final String DEFAULT_DIRECTION = "BOTH";
    private static final String DEFAULT_DEPTH     = "3";
    private static final String DEFAULT_LIMIT     = "0";
    private static final String DEFAULT_OFFSET     = "25";

    @Autowired
    private AssetService assetService;
    @Autowired
    private WorkspaceInfoService workspaceInfoService;

    /**
     * 获取数据资产概要：hivedb数、hivetable数据、总存储量
     */
    @GET
    @Path("/hiveSummary")
    public Response getHiveSummary(@Context HttpServletRequest req) throws Exception {

        return Message.messageToResponse(Message.ok().data("result", assetService.getHiveSummary()));
    }

    /**
     * 搜索hive表
     */
    @GET
    @Path("/hiveTbl/search")
    public Response searchHiveTbl(@QueryParam("classification") String classification,
                                  @QueryParam("query")  String query,
                                  @QueryParam("owner") @DefaultValue("")  String owner,
                                  @QueryParam("limit") @DefaultValue(DEFAULT_LIMIT) int limit,
                                  @QueryParam("offset") @DefaultValue(DEFAULT_OFFSET) int offset) throws Exception {
        List<HiveTblSimpleInfo> hiveTblBasicList = assetService.searchHiveTable(classification,'*'+query+'*',limit,offset);
        if(owner.equals(null)||owner.equals("")||owner.equals(" ")||owner.equals("undefined")){
            return Message.messageToResponse(Message.ok().data("result",hiveTblBasicList));
        }
        else {
            List<HiveTblSimpleInfo> res=new ArrayList<>();
            for (HiveTblSimpleInfo hiveTblSimpleInfo : hiveTblBasicList) {
                if(hiveTblSimpleInfo.getOwner().equals(owner)){
                    res.add(hiveTblSimpleInfo);
                }
            }
            return Message.messageToResponse(Message.ok().data("result",res));
        }
    }

    /**
     * 获取单个表的详细信息，包括：基本信息、字段信息
     */
    @GET
    @Path("/hiveTbl/{guid}/basic")
    public Response getHiveTblBasic(@PathParam("guid") String guid) throws Exception {
        return Message.messageToResponse(Message.ok().data("result",assetService.getHiveTblDetail(guid)));
    }

    /**
     * 获取表分区信息
     */
    @GET
    @Path("/hiveTbl/{guid}/partition")
    public Response getHiveTblPartition(@PathParam("guid") String guid) throws Exception {
        List<PartInfo> hiveTblPartition = assetService.getHiveTblPartition(guid);
        if (hiveTblPartition.size()>0){
            return Message.messageToResponse(Message.ok().data("result",hiveTblPartition));
        }
        else {
            return Message.messageToResponse(Message.ok().data("result",null));
        }
    }

    /**
     * 获取表的血缘信息
     */
    @GET
    @Path("/hiveTbl/{guid}/lineage")
    public Response getHiveTblLineage(@PathParam("guid") String guid,
                                    @QueryParam("direction") @DefaultValue(DEFAULT_DIRECTION) AtlasLineageInfo.LineageDirection direction,
                                    @QueryParam("depth") @DefaultValue(DEFAULT_DEPTH) int depth) throws Exception {
        return Message.messageToResponse(Message.ok().data("result",assetService.getHiveTblLineage(guid,direction,depth)));
    }

    /**
     * 获取表的select语句
     */
    @GET
    @Path("/hiveTbl/{guid}/select")
    public Response getHiveTblSelect(@PathParam("guid") String guid) throws Exception {


        return  Message.messageToResponse(Message.ok().data("result",assetService.getTbSelect(guid)));

    }

    /**
     * 获取表的create语句
     */
    @GET
    @Path("/hiveTbl/{guid}/create")
    public Response getHiveTblCreate(@PathParam("guid") String guid) throws Exception {
        return  Message.messageToResponse(Message.ok().data("result",assetService.getTbCreate(guid)));

    }
    /**
     * 获取存储量前10的表信息
     */
    @GET
    @Path("/hiveTbl/topStorage")
    public  Response getTop10Storage(@Context HttpServletRequest req) throws  Exception{
        List<TableInfo> top10Table = assetService.getTop10Table();
        return  Message.messageToResponse(Message.ok().data("result",top10Table));
    }

    /**
     * 修改单个表或单个列注释
     */
    @PUT
    @Path("/comment/{guid}")
    public Response modifyComment(@PathParam("guid") String guid,@QueryParam("comment") String comment) throws Exception {
        comment="\""+comment+"\"";
        assetService.modifyComment(guid,comment);
        return Message.messageToResponse(Message.ok().data("result","修改成功"));
    }

    /**
     * 批量修改多个个表或列注释
     */
    @PUT
    @Path("/comment/bulk")
    public Response modifyComment(@RequestBody Map<String,String> commentMap) throws Exception {
        for (Map.Entry<String, String> stringStringEntry : commentMap.entrySet()) {
            stringStringEntry.setValue("\""+stringStringEntry.getValue()+"\"");
        }
        assetService.bulkModifyComment(commentMap);

        return Message.messageToResponse(Message.ok().data("result","修改成功"));
    }


    /**
     * 设置单个表或单个列的标签
     */
    @POST
    @Path("/label/{guid}")
    public Response setLabels(@PathParam("guid") String guid, @RequestBody Set<String> labels) throws Exception {
        assetService.setLabels(guid,labels);

        return Message.messageToResponse(Message.ok().data("result","设置成功"));
    }

    /**
     * 获取工作空间下所有用户名
     */
    @GET
    @Path("getWorkspaceUsers/{workspaceId}/{search}")
    public Response getWorkspaceUsers(@PathParam("workspaceId") int workspaceId,@PathParam("search")  String search) throws  Exception{
        String searchs="%"+search+"%";
        List<String> workspaceUsers = workspaceInfoService.getWorkspaceUsers(workspaceId,searchs);
        return  Message.messageToResponse(Message.ok().data("result",workspaceUsers));

    }

}
