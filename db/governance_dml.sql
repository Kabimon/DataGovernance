-- 插入 g8e_dw_layer 记录
INSERT INTO `g8e_dw_layer`(name, description, authority, preset, enabled, create_user, create_time, status, version) VALUES ('原数据层（ODS）', '由业务系统同步到数据仓库的原始数据，一般不经过加工', '所有角色', 1, 1, 'hdfs', '2021-09-01 00:00:00', 1, 1);
INSERT INTO `g8e_dw_layer`(name, description, authority, preset, enabled, create_user, create_time, status, version) VALUES ('明细层（DWD）', '从ods层经过ETL得到的明细数据，表示具体的事实', '所有角色', 1, 1, 'hdfs', '2021-09-01 00:00:00', 1, 1);
INSERT INTO `g8e_dw_layer`(name, description, authority, preset, enabled, create_user, create_time, status, version) VALUES ('汇总层（DWS）', '由明细数据经过汇总得到的数据，主要由统计维度和指标构成', '所有角色', 1, 1, 'hdfs', '2021-09-01 00:00:00', 1, 1);