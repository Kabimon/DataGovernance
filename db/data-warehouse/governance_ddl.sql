DROP TABLE IF EXISTS `g8e_dw_layer`;
CREATE TABLE `g8e_dw_layer`  (
    id bigint auto_increment,
    name varchar(64) not null unique,
    description varchar(255),
    authority text,
    preset bit not null default 0 comment '0 自定义 1 预置',
    enabled bit not null default 1,
    create_user varchar(32) not null,
    create_time datetime not null,
    modify_user varchar(32),
    modify_time datetime,
    status bit default 1 comment '0 删除 1 正常',
    version bigint default 1,
    primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '数仓规划 - 分层信息表';

DROP TABLE IF EXISTS `g8e_dw_subject_domain`;
CREATE TABLE `g8e_dw_subject_domain`  (
    id bigint auto_increment,
    parent_id int default null,
    name varchar(64) not null unique,
    description varchar(255),
    authority text,
    create_user varchar(32) not null,
    create_time datetime not null,
    modify_user varchar(32),
    modify_time datetime,
    status bit default 1 comment '0 删除 1 正常',
    version bigint default 1,
    primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '数仓规划 - 主题域信息表';

DROP TABLE IF EXISTS `g8e_dw_decoration`;
CREATE TABLE `g8e_dw_decoration`  (
    id bigint auto_increment,
    subject_domain_id bigint not null,
    layer_id bigint not null,
    type_name varchar(64) not null unique comment '修饰词类别',
    type_name_alias varchar(64) comment '英文别名',
    description varchar(255),
    decoration_list text comment '修饰词列表：[{name: "", identifier: "", formula: ""}, {...}]',
    enabled bit not null default 1 comment '启用状态，1：启用 0：禁用',
    create_user varchar(32) not null,
    create_time datetime not null,
    modify_user varchar(32),
    modify_time datetime,
    status bit default 1 comment '0 删除 1 正常',
    version bigint default 1,
    primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '数仓规划 - 修饰词信息表';

DROP TABLE IF EXISTS `g8e_dw_stat_cycle`;
CREATE TABLE `g8e_dw_stat_cycle`  (
    id bigint auto_increment,
    subject_domain_id bigint not null,
    layer_id bigint not null,
    name varchar(64) not null unique comment '统计周期名称',
    name_alias varchar(64) comment '统计周期英文别名',
    description varchar(255),
    stat_start_formula varchar(255) comment '统计开始时间公式',
    stat_end_formula varchar(255) comment '统计结束时间公式',
    enabled bit not null default 1 comment '启用状态，1：启用 0：禁用',
    create_user varchar(32) not null,
    create_time datetime not null,
    charge_user text comment '负责人列表',
    modify_user varchar(32),
    modify_time datetime,
    status bit default 1 comment '0 删除 1 正常',
    version bigint default 1,
    primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '数仓规划 - 统计周期信息表';