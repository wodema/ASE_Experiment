/*
 Navicat MySQL Data Transfer

 Source Server         : wsl
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 172.23.116.70:4396
 Source Schema         : soft_exp

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 28/11/2021 11:45:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kind` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int NULL DEFAULT NULL,
  `option1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `questions_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (1, '随便', '计算机', 10, '12', '123', '312', '412', 'A');
INSERT INTO `questions` VALUES (2, '英语', '计算机', 10, '321', '213', '2', '31', 'B');
INSERT INTO `questions` VALUES (3, '计算器', '英语', 5, 'asd', 'a', 'fas', 'vxc', 'C');
INSERT INTO `questions` VALUES (4, 'sb', 'sb', 114, 'asd', 'akdj', 'askdh', 'sada', 'D');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `student_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (3, 'dzq', 21);
INSERT INTO `student` VALUES (5, 'dzq', 21);

-- ----------------------------
-- Table structure for wrongquestions
-- ----------------------------
DROP TABLE IF EXISTS `wrongquestions`;
CREATE TABLE `wrongquestions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `qid` int NULL DEFAULT NULL,
  `sid` int NOT NULL,
  `kind` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `wrongQuestions_id_uindex`(`id`) USING BTREE,
  INDEX `wrongquestions_student_id_fk`(`sid`) USING BTREE,
  INDEX `wrongquestions_questions_id_fk`(`qid`) USING BTREE,
  CONSTRAINT `wrongquestions_questions_id_fk` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `wrongquestions_student_id_fk` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wrongquestions
-- ----------------------------
INSERT INTO `wrongquestions` VALUES (1, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (2, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (3, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (4, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (5, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (6, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (7, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (8, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (9, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (10, 1, 3, '大学习', '傻逼');
INSERT INTO `wrongquestions` VALUES (11, 2, 3, '大学习', '傻逼');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `paper_show`;
CREATE TABLE `paper_show`  (
  `paper_id` int NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `paper_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程名称',
  `paper_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试日期',
  `total_time` int(20) NULL DEFAULT NULL COMMENT '持续时长',
  `total_score` int(4) NULL DEFAULT NULL COMMENT '总分',
  PRIMARY KEY (`paper_id`) USING BTREE
);

DROP TABLE IF EXISTS paper_question;
create table paper_question(
	paper_id int AUTO_INCREMENT COMMENT '卷子ID号',
	question_id int,
	primary key(paper_id,question_id)
);