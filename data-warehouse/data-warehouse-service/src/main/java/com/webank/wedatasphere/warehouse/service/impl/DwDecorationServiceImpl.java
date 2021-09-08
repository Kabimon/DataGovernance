package com.webank.wedatasphere.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.DwDecorationCreateCommand;
import com.webank.wedatasphere.warehouse.cqe.DwDecorationQueryCommand;
import com.webank.wedatasphere.warehouse.cqe.DwDecorationUpdateCommand;
import com.webank.wedatasphere.warehouse.dao.domain.DwDecoration;
import com.webank.wedatasphere.warehouse.dao.domain.DwLayer;
import com.webank.wedatasphere.warehouse.dao.domain.DwSubjectDomain;
import com.webank.wedatasphere.warehouse.dao.mapper.DwDecorationMapper;
import com.webank.wedatasphere.warehouse.dao.mapper.DwLayerMapper;
import com.webank.wedatasphere.warehouse.dao.mapper.DwSubjectDomainMapper;
import com.webank.wedatasphere.warehouse.dto.DwDecorationDTO;
import com.webank.wedatasphere.warehouse.dto.DwDecorationListItemDTO;
import com.webank.wedatasphere.warehouse.dto.PageInfo;
import com.webank.wedatasphere.warehouse.exception.DwException;
import com.webank.wedatasphere.warehouse.mapper.DwDecorationModelMapper;
import com.webank.wedatasphere.warehouse.service.DwDecorationService;
import com.webank.wedatasphere.warehouse.utils.PreconditionUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DwDecorationServiceImpl implements DwDecorationService {

    private final DwDecorationMapper dwDecorationMapper;
    private final DwLayerMapper dwLayerMapper;
    private final DwSubjectDomainMapper dwSubjectDomainMapper;

    @Autowired
    public DwDecorationServiceImpl(
            DwDecorationMapper dwDecorationMapper,
            DwLayerMapper dwLayerMapper,
            DwSubjectDomainMapper dwSubjectDomainMapper
    ) {
        this.dwDecorationMapper = dwDecorationMapper;
        this.dwLayerMapper = dwLayerMapper;
        this.dwSubjectDomainMapper =  dwSubjectDomainMapper;
    }

    @Override
    public Message queryPage(HttpServletRequest request, DwDecorationQueryCommand command) throws DwException {
        Integer page = command.getPage();
        Integer size = command.getSize();
        String typeName = command.getTypeName();
        if (Objects.isNull(page))
            page = 1;

        if (Objects.isNull(size))
            size = 10;

        QueryWrapper<DwDecoration> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Boolean.TRUE);
        if (Strings.isNotBlank(typeName)) {
            queryWrapper.like("type_name", typeName);
        }
        Page<DwDecoration> queryPage = new Page<>(page, size);

        IPage<DwDecoration> _page = this.dwDecorationMapper.selectPage(queryPage, queryWrapper);

        List<DwDecoration> records = _page.getRecords();

        List<DwDecorationListItemDTO> list = DwDecorationModelMapper.INSTANCE.toList(records);

        PageInfo<DwDecorationListItemDTO> __page = new PageInfo<>(list, _page.getCurrent(), _page.getSize(), _page.getTotal());

        return Message.ok().data("page", __page);
    }

    @Transactional
    @Override
    public Message create(HttpServletRequest request, DwDecorationCreateCommand command) throws DwException {
        Long subjectDomainId = command.getSubjectDomainId();
        Long layerId = command.getLayerId();
        String typeName = command.getTypeName();
        String typeNameAlias = command.getTypeNameAlias();
        String decorationList = command.getDecorationList();
        String description = command.getDescription();

        PreconditionUtil.checkArgument(!Objects.isNull(subjectDomainId), DwException.argumentReject("subjectdomain id should not empty"));
        PreconditionUtil.checkArgument(!Objects.isNull(layerId), DwException.argumentReject("layer id should not empty"));
        typeName = PreconditionUtil.checkStringArgumentNotBlankTrim(typeName, DwException.argumentReject("typeName should not empty"));
        typeNameAlias = PreconditionUtil.checkStringArgumentNotBlankTrim(typeNameAlias, DwException.argumentReject("typeNameAlias should not empty"));

        // 验证 主题域  分层是否存在
        QueryWrapper<DwLayer> layerQueryWrapper = new QueryWrapper<>();
        layerQueryWrapper.eq("id", layerId);
        layerQueryWrapper.eq("status", Boolean.TRUE);
        DwLayer dwLayer = this.dwLayerMapper.selectOne(layerQueryWrapper);
        PreconditionUtil.checkState(!Objects.isNull(dwLayer), DwException.stateReject("layer not found"));
        PreconditionUtil.checkState(dwLayer.getEnabled(), DwException.stateReject("layer disabled"));

        QueryWrapper<DwSubjectDomain> subjectDomainQueryWrapper = new QueryWrapper<>();
        subjectDomainQueryWrapper.eq("id", subjectDomainId);
        subjectDomainQueryWrapper.eq("status", Boolean.TRUE);
        DwSubjectDomain dwSubjectDomain = this.dwSubjectDomainMapper.selectOne(subjectDomainQueryWrapper);
        PreconditionUtil.checkState(!Objects.isNull(dwSubjectDomain), DwException.stateReject("subjectdomain not found"));

        // typeName 唯一性检测
        QueryWrapper<DwDecoration> nameUniqueCheckQuery = new QueryWrapper<>();
        nameUniqueCheckQuery.eq("type_name", typeName);
        nameUniqueCheckQuery.eq("status", Boolean.TRUE);
        DwDecoration exist = this.dwDecorationMapper.selectOne(nameUniqueCheckQuery);
        PreconditionUtil.checkState(Objects.isNull(exist), DwException.stateReject("decoration typeName aleardy exists"));

        // typeNameAlias 唯一性检测
        QueryWrapper<DwDecoration> nameAliasUniqueCheckQuery = new QueryWrapper<>();
        nameAliasUniqueCheckQuery.eq("type_name_alias", typeNameAlias);
        nameAliasUniqueCheckQuery.eq("status", Boolean.TRUE);
        DwDecoration exist2 = this.dwDecorationMapper.selectOne(nameAliasUniqueCheckQuery);
        PreconditionUtil.checkState(Objects.isNull(exist2), DwException.stateReject("decoration typeNameAlias aleardy exists"));

        String user = "hdfs";

        Date now = new Date();
//        if (Strings.isNotBlank(decorationList)) {
//
//        }

        DwDecoration record = new DwDecoration();
        record.setSubjectDomainId(dwSubjectDomain.getId());
        record.setLayerId(dwLayer.getId());
        record.setTypeName(typeName);
        record.setTypeNameAlias(typeNameAlias);
        record.setDescription(description);
        record.setDecorationList(decorationList);
        record.setEnabled(Boolean.TRUE);
        record.setCreateUser(user);
        record.setCreateTime(now);
        record.setStatus(Boolean.TRUE);
        record.setVersion(1L);

        int insert = this.dwDecorationMapper.insert(record);
        PreconditionUtil.checkState(1 == insert, DwException.stateReject("create dw decoration failed"));

        return Message.ok().data("id", record.getId());
    }

    @Override
    public Message getById(HttpServletRequest request, Long id) throws DwException {
        PreconditionUtil.checkArgument(!Objects.isNull(id), DwException.argumentReject("id should not be null"));

        DwDecoration record = this.dwDecorationMapper.selectById(id);
        PreconditionUtil.checkState(!Objects.isNull(record), DwException.stateReject("decoration not found"));
        PreconditionUtil.checkState(record.getStatus(), DwException.stateReject("decoration has been removed"));

        DwDecorationDTO dto = DwDecorationModelMapper.INSTANCE.toDTO(record);

        return Message.ok().data("item", dto);
    }

    @Transactional
    @Override
    public Message deleteById(HttpServletRequest request, Long id) throws DwException {
        PreconditionUtil.checkArgument(!Objects.isNull(id), DwException.argumentReject("id should not be null"));
        DwDecoration record = this.dwDecorationMapper.selectById(id);
        PreconditionUtil.checkState(!Objects.isNull(record), DwException.stateReject("decoration not found"));
        if (Objects.equals(Boolean.FALSE, record.getStatus())) {
            return Message.ok();
        }
        record.setStatus(Boolean.FALSE);
        int i = this.dwDecorationMapper.updateById(record);
        PreconditionUtil.checkState(1 == i, DwException.stateReject("remove action failed"));
        return Message.ok();
    }

    @Transactional
    @Override
    public Message update(HttpServletRequest request, DwDecorationUpdateCommand command) throws DwException {
        Long id = command.getId();
        Long subjectDomainId = command.getSubjectDomainId();
        Long layerId = command.getLayerId();
        String typeName = command.getTypeName();
        String typeNameAlias = command.getTypeNameAlias();
        String decorationList = command.getDecorationList();
        String description = command.getDescription();

        PreconditionUtil.checkArgument(!Objects.isNull(id), DwException.argumentReject("id should not empty"));
        PreconditionUtil.checkArgument(!Objects.isNull(subjectDomainId), DwException.argumentReject("subjectdomain id should not empty"));
        PreconditionUtil.checkArgument(!Objects.isNull(layerId), DwException.argumentReject("layer id should not empty"));
        typeName = PreconditionUtil.checkStringArgumentNotBlankTrim(typeName, DwException.argumentReject("typeName should not empty"));
        typeNameAlias = PreconditionUtil.checkStringArgumentNotBlankTrim(typeNameAlias, DwException.argumentReject("typeNameAlias should not empty"));

        // 验证 主题域  分层是否存在
        QueryWrapper<DwLayer> layerQueryWrapper = new QueryWrapper<>();
        layerQueryWrapper.eq("id", layerId);
        layerQueryWrapper.eq("status", Boolean.TRUE);
        DwLayer dwLayer = this.dwLayerMapper.selectOne(layerQueryWrapper);
        PreconditionUtil.checkState(!Objects.isNull(dwLayer), DwException.stateReject("layer not found"));
        PreconditionUtil.checkState(dwLayer.getEnabled(), DwException.stateReject("layer disabled"));

        QueryWrapper<DwSubjectDomain> subjectDomainQueryWrapper = new QueryWrapper<>();
        subjectDomainQueryWrapper.eq("id", subjectDomainId);
        subjectDomainQueryWrapper.eq("status", Boolean.TRUE);
        DwSubjectDomain dwSubjectDomain = this.dwSubjectDomainMapper.selectOne(subjectDomainQueryWrapper);
        PreconditionUtil.checkState(!Objects.isNull(dwSubjectDomain), DwException.stateReject("subjectdomain not found"));

        // 实体校验
        DwDecoration record = this.dwDecorationMapper.selectById(id);
        PreconditionUtil.checkState(!Objects.isNull(record), DwException.stateReject("decoration not found"));

        // typeName 唯一性检测
        QueryWrapper<DwDecoration> nameUniqueCheckQuery = new QueryWrapper<>();
        nameUniqueCheckQuery.eq("type_name", typeName);
        nameUniqueCheckQuery.ne("id", id);
        nameUniqueCheckQuery.eq("status", Boolean.TRUE);
        DwDecoration exist = this.dwDecorationMapper.selectOne(nameUniqueCheckQuery);
        PreconditionUtil.checkState(Objects.isNull(exist), DwException.stateReject("decoration typeName aleardy exists"));
        // typeNameAlias 唯一性检测
        QueryWrapper<DwDecoration> nameAliasUniqueCheckQuery = new QueryWrapper<>();
        nameAliasUniqueCheckQuery.eq("type_name_alias", typeNameAlias);
        nameAliasUniqueCheckQuery.ne("id", id);
        nameAliasUniqueCheckQuery.eq("status", Boolean.TRUE);
        DwDecoration exist2 = this.dwDecorationMapper.selectOne(nameAliasUniqueCheckQuery);
        PreconditionUtil.checkState(Objects.isNull(exist2), DwException.stateReject("decoration typeNameAlias aleardy exists"));

        // TODO
        String user = "hdfs";

        Long oldVersion = record.getVersion();

        Date now = new Date();

        record.setSubjectDomainId(dwSubjectDomain.getId());
        record.setLayerId(dwLayer.getId());
        record.setTypeName(typeName);
        record.setTypeNameAlias(typeNameAlias);
        record.setDescription(description);
        record.setDecorationList(decorationList);
        record.setModifyUser(user);
        record.setModifyTime(now);
        record.setVersion(oldVersion + 1);

        UpdateWrapper<DwDecoration> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", record.getId());
        updateWrapper.eq("version", oldVersion);

        int i = this.dwDecorationMapper.update(record, updateWrapper);

        PreconditionUtil.checkState(1 == i, DwException.stateReject("update decoration failed"));
        return Message.ok();
    }
}
