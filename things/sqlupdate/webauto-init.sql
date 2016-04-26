/*
Navicat MySQL Data Transfer

Source Server         : testdb205
Source Server Version : 50620
Source Host           : 192.168.1.205:3306
Source Database       : webauto

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-04-25 16:12:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `w_case`
-- ----------------------------
DROP TABLE IF EXISTS `w_case`;
CREATE TABLE `w_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用例ID',
  `premise` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '前提条件',
  `module` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名',
  `caseName` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '用例名',
  `priority` int(1) DEFAULT NULL COMMENT '优先级',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(1) DEFAULT NULL COMMENT '用例版本',
  `author` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `caseNumber` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用例编号（自定义的有序编号，可通过该字段确定用例顺序）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `caseNumber` (`caseNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_data`
-- ----------------------------
DROP TABLE IF EXISTS `w_data`;
CREATE TABLE `w_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseId` int(11) NOT NULL COMMENT '用例id',
  `parameValue` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '变量值',
  `expect` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '预期结果',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `level` int(3) DEFAULT NULL COMMENT '执行等级(1 正向数据必须执行，2次级执行，3 ......)',
  PRIMARY KEY (`id`),
  KEY `caseIdKey` (`caseId`),
  CONSTRAINT `caseIdKey` FOREIGN KEY (`caseId`) REFERENCES `w_case` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_element`
-- ----------------------------
DROP TABLE IF EXISTS `w_element`;
CREATE TABLE `w_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'elementID',
  `pageID` int(11) NOT NULL COMMENT 'pageID',
  `elememtName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '元素名称',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_page`
-- ----------------------------
DROP TABLE IF EXISTS `w_page`;
CREATE TABLE `w_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'pageID',
  `alias` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '页面别名',
  `title` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '页面标题',
  `uri` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_plan`
-- ----------------------------
DROP TABLE IF EXISTS `w_plan`;
CREATE TABLE `w_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'suiteID',
  `planName` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '计划名称',
  `commitVersion` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '提测版本',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `planExecutionTime` datetime DEFAULT NULL COMMENT '预计执行时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `author` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_selector`
-- ----------------------------
DROP TABLE IF EXISTS `w_selector`;
CREATE TABLE `w_selector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elementID` int(11) NOT NULL COMMENT 'elementID',
  `selector` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '选择器',
  `selectPath` varchar(1000) COLLATE utf8_bin NOT NULL COMMENT '选择路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



-- ----------------------------
-- Table structure for `w_step`
-- ----------------------------
DROP TABLE IF EXISTS `w_step`;
CREATE TABLE `w_step` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'steepID',
  `caseID` int(11) NOT NULL,
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '步骤描述',
  `pageAlias` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '页面别名',
  `step` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '步骤',
  `expectation` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '预期结果',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(1) DEFAULT NULL COMMENT '步骤版本（只保留最近的3个版本）',
  `author` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_suite`
-- ----------------------------
DROP TABLE IF EXISTS `w_suite`;
CREATE TABLE `w_suite` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'suiteID',
  `suietName` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '模块集名称',
  `planId` int(11) DEFAULT NULL,
  `caseList` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '用例集',
  `version` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '版本',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `author` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_testres`
-- ----------------------------
DROP TABLE IF EXISTS `w_testres`;
CREATE TABLE `w_testres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `testNumber` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '测试批号',
  `planId` int(11) NOT NULL COMMENT '计划id',
  `suiteId` int(11) NOT NULL,
  `caseId` int(11) NOT NULL COMMENT '测试结果（1为成功，0为失败，null为不记录结果）',
  `steepId` int(11) NOT NULL COMMENT '实际结果',
  `useData` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '使用的数据',
  `expected` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '预期结果',
  `result` int(1) NOT NULL DEFAULT '1' COMMENT '执行结果（0 失败，1 成功，3 其他）',
  `actual` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '实际结果',
  `testTime` datetime DEFAULT NULL COMMENT '测试时间',
  `bugId` int(11) DEFAULT NULL COMMENT '如果失败，则记录bug id',
  `printscreenPath` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '错误截图路径+图片名（如果failed）',
  `tester` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '测试者',
  `priority` int(11) DEFAULT '2' COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `w_zentaobug`
-- ----------------------------
DROP TABLE IF EXISTS `w_zentaobug`;
CREATE TABLE `w_zentaobug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `module` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `project` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `openedBuild` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `assignedTo` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `os` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `browser` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `severity` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `pri` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `steps` varchar(5000) COLLATE utf8_bin NOT NULL,
  `story` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `task` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `mailto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `keywords` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `files` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `labels` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `case_` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `caseVersion` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `result` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `testtask` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



-- ----------------------------
-- Procedure structure for `w_p_addtestcase`
-- ----------------------------
DROP PROCEDURE IF EXISTS `w_p_addtestcase`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `w_p_addtestcase`()
BEGIN
	#add testcast and teststeep
	

END
;;
DELIMITER ;
