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
@TableName("g8e_dw_layer")
public class DwLayer {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String authority;

    private Boolean preset;

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
