package com.webank.wedatasphere.warehouse.service;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.*;
import com.webank.wedatasphere.warehouse.exception.DwException;

import javax.servlet.http.HttpServletRequest;

public interface DwSubjectDomainService {
    Message queryPage(HttpServletRequest request, DwSubjectDomainQueryCommand command) throws DwException;

    Message create(HttpServletRequest request, DwSubjectDomainCreateCommand command) throws DwException;

    Message getById(HttpServletRequest request, Long id) throws DwException;

    Message deleteById(HttpServletRequest request, Long id) throws DwException;

    Message update(HttpServletRequest request, DwSubjectDomainUpdateCommand command) throws DwException;
}
