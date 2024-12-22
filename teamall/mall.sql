/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2024-04-23 16:17:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `carid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户表id',
  `proid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id',
  `quantity` int DEFAULT NULL COMMENT '数量',
  `checked` int DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`carid`) USING BTREE,
  KEY `FK_Reference_2` (`userid`) USING BTREE,
  KEY `FK_Reference_3` (`proid`) USING BTREE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`proid`) REFERENCES `product` (`proid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('14745212-175b-43f0-99c1-1ec21f1a461a', '1', '4', '1', '1', '2024-04-23 15:03:25', '2024-04-23 15:03:25');
INSERT INTO `cart` VALUES ('ff95d789-c40b-4671-91b5-5bb01a80a81c', '1', '14', '1', '1', '2024-04-23 15:38:09', '2024-04-23 15:38:09');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cateid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别Id',
  `parentid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别名称',
  `status` int DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sortorder` int DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`cateid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', null, '茶叶', '1', null, '2024-04-02 21:51:51', '2024-04-02 21:51:53');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单子表id',
  `orderid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单id',
  `userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户表id',
  `proid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id',
  `currentunitprice` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int DEFAULT NULL COMMENT '商品数量',
  `totalprice` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `status` int DEFAULT NULL COMMENT '订单条目状态：0-未评价，1-已评价',
  `revid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评价id',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_5` (`orderid`) USING BTREE,
  KEY `FK_Reference_8` (`userid`) USING BTREE,
  KEY `FK_Reference_9` (`proid`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`proid`) REFERENCES `product` (`proid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('05b474b1-d7d1-4b4f-b7d3-31f6e59e0f0e', 'be498ce5-a291-4d21-82b4-ab5ae9a216a2', '1', '10', '800.00', '10', '8000.00', '0', null, '2024-04-21 14:18:34', '2024-04-21 14:18:34');
INSERT INTO `orderitem` VALUES ('0d26399b-d065-4e1d-bef8-7219939d1c21', 'bfae6d6a-eae5-4274-b679-ddce050a3ae0', '1', '2', '800.00', '6', '4800.00', '0', null, '2024-04-21 14:21:22', '2024-04-21 14:21:22');
INSERT INTO `orderitem` VALUES ('1', '1', '1', '1', '12.00', '2', '24.00', '1', '274df1f3-3fa2-455e-a84c-b3b4c1bdbff7', '2024-04-18 17:58:13', '2024-04-21 15:51:12');
INSERT INTO `orderitem` VALUES ('13794659-7484-423b-a619-8479c969655d', 'bfae6d6a-eae5-4274-b679-ddce050a3ae0', '1', '10', '800.00', '10', '8000.00', '0', null, '2024-04-21 14:21:22', '2024-04-21 14:21:22');
INSERT INTO `orderitem` VALUES ('2082dfbf-7d43-47a4-9426-36871e93a62b', 'be498ce5-a291-4d21-82b4-ab5ae9a216a2', '1', '2', '800.00', '6', '4800.00', '1', 'e2d2fc9d-68d7-472a-974d-675e5c0c9e16', '2024-04-21 14:18:34', '2024-04-22 15:20:44');
INSERT INTO `orderitem` VALUES ('2e724155-3804-4b53-a195-446b4718cd1e', '66d5847f-ee34-4ecb-8cb2-833f73fe6089', '1', '4', '800.00', '1', '800.00', '1', '38faacea-bc47-42d9-a186-b1bc21ea628a', '2024-04-21 14:26:06', '2024-04-22 09:28:38');
INSERT INTO `orderitem` VALUES ('2f0bfbed-21be-4df7-bfae-edd45b01a09b', 'be498ce5-a291-4d21-82b4-ab5ae9a216a2', '1', '8', '800.00', '7', '5600.00', '0', null, '2024-04-21 14:18:34', '2024-04-21 14:18:34');
INSERT INTO `orderitem` VALUES ('2f2fcf8a-7e5d-432f-8f9a-17d3e5ef614d', 'bfae6d6a-eae5-4274-b679-ddce050a3ae0', '1', '15', '800.00', '1', '800.00', '0', null, '2024-04-21 14:21:22', '2024-04-21 14:21:22');
INSERT INTO `orderitem` VALUES ('3714ddc4-0f6f-49bf-945a-8b5ba33cf706', 'be498ce5-a291-4d21-82b4-ab5ae9a216a2', '1', '7', '800.00', '4', '3200.00', '0', null, '2024-04-21 14:18:34', '2024-04-21 14:18:34');
INSERT INTO `orderitem` VALUES ('41bfc3f8-603d-4afa-b52a-53fc03abc4a1', '48a36146-caae-4244-870b-6d8c4e88477b', '1', '3', '800.00', '1', '800.00', '1', 'd09c3643-fa0b-46ac-a62a-03d72b0de54a', '2024-04-23 11:32:13', '2024-04-23 16:03:55');
INSERT INTO `orderitem` VALUES ('47c6ed39-9a00-41af-ab26-ad57e967732b', 'bfae6d6a-eae5-4274-b679-ddce050a3ae0', '1', '7', '800.00', '4', '3200.00', '0', null, '2024-04-21 14:21:22', '2024-04-21 14:21:22');
INSERT INTO `orderitem` VALUES ('509aea81-4125-4945-a36d-a919dd80982e', 'bfae6d6a-eae5-4274-b679-ddce050a3ae0', '1', '8', '800.00', '7', '5600.00', '0', null, '2024-04-21 14:21:22', '2024-04-21 14:21:22');
INSERT INTO `orderitem` VALUES ('583ac404-5d69-4891-b4fe-0b55537eb7f4', '66d5847f-ee34-4ecb-8cb2-833f73fe6089', '1', '17', '800.00', '1', '800.00', '0', null, '2024-04-21 14:26:06', '2024-04-21 14:26:06');
INSERT INTO `orderitem` VALUES ('7b386237-6938-4b2c-8915-50593f51096e', 'be498ce5-a291-4d21-82b4-ab5ae9a216a2', '1', '15', '800.00', '1', '800.00', '0', null, '2024-04-21 14:18:34', '2024-04-21 14:18:34');
INSERT INTO `orderitem` VALUES ('ffff5218-18d7-4da2-b02e-97232af8e757', '48a36146-caae-4244-870b-6d8c4e88477b', '1', '9', '800.00', '1', '800.00', '0', null, '2024-04-23 11:32:13', '2024-04-23 11:32:13');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `paymenttype` int DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int DEFAULT NULL COMMENT '运费,单位是元',
  `status` int DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `paymenttime` datetime DEFAULT NULL COMMENT '支付时间',
  `sendtime` datetime DEFAULT NULL COMMENT '发货时间',
  `endtime` datetime DEFAULT NULL COMMENT '交易完成时间',
  `closetime` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`orderid`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '80.00', null, null, '50', '2024-04-22 16:31:03', '2024-04-22 16:31:13', '2024-04-23 09:42:44', null, '2024-04-18 16:54:02', '2024-04-23 09:42:44');
INSERT INTO `orders` VALUES ('48a36146-caae-4244-870b-6d8c4e88477b', '1', '1600.00', '1', '0', '20', '2024-04-23 11:32:13', null, null, null, '2024-04-23 11:32:13', '2024-04-23 11:32:13');
INSERT INTO `orders` VALUES ('66d5847f-ee34-4ecb-8cb2-833f73fe6089', '1', '1600.00', '1', '0', '40', '2024-04-23 09:41:20', '2024-04-23 09:39:23', null, null, '2024-04-21 14:26:06', '2024-04-23 09:39:23');
INSERT INTO `orders` VALUES ('be498ce5-a291-4d21-82b4-ab5ae9a216a2', '1', '22400.00', '1', '0', '40', '2024-04-21 14:18:34', '2024-04-23 09:42:36', null, null, '2024-04-21 14:18:34', '2024-04-23 09:42:36');
INSERT INTO `orders` VALUES ('bfae6d6a-eae5-4274-b679-ddce050a3ae0', '1', '22400.00', '1', '0', '20', '2024-04-21 14:21:22', null, null, null, '2024-04-21 14:21:22', '2024-04-21 14:21:22');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `proid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `cateid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别Id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品副标题',
  `mainimage` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品主图,url相对地址',
  `subimages` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '图片地址,json格式,扩展用',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '规格，json格式',
  `price` decimal(20,2) NOT NULL COMMENT '商品原价,单位-元保留两位小数',
  `disprice` decimal(20,2) NOT NULL COMMENT '优惠价格,单位-元保留两位小数',
  `stock` int NOT NULL COMMENT '库存数量',
  `status` int DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '长文描述',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`proid`) USING BTREE,
  KEY `FK_Reference_1` (`cateid`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`cateid`) REFERENCES `category` (`cateid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '1', '商品13', '商品12332', '/upload/1_0.jpg', '[\"/upload/1_1.jpg\",\"/upload/1_2.jpg\",\"/upload/1_3.jpg\"]', null, '900.00', '188.00', '99', '1', '广丰扥广丰灯光', '2024-04-02 21:50:32', '2024-04-22 21:33:46');
INSERT INTO `product` VALUES ('10', '1', '商品10', '商品10', '/upload/10_0.jpeg', '[\"/upload/1_1.jpg\",\"/upload/1_2.jpg\",\"/upload/1_3.jpg\"]', null, '1212.00', '800.00', '100', '1', '商品131', '2024-04-02 21:50:00', '2024-04-22 21:36:32');
INSERT INTO `product` VALUES ('11', '1', '商品11', '商品11', '/upload/11_0.jpg', '[\"/upload/1_1.jpg\",\"/upload/1_2.jpg\",\"/upload/1_3.jpg\"]', null, '1212.00', '800.00', '100', '1', '商品132', '2024-04-02 21:50:00', '2024-04-22 21:37:26');
INSERT INTO `product` VALUES ('12', '1', '商品12', '商品12', '/upload/12_0.jpg', '[\"/upload/1_1.jpg\",\"/upload/1_2.jpg\",\"/upload/1_3.jpg\"]', null, '1212.00', '800.00', '100', '1', '商品133', '2024-04-02 21:50:00', '2024-04-23 14:57:00');
INSERT INTO `product` VALUES ('13', '1', '商品13', '商品13', '/upload/13_0.jpg', '[\"/upload/1_1.jpg\",\"/upload/1_2.jpg\",\"/upload/1_3.jpg\"]', null, '1212.00', '800.00', '100', '1', '商品134', '2024-04-02 21:50:00', '2024-04-23 14:56:31');
INSERT INTO `product` VALUES ('14', '1', '商品14', '商品14', '/upload/14_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品135', '2024-04-02 21:50:00', '2024-04-23 14:56:20');
INSERT INTO `product` VALUES ('15', '1', '商品15', '商品15', '/upload/15_0.jpg', '[\"/upload/15_1.jpg\",\"/upload/15_2.jpg\"]', null, '1212.00', '800.00', '100', '1', '商品136', '2024-04-02 21:50:00', '2024-04-23 16:09:17');
INSERT INTO `product` VALUES ('16', '1', '商品16', '商品16', '/upload/16_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品137', '2024-04-02 21:50:00', '2024-04-23 14:58:23');
INSERT INTO `product` VALUES ('17', '1', '商品17', '商品17', '/upload/17_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品138', '2024-04-02 21:50:00', '2024-04-23 14:58:37');
INSERT INTO `product` VALUES ('18', '1', '商品18', '商品18', '/upload/18_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品139', '2024-04-02 21:50:00', '2024-04-23 14:58:46');
INSERT INTO `product` VALUES ('2', '1', '商品2', '商品2', '/upload/2_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品123', '2024-04-02 21:50:00', '2024-04-23 15:00:13');
INSERT INTO `product` VALUES ('3', '1', '商品3', '商品3', '/upload/3_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品124', '2024-04-02 21:50:00', '2024-04-23 15:00:27');
INSERT INTO `product` VALUES ('4', '1', '商品4', '商品4', '/upload/4_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品125', '2024-04-02 21:50:00', '2024-04-23 15:00:38');
INSERT INTO `product` VALUES ('5', '1', '商品5', '商品5', '/upload/5_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品126', '2024-04-02 21:50:00', '2024-04-23 15:00:47');
INSERT INTO `product` VALUES ('6', '1', '商品6', '商品6', '/upload/6_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品127', '2024-04-02 21:50:00', '2024-04-23 15:01:00');
INSERT INTO `product` VALUES ('7', '1', '商品7', '商品7', '/upload/7_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品128', '2024-04-02 21:50:00', '2024-04-23 15:01:42');
INSERT INTO `product` VALUES ('8', '1', '商品8', '商品8', '/upload/8_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品129', '2024-04-02 21:50:00', '2024-04-23 15:01:53');
INSERT INTO `product` VALUES ('9', '1', '商品9', '商品9', '/upload/9_0.jpg', null, null, '1212.00', '800.00', '100', '1', '商品130', '2024-04-02 21:50:00', '2024-04-23 15:02:02');

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `revid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价id',
  `userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户表id',
  `proid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评价内容',
  `level` int DEFAULT NULL COMMENT '星级1-5',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`revid`) USING BTREE,
  KEY `FK_Reference_2` (`userid`) USING BTREE,
  KEY `FK_Reference_3` (`proid`) USING BTREE,
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`proid`) REFERENCES `product` (`proid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES ('1', '1', '1', 'test', '5', '2024-04-18 15:34:17', '2024-04-18 15:34:20');
INSERT INTO `review` VALUES ('274df1f3-3fa2-455e-a84c-b3b4c1bdbff7', '1', '1', '商品不错', null, '2024-04-21 15:51:12', '2024-04-22 09:19:44');
INSERT INTO `review` VALUES ('38faacea-bc47-42d9-a186-b1bc21ea628a', '1', '4', '商品不错', null, '2024-04-22 09:28:38', '2024-04-22 09:28:38');
INSERT INTO `review` VALUES ('5c5e2982-4bbd-43b6-8609-71dfc0435e07', '1', '2', 'testttttt', null, '2024-04-21 16:03:07', '2024-04-21 16:03:07');
INSERT INTO `review` VALUES ('7194fde1-5658-4c62-b9f2-5c8f56758c18', '1', '4', '商品不错1', null, '2024-04-22 09:21:11', '2024-04-22 09:21:11');
INSERT INTO `review` VALUES ('d09c3643-fa0b-46ac-a62a-03d72b0de54a', '1', '3', '好评', null, '2024-04-23 16:03:55', '2024-04-23 16:03:55');
INSERT INTO `review` VALUES ('e2d2fc9d-68d7-472a-974d-675e5c0c9e16', '1', '2', '454546', null, '2024-04-22 15:20:44', '2024-04-22 15:20:44');
INSERT INTO `review` VALUES ('e63a5ecc-0af2-4582-a0c4-1098bd7fa635', '1', '2', 'testttttt', null, '2024-04-21 16:01:58', '2024-04-21 16:01:58');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户表id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码，MD5加密',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户手机号码',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户收获地址',
  `role` int NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `realname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`userid`,`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user1', '123456', '189488833949', '浙江省杭州市余杭区五常街道文一西路969号3幢5层554室', '0', '用户A', '2024-04-08 21:49:34', '2024-04-21 14:56:37');
INSERT INTO `user` VALUES ('2', 'user2', '123456', '12354334', '北京回龙观', '1', '李四', '2024-04-08 21:49:56', '2024-04-22 22:28:30');
INSERT INTO `user` VALUES ('3c3f1412-9c83-4ce4-b404-89a50385bd0b', 'user3', '123456', '18989898', '南京新街口', '1', '张三', '2024-04-22 15:15:16', '2024-04-22 22:27:56');
