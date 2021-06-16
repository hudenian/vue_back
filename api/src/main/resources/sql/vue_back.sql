/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : vue_back

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-06-14 21:41:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(20) NOT NULL COMMENT '权限名称',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `perms` varchar(512) DEFAULT NULL COMMENT '权限标识',
  `type` tinyint(4) NOT NULL COMMENT '菜单权限类型: 1-目录, 2-菜单, 3-按钮',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `seq_no` bigint(20) DEFAULT NULL COMMENT '序号',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '3', 'SQL 监控', '/druid/sql.html', '', '2', '', '102', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('2', '0', '组织管理', 'xx.html', '', '1', '', '100', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('3', '0', '系统管理', 'xx.html', '', '1', '', '102', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('4', '3', '接口管理', '/swagger-ui.html', '', '2', '', '100', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('5', '3', '日志管理', '/index/logs', '', '2', '', '100', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('6', '2', '菜单权限管理', '/index/menus', '', '2', '', '102', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('7', '2', '用户管理', '/index/users', '', '2', '', '100', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('8', '2', '部门管理', '/index/depts', '', '2', '', '100', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');
INSERT INTO `t_permission` VALUES ('9', '2', '角色管理', '/index/roles', '', '2', '', '99', '1', '2021-06-13 15:29:08', '2021-06-13 15:29:08');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID(自增长)',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '菜单权限ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '盐值',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别 0男 1女',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USER_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO `t_user` VALUES ('1', 'hudenian', '1f5867546e3cb94c9b2198570cba447b', '78a8b04a010159a3c930092196bf4ffdba20cef39ce953fb', null, null, null, null, null, '1', '2021-06-16 23:35:24', '2021-06-16 23:35:24');
INSERT INTO `t_user` VALUES ('2', 'hdn', 'e0202aa970c8bd89c8487d5b13ea7477', '9f4b75dab420320ae9b8251ba0a139aa92c073abca2f4927', null, null, null, null, null, '1', '2021-06-16 23:36:39', '2021-06-16 23:36:39');
INSERT INTO `t_user` VALUES ('3', 'zhangsan', '2b7b9820f13129f1622f682e8a8016d9', '224dcb520a34e466b7c3c0162747258551a8c1ad61be3645', null, null, null, null, null, '1', '2021-06-16 23:37:18', '2021-06-16 23:37:18');
INSERT INTO `t_user` VALUES ('4', 'lishi', 'da828e2fecd1adc5ae13e1dcddeb1d74', '87884720f645d5e86eddb293f1876bf62298eb434a6f30a5', null, null, null, null, null, '1', '2021-06-16 23:37:38', '2021-06-16 23:37:38');
INSERT INTO `t_user` VALUES ('5', 'wangwu', '98af159bea58faaf5dc13eaeccd061e6', '2de5ea58914aa292dd2d584e8ad3d019abe52c778453ebf4', null, null, null, null, null, '1', '2021-06-16 23:37:57', '2021-06-16 23:37:57');
INSERT INTO `t_user` VALUES ('6', 'zhaoliu', '1b396c2420a69db93b403a48957af1ab', '2cb7311288fadda0dd419456446bc66f26ce9a999d07598e', null, null, null, null, null, '1', '2021-06-16 23:38:19', '2021-06-16 23:38:19');
INSERT INTO `t_user` VALUES ('7', 'sunqi', 'de51fe899393eaee2425c10c27c2eac0', '687f814b2942562770e605f7a95a571aaa11b7bf4886e2bc', null, null, null, null, null, '1', '2021-06-16 23:38:38', '2021-06-16 23:38:38');
INSERT INTO `t_user` VALUES ('8', 'zhuba', 'a15fd5a17ff1d4c83608263e7f26560a', '2c4cc256b32cfa06e8732d2ae0f081ead474e4c72e48cc6d', null, null, null, null, null, '1', '2021-06-16 23:39:01', '2021-06-16 23:39:01');
INSERT INTO `t_user` VALUES ('9', 'chener', '87f9704aaf7c5cdcb386d1f4b6cda0fc', 'a76a908331f437a4ff822401d95ad24fd87caf390edbe35c', null, null, null, null, null, '1', '2021-06-16 23:39:33', '2021-06-16 23:39:33');
INSERT INTO `t_user` VALUES ('10', 'wangyi', '01a764eddb6dfcaa7c9540796c45b69d', 'c0d8ee270c10f9f03467fb43aadcb72c91bdd09b10090df9', null, null, null, null, null, '1', '2021-06-16 23:39:53', '2021-06-16 23:39:53');
INSERT INTO `t_user` VALUES ('11', 'hushiwu', '9782e046b9d2295f7576f77c4ba48aec', '74cded42108d8f6d90f900d3f5b9d97e7f55aa7870388d3c', null, null, null, null, null, '1', '2021-06-16 23:40:16', '2021-06-16 23:40:16');


-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色ID(自增长)',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
