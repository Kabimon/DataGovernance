package com.webank.wedatasphere.warehouse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DwSubjectDomainListItemDTO extends DwSubjectDomainDTO {
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
