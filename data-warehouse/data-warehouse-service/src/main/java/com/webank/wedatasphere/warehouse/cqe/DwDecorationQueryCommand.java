package com.webank.wedatasphere.warehouse.cqe;

import com.webank.wedatasphere.warehouse.cqe.common.PageCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DwDecorationQueryCommand extends PageCommand {
    private String typeName;
    private Boolean enabled;
}