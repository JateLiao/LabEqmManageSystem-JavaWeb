/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : labequipmentmanagesystem

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-02-09 23:02:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `administrator`
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `KeyID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AdminID` varchar(30) NOT NULL,
  `PassWord` varchar(50) DEFAULT NULL COMMENT '密码',
  `AdminName` varchar(50) DEFAULT NULL COMMENT '管理员姓名',
  `AdminLevel` varchar(3) NOT NULL COMMENT '"角色：\n0：学生；\n1：普通教职工；\n2：实验室管理员；\n3：设备管理员；\n99：超级管理员。"\r\n',
  `AdminSex` char(2) DEFAULT NULL COMMENT '管理员性别',
  `AdminIDCardNo` varchar(20) DEFAULT NULL COMMENT '管理员身份证号',
  `AdminTel` varchar(15) DEFAULT NULL COMMENT '管理员联系方式',
  `AdminEmail` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `AdminAddress` varchar(100) DEFAULT NULL COMMENT '管理员家庭住址',
  `AdminNativePlace` varchar(30) DEFAULT NULL,
  `AdminDept` varchar(30) DEFAULT NULL COMMENT '管理员部门，如后勤部',
  `AdminCollege` varchar(30) DEFAULT NULL COMMENT '管理员所属学院，比如计科的教师可以管理本学院的某些设备',
  `AdminBirthday` datetime DEFAULT NULL COMMENT '管理员出生日期',
  `Description` varchar(200) DEFAULT NULL COMMENT '描述',
  `ModifyTime` date DEFAULT NULL COMMENT '记录修改时间',
  `tmp1` varchar(20) DEFAULT NULL COMMENT '扩展字段',
  `tmp2` varchar(20) DEFAULT NULL COMMENT '扩展字段',
  `tmp3` varchar(20) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`KeyID`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('4', '5', 'c4ca4238a0b923820dcc509a6f75849b', '加内特', '0', '男', '2102598745985462', '13228170623', '374682617@qq.com', 'fg', '四川绵阳', '武装部', '美术学院', '2016-03-30 00:00:00', null, '2016-05-14', '1', null, null);
INSERT INTO `administrator` VALUES ('2', '1', 'c4ca4238a0b923820dcc509a6f75849b', '罗斯', '99', '男', '10086', '13228170623', '374682617@qq.com', '用户体验和一塌糊涂', '就是那个屯儿', '你猜啊', '体育学院', '2016-04-19 00:00:00', null, '2016-05-12', '1', null, null);
INSERT INTO `administrator` VALUES ('3', '3', 'c4ca4238a0b923820dcc509a6f75849b', '韦德', '3', '男', '510723199304152075', '13228170623', '374682617@qq.com', '四川省成都市金牛区一环路北一段1819号', '四川绵阳', '计划生育部', '物理与电子工程学院', '2016-04-19 00:00:00', null, '2016-04-20', '1', null, null);
INSERT INTO `administrator` VALUES ('59', '0000', 'c4ca4238a0b923820dcc509a6f75849b', '马特达蒙', '2', '男', '51072319548741236', '13228170623', '374682617@qq.com', null, null, '你倒是猜啊！！！', '物理与电子工程学院', '2016-05-03 00:00:00', null, '2016-05-11', '0', null, null);
INSERT INTO `administrator` VALUES ('46', '8', 'c4ca4238a0b923820dcc509a6f75849b', '科比', '1', '男', '512072319963214852', '13228170623', '374682617@qq.com', '洛杉矶圣城路24号', '美国费城', '钢铁生产部', '体育学院', '2016-04-05 00:00:00', null, '2016-04-24', '1', null, null);
INSERT INTO `administrator` VALUES ('47', '2', 'c4ca4238a0b923820dcc509a6f75849b', '沃尔', '2', '男', '510723199304152075', '13228170623', '374682617@qq.com', '你那么聪明你倒是猜啊', null, '神盾局', '物理与电子工程学院', '2016-04-05 00:00:00', null, '2016-10-02', '1', null, null);
INSERT INTO `administrator` VALUES ('57', '2012110328', 'c4ca4238a0b923820dcc509a6f75849b', '廖仕杰', '3', '男', '510723199548752036', '13228170623', '374682617@qq.com', null, null, null, '计算机科学学院', '2016-04-12 00:00:00', null, '2016-05-07', '1', null, null);

