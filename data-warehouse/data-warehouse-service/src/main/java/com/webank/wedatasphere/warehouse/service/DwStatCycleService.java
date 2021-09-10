package com.webank.wedatasphere.warehouse.service;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.DwStatCycleCreateCommand;
import com.webank.wedatasphere.warehouse.cqe.DwStatCycleQueryCommand;
import com.webank.wedatasphere.warehouse.cqe.DwStatCycleUpdateCommand;
import com.webank.wedatasphere.warehouse.exception.DwException;

import javax.servlet.http.HttpServletRequest;

public interface DwStatCycleService {
    Message queryPage(HttpServletRequest request, DwStatCycleQueryCommand command) throws DwException;

    Message create(HttpServletRequest request, DwStatCycleCreateCommand command) throws DwException;

    Message getById(HttpServletRequest request, Long id) throws DwException;

    Message deleteById(HttpServletRequest request, Long id) throws DwException;

    Message update(HttpServletRequest request, DwStatCycleUpdateCommand command) throws DwException;
}
