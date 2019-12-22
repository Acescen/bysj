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

 Date: 22/12/2019 16:58:37
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
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `phone_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`topic_id`) USING BTREE,
  UNIQUE INDEX `topic_id`(`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'nuc_topic' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nuc_topic
-- ----------------------------
INSERT INTO `nuc_topic` VALUES (7, '4', 'jurui', 'sa的撒', 21, '1000-01-01 00:00:00', '', '已被选', '的撒打算打算');
INSERT INTO `nuc_topic` VALUES (9, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '已被选', '');
INSERT INTO `nuc_topic` VALUES (10, '4', '一号', NULL, -1, '1000-01-01 00:00:00', '', '未被选择', '');
INSERT INTO `nuc_topic` VALUES (11, '4', '二号', NULL, -1, '1000-01-01 00:00:00', '', '未被选择', '');
INSERT INTO `nuc_topic` VALUES (12, '4', '三号', NULL, -1, '1000-01-01 00:00:00', '', '未被选择', '');
INSERT INTO `nuc_topic` VALUES (13, '4', '四号', NULL, -1, '1000-01-01 00:00:00', '', '未被选择', '');
INSERT INTO `nuc_topic` VALUES (14, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (15, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (16, '4', 'li', NULL, 21, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (17, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (18, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (19, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (20, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (21, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (22, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (23, '4', 'ren', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (24, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (25, '4', '', 'hehe', -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (26, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (27, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (28, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (29, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (30, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (31, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (32, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (33, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (34, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (35, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (36, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (37, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (38, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (39, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (40, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (41, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (42, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (43, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (44, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (45, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (46, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (47, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (48, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (49, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (50, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (51, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (52, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (53, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (54, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (55, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (56, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (57, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (58, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (59, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (60, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (61, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (62, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (63, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (64, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (65, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (66, '4', '', NULL, -1, '1000-01-01 00:00:00', '', '', '');
INSERT INTO `nuc_topic` VALUES (67, 'dadsa', 'das', 'dasd', 21, '2019-12-22 10:45:10', '琚锐', '未被选择', '12345678');

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'nuc_user' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nuc_user
-- ----------------------------
INSERT INTO `nuc_user` VALUES (22, 'z', 'c4ebcd0c6cbd3f1b8598032a66811957', 'z', 'z', '管理员', '可用', 'z', '2019-12-22 11:23:11');
INSERT INTO `nuc_user` VALUES (23, 'x', 'a267613a4c793548c5cb51ae90b93271', 'x', 'x', '老师', '可用', 'x', '2019-12-22 11:23:17');
INSERT INTO `nuc_user` VALUES (24, 'c', 'bbba8f92bc9da0b4330ad1c62f6d0b61', 'c', 'c', '学生', '可用', 'c', '2019-12-22 11:23:23');

SET FOREIGN_KEY_CHECKS = 1;
