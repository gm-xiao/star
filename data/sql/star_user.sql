/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50725
Source Host           : 127.0.0.1:3306
Source Database       : star_user

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-07 15:42:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` varchar(64) NOT NULL COMMENT 'Id',
  `code` varchar(64) DEFAULT NULL COMMENT '权限编码',
  `level` int(11) DEFAULT NULL COMMENT '权限等级',
  `name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父级权限',
  `sort` int(11) DEFAULT NULL COMMENT '权限顺序',
  `type` varchar(32) DEFAULT NULL COMMENT '权限类型(0.菜单 1.动作)',
  `url` varchar(64) DEFAULT NULL COMMENT '访问路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限';

-- ----------------------------
-- Records of power
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(64) NOT NULL COMMENT 'Id',
  `create_id` varchar(20) DEFAULT NULL COMMENT '创建人Id',
  `create_name` varchar(30) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` varchar(20) DEFAULT NULL COMMENT '修改人Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(30) DEFAULT NULL COMMENT '修改人姓名',
  `code` varchar(30) DEFAULT NULL COMMENT '角色编码',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(225) DEFAULT NULL COMMENT '角色说明',
  `sort` int(11) DEFAULT NULL COMMENT '排序值',
  `status` varchar(4) DEFAULT NULL COMMENT '角色状态(0.冻结 1.启用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_power
-- ----------------------------
DROP TABLE IF EXISTS `role_power`;
CREATE TABLE `role_power` (
  `id` varchar(64) NOT NULL COMMENT 'Id',
  `power_id` varchar(64) DEFAULT NULL COMMENT '权限ID',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限';

-- ----------------------------
-- Records of role_power
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL COMMENT 'Id',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `role` varchar(32) DEFAULT NULL COMMENT '角色',
  `examine` varchar(4) DEFAULT NULL COMMENT '认证(0.未认证 1.处理中 2.已认证 3.认证失败)',
  `is_delete` varchar(2) DEFAULT NULL COMMENT '是否已删除(0.未删除 1.已删除',
  `address` varchar(125) DEFAULT NULL COMMENT '地址',
  `code` varchar(30) DEFAULT NULL COMMENT '编号',
  `examine_time` datetime DEFAULT NULL COMMENT '认证时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd` varchar(125) DEFAULT NULL COMMENT '密码',
  `sex` varchar(30) DEFAULT NULL COMMENT '性别',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0.正常 1.冻结)',
  `invitation_code` varchar(32) DEFAULT NULL COMMENT '邀请码',
  `poll_code` varchar(32) DEFAULT NULL COMMENT '注册码',
  `owner_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `parent_name` varchar(30) DEFAULT NULL COMMENT '上级用户姓名',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `level` int(11) DEFAULT NULL COMMENT '代理等级(0.平台 1.一级代理 2.二级代理)',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '上级用户ID',
  `parent_mobile` varchar(30) DEFAULT NULL COMMENT '上级用户手机号',
  `create_id` varchar(20) DEFAULT NULL COMMENT '创建人Id',
  `create_name` varchar(30) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` varchar(20) DEFAULT NULL COMMENT '修改人Id',
  `modify_name` varchar(30) DEFAULT NULL COMMENT '修改人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `website` varchar(255) DEFAULT NULL COMMENT '官网地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('550744210001625088', '15817123702', 'gm', null, '0', '0', null, '15817123702', null, null, '$2a$10$uOCGiOGcI8DZyb9oLY1L7.GnoHuRRIDl32fNoEY7VK8V1Fc5kBykm', null, '0', null, null, null, null, null, null, null, null, null, null, null, '2019-02-28 18:20:52', null, null, null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(64) NOT NULL COMMENT 'Id',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
