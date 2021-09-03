package com.webank.wedatasphere.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.DwSubjectDomainCreateCommand;
import com.webank.wedatasphere.warehouse.cqe.DwSubjectDomainQueryCommand;
import com.webank.wedatasphere.warehouse.cqe.DwSubjectDomainUpdateCommand;
import com.webank.wedatasphere.warehouse.dao.domain.DwSubjectDomain;
import com.webank.wedatasphere.warehouse.dao.mapper.DwSubjectDomainMapper;
import com.webank.wedatasphere.warehouse.dto.DwSubjectDomainDTO;
import com.webank.wedatasphere.warehouse.dto.DwSubjectDomainListItemDTO;
import com.webank.wedatasphere.warehouse.dto.PageInfo;
import com.webank.wedatasphere.warehouse.exception.DwException;
import com.webank.wedatasphere.warehouse.mapper.DwSubjectDomainModelMapper;
import com.webank.wedatasphere.warehouse.service.DwSubjectDomainService;
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
public class DwSubjectDomainServiceImpl implements DwSubjectDomainService {

    private final DwSubjectDomainMapper dwSubjectDomainMapper;

    @Autowired
    public DwSubjectDomainServiceImpl(DwSubjectDomainMapper dwSubjectDomainMapper) {
        this.dwSubjectDomainMapper = dwSubjectDomainMapper;
    }

    @Override
    public Message queryPage(HttpServletRequest request, DwSubjectDomainQueryCommand command) throws DwException {
        Integer page = command.getPage();
        Integer size = command.getSize();
        String name = command.getName();
        if (Objects.isNull(page))
            page = 1;

        if (Objects.isNull(size))
            size = 10;

        QueryWrapper<DwSubjectDomain> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Boolean.TRUE);
        if (Strings.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        Page<DwSubjectDomain> queryPage = new Page<>(page, size);

        IPage<DwSubjectDomain> _page = this.dwSubjectDomainMapper.selectPage(queryPage, queryWrapper);

        List<DwSubjectDomain> records = _page.getRecords();

        List<DwSubjectDomainListItemDTO> list = DwSubjectDomainModelMapper.INSTANCE.toList(records);

        PageInfo<DwSubjectDomainListItemDTO> __page = new PageInfo<>(list, _page.getCurrent(), _page.getSize(), _page.getTotal());

        return Message.ok().data("page", __page);
    }

    @Transactional
    @Override
    public Message create(HttpServletRequest request, DwSubjectDomainCreateCommand command) throws DwException {
        String name = command.getName();
        String description = command.getDescription();
        String authority = command.getAuthority();

        name = PreconditionUtil.checkStringArgumentNotBlankTrim(name, DwException.argumentReject("name should not empty"));
        authority = PreconditionUtil.checkStringArgumentNotBlankTrim(authority, DwException.argumentReject("authority should not empty"));

        String user = "hdfs";

        Date now = new Date();

        DwSubjectDomain record = new DwSubjectDomain();
        record.setParentId(0L);
        record.setName(name);
        record.setDescription(description);
        record.setAuthority(authority);
        record.setCreateUser(user);
        record.setCreateTime(now);
        record.setStatus(Boolean.TRUE);
        record.setVersion(1L);

        int insert = this.dwSubjectDomainMapper.insert(record);
        PreconditionUtil.checkState(1 == insert, DwException.stateReject("create dw subject domain failed"));

        return Message.ok().data("id", record.getId());
    }

    @Override
    public Message getById(HttpServletRequest request, Long id) throws DwException {
        PreconditionUtil.checkArgument(!Objects.isNull(id), DwException.argumentReject("id should not be null"));

        DwSubjectDomain record = this.dwSubjectDomainMapper.selectById(id);
        PreconditionUtil.checkState(!Objects.isNull(record), DwException.stateReject("subjectdomain not found"));
        PreconditionUtil.checkState(record.getStatus(), DwException.stateReject("subjectdomain has been removed"));

        DwSubjectDomainDTO dto = DwSubjectDomainModelMapper.INSTANCE.toDTO(record);

        return Message.ok().data("item", dto);
    }

    // TODO 后面需要检查主题域下有没有关联的 Hive 表，才决定是否删除
    @Transactional
    @Override
    public Message deleteById(HttpServletRequest request, Long id) throws DwException {
        PreconditionUtil.checkArgument(!Objects.isNull(id), DwException.argumentReject("id should not be null"));
        DwSubjectDomain record = this.dwSubjectDomainMapper.selectById(id);
        PreconditionUtil.checkState(!Objects.isNull(record), DwException.stateReject("subjectdomain not found"));
        if (Objects.equals(Boolean.FALSE, record.getStatus())) {
            return Message.ok();
        }
        record.setStatus(Boolean.FALSE);
        int i = this.dwSubjectDomainMapper.updateById(record);
        PreconditionUtil.checkState(1 == i, DwException.stateReject("remove action failed"));
        return Message.ok();
    }

    @Transactional
    @Override
    public Message update(HttpServletRequest request, DwSubjectDomainUpdateCommand command) throws DwException {
        // 基本参数校验
        Long id = command.getId();
        String name = command.getName();
        String authority = command.getAuthority();
        String description = command.getDescription();
        PreconditionUtil.checkArgument(!Objects.isNull(id), DwException.argumentReject("id should not be null"));
        authority = PreconditionUtil.checkStringArgumentNotBlankTrim(authority, DwException.argumentReject("authority should not empty"));
        name = PreconditionUtil.checkStringArgumentNotBlankTrim(name, DwException.argumentReject("name should not empty"));

        // 实体校验
        DwSubjectDomain record = this.dwSubjectDomainMapper.selectById(id);
        PreconditionUtil.checkState(!Objects.isNull(record), DwException.stateReject("subjectdomain not found"));

        // name 唯一性检测
        QueryWrapper<DwSubjectDomain> nameUniqueCheckQuery = new QueryWrapper<>();
        nameUniqueCheckQuery.eq("name", name);
        nameUniqueCheckQuery.ne("id", id);
        nameUniqueCheckQuery.eq("status", Boolean.TRUE);
        DwSubjectDomain exist = this.dwSubjectDomainMapper.selectOne(nameUniqueCheckQuery);
        PreconditionUtil.checkState(Objects.isNull(exist), DwException.stateReject("subjectdomain name aleardy exists"));

        // TODO
        String user = "hdfs";

        Long oldVersion = record.getVersion();

        Date now = new Date();
        record.setName(name);
        record.setDescription(description);
        record.setAuthority(authority);
        record.setModifyUser(user);
        record.setModifyTime(now);
        record.setVersion(oldVersion + 1);

        UpdateWrapper<DwSubjectDomain> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", record.getId());
        updateWrapper.eq("version", oldVersion);

        int i = this.dwSubjectDomainMapper.update(record, updateWrapper);

        PreconditionUtil.checkState(1 == i, DwException.stateReject("update subjectdomain failed"));
        return Message.ok();
    }
}
