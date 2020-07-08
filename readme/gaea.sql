/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : gaea

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-07-08 21:09:03
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
INSERT INTO `category_info` VALUES ('21', '历史文化', '0', null);
INSERT INTO `category_info` VALUES ('22', '古籍', '21', 'attach/2020/05/01/75999da99380e67afc3fcade31f475d4.jpg');
INSERT INTO `category_info` VALUES ('23', '宗教', '21', 'attach/2020/05/01/baa60e064a502842b2a5fdabb6c52567.jpg');
INSERT INTO `category_info` VALUES ('25', '哲学', '21', 'attach/2020/05/01/e154c74fb2cb59805d85407e294f1a03.jpg');
INSERT INTO `category_info` VALUES ('26', '文艺', '0', null);
INSERT INTO `category_info` VALUES ('27', '科技', '0', null);

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
INSERT INTO `item_info` VALUES ('159', '逻辑思维', '300.0000', '0.1000', '25', '3', null);
INSERT INTO `item_info` VALUES ('160', '春秋传', '1000.0000', '0.3500', '22', '0', null);
INSERT INTO `item_info` VALUES ('162', '心经', '1500.0000', '0.5000', '23', '123', null);
INSERT INTO `item_info` VALUES ('163', '沉思录', '3000.0000', '0.3000', '25', '3', null);
INSERT INTO `item_info` VALUES ('164', '矛盾论', '500.0000', '0.2000', '25', '6', null);
INSERT INTO `item_info` VALUES ('165', '厚黑学', '400.0000', '0.6000', '25', '1', null);
INSERT INTO `item_info` VALUES ('166', '公羊传', '1000.0000', '0.5000', '22', '3', '<p>2018白毫银针-A02款 很实惠的一款白毫银针</p>');
INSERT INTO `item_info` VALUES ('167', '道德经', '300.0000', '0.5000', '23', '12', null);
INSERT INTO `item_info` VALUES ('168', '圣经', '600.0000', '0.3000', '23', '10', null);
INSERT INTO `item_info` VALUES ('169', '列国传', '900.0000', '0.3500', '22', '8', null);

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
INSERT INTO `kefu_log` VALUES ('72ec9a8e8135ff532a36a96f6d43b686', '3', '2020-07-08 13:01:05', '1', '11');

-- ----------------------------
-- Table structure for order_bill
-- ----------------------------
DROP TABLE IF EXISTS `order_bill`;
CREATE TABLE `order_bill` (
  `id` varchar(40) NOT NULL,
  `order_no` varchar(40) NOT NULL COMMENT '订单编号',
  `trade_no` varchar(40) DEFAULT NULL COMMENT '支付订单流水号',
  `logistics_id` varchar(40) DEFAULT NULL COMMENT '物流信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `sum_money` decimal(16,2) NOT NULL COMMENT '订单金额',
  `pay_type` tinyint(2) DEFAULT NULL COMMENT '支付类型',
  `pay_money` decimal(16,2) DEFAULT NULL COMMENT '支付金额',
  `item_amount_total` decimal(16,2) DEFAULT NULL COMMENT '订单商品数量合计',
  `freight` decimal(16,2) DEFAULT NULL COMMENT '运费',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) NOT NULL COMMENT '用户ID',
  `state` tinyint(3) NOT NULL COMMENT '单据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_bill
