/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-04-25 11:34:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_orders
-- ----------------------------
DROP TABLE IF EXISTS `m_orders`;
CREATE TABLE `m_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '下单用户id',
  `number` varchar(32) NOT NULL COMMENT '订单号',
  `createtime` datetime NOT NULL COMMENT '创建订单时间',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_orders_1` (`user_id`),
  CONSTRAINT `FK_orders_id` FOREIGN KEY (`user_id`) REFERENCES `m_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_orders
-- ----------------------------
INSERT INTO `m_orders` VALUES ('3', '1', '1000010', '2015-02-04 13:22:35', null);
INSERT INTO `m_orders` VALUES ('4', '1', '1000011', '2015-02-03 13:22:41', null);
INSERT INTO `m_orders` VALUES ('5', '10', '1000012', '2015-02-12 16:13:23', null);

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES ('1', '王五', null, '2', null);
INSERT INTO `m_user` VALUES ('10', '张三', '2014-07-10', '1', '北京市');
INSERT INTO `m_user` VALUES ('16', '张小明', null, '1', '北京市');
INSERT INTO `m_user` VALUES ('22', '陈小明', null, '1', '北京市');
INSERT INTO `m_user` VALUES ('24', '张三丰', null, '1', '北京市');
INSERT INTO `m_user` VALUES ('25', '陈小明', null, '1', '北京市');
INSERT INTO `m_user` VALUES ('26', '王五', null, null, null);