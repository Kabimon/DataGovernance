package com.webank.wedatasphere.warehouse.cqe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DwStatCycleUpdateCommand {
    private Long id;
    private Long subjectDomainId;
    private Long layerId;
    private String name;
    private String nameAlias;
    private String description;
    private String statStartFormula;
    private String statEndFormula;
    private String availableRoles;
    private String chargeUser;
}
