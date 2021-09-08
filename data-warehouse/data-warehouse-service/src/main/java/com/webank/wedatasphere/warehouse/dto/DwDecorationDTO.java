package com.webank.wedatasphere.warehouse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DwDecorationDTO {
    private Long id;
    private String typeName;
    private String typeNameAlias;
    private String description;
}