-- ----------------------------
INSERT INTO `order_bill` VALUES ('13d791f9aa5a439da4e9f08b76668301', 'DD2007082105031000', null, '49d8759c5027401b8f7089fc2d137413', '', '1000.00', '100', '1000.00', '1.00', '0.00', '2020-07-08 21:05:03', '3', '0');
INSERT INTO `order_bill` VALUES ('9aede27497664e93bb870b46d1dbe963', 'DD2007082050041001', null, '6284503108474b3aa1109d67a4f08ac1', '', '5000.00', '100', '5000.00', '5.00', '0.00', '2020-07-08 20:50:04', '3', '0');
INSERT INTO `order_bill` VALUES ('e61f150310334b6d8da0f3141cd539be', 'DD2007082057401000', null, '0cf157b64d5d40a58901b77c7b8f63aa', '', '2000.00', '100', '2000.00', '2.00', '0.00', '2020-07-08 20:57:40', '3', '0');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` varchar(40) NOT NULL,
  `order_id` varchar(40) NOT NULL COMMENT '订单ID',
  `item_id` varchar(40) NOT NULL COMMENT '商品ID',
  `item_name` varchar(255) NOT NULL COMMENT '商品名',
  `item_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `amount` decimal(16,2) NOT NULL COMMENT '购买数量',
  `sum_money` decimal(16,2) NOT NULL COMMENT '金额小计',
  `remark` varchar(255) NOT NULL COMMENT '商品备注',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) NOT NULL,
  `item_img_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('4318f60e16f523484e1a4cdd43c43ab9', '13d791f9aa5a439da4e9f08b76668301', '166', '公羊传', '1000.00', '1.00', '1000.00', '', '2020-07-08 21:05:03', '3', 'attach/2020/05/06/85.jpg');
INSERT INTO `order_detail` VALUES ('6edc3b5cc69c1d0f683b6807165c8b1e', 'e61f150310334b6d8da0f3141cd539be', '160', '春秋传', '1000.00', '2.00', '2000.00', '', '2020-07-08 20:57:40', '3', 'attach/2020/04/17/61.jpg');
INSERT INTO `order_detail` VALUES ('79cd255eef1865f8d49304539dc8c531', '9aede27497664e93bb870b46d1dbe963', '166', '公羊传', '1000.00', '5.00', '5000.00', '', '2020-07-08 20:50:04', '3', 'attach/2020/05/06/85.jpg');

-- ----------------------------
-- Table structure for order_logistics
-- ----------------------------
DROP TABLE IF EXISTS `order_logistics`;
CREATE TABLE `order_logistics` (
  `id` varchar(40) NOT NULL,
  `order_id` varchar(40) NOT NULL COMMENT '订单ID',
  `express_no` varchar(40) DEFAULT NULL COMMENT '快递单号',
  `cnee_realname` varchar(100) NOT NULL COMMENT '收货人姓名',
  `cnee_telephone` varchar(20) NOT NULL COMMENT '收货人手机号',
  `cnee_address` varchar(100) NOT NULL COMMENT '收货人地址',
  `freight` decimal(16,2) NOT NULL COMMENT '运费',
  `delivery_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发货时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '物流更新时间',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL COMMENT '物流状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_logistics
-- ----------------------------
INSERT INTO `order_logistics` VALUES ('0cf157b64d5d40a58901b77c7b8f63aa', 'e61f150310334b6d8da0f3141cd539be', null, '王麻', '119', 'AAAA级', '0.00', null, '2020-07-08 20:57:40', '2020-07-08 20:57:40', '101');
INSERT INTO `order_logistics` VALUES ('35cc917c744f4f63b7fd580da4d19521', '87e34f6336a14657a9d7ea48570baacb', null, '王五', '114', '啊啊啊1号', '0.00', null, '2020-07-08 08:44:23', '2020-07-08 08:44:23', '101');
INSERT INTO `order_logistics` VALUES ('405f6a863a664a7f8597a60cb7299e49', 'd21f8604ff4841ff9a762f0c6fcdeb1f', null, '王五', '114', '啊啊啊1号', '0.00', null, '2020-07-08 14:29:45', '2020-07-08 14:29:45', '101');
INSERT INTO `order_logistics` VALUES ('49d8759c5027401b8f7089fc2d137413', '13d791f9aa5a439da4e9f08b76668301', null, '王麻', '119', 'AAAA级', '0.00', null, '2020-07-08 21:05:03', '2020-07-08 21:05:03', '101');
INSERT INTO `order_logistics` VALUES ('6284503108474b3aa1109d67a4f08ac1', '9aede27497664e93bb870b46d1dbe963', null, '王麻', '119', 'AAAA级', '0.00', null, '2020-07-08 20:50:04', '2020-07-08 20:50:04', '101');

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
INSERT INTO `receipt_info` VALUES ('651d44bc61e37212e1f369f9d2a42f46', '3', 'AAAA级', '119', '王麻', '', '0', '0', '0', '0');
INSERT INTO `receipt_info` VALUES ('ec1ef77a92225398d9c5cc45a98bfdc8', '3', '19号', '122', '李四', '', '0', '9', '153', '1902');

-- ----------------------------
-- Table structure for shop_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart_item`;
CREATE TABLE `shop_cart_item` (
  `id` varchar(40) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `amount` decimal(10,2) NOT NULL COMMENT '购物车数量',
  `price` decimal(10,2) NOT NULL COMMENT '加购价格',
  `create_time` datetime NOT NULL COMMENT '加购时间',
  `item_name` varchar(100) NOT NULL COMMENT '商品加购名称',
  `item_id` int(11) NOT NULL COMMENT '商品主键',
  `item_img_url` varchar(200) NOT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of shop_cart_item
-- ----------------------------

-- ----------------------------
-- Table structure for user_fav_item
-- ----------------------------
DROP TABLE IF EXISTS `user_fav_item`;
CREATE TABLE `user_fav_item` (
  `id` varchar(40) NOT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `item_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_fav_item
-- ----------------------------
INSERT INTO `user_fav_item` VALUES ('1280710762852839426', '3', '160');

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
INSERT INTO `user_info` VALUES ('3', 'ssaab', '110', 'qq', 'upload/f308b4e4-dd77-4a6e-8e37-f722e6b2688a20190705133636_11038.jpg', '123456');
