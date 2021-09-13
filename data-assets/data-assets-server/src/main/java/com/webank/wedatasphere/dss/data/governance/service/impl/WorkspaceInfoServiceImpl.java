/*
 *
 *  * Copyright 2019 WeBank
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.webank.wedatasphere.dss.data.governance.service.impl;

import com.webank.wedatasphere.dss.data.governance.dao.WorkspaceInfoMapper;
import com.webank.wedatasphere.dss.data.governance.exception.DataGovernanceException;
import com.webank.wedatasphere.dss.data.governance.service.WorkspaceInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: chaogefeng
 * @Date: 2020/3/9
 */
@Service
public class WorkspaceInfoServiceImpl implements WorkspaceInfoService {

    @Autowired
    private WorkspaceInfoMapper workspaceInfoMapper;


    @Override
    public List<String> getWorkspaceUsers(int workspaceId,String search) throws DataGovernanceException {

        List<String> workspaceUsers = workspaceInfoMapper.getWorkspaceUsersName(workspaceId,search);
        return  workspaceUsers;
    }
}
