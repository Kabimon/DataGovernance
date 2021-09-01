package com.webank.wedatasphere.warehouse.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DwLayerListItemDTO extends DwLayerDTO {
    private Boolean preset;
    private Boolean enabled;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
