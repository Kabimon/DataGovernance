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