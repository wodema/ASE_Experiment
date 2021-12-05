/*
 Navicat MySQL Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 192.168.2.165:4396
 Source Schema         : soft_exp

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 05/12/2021 14:14:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for paper_question
-- ----------------------------
DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question`  (
  `paper_id` int NOT NULL COMMENT '卷子ID号',
  `question_id` int NOT NULL,
  PRIMARY KEY (`paper_id`, `question_id`) USING BTREE,
  CONSTRAINT `paper_question_paper_show_paper_id_fk` FOREIGN KEY (`paper_id`) REFERENCES `paper_show` (`paper_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of paper_question
-- ----------------------------
INSERT INTO `paper_question` VALUES (3, 11);
INSERT INTO `paper_question` VALUES (3, 12);
INSERT INTO `paper_question` VALUES (3, 13);
INSERT INTO `paper_question` VALUES (3, 14);
INSERT INTO `paper_question` VALUES (3, 15);
INSERT INTO `paper_question` VALUES (3, 16);
INSERT INTO `paper_question` VALUES (3, 17);
INSERT INTO `paper_question` VALUES (3, 18);
INSERT INTO `paper_question` VALUES (3, 19);
INSERT INTO `paper_question` VALUES (3, 20);

-- ----------------------------
-- Table structure for paper_show
-- ----------------------------
DROP TABLE IF EXISTS `paper_show`;
CREATE TABLE `paper_show`  (
  `paper_id` int NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `paper_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程名称',
  `paper_date` datetime NOT NULL DEFAULT '2021-12-12 00:00:00' COMMENT '考试日期',
  `total_time` int NULL DEFAULT 600 COMMENT '持续时长秒数',
  `total_score` int NULL DEFAULT 100 COMMENT '总分',
  PRIMARY KEY (`paper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of paper_show
-- ----------------------------
INSERT INTO `paper_show` VALUES (1, 'jsj', '2021-12-12 00:00:00', 600, 100);
INSERT INTO `paper_show` VALUES (2, '计算机', '2021-12-12 00:00:00', 600, 100);
INSERT INTO `paper_show` VALUES (3, '计算机综合期末考试', '2021-12-04 17:13:28', 600, 100);

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kind` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int NULL DEFAULT 10,
  `option1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `questions_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (1, '下列有关计算机结构的叙述中，错误的是（）。', '计算机', 10, '最早的计算机基本上采用直接连接的方式，冯.诺依曼研制的计算机IAS，基本上就采用了直接连接的结构', '直接连接方式连接速度快，而且易于扩展 ', '显得计算机普遍采用总线结构', '数据总线的位数，通常与CPU的位数相对应', 'B');
INSERT INTO `questions` VALUES (2, '编译程序绝大多数时间花在（）上。', '计算机', 10, '词法分析', '语法分析', '语义分析', '表格管理', 'D');
INSERT INTO `questions` VALUES (3, '计算器', '英语', 10, 'asd', 'a', 'fas', 'vxc', 'C');
INSERT INTO `questions` VALUES (4, 'sb', 'sb', 10, 'asd', 'akdj', 'askdh', 'sada', 'D');
INSERT INTO `questions` VALUES (5, '某计算机的内存容量为256M，指的是（）。', '计算机', 10, '256位', '265M字节', '256M字', '256000K字', 'B');
INSERT INTO `questions` VALUES (6, '微型计算机存储系统中的Cache是（）。', '计算机', 10, '只读存储器', '高速缓冲存储器 ', '可编程只读存储器', '可擦可写只读存储器', 'B');
INSERT INTO `questions` VALUES (7, 'CSS 中，top 属性规定元素的顶部边缘。该属性定义了一个定位元素的上外边距边界与其包含块上（）边距的距离？', '计算机', 10, '外边距', '边框', '内边距', '内容', 'C');
INSERT INTO `questions` VALUES (8, '二分查找算法的时间复杂度是（）？', '计算机', 10, 'n', 'logn', 'n^2', '2n', 'B');
INSERT INTO `questions` VALUES (9, 'HTTP协议通常使用（）协议进行传输？', '计算机', 10, 'ARP', 'DHCP', 'UDP', 'TCP', 'D');
INSERT INTO `questions` VALUES (10, '查看DNS缓存记录的Windows cmd命令是（）？', '计算机', 10, 'ipconfig/displaydns', 'nslookup', 'ipconfig/release', 'ipconfig/flushdns', 'A');
INSERT INTO `questions` VALUES (11, 'DHCP（）报文的目的IP地址为255.255.255.255。', '计算机', 10, 'DhcpDisover', 'DhcpOffer', 'DhcpAck', 'DhcpNack', 'A');
INSERT INTO `questions` VALUES (12, '下列地址中，（）不是DHCP服务器分配的IP地址。', '计算机', 10, '196.254.109.100', '169.254.12.42', '69.254.48.45', '96.254.54.15', 'B');
INSERT INTO `questions` VALUES (13, 'DHCP通常可以为客户端自动配置哪些网络参数（）？', '计算机', 10, 'IP，掩码，网关，DNS', 'IP，掩码，域名，SMTP', '网关，掩码，浏览器，FTP', 'IP，网关，DNS，服务器', 'A');
INSERT INTO `questions` VALUES (14, 'DNS服务器在名称解析过程中正确的查询顺序为（）？', '计算机', 10, '本地缓存记录→区域记录→转发域名服务器→根域名服务器', '区域记录→本地缓存记录→转发域名服务器→根域名服务器', '本地缓存记录→区域记录→根域名服务器→转发域名服务器', '区域记录→本地缓存记录→根域名服务器→转发域名服务器', 'A');
INSERT INTO `questions` VALUES (15, '在TCP/IP协议中，序号小于（）的端口称为熟知端口(well-known port)。', '计算机', 10, '1024', '64', '256', '128', 'A');
INSERT INTO `questions` VALUES (16, '在Internet上用TCP/IP播放视频，想用传输层的最快协议，以减少时延，要使用（）？', '计算机', 10, 'UDP协议的低开销特性', 'UDP协议的高开销特性', 'TCP协议的低开销特性', 'TCP协议的高开销特性', 'A');
INSERT INTO `questions` VALUES (17, '在TCP协议中采用（）来区分不同的应用进程。', '计算机', 10, '端口号', 'IP地址', '协议类型', 'MAC地址', 'A');
INSERT INTO `questions` VALUES (18, '可靠的传输协议中的“可靠”指的是（）。', '计算机', 10, '使用面向连接的会话', '使用“尽力而为”的传输', '使用滑动窗口来维持可靠性', '使用确认重传机制来确保传输的数据不丢失', 'D');
INSERT INTO `questions` VALUES (19, '假设拥塞窗口为50KB，接收窗口为80KB，TCP能够发送的最大字节数为（）。', '计算机', 10, '50KB', '80KB', '130KB', '30KB', 'A');
INSERT INTO `questions` VALUES (20, '主机A向主机B发送一个（SYN=1，seq=2000）的TCP报文，期望与主机B建立连接，若主机B接受连接请求，则主机B发送的正确有TCP报文可能是（）？', '计算机', 10, '（SYN=0,ACK=0,seq=2001,ack=2001）', '（SYN=1,ACK=1,seq=2000,ack=2000）', '•	C.（SYN=1,ACK=1,seq=2001,ack=2001）', '（SYN=0,ACK=1,seq=2000,ack=2000）', 'C');
INSERT INTO `questions` VALUES (21, '主机A向主机B连续发送了两个TCP报文段，其序号分别为70和100。试问： （1）第一个报文段携带了（）个字节的数据？', '计算机', 10, ' 70', '30', '100', '170', 'B');
INSERT INTO `questions` VALUES (22, 'PCM脉码调制的过程为（）。', '计算机', 10, '采样、量化、编码', '量化、编码、采样', '编码、量化、采样', '采样、编码、量化', 'A');
INSERT INTO `questions` VALUES (23, '若某采用4相位调制的通信链路的数据传输速率为2400bps，则该链路的波特率为（）？', '计算机', 10, '600Baud', '1200Baud', '4800Baud', '9600Baud', 'B');
INSERT INTO `questions` VALUES (24, '以下关于数据传输速率的描述中，错误的是（）？', '计算机', 10, '数据传输速率表示每秒钟传输构成数据代码的二进制比特数', '对于二进制数据，数据传输速率为S=1/T (bps)', '常用的数据传输速率单位有: 1Mbps=1.024×106bps', '数据传输速率是描述数据传输系统性能的重要技术指标之一', 'C');
INSERT INTO `questions` VALUES (25, '以下关于时分多路复用概念的描述中，错误的是（）？', '计算机', 10, '时分多路复用将线路使用的时间分成多个时间片', '时分多路复用分为同步时分多路复用与统计时分多路复用', '时分多路复用使用“帧”与数据链路层“帧”的概念、作用是不同的', '统计时分多路复用将时间片预先分配给各个信道', 'D');
INSERT INTO `questions` VALUES (26, '1000BASE-T标准支持的传输介质是（）？', '计算机', 10, '双绞线', '同轴电缆', '光纤', '无线电', 'A');
INSERT INTO `questions` VALUES (27, '一个以太网交换机，读取整个数据帧，对数据帧进行差错校验后再转发出去，这种交换方式称为 （）？', '计算机', 10, '直通交换', '无碎片交换', '无差错交换', '存储转发交换', 'D');
INSERT INTO `questions` VALUES (28, '关于VLAN，下面的描述中正确的是（）。', '计算机', 10, '一个新的交换机没有配置VLAN', '通过配置VLAN减少了冲突域的数量', '一个VLAN不能跨越多个交换机', '各个VLAN属于不同的广播域', 'D');
INSERT INTO `questions` VALUES (29, '以太网协议中使用物理地址作用是（）？', '计算机', 10, '.用于不同子网中的主机进行通信', '作为第二层设备的唯一标识', '用于区别第二层第三层的协议数据单元', '保存主机可检测未知的远程设备', 'B');
INSERT INTO `questions` VALUES (30, '以太网采用的CSMA/CD协议，当冲突发生时要通过二进制指数后退算法计算后退延时， 关于这个算法，以下论述中错误的是 （）？', '计算机', 10, '冲突次数越多，后退的时间越短', '平均后退次数的多少与负载大小有关', '后退时延的平均值与负载大小有关', '重发次数达到一定极限后放弃发送', 'A');
INSERT INTO `questions` VALUES (31, '以下关于交换机获取与其端口连接设备的MAC地址的叙述中，正确的是（）。', '计算机', 10, '交换机从路由表中提取设备的MAC地址', '交换机检查端口流入分组的源地址', '交换机之间互相交换地址表', '网络管理员手工输入设备的MAC地址', 'B');
INSERT INTO `questions` VALUES (32, '如果G (x）为11010010，以下4个CRC校验比特序列中只有哪个可能是正确的 ？', '计算机', 10, '1101011001', '101011011', '11011011', '1011001', 'B');
INSERT INTO `questions` VALUES (33, '以下关于Ethernet物理地址的描述中，错误的是（）？', '计算机', 10, 'Ethernet物理地址又叫做MAC地址', '48位的Ethernet物理地址允许分配的地址数达到247个', '网卡的物理地址写入主机的EPROM中', '每一块网卡的物理地址在全世界是唯一的', 'C');
INSERT INTO `questions` VALUES (34, '下列帧类型中，不属于HDLC帧类型的是（）？', '计算机', 10, '信息帧', '确认帧', '监控帧', '无编号帧', 'B');
INSERT INTO `questions` VALUES (35, '通过交换机连接的一组站点，关于它们的广播域和冲突域说法正确的是（）。', '计算机', 10, '组成一个冲突域，但不是一个广播域', '组成一个广播域，但不是一个冲突域', '组成一个冲突域，也是一个广播域', '既不一个冲突域，也不是一个广播域', 'B');
INSERT INTO `questions` VALUES (36, '数据链路层的数据单位是（）。', '计算机', 10, '帧', '字节', '比特', '分组', 'A');
INSERT INTO `questions` VALUES (37, 'LAN参考模型可分为物理层、（ ）。', '计算机', 10, 'MAC，LLC等三层', 'LLC，MHS等三层', 'MAC，FTAM等三层', 'LLC，VT等三层', 'A');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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


-- ----------------------------
-- Table structure for answeredquestions
-- ----------------------------
DROP TABLE IF EXISTS `answeredquestions`;
CREATE TABLE `answeredquestions`  (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `qid` int NULL DEFAULT NULL,
                                      `sid` int NOT NULL,
                                      `studentAnswer` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                      PRIMARY KEY (`id`) USING BTREE,
                                      UNIQUE INDEX `wrongQuestions_id_uindex`(`id`) USING BTREE,
                                      INDEX `wrongquestions_student_id_fk`(`sid`) USING BTREE,
                                      INDEX `wrongquestions_questions_id_fk`(`qid`) USING BTREE,
                                      CONSTRAINT `wrongquestions_questions_id_fk` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT `wrongquestions_student_id_fk` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of answeredquestions
-- ----------------------------
INSERT INTO `answeredquestions` VALUES (1, 1, 3, 'A');
INSERT INTO `answeredquestions` VALUES (2, 2, 3, 'A');
INSERT INTO `answeredquestions` VALUES (3, 2, 3, 'B');
INSERT INTO `answeredquestions` VALUES (17, 3, 3, 'A');
INSERT INTO `answeredquestions` VALUES (24, 5, 3, 'C');
INSERT INTO `answeredquestions` VALUES (25, 5, 3, 'C');
INSERT INTO `answeredquestions` VALUES (26, 5, 3, 'A');
INSERT INTO `answeredquestions` VALUES (27, 2, 3, 'C');

INSERT INTO `paper_show` VALUES (4, '英语', '2021-12-04 20:45:02', 600, 100);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `sid` int NOT NULL,
                          `paper_id` int NOT NULL,
                          `score` float NOT NULL,
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE INDEX `score_id_uindex`(`id`) USING BTREE,
                          INDEX `score_student_id_fk`(`sid`) USING BTREE,
                          INDEX `score_paper_show_paper_id_fk`(`paper_id`) USING BTREE,
                          CONSTRAINT `score_paper_show_paper_id_fk` FOREIGN KEY (`paper_id`) REFERENCES `paper_show` (`paper_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `score_student_id_fk` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 3, 2, 90);
INSERT INTO `score` VALUES (2, 3, 1, 60);
INSERT INTO `score` VALUES (4, 5, 2, 32);

INSERT INTO `student` VALUES (5, 'xy', 21);