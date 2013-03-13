-- MySQL dump 10.13  Distrib 5.5.14, for osx10.7 (i386)
--
-- Host: localhost    Database: moneyger4u
-- ------------------------------------------------------
-- Server version	5.5.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `daily_outcome`
--

DROP TABLE IF EXISTS `daily_outcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_outcome` (
  `DAILY_OUTCOME_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `OUTCOME_DATE` date NOT NULL,
  `DAILY_OUTCOME_CATEGORY_ID` int(3) unsigned NOT NULL,
  `OUTCOME_NAME` varchar(100) NOT NULL,
  `AMOUNT` int(10) unsigned NOT NULL,
  `PAYMENT` varchar(10) NOT NULL,
  `IS_WASTE` tinyint(1) NOT NULL,
  `REMARKS` varchar(256) DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL,
  `UPDATED_BY` int(11) NOT NULL,
  PRIMARY KEY (`DAILY_OUTCOME_ID`),
  KEY `DAILY_OUTCOME_CATEGORY_ID` (`DAILY_OUTCOME_CATEGORY_ID`),
  KEY `UPDATED_BY` (`UPDATED_BY`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `daily_outcome_ibfk_3` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `daily_outcome_ibfk_1` FOREIGN KEY (`DAILY_OUTCOME_CATEGORY_ID`) REFERENCES `daily_outcome_category` (`DAILY_OUTCOME_CATEGORY_ID`),
  CONSTRAINT `daily_outcome_ibfk_2` FOREIGN KEY (`UPDATED_BY`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_outcome`
--

LOCK TABLES `daily_outcome` WRITE;
/*!40000 ALTER TABLE `daily_outcome` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_outcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_outcome_category`
--

DROP TABLE IF EXISTS `daily_outcome_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_outcome_category` (
  `DAILY_OUTCOME_CATEGORY_ID` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(30) NOT NULL,
  `PARENT_OUTCOME_CATEGORY_ID` int(3) unsigned NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`DAILY_OUTCOME_CATEGORY_ID`),
  UNIQUE KEY `id_name` (`DAILY_OUTCOME_CATEGORY_ID`,`CATEGORY_NAME`),
  KEY `PARENT_OUTCOME_CATEGORY_ID` (`PARENT_OUTCOME_CATEGORY_ID`),
  CONSTRAINT `daily_outcome_category_ibfk_1` FOREIGN KEY (`PARENT_OUTCOME_CATEGORY_ID`) REFERENCES `parent_outcome_category` (`PARENT_OUTCOME_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_outcome_category`
--

LOCK TABLES `daily_outcome_category` WRITE;
/*!40000 ALTER TABLE `daily_outcome_category` DISABLE KEYS */;
INSERT INTO `daily_outcome_category` VALUES (1,'米',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(2,'パン',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(3,'麺類',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(4,'肉',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(5,'魚介類',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(6,'野菜',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(7,'卵',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(8,'調味料',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(9,'菓子',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(10,'果物',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(11,'コーヒー',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(12,'ジュース',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(13,'酒類',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(14,'外食',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(15,'その他',1,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(16,'洋服',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(17,'下着',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(18,'靴',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(19,'アクセサリー',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(20,'傘',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(21,'クリーニング',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(22,'その他',2,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(23,'台所用品',3,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(24,'石鹸',3,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(25,'ティッシュ',3,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(26,'家具',3,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(27,'電化製品',3,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(28,'その他',3,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(29,'保育園',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(30,'幼稚園',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(31,'学校',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(32,'塾',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(33,'書籍',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(34,'文房具',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(35,'習い事',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(36,'その他',4,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(37,'旅行',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(38,'スポーツ',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(39,'映画',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(40,'観劇',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(41,'雑誌',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(42,'DVD',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(43,'写真',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(44,'その他',5,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(45,'交通費',6,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(46,'ガソリン',6,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(47,'駐車料金',6,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(48,'郵便料金',6,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(49,'宅配便',6,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(50,'その他',6,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(51,'医療費',7,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(52,'薬',7,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(53,'その他',7,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(54,'化粧品',8,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(55,'理髪',8,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(56,'エステ',8,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(57,'入浴',8,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(58,'その他',8,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(59,'冠婚葬祭',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(60,'お中元',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(61,'お歳暮',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(62,'手みやげ',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(63,'町会日',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(64,'募金',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(65,'その他',9,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(66,'家族の小遣い',10,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(67,'その他',10,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(68,'育児',11,'2013-03-12 15:00:00','2013-03-12 15:00:00',0),(69,'ペット',11,'2013-03-12 15:00:00','2013-03-12 15:00:00',0);
/*!40000 ALTER TABLE `daily_outcome_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family` (
  `FAMILY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FAMILY_NAME` varchar(10) NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FAMILY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
INSERT INTO `family` VALUES (1,'My Family','2013-03-12 15:00:00','2013-03-12 15:00:00',0);
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income` (
  `INCOME_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `INCOME_CATEGORY_ID` int(3) unsigned NOT NULL,
  `INCOME_NAME` varchar(50) NOT NULL,
  `AMOUNT` int(10) unsigned NOT NULL,
  `INCOME_DATE` date NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL,
  `FAMILY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`INCOME_ID`),
  KEY `FAMILY_ID` (`FAMILY_ID`),
  KEY `INCOME_CATEGORY_ID` (`INCOME_CATEGORY_ID`),
  CONSTRAINT `income_ibfk_2` FOREIGN KEY (`INCOME_CATEGORY_ID`) REFERENCES `income_category` (`INCOME_CATEGORY_ID`),
  CONSTRAINT `income_ibfk_1` FOREIGN KEY (`FAMILY_ID`) REFERENCES `family` (`FAMILY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_category`
--

DROP TABLE IF EXISTS `income_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income_category` (
  `INCOME_CATEGORY_ID` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(30) NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`INCOME_CATEGORY_ID`),
  UNIQUE KEY `CATEGORY_NAME` (`CATEGORY_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_category`
--

LOCK TABLES `income_category` WRITE;
/*!40000 ALTER TABLE `income_category` DISABLE KEYS */;
INSERT INTO `income_category` VALUES (1,'給料','2013-03-12 15:00:00','2013-03-12 15:00:00',0);
/*!40000 ALTER TABLE `income_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_outcome`
--

DROP TABLE IF EXISTS `monthly_outcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_outcome` (
  `MONTHLY_OUTCOME_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `OUTCOME_DATE` date NOT NULL,
  `MONTHLY_OUTCOME_CATEGORY_ID` int(3) unsigned NOT NULL,
  `OUTCOME_NAME` varchar(100) DEFAULT NULL,
  `AMOUNT` int(10) unsigned NOT NULL,
  `QUANTITY` decimal(5,3) unsigned DEFAULT NULL,
  `REMARKS` varchar(256) DEFAULT NULL,
  `FAMILY_ID` int(10) unsigned NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL DEFAULT '0',
  `UPDATED_BY` int(11) NOT NULL,
  PRIMARY KEY (`MONTHLY_OUTCOME_ID`),
  KEY `FAMILY_ID` (`FAMILY_ID`),
  KEY `MONTHLY_OUTCOME_CATEGORY_ID` (`MONTHLY_OUTCOME_CATEGORY_ID`),
  KEY `UPDATED_BY` (`UPDATED_BY`),
  CONSTRAINT `monthly_outcome_ibfk_3` FOREIGN KEY (`UPDATED_BY`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `monthly_outcome_ibfk_1` FOREIGN KEY (`FAMILY_ID`) REFERENCES `family` (`FAMILY_ID`),
  CONSTRAINT `monthly_outcome_ibfk_2` FOREIGN KEY (`MONTHLY_OUTCOME_CATEGORY_ID`) REFERENCES `monthly_outcome_category` (`MONTHLY_OUTCOME_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_outcome`
--

LOCK TABLES `monthly_outcome` WRITE;
/*!40000 ALTER TABLE `monthly_outcome` DISABLE KEYS */;
/*!40000 ALTER TABLE `monthly_outcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_outcome_category`
--

DROP TABLE IF EXISTS `monthly_outcome_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_outcome_category` (
  `MONTHLY_OUTCOME_CATEGORY_ID` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(30) NOT NULL,
  `UNIT_NAME` varchar(10) DEFAULT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`MONTHLY_OUTCOME_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_outcome_category`
--

LOCK TABLES `monthly_outcome_category` WRITE;
/*!40000 ALTER TABLE `monthly_outcome_category` DISABLE KEYS */;
INSERT INTO `monthly_outcome_category` VALUES (1,'家賃','','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(2,'電気','kWh','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(3,'ガス','m^3','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(4,'水道','m^3','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(5,'電話','分','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(6,'インターネット','','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(7,'新聞','','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(8,'保険','','2013-03-12 15:00:00','2013-03-12 15:00:00',0),(9,'貯蓄','','2013-03-12 15:00:00','2013-03-12 15:00:00',0);
/*!40000 ALTER TABLE `monthly_outcome_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_outcome_category`
--

DROP TABLE IF EXISTS `parent_outcome_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_outcome_category` (
  `PARENT_OUTCOME_CATEGORY_ID` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(30) NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`PARENT_OUTCOME_CATEGORY_ID`),
  UNIQUE KEY `CATEGORY_NAME` (`CATEGORY_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_outcome_category`
--

LOCK TABLES `parent_outcome_category` WRITE;
/*!40000 ALTER TABLE `parent_outcome_category` DISABLE KEYS */;
INSERT INTO `parent_outcome_category` VALUES (1,'食費','2013-03-12 15:06:44','2013-03-12 15:06:02',0),(2,'衣服','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(3,'日用品','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(4,'教育・教養','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(5,'娯楽・レジャー','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(6,'交通・通信','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(7,'医療','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(8,'美容・衛生','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(9,'交際','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(10,'小遣い','2013-03-12 15:06:44','2013-03-12 15:06:44',0),(11,'その他','2013-03-12 15:06:44','2013-03-12 15:06:44',0);
/*!40000 ALTER TABLE `parent_outcome_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_NAME` varchar(10) NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMIN','2013-03-12 15:00:00','2013-03-12 15:00:00',0),('ROOT','2013-03-12 16:09:18','2013-03-12 15:00:00',0),('USER','2013-03-12 15:00:00','2013-03-12 15:00:00',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(30) NOT NULL,
  `LAST_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(10) unsigned NOT NULL,
  `FAMILY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FAMILY_ID` (`FAMILY_ID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`FAMILY_ID`) REFERENCES `family` (`FAMILY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Toshiaki','Maki','maki@example.com','password','2013-03-12 15:00:00','2013-03-12 15:00:00',0,1),(2,'Taro','Yamada','yamada@example.com','password','2013-03-12 15:00:00','2013-03-12 15:00:00',0,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_NAME` varchar(10) NOT NULL,
  KEY `ROLE_NAME` (`ROLE_NAME`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`ROLE_NAME`) REFERENCES `role` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'USER'),(1,'ADMIN'),(1,'ROOT'),(2,'USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-13  2:21:01
