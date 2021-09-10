package com.webank.wedatasphere.warehouse.cqe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DwSubjectDomainCreateCommand {
    private String name;
    private String nameAlias;
    private String chargeUser;
    private String availableRoles;
    private String description;
    private String authority;
}
