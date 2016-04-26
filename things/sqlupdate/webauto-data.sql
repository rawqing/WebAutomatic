/*
Navicat MySQL Data Transfer

Source Server         : testdb205
Source Server Version : 50620
Source Host           : 192.168.1.205:3306
Source Database       : webauto

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-04-25 16:42:12
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
-- Records of w_case
-- ----------------------------
INSERT INTO `w_case` VALUES ('1', '无', '首页', '打开首页', '1', '2015-12-30 16:49:19', '2015-12-30 16:49:22', '1', 'aa', 'aabc', 'SY001');
INSERT INTO `w_case` VALUES ('19', null, null, '输入用户名', '1', '2016-01-19 11:19:18', '2016-01-19 11:19:18', '1', null, null, 'TE001');
INSERT INTO `w_case` VALUES ('53', null, null, 'reg', '2', '2016-01-19 16:58:21', '2016-01-19 16:58:21', '0', 'cc', null, 'RE002');
INSERT INTO `w_case` VALUES ('54', null, null, 'reg', '2', '2016-01-19 17:18:15', '2016-01-19 17:18:15', '0', 'cc', null, 'RE003');
INSERT INTO `w_case` VALUES ('61', '无', '首页', '开始登陆', '1', '2016-01-25 09:58:01', '2016-01-25 09:58:01', '1', null, null, 'case_01');
INSERT INTO `w_case` VALUES ('64', null, null, 'reg', '2', '2016-01-29 18:19:07', '2016-01-29 18:19:07', '0', 'cc', null, 'RE004');
INSERT INTO `w_case` VALUES ('65', null, null, 'reg', '2', '2016-01-29 18:22:42', '2016-01-29 18:22:42', '0', 'cc', null, 'RE005');
INSERT INTO `w_case` VALUES ('67', null, null, 'reg', '2', '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', 'cc', null, 'RE006');
INSERT INTO `w_case` VALUES ('68', null, null, 'reg', '2', '2016-01-29 18:26:59', '2016-01-29 18:26:59', '0', 'cc', null, 'RE007');
INSERT INTO `w_case` VALUES ('69', '无', '登录', '进入登录页面', '1', '2016-04-14 22:45:12', '2016-04-14 22:45:12', '1', null, null, 'case_001');
INSERT INTO `w_case` VALUES ('71', '无', '登录', '进入登录页面', '1', '2016-04-14 22:46:56', '2016-04-14 22:46:56', '1', null, null, 'case_101');
INSERT INTO `w_case` VALUES ('73', '无', '登录', '进入登录页面', '1', '2016-04-14 23:01:28', '2016-04-14 23:01:28', '1', null, null, 'case_201');
INSERT INTO `w_case` VALUES ('75', '无', '登录', '登录系统', '1', '2016-04-14 23:03:55', '2016-04-14 23:03:55', '1', null, null, 'case_202');

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
-- Records of w_data
-- ----------------------------
INSERT INTO `w_data` VALUES ('1', '1', '{\"$username\":\"user1\",\"$pwd\":\"123456\"}', null, null, null, null);
INSERT INTO `w_data` VALUES ('2', '1', '1211', null, null, null, null);
INSERT INTO `w_data` VALUES ('3', '1', '13111', null, null, null, null);
INSERT INTO `w_data` VALUES ('4', '1', '001', null, null, null, null);
INSERT INTO `w_data` VALUES ('5', '1', '221', null, null, null, null);

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
-- Records of w_element
-- ----------------------------
INSERT INTO `w_element` VALUES ('1', '1', '立即登陆', null);
INSERT INTO `w_element` VALUES ('2', '2', '用户名', null);
INSERT INTO `w_element` VALUES ('3', '2', '密码', null);
INSERT INTO `w_element` VALUES ('4', '2', '登录', '登录系统按钮');
INSERT INTO `w_element` VALUES ('9', '3', '手机号', '手机号输入框');
INSERT INTO `w_element` VALUES ('10', '3', '密码', '');
INSERT INTO `w_element` VALUES ('11', '3', '确认密码', '');
INSERT INTO `w_element` VALUES ('12', '3', '用户名', '用户名输入框');
INSERT INTO `w_element` VALUES ('13', '3', '手机号', '手机号输入框');
INSERT INTO `w_element` VALUES ('14', '3', '密码', '');
INSERT INTO `w_element` VALUES ('15', '3', '确认密码', '');
INSERT INTO `w_element` VALUES ('16', '1', 'login', '立即登陆按钮');
INSERT INTO `w_element` VALUES ('17', '2', 'username', null);
INSERT INTO `w_element` VALUES ('18', '2', 'pwd', null);

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
-- Records of w_page
-- ----------------------------
INSERT INTO `w_page` VALUES ('1', '首页', '保驾护航', '/xxxx/xxxx/abc');
INSERT INTO `w_page` VALUES ('2', '登录', '先登录再说', '/aaa/aaa/aaa.aa');
INSERT INTO `w_page` VALUES ('3', '注册', '注册了才能玩儿', '/reg/xxx/xxx.jsp');

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
-- Records of w_plan
-- ----------------------------
INSERT INTO `w_plan` VALUES ('1', null, null, null, null, null, null);
INSERT INTO `w_plan` VALUES ('2', '冒烟测试', 'v1.0.2', null, '2016-02-01 14:35:55', '2016-02-01 14:35:55', '');

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
-- Records of w_selector
-- ----------------------------
INSERT INTO `w_selector` VALUES ('1', '16', 'linkText', '立即登录');
INSERT INTO `w_selector` VALUES ('2', '1', 'name', 'ccid');
INSERT INTO `w_selector` VALUES ('3', '17', 'id', 'loginName');
INSERT INTO `w_selector` VALUES ('4', '2', 'cssselect', '.password');
INSERT INTO `w_selector` VALUES ('5', '18', 'id', 'password');
INSERT INTO `w_selector` VALUES ('6', '3', 'xpath', '//dasd/sds');
INSERT INTO `w_selector` VALUES ('7', '4', 'id', 'loginBtn');
INSERT INTO `w_selector` VALUES ('8', '4', 'xpath', './/*[@id=\'loginBtn\']');
INSERT INTO `w_selector` VALUES ('9', '9', 'id', 'id1');
INSERT INTO `w_selector` VALUES ('10', '10', 'id', 'id1');
INSERT INTO `w_selector` VALUES ('11', '10', 'xpath', 'xpath1');
INSERT INTO `w_selector` VALUES ('12', '11', 'id', 'id1');
INSERT INTO `w_selector` VALUES ('13', '11', 'name', 'name1');
INSERT INTO `w_selector` VALUES ('14', '11', 'cssselecter', 'cssselecter1');
INSERT INTO `w_selector` VALUES ('15', '12', 'id', 'username');
INSERT INTO `w_selector` VALUES ('16', '12', 'name', 'nickname');
INSERT INTO `w_selector` VALUES ('17', '13', 'id', 'mobileno');
INSERT INTO `w_selector` VALUES ('18', '14', 'id', 'password1');
INSERT INTO `w_selector` VALUES ('19', '14', 'xpath', '//div[1]/input[1]');
INSERT INTO `w_selector` VALUES ('20', '15', 'id', 'password2');
INSERT INTO `w_selector` VALUES ('21', '15', 'name', 'password2');
INSERT INTO `w_selector` VALUES ('22', '15', 'cssselecter', '#password');

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
-- Records of w_step
-- ----------------------------
INSERT INTO `w_step` VALUES ('1', '61', '输入用户名', 'sendKeys', 'tydzh006', 'tydzh006', null, null, '1', 'aa');
INSERT INTO `w_step` VALUES ('3', '61', '输入密码', 'sendKeys', '123456', '123456', null, null, '1', 'aa');
INSERT INTO `w_step` VALUES ('4', '61', '登录系统', 'click', null, 'true', '2016-01-19 11:06:38', '2016-01-19 11:06:44', '1', '121');
INSERT INTO `w_step` VALUES ('5', '67', null, null, '1', null, null, null, null, null);
INSERT INTO `w_step` VALUES ('10', '52', null, 'click', '$pc', null, '2016-01-19 15:56:22', '2016-01-19 15:56:22', '0', null);
INSERT INTO `w_step` VALUES ('11', '53', null, 'click', '$ps', null, '2016-01-19 16:58:21', '2016-01-19 16:58:21', '0', null);
INSERT INTO `w_step` VALUES ('12', '53', null, 'click', '$pc', null, '2016-01-19 16:58:21', '2016-01-19 16:58:21', '0', null);
INSERT INTO `w_step` VALUES ('13', '54', null, 'click', '$ps', null, '2016-01-19 17:18:15', '2016-01-19 17:18:15', '0', null);
INSERT INTO `w_step` VALUES ('14', '54', null, 'click', '$pc', null, '2016-01-19 17:18:15', '2016-01-19 17:18:15', '0', null);
INSERT INTO `w_step` VALUES ('19', '19', '点击{立即登陆}', 'click', null, '进入登陆页面', '2016-01-25 09:58:01', '2016-01-25 09:58:01', '1', null);
INSERT INTO `w_step` VALUES ('20', '64', null, 'click', '$ps', null, '2016-01-29 18:19:07', '2016-01-29 18:19:07', '0', null);
INSERT INTO `w_step` VALUES ('21', '64', null, 'click', '$pc', null, '2016-01-29 18:19:07', '2016-01-29 18:19:07', '0', null);
INSERT INTO `w_step` VALUES ('22', '65', null, 'click', '$ps', null, '2016-01-29 18:22:42', '2016-01-29 18:22:42', '0', null);
INSERT INTO `w_step` VALUES ('23', '65', null, 'click', '$pc', null, '2016-01-29 18:22:42', '2016-01-29 18:22:42', '0', null);
INSERT INTO `w_step` VALUES ('24', '67', null, 'click', '$ps', null, '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', null);
INSERT INTO `w_step` VALUES ('25', '67', null, 'click', '$pc', null, '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', null);
INSERT INTO `w_step` VALUES ('26', '67', null, 'click', '$pc', null, '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', null);
INSERT INTO `w_step` VALUES ('27', '69', null, '首页', 'click login button', '', '2016-04-14 22:45:12', '2016-04-14 22:45:12', '1', null);
INSERT INTO `w_step` VALUES ('28', '71', null, '首页', 'click login button', '', '2016-04-14 22:46:56', '2016-04-14 22:46:56', '1', null);
INSERT INTO `w_step` VALUES ('29', '73', null, '首页', 'click login button', '', '2016-04-14 23:01:28', '2016-04-14 23:01:28', '1', null);
INSERT INTO `w_step` VALUES ('30', '75', null, '登录', 'sendKeys {17811110000} into username input', '', '2016-04-14 23:03:55', '2016-04-14 23:03:55', '1', null);
INSERT INTO `w_step` VALUES ('31', '75', null, '登录', 'sendKeys {123456} into pwd input', 'show login button or show abc', '2016-04-14 23:03:55', '2016-04-14 23:03:55', '1', null);

-- ----------------------------
-- Table structure for `w_step_copy`
-- ----------------------------
DROP TABLE IF EXISTS `w_step_copy`;
CREATE TABLE `w_step_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'steepID',
  `caseID` int(11) NOT NULL,
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '步骤描述',
  `action` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '测试方法',
  `elementID` int(11) DEFAULT NULL COMMENT '元素id',
  `inputData` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '输入值',
  `expectation` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '预期结果',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(1) DEFAULT NULL COMMENT '步骤版本（只保留最近的3个版本）',
  `author` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of w_step_copy
-- ----------------------------
INSERT INTO `w_step_copy` VALUES ('1', '61', '输入用户名', 'sendKeys', '2', 'tydzh006', 'tydzh006', null, null, '1', 'aa');
INSERT INTO `w_step_copy` VALUES ('3', '61', '输入密码', 'sendKeys', '3', '123456', '123456', null, null, '1', 'aa');
INSERT INTO `w_step_copy` VALUES ('4', '61', '登录系统', 'click', '4', null, 'true', '2016-01-19 11:06:38', '2016-01-19 11:06:44', '1', '121');
INSERT INTO `w_step_copy` VALUES ('5', '67', null, null, '2', '1', null, null, null, null, null);
INSERT INTO `w_step_copy` VALUES ('10', '52', null, 'click', '2', '$pc', null, '2016-01-19 15:56:22', '2016-01-19 15:56:22', '0', null);
INSERT INTO `w_step_copy` VALUES ('11', '53', null, 'click', '1', '$ps', null, '2016-01-19 16:58:21', '2016-01-19 16:58:21', '0', null);
INSERT INTO `w_step_copy` VALUES ('12', '53', null, 'click', '2', '$pc', null, '2016-01-19 16:58:21', '2016-01-19 16:58:21', '0', null);
INSERT INTO `w_step_copy` VALUES ('13', '54', null, 'click', '1', '$ps', null, '2016-01-19 17:18:15', '2016-01-19 17:18:15', '0', null);
INSERT INTO `w_step_copy` VALUES ('14', '54', null, 'click', '2', '$pc', null, '2016-01-19 17:18:15', '2016-01-19 17:18:15', '0', null);
INSERT INTO `w_step_copy` VALUES ('19', '19', '点击{立即登陆}', 'click', '1', null, '进入登陆页面', '2016-01-25 09:58:01', '2016-01-25 09:58:01', '1', null);
INSERT INTO `w_step_copy` VALUES ('20', '64', null, 'click', '1', '$ps', null, '2016-01-29 18:19:07', '2016-01-29 18:19:07', '0', null);
INSERT INTO `w_step_copy` VALUES ('21', '64', null, 'click', '2', '$pc', null, '2016-01-29 18:19:07', '2016-01-29 18:19:07', '0', null);
INSERT INTO `w_step_copy` VALUES ('22', '65', null, 'click', '1', '$ps', null, '2016-01-29 18:22:42', '2016-01-29 18:22:42', '0', null);
INSERT INTO `w_step_copy` VALUES ('23', '65', null, 'click', '2', '$pc', null, '2016-01-29 18:22:42', '2016-01-29 18:22:42', '0', null);
INSERT INTO `w_step_copy` VALUES ('24', '67', null, 'click', '1', '$ps', null, '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', null);
INSERT INTO `w_step_copy` VALUES ('25', '67', null, 'click', '2', '$pc', null, '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', null);
INSERT INTO `w_step_copy` VALUES ('26', '67', null, 'click', '0', '$pc', null, '2016-01-29 18:25:40', '2016-01-29 18:25:40', '0', null);

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
-- Records of w_suite
-- ----------------------------
INSERT INTO `w_suite` VALUES ('1', '用户', '2', '[73,75]', '1', '登录、注册', '2016-02-01 15:04:40', '2016-02-01 15:04:40', '');

-- ----------------------------
-- Table structure for `w_suite_copy`
-- ----------------------------
DROP TABLE IF EXISTS `w_suite_copy`;
CREATE TABLE `w_suite_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'suiteID',
  `planName` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '计划名称',
  `caseList` text COLLATE utf8_bin COMMENT '包含用例',
  `commitVersion` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '提测版本',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `planExecutionTime` datetime DEFAULT NULL COMMENT '预计执行时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `author` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of w_suite_copy
-- ----------------------------

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
-- Records of w_testres
-- ----------------------------
INSERT INTO `w_testres` VALUES ('1', '2-1458900656364', '0', '1', '19', '19', null, '进入登陆页面', '0', 'true', '2016-03-25 18:11:04', '0', '', 'tester', '1');
INSERT INTO `w_testres` VALUES ('2', '2-1458900656364', '0', '1', '61', '1', 'tydzh006', 'tydzh006', '0', 'true', '2016-03-25 18:11:05', '0', '', 'tester', '1');
INSERT INTO `w_testres` VALUES ('3', '2-1459136615239', '0', '1', '19', '19', null, '进入登陆页面', '0', 'true', '2016-03-28 11:43:51', '0', '', 'tester', '1');
INSERT INTO `w_testres` VALUES ('4', '2-1459136615239', '0', '1', '61', '1', 'tydzh006', 'tydzh006', '0', 'true', '2016-03-28 11:43:52', '0', '', 'tester', '1');
INSERT INTO `w_testres` VALUES ('5', '2-1459137185121', '0', '1', '19', '19', null, '进入登陆页面', '0', 'true', '2016-03-28 12:04:14', '0', '', 'tester', '1');
INSERT INTO `w_testres` VALUES ('6', '2-1459137185121', '0', '1', '61', '1', 'tydzh006', 'tydzh006', '0', 'true', '2016-03-28 12:04:20', '0', '', 'tester', '1');
INSERT INTO `w_testres` VALUES ('7', '2-1460961523765', '2', '1', '73', '29', null, '', '0', '', '2016-04-14 23:40:57', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('8', '2-1460966096596', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 00:57:10', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('9', '2-1460966465329', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 01:03:19', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('10', '2-1460966558385', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 01:05:31', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('11', '2-1460967178770', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 01:15:25', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('12', '2-1460967265539', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 01:16:39', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('13', '2-1460967265539', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 01:16:40', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('14', '2-1460967265539', '2', '1', '75', '31', '123456', '', '0', '', '2016-04-15 01:16:40', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('15', '2-1460968893624', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 01:43:47', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('16', '2-1460968893624', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 01:43:48', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('17', '2-1460968893624', '2', '1', '75', '31', '123456', 'click login button', '1', '', '2016-04-15 01:43:49', '-1', 'http://192.168.1.101', null, '1');
INSERT INTO `w_testres` VALUES ('18', '2-1460969483799', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 01:54:09', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('19', '2-1460969483799', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 01:54:32', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('20', '2-1460969483799', '2', '1', '75', '31', '123456', 'click login button', '1', '', '2016-04-15 02:00:25', '-1', 'http://192.168.1.101/zentao/data/upload/1/201604/1816581107107c3t.png', null, '1');
INSERT INTO `w_testres` VALUES ('21', '2-1460970232890', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 02:06:12', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('22', '2-1460970232890', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 02:06:24', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('23', '2-1460970232890', '2', '1', '75', '31', '123456', 'click login button', '1', '', '2016-04-15 02:10:12', '-1', 'http://192.168.1.101/zentao/data/upload/1/201604/18170758019488t7.png', null, '1');
INSERT INTO `w_testres` VALUES ('24', '2-1460970778666', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 02:15:12', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('25', '2-1460970778666', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 02:15:13', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('26', '2-1460970875949', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 02:16:57', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('27', '2-1460970875949', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 02:17:10', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('28', '2-1460972531758', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 02:44:25', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('29', '2-1460972531758', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 02:44:26', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('30', '2-1460972531758', '2', '1', '75', '31', '123456', 'click login button', '0', ' click login button -> false ; ', '2016-04-15 02:44:27', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('31', '2-1460972659385', '2', '1', '73', '29', null, '', '0', '', '2016-04-15 02:48:26', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('32', '2-1460972659385', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-15 02:48:36', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('33', '2-1461567224542', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 14:53:46', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('34', '2-1461567224542', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 14:53:47', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('35', '2-1461567224542', '2', '1', '75', '31', '123456', 'click login button', '0', ' click login button -> false ; ', '2016-04-25 14:53:48', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('36', '2-1461567618351', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:03:28', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('37', '2-1461567618351', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:03:44', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('38', '2-1461568054750', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:07:36', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('39', '2-1461568054750', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:07:37', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('40', '2-1461568371047', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:12:52', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('41', '2-1461568371047', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:12:53', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('42', '2-1461569399974', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:30:01', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('43', '2-1461569399974', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:30:02', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('44', '2-1461569399974', '2', '1', '75', '31', '123456', 'click login button', '1', ' click login button -> false ; ', '2016-04-25 15:30:03', '4', 'http://192.168.1.101/zentao/data/upload/1/201604/2515300209183608.png', null, '1');
INSERT INTO `w_testres` VALUES ('45', '2-1461570040638', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:40:41', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('46', '2-1461570040638', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:40:42', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('47', '2-1461570040638', '2', '1', '75', '31', '123456', 'show login button', '1', ' show login button -> false ; ', '2016-04-25 15:40:43', '5', 'http://192.168.1.101/zentao/data/upload/1/201604/2515404307592f8s.png', null, '1');
INSERT INTO `w_testres` VALUES ('48', '2-1461570708426', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:51:49', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('49', '2-1461570708426', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:51:50', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('50', '2-1461570708426', '2', '1', '75', '31', '123456', 'show login button', '1', ' show login button -> false ; ', '2016-04-25 15:51:51', '6', 'http://192.168.1.101/zentao/data/upload/1/201604/2515515109092qkf.png', null, '1');
INSERT INTO `w_testres` VALUES ('51', '2-1461570923318', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 15:55:24', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('52', '2-1461570923318', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 15:55:25', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('53', '2-1461570923318', '2', '1', '75', '31', '123456', 'show login button', '1', ' show login button -> false ; ', '2016-04-25 15:55:26', '7', 'http://192.168.1.101/zentao/data/upload/1/201604/2515552608301e9c.png', null, '1');
INSERT INTO `w_testres` VALUES ('54', '2-1461571395649', '2', '1', '73', '29', null, '', '0', '', '2016-04-25 16:03:16', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('55', '2-1461571395649', '2', '1', '75', '30', '17811110000', '', '0', '', '2016-04-25 16:03:17', '0', null, null, '1');
INSERT INTO `w_testres` VALUES ('56', '2-1461571395649', '2', '1', '75', '31', '123456', 'show login button or show abc', '1', ' show login button -> false ;  show abc -> false ; ', '2016-04-25 16:03:18', '8', 'http://192.168.1.101/zentao/data/upload/1/201604/2516031808207g31.png', null, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of w_zentaobug
-- ----------------------------
INSERT INTO `w_zentaobug` VALUES ('18', null, null, null, null, null, null, null, null, null, null, null, '啊啊啊啊啊', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `w_zentaobug` VALUES ('19', null, null, null, null, null, null, null, null, null, null, null, '啊啊啊啊啊啊啊啊啊啊', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `w_zentaobug` VALUES ('20', null, null, null, null, null, null, null, null, null, null, null, '啊啊啊啊啊啊啊啊啊啊', null, null, null, null, null, null, null, null, null, null, null);

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
