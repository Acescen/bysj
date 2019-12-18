/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : bysj

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 18/12/2019 10:50:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nuc_record
-- ----------------------------
DROP TABLE IF EXISTS `nuc_record`;
CREATE TABLE `nuc_record`  (
  `topic_id` bigint(15) NOT NULL,
  `user_id` bigint(15) NOT NULL,
  `create_time` datetime(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nuc_topic
-- ----------------------------
DROP TABLE IF EXISTS `nuc_topic`;
CREATE TABLE `nuc_topic`  (
  `topic_id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'topicId',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'title',
  `introduction` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'introduction',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'details',
  `user_id` bigint(15) NOT NULL DEFAULT -1 COMMENT 'userId',
  `create_time` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
  PRIMARY KEY (`topic_id`) USING BTREE,
  UNIQUE INDEX `topic_id`(`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'nuc_topic' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nuc_user
-- ----------------------------
DROP TABLE IF EXISTS `nuc_user`;
CREATE TABLE `nuc_user`  (
  `user_id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'userId',
  `phone_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'phoneNum',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'password',
  `stu_or_wor_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'stuOrWorId',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'name',
  `user_role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '2' COMMENT 'userRole',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT 'status',
  `note` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'note',
  `create_time` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'nuc_user' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nuc_user
-- ----------------------------
INSERT INTO `nuc_user` VALUES (1, '18834165730', '9865fbbcbba15ab9ecc344b854f35747', '123456', '123456', '1', '0', '', '2019-12-17 18:18:16');
INSERT INTO `nuc_user` VALUES (4, '11111111111', '395f4e13a1294608ad1255137094199b', '111111', '111', '1', '0', '', '2019-12-18 08:59:25');
INSERT INTO `nuc_user` VALUES (5, '11111111111', '395f4e13a1294608ad1255137094199b', '1', '111', '1', '0', '', '2019-12-18 09:00:57');
INSERT INTO `nuc_user` VALUES (6, '11111111111', '395f4e13a1294608ad1255137094199b', '2', '2', '1', '0', '', '2019-12-18 09:02:41');
INSERT INTO `nuc_user` VALUES (7, '11111111111', '395f4e13a1294608ad1255137094199b', '3', '2', '1', '0', '', '2019-12-18 09:03:31');
INSERT INTO `nuc_user` VALUES (8, '11111111111', '395f4e13a1294608ad1255137094199b', '5', '3', '1', '0', '', '2019-12-18 09:04:45');
INSERT INTO `nuc_user` VALUES (9, '11111111111', '395f4e13a1294608ad1255137094199b', '21', '1', '1', '0', '', '2019-12-18 09:07:16');
INSERT INTO `nuc_user` VALUES (10, '11111111111', '8d2a7224b8eaedfb2be1a021e0dffcfc', '321', '12', '1', '0', '', '2019-12-18 09:11:41');
INSERT INTO `nuc_user` VALUES (11, '11111111111', '5d17762c2e678a3c279fbadf16d5ce89', '1233', '2', '1', '0', '', '2019-12-18 09:12:33');
INSERT INTO `nuc_user` VALUES (12, '21', '', '', '', '2', '0', '', '1000-01-01 00:00:00');
INSERT INTO `nuc_user` VALUES (13, '213', '', '', '', '2', '0', '', '1000-01-01 00:00:00');
INSERT INTO `nuc_user` VALUES (14, '43', '', '', '', '2', '0', '', '1000-01-01 00:00:00');
INSERT INTO `nuc_user` VALUES (15, '342', '', '', '', '2', '0', '', '1000-01-01 00:00:00');
INSERT INTO `nuc_user` VALUES (16, '32', '', '', '', '2', '0', '', '1000-01-01 00:00:00');
INSERT INTO `nuc_user` VALUES (17, '32', '', '', '', '2', '0', '', '1000-01-01 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
