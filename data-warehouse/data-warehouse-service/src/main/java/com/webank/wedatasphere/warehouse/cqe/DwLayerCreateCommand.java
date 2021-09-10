package com.webank.wedatasphere.warehouse.cqe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DwLayerCreateCommand {
    private String name;
    private String nameAlias;
    private String availableDbs;
    private String autoCollectStrategy;
    private String chargeUser;
    private String description;
    private String authority;
}
