/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : gaea

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-07-07 10:14:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '父类',
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category_info
-- ----------------------------
INSERT INTO `category_info` VALUES ('21', '福鼎白茶', '0', null);
INSERT INTO `category_info` VALUES ('22', '白毫银针', '21', 'attach/2020/05/01/75999da99380e67afc3fcade31f475d4.jpg');
INSERT INTO `category_info` VALUES ('23', '白牡丹', '21', 'attach/2020/05/01/baa60e064a502842b2a5fdabb6c52567.jpg');
INSERT INTO `category_info` VALUES ('25', '寿眉', '21', 'attach/2020/05/01/e154c74fb2cb59805d85407e294f1a03.jpg');
INSERT INTO `category_info` VALUES ('26', '茶具', '0', null);
INSERT INTO `category_info` VALUES ('27', '福鼎小吃', '0', null);

-- ----------------------------
-- Table structure for item_img
-- ----------------------------
DROP TABLE IF EXISTS `item_img`;
CREATE TABLE `item_img` (
  `id` varchar(40) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `default_flag` tinyint(4) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of item_img
-- ----------------------------
INSERT INTO `item_img` VALUES ('1', '159', '1', 'attach/2020/04/17/58.jpg');
INSERT INTO `item_img` VALUES ('10', '169', '1', 'attach/2020/05/06/88.jpg');
INSERT INTO `item_img` VALUES ('2', '160', '1', 'attach/2020/04/17/61.jpg');
INSERT INTO `item_img` VALUES ('3', '162', '1', 'attach/2020/05/01/77.jpg');
INSERT INTO `item_img` VALUES ('4', '163', '1', 'attach/2020/05/06/82.jpg');
INSERT INTO `item_img` VALUES ('5', '164', '1', 'attach/2020/05/06/83.jpg');
INSERT INTO `item_img` VALUES ('6', '165', '1', 'attach/2020/05/06/84.jpg');
INSERT INTO `item_img` VALUES ('7', '166', '1', 'attach/2020/05/06/85.jpg');
INSERT INTO `item_img` VALUES ('8', '167', '1', 'attach/2020/05/06/86.jpg');
INSERT INTO `item_img` VALUES ('9', '168', '1', 'attach/2020/05/06/87.jpg');

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(18,4) NOT NULL,
  `weight` decimal(18,4) NOT NULL COMMENT '重量',
  `category_id` int(11) DEFAULT NULL COMMENT '类别',
  `buy_num` int(11) DEFAULT NULL COMMENT '月销数量',
  `content` varchar(255) DEFAULT NULL COMMENT '商品详情内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES ('159', '2017年寿眉-C01', '300.0000', '0.1000', '25', '3', null);
INSERT INTO `item_info` VALUES ('160', '2020白毫银针-A01', '1000.0000', '0.3500', '22', '0', null);
INSERT INTO `item_info` VALUES ('162', '2018年一级白牡丹', '1500.0000', '0.5000', '23', '123', null);
INSERT INTO `item_info` VALUES ('163', '2014年贡眉-A01', '3000.0000', '0.3000', '25', '3', null);
INSERT INTO `item_info` VALUES ('164', '2006年老寿眉-B01', '500.0000', '0.2000', '25', '6', null);
INSERT INTO `item_info` VALUES ('165', '2012老寿眉-A01', '400.0000', '0.6000', '25', '1', null);
INSERT INTO `item_info` VALUES ('166', '2018白毫银针-A02', '1000.0000', '0.5000', '22', '3', '<p>2018白毫银针-A02款 很实惠的一款白毫银针</p>');
INSERT INTO `item_info` VALUES ('167', '2018白牡丹散茶-B01', '300.0000', '0.5000', '23', '12', null);
INSERT INTO `item_info` VALUES ('168', '2018年牡丹王-A01', '600.0000', '0.3000', '23', '10', null);
INSERT INTO `item_info` VALUES ('169', '2019年白毫银针-A01', '900.0000', '0.3500', '22', '8', null);

-- ----------------------------
-- Table structure for kefu_log
-- ----------------------------
DROP TABLE IF EXISTS `kefu_log`;
CREATE TABLE `kefu_log` (
  `id` varchar(40) NOT NULL,
  `user_id` varchar(40) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(2) NOT NULL COMMENT '状态',
  `content` varchar(200) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of kefu_log
-- ----------------------------
INSERT INTO `kefu_log` VALUES ('1', '3', '2020-07-07 09:25:11', '1', '有券吗？');
INSERT INTO `kefu_log` VALUES ('590fecce32391083d0b07115546f6685', '3', '2020-07-07 09:58:11', '1', '有人吗');

-- ----------------------------
-- Table structure for receipt_info
-- ----------------------------
DROP TABLE IF EXISTS `receipt_info`;
CREATE TABLE `receipt_info` (
  `id` varchar(40) NOT NULL,
  `user_id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL DEFAULT '',
  `telephone` varchar(255) NOT NULL DEFAULT '',
  `true_name` varchar(255) NOT NULL DEFAULT '',
  `zip_code` varchar(255) NOT NULL DEFAULT '',
  `default_flag` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `province_id` int(10) unsigned NOT NULL DEFAULT '0',
  `city_id` int(10) unsigned NOT NULL DEFAULT '0',
  `town_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of receipt_info
-- ----------------------------
INSERT INTO `receipt_info` VALUES ('1', '1', '九号楼', '15620606954', '张三三', '', '0', '0', '0', '0');
INSERT INTO `receipt_info` VALUES ('ec1ef77a92225398d9c5cc45a98bfdc8', '3', '19号', '122', '李四', '', '0', '9', '153', '1902');

-- ----------------------------
-- Table structure for shop_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart_item`;
CREATE TABLE `shop_cart_item` (
  `id` varchar(40) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户主键',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '购物车数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '加购价格',
  `create_time` datetime DEFAULT NULL COMMENT '加购时间',
  `title` varchar(255) DEFAULT NULL COMMENT '商品加购名称',
  `item_id` int(11) DEFAULT NULL COMMENT '商品主键',
  `item_img_url` varchar(255) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of shop_cart_item
-- ----------------------------
INSERT INTO `shop_cart_item` VALUES ('35bee208c058aea7ad9b0e5b31de33c7', '3', '5.00', '3000.00', '2020-07-06 17:43:05', '2014年贡眉-A01', '163', 'attach/2020/05/06/82.jpg');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(40) NOT NULL,
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `telephone` varchar(255) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `paypwd` varchar(255) DEFAULT NULL COMMENT '支付密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('3', 'ssaab', '110', 'ss', '099b05b8-c433-4374-9a92-e87f5540fc2e下载.jpg', 'aaaaa');
