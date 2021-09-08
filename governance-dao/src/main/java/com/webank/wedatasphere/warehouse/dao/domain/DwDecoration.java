package com.webank.wedatasphere.warehouse.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName("g8e_dw_decoration")
public class DwDecoration {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "subject_domain_id")
    private Long subjectDomainId;

    @TableField(value = "layer_id")
    private Long layerId;

    @TableField(value = "type_name")
    private String typeName;

    @TableField(value = "type_name_alias")
    private String typeNameAlias;

    private String description;

    @TableField(value = "decoration_list")
    private String decorationList;

    private Boolean enabled;

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "modify_user")
    private String modifyUser;

    @TableField(value = "modify_time")
    private Date modifyTime;

    private Boolean status;

    private Long version;
}
