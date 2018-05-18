create database manage;




use manage;


CREATE TABLE IF NOT EXISTS `manage`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NULL COMMENT '用户名',
  `password` VARCHAR(45) NULL COMMENT '密码',
  `full_name` VARCHAR(100) NULL COMMENT '全名',
  `nickname` VARCHAR(100) NULL COMMENT '昵称',
  `dept_id` INT NULL COMMENT '部门id',
  `job_id` INT NULL COMMENT '职位id',
  `sex` TINYINT NULL DEFAULT -1 COMMENT '性别: -1:未知 ; 0:女;1:男',
  `birthday` VARCHAR(8) NULL COMMENT '生日',
  `img` VARCHAR(100) NULL COMMENT '头像',
  `email` VARCHAR(100) NULL COMMENT '邮件',
  `tel` VARCHAR(13) NULL COMMENT '电话',
  `create_time` DATETIME NULL DEFAULT current_timestamp COMMENT '创建时间',
  `modify_time` DATETIME NULL COMMENT '修改时间',
  `status` TINYINT NULL DEFAULT 0 COMMENT '状态 0: 正常; -1 :删除',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '用户表';


insert into `manage`.user (username,password,dept_id,job_id) values ("admin","111111",1,1);


CREATE TABLE IF NOT EXISTS `manage`.`dept` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NULL COMMENT '部门名称',
  `nickname` VARCHAR(100) NULL COMMENT '部门简称',
  `level` INT NULL COMMENT '排序',
  `p_id` INT NULL DEFAULT 0 COMMENT '上级部门id',
  `p_ids` VARCHAR(1000) NULL COMMENT '直接 或者间接 上级 id, 形如 [1],[2]',
  `create_time` DATETIME NULL DEFAULT current_timestamp COMMENT '创建时间',
  `modify_time` DATETIME NULL COMMENT '修改时间',
  `status` TINYINT NULL DEFAULT 0 COMMENT '状态 0: 正常; -1 :删除',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '部门';


insert into `manage`.dept (full_name,nickname,level, p_id, p_ids) values ("总公司","总公司", 0, 0, "[0]");


CREATE TABLE IF NOT EXISTS `manage`.`job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NULL COMMENT '全称',
  `nickname` VARCHAR(50) NULL COMMENT '职位简称',
  `dept_id` INT NULL,
  `create_time` DATETIME NULL DEFAULT current_timestamp,
  `modify_time` DATETIME NULL,
  `status` TINYINT NULL DEFAULT 0 COMMENT '状态 0: 正常; -1 :删除',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '职位';

insert into `manage`.job (full_name,nickname,dept_id) values ("超级管理员","总司令",1);




CREATE TABLE IF NOT EXISTS `manage`.`config` (
  `name` VARCHAR(100) NULL,
  `value` VARCHAR(100) NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `show_name` VARCHAR(100) NULL COMMENT '显示信息',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '参数配置';


insert into `manage`.config (name, value, show_name) values ("cpu", "80", "cpu利用率阀值(%)");
insert into `manage`.config (name, value, show_name) values ("mem", "7", "内存使用量阀值(G)");
insert into `manage`.config (name, value, show_name) values ("disk", "400", "磁盘使用量阀值(G)");


CREATE TABLE IF NOT EXISTS `manage`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cron` VARCHAR(50) NULL COMMENT '定时任务的执行时间',
  `name` VARCHAR(100) NULL COMMENT '任务名称',
  `group_id` INT NULL COMMENT '任务组id',
  `group_name` VARCHAR(100) NULL COMMENT '任务组名称',
  `create_time` DATETIME NULL DEFAULT current_timestamp,
  `modify_time` DATETIME NULL,
  `status` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '任务调度';




insert into `manage`.task (name,group_id,group_name, cron) values ('默认发送时间', 1,'系统监控任务','0 0 12 * * ?');

CREATE TABLE IF NOT EXISTS `manage`.`menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL COMMENT '菜单名称',
  `icon` VARCHAR(100) NULL COMMENT '菜单图标',
  `url` VARCHAR(100) NULL COMMENT '菜单链接',
  `level` INT NULL COMMENT '菜单层级',
  `num` INT NULL COMMENT '菜单排序',
  `p_id` INT NULL COMMENT '父菜单',
  `code` VARCHAR(45) NULL COMMENT '关键字，用于菜单高亮，不能重复',
  `create_time` DATETIME NULL DEFAULT current_timestamp,
  `modify_time` DATETIME NULL,
  `status` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `key_UNIQUE` (`code` ASC))
ENGINE = InnoDB;


insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('系统配置','fa fa-gear','#',1,1,0,'system');
insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('系统参数','fa fa-wrench','/back/system/home',2,1,1,'setting');
insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('用户管理','fa fa-user','/back/users/home',2,2,1,'user-manage');
insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('岗位管理','fa fa-navicon','/back/jobs/home',2,3,1,'job-manage');
insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('部门管理','fa fa-group','/back/dept/home',2,4,1,'dept-manage');
insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('组件管理','fa fa-cube','/back/component/home',1,2,0,'component');
insert into `manage`.menu (name, icon, url, level, num, p_id, code) values ('平台监控和告警','fa fa-warning','/back/monitor/home',1,3,0,'monitor');


CREATE TABLE IF NOT EXISTS `manage`.`job_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `menu_id` INT NULL,
  `job_id` INT NULL,
  `create_time` DATETIME NULL DEFAULT current_timestamp,
  `status` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

insert into `manage`.`job_menu` (job_id,menu_id) values (1, 1);
insert into `manage`.`job_menu` (job_id,menu_id) values (1, 2);
insert into `manage`.`job_menu` (job_id,menu_id) values (1, 3);
insert into `manage`.`job_menu` (job_id,menu_id) values (1, 4);
insert into `manage`.`job_menu` (job_id,menu_id) values (1, 5);
insert into `manage`.`job_menu` (job_id,menu_id) values (1, 6);
insert into `manage`.`job_menu` (job_id,menu_id) values (1, 7);
