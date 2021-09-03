package com.webank.wedatasphere.warehouse.cqe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DwSubjectDomainUpdateCommand {
    private Long id;
    private String name;
    private String description;
    private String authority;
}
