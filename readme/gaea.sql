/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : gaea

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 05/07/2020 18:16:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pid` int(0) NULL DEFAULT NULL COMMENT '父类',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_info
-- ----------------------------
INSERT INTO `category_info` VALUES (21, '福鼎白茶', 0, NULL);
INSERT INTO `category_info` VALUES (22, '白毫银针', 21, 'attach/2020/05/01/75999da99380e67afc3fcade31f475d4.jpg');
INSERT INTO `category_info` VALUES (23, '白牡丹', 21, 'attach/2020/05/01/baa60e064a502842b2a5fdabb6c52567.jpg');
INSERT INTO `category_info` VALUES (25, '寿眉', 21, 'attach/2020/05/01/e154c74fb2cb59805d85407e294f1a03.jpg');
INSERT INTO `category_info` VALUES (26, '茶具', 0, NULL);
INSERT INTO `category_info` VALUES (27, '福鼎小吃', 0, NULL);

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(18, 4) NOT NULL,
  `weight` decimal(18, 4) NOT NULL COMMENT '重量',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '类别',
  `buy_num` int(0) NULL DEFAULT NULL COMMENT '月销数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (159, '2017年寿眉-C01', 'attach/2020/04/17/58.jpg', 300.0000, 0.1000, 25, 3);
INSERT INTO `item` VALUES (160, '2020白毫银针-A01', 'attach/2020/04/17/61.jpg', 1000.0000, 0.3500, 22, 0);
INSERT INTO `item` VALUES (162, '2018年一级白牡丹', 'attach/2020/05/01/77.jpg', 1500.0000, 0.5000, 23, 123);
INSERT INTO `item` VALUES (163, '2014年贡眉-A01', 'attach/2020/05/06/82.jpg', 3000.0000, 0.3000, 25, 3);
INSERT INTO `item` VALUES (164, '2006年老寿眉-B01', 'attach/2020/05/06/83.jpg', 500.0000, 0.2000, 25, 6);
INSERT INTO `item` VALUES (165, '2012老寿眉-A01', 'attach/2020/05/06/84.jpg', 400.0000, 0.6000, 25, 1);
INSERT INTO `item` VALUES (166, '2018白毫银针-A02', 'attach/2020/05/06/85.jpg', 1000.0000, 0.5000, 22, 3);
INSERT INTO `item` VALUES (167, '2018白牡丹散茶-B01', 'attach/2020/05/06/86.jpg', 300.0000, 0.5000, 23, 12);
INSERT INTO `item` VALUES (168, '2018年牡丹王-A01', 'attach/2020/05/06/87.jpg', 600.0000, 0.3000, 23, 10);
INSERT INTO `item` VALUES (169, '2019年白毫银针-A01', 'attach/2020/05/06/88.jpg', 900.0000, 0.3500, 22, 8);

-- ----------------------------
-- Table structure for receipt_info
-- ----------------------------
DROP TABLE IF EXISTS `receipt_info`;
CREATE TABLE `receipt_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `true_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `zip_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `default_flag` tinyint(0) UNSIGNED NOT NULL DEFAULT 0,
  `province_id` int(0) UNSIGNED NOT NULL DEFAULT 0,
  `city_id` int(0) UNSIGNED NOT NULL DEFAULT 0,
  `town_id` int(0) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receipt_info
-- ----------------------------
INSERT INTO `receipt_info` VALUES (1, 1, '九号楼', '15620606954', '张三', '', 0, 1, 37, 567);

SET FOREIGN_KEY_CHECKS = 1;
