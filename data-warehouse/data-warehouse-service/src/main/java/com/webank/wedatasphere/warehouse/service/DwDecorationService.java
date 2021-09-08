package com.webank.wedatasphere.warehouse.service;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.DwDecorationCreateCommand;
import com.webank.wedatasphere.warehouse.cqe.DwDecorationQueryCommand;
import com.webank.wedatasphere.warehouse.cqe.DwDecorationUpdateCommand;
import com.webank.wedatasphere.warehouse.exception.DwException;

import javax.servlet.http.HttpServletRequest;

public interface DwDecorationService {
    Message queryPage(HttpServletRequest request, DwDecorationQueryCommand command) throws DwException;

    Message create(HttpServletRequest request, DwDecorationCreateCommand command) throws DwException;

    Message getById(HttpServletRequest request, Long id) throws DwException;

    Message deleteById(HttpServletRequest request, Long id) throws DwException;

    Message update(HttpServletRequest request, DwDecorationUpdateCommand command) throws DwException;
}
