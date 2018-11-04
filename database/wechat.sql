/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.27 : Database - wechat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wechat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wechat`;

/*Table structure for table `chat_content` */

DROP TABLE IF EXISTS `chat_content`;

CREATE TABLE `chat_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_id` int(11) DEFAULT NULL,
  `friend_id` int(11) DEFAULT NULL,
  `insert_time` decimal(10,0) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `chat_content` */

insert  into `chat_content`(`id`,`host_id`,`friend_id`,`insert_time`,`content`,`state`) values (1,1,2,'213','1TO2',0),(2,2,1,'222','2TO1',1),(4,1,2,'555','1',0),(5,1,2,'555','你好',0),(6,1,2,'555','你好',0),(7,1,2,'555','你好',0),(8,1,2,'555','你好',0);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fc_id` int(11) DEFAULT NULL,
  `s_uid` int(11) DEFAULT NULL,
  `r_uid` int(11) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `insert_time` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

/*Table structure for table `friend_circle` */

DROP TABLE IF EXISTS `friend_circle`;

CREATE TABLE `friend_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `insert_time` decimal(10,0) DEFAULT NULL,
  `num_good` int(11) DEFAULT NULL,
  `pic_paths` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `friend_circle` */

insert  into `friend_circle`(`id`,`uid`,`content`,`insert_time`,`num_good`,`pic_paths`) values (1,1,'今天','123',1,'sdas'),(2,3,'比心','17',2,'ads');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `mcode` varchar(30) DEFAULT NULL,
  `head_pic_path` varchar(32) DEFAULT NULL,
  `friend_pic_path` varchar(32) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `motto` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`mcode`,`head_pic_path`,`friend_pic_path`,`province`,`city`,`motto`,`phone`,`pwd`,`sex`) values (1,'张三','zhangsan','asd','sadasdsa','山西','太原','千山鸟飞绝','11111111111','123456',1),(2,'李四','lisi','bggt','bgt','山西','临汾','万径人踪灭','13222222222','123456',0),(3,'王五','wangwu','asdsadsa','asdsadsad','山西','运城','孤舟蓑笠翁','13333333333','123456',1),(4,'赵六','zhaoliu','asdsadsa','asdsadsa','山西','吕梁','独钓寒江雪','14444444444','123456',0),(5,'钱七','qianqi','asdsadsa','asdasdsa','山西','晋中','一去二三里','15555555555','123456',1),(6,'马八','maba','asdasd','sadasdsa','山西','长治','烟村四五家','16666666666','123456',0),(7,'董九','dongjiu','asdasda','asdsad','山西','汾阳','亭台六七座','17777777777','123456',1),(8,'刘十','liushi',NULL,NULL,'山西','孝义','八九十只花','18888888888','123456',1),(9,'吕二','lver',NULL,NULL,'山西','高平','哇哈哈哈哈','19999999999','123456',1),(10,'子华星','xing',NULL,NULL,'福建','厦门','你还怕大雨吗','15312345678','123456',0),(11,'桐宇','tong',NULL,NULL,'山西','大同','独家记忆','13364738854','123456',0),(12,'紫夏','xia',NULL,NULL,'陕西','西安','夏天夏天悄悄过去留下小秘密','15234342453','123456',0),(13,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'16923455322','123456',0),(14,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15123680766','123456',0),(15,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13567885678','123456',0),(16,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13382635772','123456',0),(17,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13725782921','123456',0),(18,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15836367891','123456',0),(19,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13472628971','123456',0),(20,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13587281913','123456',0),(21,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','123456',0),(22,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(23,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(24,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(25,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(26,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(27,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(28,'11111','asd','dsa','qwe','rew','xzc','asdsadasda','11111','11111',NULL);

/*Table structure for table `user2user` */

DROP TABLE IF EXISTS `user2user`;

CREATE TABLE `user2user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_id` int(11) DEFAULT NULL,
  `friend_id` int(11) DEFAULT NULL,
  `mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `user2user` */

insert  into `user2user`(`id`,`host_id`,`friend_id`,`mark`) values (1,1,2,'qwe'),(2,2,1,'asd'),(3,1,3,NULL),(4,2,3,NULL),(5,4,3,NULL),(6,5,3,NULL),(7,6,3,NULL),(8,7,3,NULL),(9,8,3,NULL),(10,9,3,NULL),(11,3,5,NULL),(12,11,2,NULL),(13,2,5,NULL),(14,3,8,NULL),(15,4,1,NULL),(16,2,5,NULL),(17,3,6,NULL),(18,11,1,NULL),(19,12,4,NULL),(20,9,5,NULL),(21,5,7,NULL),(22,5,9,NULL),(23,3,1,NULL),(24,7,9,NULL),(25,7,3,NULL),(26,7,6,NULL),(27,28,1,NULL),(28,28,2,NULL),(29,28,28,NULL),(30,1,1,NULL),(31,2,2,NULL),(32,2,3,NULL),(33,2,4,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