-- ----------------------------
-- Table structure for `eqmlendrecord`
-- ----------------------------
DROP TABLE IF EXISTS `eqmlendrecord`;
CREATE TABLE `eqmlendrecord` (
  `KeyID` int(11) NOT NULL AUTO_INCREMENT,
  `eqmName` varchar(100) DEFAULT NULL,
  `propertyNo` varchar(50) DEFAULT NULL COMMENT '设备id，资产号',
  `AdminId` varchar(30) DEFAULT NULL COMMENT '处理的管理员id',
  `AdminName` varchar(30) DEFAULT NULL,
  `LenderId` varchar(30) DEFAULT NULL COMMENT '借出人id',
  `LenderName` varchar(30) DEFAULT NULL COMMENT '借出人姓名',
  `LenderLevel` varchar(3) DEFAULT NULL COMMENT '借出人角色',
  `LendDate` date DEFAULT NULL COMMENT '借出时间',
  `Application` varchar(200) DEFAULT NULL COMMENT '设备借出用途',
  `PlanReturnDate` date DEFAULT NULL COMMENT '计划归还时间',
  `ActualReturnDate` date DEFAULT NULL COMMENT '实际归还时间',
  `HandleStatus` varchar(3) DEFAULT NULL COMMENT '处理状态',
  `HandleStatusName` varchar(10) DEFAULT NULL COMMENT '处理状态名',
  `HandleDate` date DEFAULT NULL COMMENT '处理时间',
  `HandleReason` varchar(200) DEFAULT NULL,
  `tmp1` varchar(255) DEFAULT NULL,
  `tmp2` varchar(255) DEFAULT NULL,
  `tmp3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eqmlendrecord
-- ----------------------------
INSERT INTO `eqmlendrecord` VALUES ('33', '天河3号', 'aw09', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-24', '死神撒发，聚划算。', '2016-05-02', '2016-10-02', '2', '已归还', '2016-10-02', null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('34', '天河3号', 'aw90', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-24', '中转站', '2016-05-10', null, '1', '已借出', '2016-05-01', null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('35', '天河3号', 'aw90', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-24', '中转站', '2016-05-10', '2016-10-02', '2', '已归还', '2016-10-02', null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('36', '天河3号', 'aw89', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-26', '萨达', '2016-05-18', null, '3', '已拒绝', '2016-05-01', 'sdvdsvdsv', null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('37', '天河3号', 'aw89', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-26', '萨达', '2016-05-18', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('38', '天河3号', 'aw89', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-26', '萨达', '2016-05-18', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('39', '天河3号', 'aw3', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-03', '嗷嗷', '2016-05-04', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('40', '天河3号', 'aw7', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-27', '是擦擦擦是', '2016-04-25', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('41', '天河3号', 'aw7', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-27', '是擦擦擦是', '2016-04-25', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('42', '天河3号', 'aw4', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-04', '闪烁', '2016-05-09', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('43', '天河3号', 'aw9', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-12', '闪烁22', '2016-05-03', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('44', '天河3号', 'aw9', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-12', '闪烁22', '2016-05-03', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('45', '天河3号', 'aw6', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-04', '闪烁333', '2016-05-10', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('46', '天河3号', 'aw6', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-04', '闪烁333', '2016-05-10', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('47', '天河3号', 'aw6', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-04', '闪烁333', '2016-05-10', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('48', '天河3号', 'aw970', 'null', 'Manager51', '2', '沃尔', '2', '2016-04-26', '水电费水电费', '2016-05-01', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('49', '天河3号', 'aw970', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-04-26', '水电费水电费', '2016-05-01', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('50', '天河3号', 'aw870', 'null', 'Manager51', '2', '沃尔', '2', '2016-05-02', '但是', '2016-05-05', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('51', '天河3号', 'aw870', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-02', '但是', '2016-05-05', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('52', '天河3号', 'aw870', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-02', '但是', '2016-05-05', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('53', '交换机51', '53', 'null', 'Manager51', '2', '沃尔', '2', '2016-05-02', '无法为', '2016-05-03', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('54', '电脑主机', 'CS-2012-407-25', '1', '罗斯', '0', '加内特', '0', '2016-05-11', 'QQQQQ', '2016-05-09', null, '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmlendrecord` VALUES ('55', '惠普超级服务器', '52', '2012110328', '廖仕杰', '5', '加内特', '0', '2016-05-14', '学习', '2016-05-10', null, '0', '未处理', null, null, null, null, null);

-- ----------------------------
-- Table structure for `eqmrepairrecord`
-- ----------------------------
DROP TABLE IF EXISTS `eqmrepairrecord`;
CREATE TABLE `eqmrepairrecord` (
  `KeyID` int(11) NOT NULL AUTO_INCREMENT,
  `eqmName` varchar(100) DEFAULT NULL,
  `propertyNo` varchar(50) DEFAULT NULL COMMENT '设备资产号',
  `AdminId` varchar(30) DEFAULT NULL COMMENT '处理的管理员id',
  `AdminName` varchar(30) DEFAULT NULL,
  `ApplyId` varchar(30) DEFAULT NULL COMMENT '报修人id',
  `ApplyName` varchar(30) DEFAULT NULL COMMENT '报修人姓名',
  `ApplyLevel` varchar(3) DEFAULT NULL COMMENT '报修人角色',
  `RepairReason` varchar(200) DEFAULT NULL COMMENT '报修说明',
  `ApplyDate` date DEFAULT NULL COMMENT '报修时间',
  `HandleStatus` varchar(3) DEFAULT NULL COMMENT '报修处理状态',
  `HandleStatusName` varchar(10) DEFAULT NULL COMMENT '报修处理状态名',
  `HandleDate` date DEFAULT NULL COMMENT '报修处理时间',
  `HandleReason` varchar(200) DEFAULT NULL COMMENT '报修处理说明',
  `tmp1` varchar(255) DEFAULT NULL,
  `tmp2` varchar(255) DEFAULT NULL,
  `tmp3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eqmrepairrecord
-- ----------------------------
INSERT INTO `eqmrepairrecord` VALUES ('1', '交换机53', '55', 'null', 'Manager53', '1', '罗斯', '99', '动手术', '2016-04-29', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('2', '交换机58', '60', 'null', 'Manager58', '1', '罗斯', '99', '宿舍', '2016-04-29', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('3', '杀人书', 'cd-89-120', '1', '罗斯', '1', '罗斯', '99', '撕坏了', '2016-04-29', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('4', '交换机56', '58', 'null', 'Manager56', '1', '罗斯', '99', 'ddddd', '2016-04-29', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('5', '交换机57', '59', 'null', 'Manager57', '1', '罗斯', '99', '说说', '2016-04-30', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('6', '交换机60', '62', 'null', 'Manager60', '1', '罗斯', '99', 'ssssssssss三大大神', '2016-04-30', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('7', '杀人书', 'cd-89-120', '1', '罗斯', '1', '罗斯', '99', '少时诵诗书水水水水我问问吾问无为谓吾问无为谓呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜我问谁水水水水水水水水是', '2016-04-30', '1', '已处理', '2016-04-30', '研究院', null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('8', '杀人书', 'cd-89-120', 'null', 'Manager60', '1', '罗斯', '99', '少时诵诗书水水水水我问问吾问无为谓吾问无为谓呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜我问谁水水水水水水水水是', '2016-04-30', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('9', '天河3号', 'aw5', '2012110328', '廖仕杰', '2', '沃尔', '2', '死神', '2016-05-01', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('10', '天河3号', 'aw89', '2012110328', '廖仕杰', '2', '沃尔', '2', '试试', '2016-05-01', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('11', '天河3号', 'aw789', '2012110328', '廖仕杰', '2', '沃尔', '2', '风格', '2016-05-01', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('12', '天河3号', 'aw789', '2012110328', '廖仕杰', '2', '沃尔', '2', '风格', '2016-05-01', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('13', '交换机55', '57', '2012110328', '廖仕杰', '2', '沃尔', '2', '胜多负少', '2016-05-01', '1', '已处理', '2016-05-01', '试试', null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('14', '交换机55', '57', '2012110328', '廖仕杰', '2', '沃尔', '2', '胜多负少', '2016-05-01', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('15', '交换机55', '57', 'null', 'Manager55', '2', '沃尔', '2', '胜多负少', '2016-05-01', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('16', '天河3号', 'aw1', '4564564568', '廖仕杰', '1', '罗斯', '99', 'adsaafasdsfafasfa sdfsfdsfds..', '2016-05-02', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('17', '天河3号', 'aw980', '4564564568', '廖仕杰', '1', '罗斯', '99', 'safafasf', '2016-05-02', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmrepairrecord` VALUES ('18', '天河3号', 'aw980', '2012110328', '廖仕杰', '1', '罗斯', '99', 'safafasf', '2016-05-02', '0', '未处理', null, null, null, null, null);

-- ----------------------------
-- Table structure for `eqmscraprecord`
-- ----------------------------
DROP TABLE IF EXISTS `eqmscraprecord`;
CREATE TABLE `eqmscraprecord` (
  `KeyID` int(11) NOT NULL AUTO_INCREMENT,
  `eqmName` varchar(100) DEFAULT NULL,
  `propertyNo` varchar(50) DEFAULT NULL COMMENT '设备资产号',
  `AdminId` varchar(30) DEFAULT NULL COMMENT '处理的管理员id',
  `AdminName` varchar(30) DEFAULT NULL,
  `ApplyId` varchar(30) DEFAULT NULL COMMENT '报修人id',
  `ApplyName` varchar(30) DEFAULT NULL COMMENT '报修人姓名',
  `ApplyLevel` varchar(3) DEFAULT NULL COMMENT '报修人角色',
  `ApplyDate` date DEFAULT NULL COMMENT '报修时间',
  `ScrapReason` varchar(200) DEFAULT NULL COMMENT '报修说明',
  `HandleStatus` varchar(3) DEFAULT NULL COMMENT '报修处理状态',
  `HandleStatusName` varchar(10) DEFAULT NULL COMMENT '报修处理状态名',
  `HandleDate` date DEFAULT NULL COMMENT '报修处理时间',
  `HandleReason` varchar(200) DEFAULT NULL COMMENT '报修处理说明',
  `tmp1` varchar(255) DEFAULT NULL,
  `tmp2` varchar(255) DEFAULT NULL,
  `tmp3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eqmscraprecord
-- ----------------------------
INSERT INTO `eqmscraprecord` VALUES ('12', '爆裂', 'we-45-ww', '1', '罗斯', '1', '罗斯', '99', '2016-04-30', '电弧！水电费！', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmscraprecord` VALUES ('13', '天河3号', 'aw0987', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-01', '擦擦是 ', '0', '未处理', null, null, null, null, null);
INSERT INTO `eqmscraprecord` VALUES ('14', '天河3号', 'aw08', '2012110328', '廖仕杰', '2', '沃尔', '2', '2016-05-01', '试试', '0', '未处理', null, null, null, null, null);

-- ----------------------------
-- Table structure for `equipment`
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `KeyID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PropertyNo` varchar(50) DEFAULT NULL COMMENT '资产号',
  `EqmName` varchar(50) NOT NULL,
  `EqmPrice` decimal(18,2) DEFAULT NULL COMMENT '设备价格',
  `EqmType` varchar(20) DEFAULT NULL COMMENT '设备型号',
  `EqmClass` varchar(20) DEFAULT NULL COMMENT '设备类别，如网络设备，处理器，显示器等',
  `EqmStatus` varchar(15) DEFAULT NULL COMMENT '设备状态：',
  `EqmLab` varchar(30) DEFAULT NULL COMMENT '设备所在实验室',
  `EqmFactory` varchar(30) DEFAULT NULL COMMENT '设备厂商',
  `BuyStaff` varchar(30) DEFAULT NULL COMMENT '采购人',
  `BuyTime` datetime DEFAULT NULL COMMENT '采购时间',
  `College` varchar(25) DEFAULT NULL COMMENT '所属学院',
  `Manager` varchar(30) DEFAULT NULL COMMENT '托管人姓名',
  `ManagerId` varchar(30) DEFAULT NULL COMMENT '管理人的账号（工号）',
  `Description` varchar(200) DEFAULT NULL COMMENT '描述，如维修记录等',
  `ModifyTime` date DEFAULT NULL COMMENT '记录修改时间',
  `tmp1` varchar(20) DEFAULT NULL COMMENT '扩展字段',
  `tmp2` varchar(20) DEFAULT NULL COMMENT '扩展字段',
  `tmp3` varchar(20) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`KeyID`),
  KEY `Equipment_KeyID` (`KeyID`)
) ENGINE=MyISAM AUTO_INCREMENT=10929148 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('52', '52', '惠普超级服务器', '85000.00', 'HP-100', '大型网络设备-服务器', '已借出', '网络中心', '惠普', '刘靖川', '2016-04-12 00:00:00', '经济与管理学院', '廖仕杰', '2012110328', '我给你们说哈，这个服务器碉堡了，几十万一台，可以发射核武器，还可以生小孩', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('53', '53', '交换机51', '4999951.00', null, 'mumymumyu', '已借出', null, null, 'Scotte51', '2016-01-22 12:00:00', '计算机科学学院51', 'Manager51', null, null, '2016-05-01', null, null, null);
INSERT INTO `equipment` VALUES ('54', '54', '交换机52', '4999952.00', null, null, '已借出', null, null, 'Scotte52', '2016-01-22 12:00:00', '计算机科学学院52', 'Manager52', null, null, '2016-04-26', null, null, null);
INSERT INTO `equipment` VALUES ('55', '55', '交换机53', '4999953.00', null, null, '维修中', null, null, 'Scotte53', '2016-01-22 12:00:00', '计算机科学学院53', 'Manager53', null, null, '2016-04-29', null, null, null);
INSERT INTO `equipment` VALUES ('56', '56', '交换机54', '4999954.00', null, null, '已借出', null, null, 'Scotte54', '2016-01-22 00:00:00', '计算机科学学院54', 'Manager54', null, '实在是听不懂你们到底在说什么，我明天就去把婚离了，不就是，对吧', '2016-04-29', null, null, null);
INSERT INTO `equipment` VALUES ('57', '57', '交换机55', '4999955.00', null, null, '使用中', null, null, 'Scotte55', '2016-01-22 12:00:00', '计算机科学学院55', 'Manager55', null, null, '2016-05-01', null, null, null);
INSERT INTO `equipment` VALUES ('58', '58', '交换机56', '4999956.00', null, null, '维修中', null, null, 'Scotte56', '2016-01-22 12:00:00', '计算机科学学院56', 'Manager56', null, null, '2016-04-29', null, null, null);
INSERT INTO `equipment` VALUES ('59', '59', '交换机57', '4999957.00', null, null, '维修中', null, null, 'Scotte57', '2016-01-22 12:00:00', '计算机科学学院57', 'Manager57', null, null, '2016-04-30', null, null, null);
INSERT INTO `equipment` VALUES ('60', '60', '交换机58', '4999958.00', null, null, '维修中', null, null, 'Scotte58', '2016-01-22 12:00:00', '计算机科学学院58', 'Manager58', null, null, '2016-04-29', null, null, null);
INSERT INTO `equipment` VALUES ('62', '62', '交换机60', '4999960.00', null, null, '维修中', null, null, 'Scotte60', '2016-01-22 12:00:00', '计算机科学学院60', 'Manager60', null, null, '2016-04-30', null, null, null);
INSERT INTO `equipment` VALUES ('10929146', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929145', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929144', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929143', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929142', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929141', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929140', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929139', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929138', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929136', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929135', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929134', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929133', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', null, '中国科学院', '廖仕杰', null, null, null, null, null, '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929132', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929130', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929129', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929128', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929127', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929126', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929125', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929124', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929123', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929120', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929119', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929118', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929117', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929116', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929115', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929114', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929113', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929112', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929110', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929109', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929108', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929107', 'sdhshdh', 'sdhfdsj', null, 'sdv', 'sdvdsvds', null, null, 'sdvds', null, null, null, '廖仕杰', '2012110328', 'sdvsdv', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929105', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929104', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929103', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929102', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929101', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929100', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929099', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929098', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929097', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929094', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929093', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929092', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929091', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929090', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929089', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929088', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929087', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929085', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929084', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929083', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929082', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929081', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929080', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929079', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929078', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929077', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929074', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929073', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929072', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929071', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929070', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929069', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929068', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929067', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929065', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929064', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929063', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929062', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929061', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929060', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929059', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929058', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929057', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929054', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929053', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929052', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929051', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929050', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929049', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929048', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929047', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929045', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929044', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929043', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929042', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929041', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929040', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929039', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929038', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929037', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929034', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929033', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929032', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929031', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929030', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929029', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929028', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929027', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929025', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929024', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929023', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929022', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929021', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929020', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929019', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929018', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929017', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929014', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929013', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929012', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929011', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929010', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929009', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929008', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929007', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929005', 'fgh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929004', 'afe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929003', 'ewtw', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929002', '3rwrw3t', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929001', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929000', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928999', 'adas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928998', 'wry56u', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928997', 'aw89', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928994', 'qw3tt', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928993', 'daadas', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928992', 'treyyu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928990', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928991', 'wer', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928987', 'ewrwe', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '4564564568', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928988', 'u45', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928989', 'uu', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929147', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929137', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929131', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929122', 'fd', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929121', 'fwgwgew', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929111', 'asr3', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-14', null, null, null);
INSERT INTO `equipment` VALUES ('10929106', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929096', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929095', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929086', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929076', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929075', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929066', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929056', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929055', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929046', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929036', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929035', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-11', null, null, null);
INSERT INTO `equipment` VALUES ('10929026', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929016', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929015', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10929006', 'dh', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928996', 'ge', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928995', 'ryiyw3r', '天河3号', '99999.00', 'T-800', '服务器', '使用中', '第一实验楼西203', '中国科学院', '廖仕杰', '2016-03-28 00:00:00', '计算机科学学院', '廖仕杰', '2012110328', '备注个串串', '2016-05-06', null, null, null);
INSERT INTO `equipment` VALUES ('10928494', 'CS-2012-407-25', '电脑主机', '1500.00', 'intel3000', '电脑主机', '已借出', '一实验楼西207ffff', '英特尔', '行长', '2016-04-12 00:00:00', '计算机科学学院', '罗斯', '1', '电脑主机', '2016-05-11', null, null, null);
