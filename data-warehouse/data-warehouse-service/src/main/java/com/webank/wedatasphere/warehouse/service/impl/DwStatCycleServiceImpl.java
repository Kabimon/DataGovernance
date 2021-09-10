package com.webank.wedatasphere.warehouse.service.impl;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.DwStatCycleCreateCommand;
import com.webank.wedatasphere.warehouse.cqe.DwStatCycleQueryCommand;
import com.webank.wedatasphere.warehouse.cqe.DwStatCycleUpdateCommand;
import com.webank.wedatasphere.warehouse.dao.mapper.DwStatCycleMapper;
import com.webank.wedatasphere.warehouse.exception.DwException;
import com.webank.wedatasphere.warehouse.service.DwStatCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class DwStatCycleServiceImpl implements DwStatCycleService {

    private final DwStatCycleMapper dwStatCycleMapper;

    @Autowired
    public DwStatCycleServiceImpl(final DwStatCycleMapper dwStatCycleMapper) {
        this.dwStatCycleMapper = dwStatCycleMapper;
    }

    @Override
    public Message queryPage(HttpServletRequest request, DwStatCycleQueryCommand command) throws DwException {
        return null;
    }

    @Transactional
    @Override
    public Message create(HttpServletRequest request, DwStatCycleCreateCommand command) throws DwException {
        return null;
    }

    @Override
    public Message getById(HttpServletRequest request, Long id) throws DwException {
        return null;
    }

    @Transactional
    @Override
    public Message deleteById(HttpServletRequest request, Long id) throws DwException {
        return null;
    }

    @Transactional
    @Override
    public Message update(HttpServletRequest request, DwStatCycleUpdateCommand command) throws DwException {
        return null;
    }
}
