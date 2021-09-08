package com.webank.wedatasphere.warehouse.cqe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DwDecorationCreateCommand {
    private Long subjectDomainId;

    private Long layerId;

    private String typeName;

    private String typeNameAlias;

    private String description;

    private String decorationList;
}
