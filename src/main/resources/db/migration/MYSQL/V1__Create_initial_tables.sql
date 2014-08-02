CREATE DATABASE IF NOT EXISTS `moneyger4u` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `moneyger4u`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: localhost    Database: moneyger4u
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `monthly_outcome_category`
--

DROP TABLE IF EXISTS `monthly_outcome_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_outcome_category` (
  `MONTHLY_OUTCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME`               VARCHAR(30)      NOT NULL,
  `UNIT_NAME`                   VARCHAR(10) DEFAULT NULL,
  `CREATED_AT`                  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`                  TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`                     INT(10) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`MONTHLY_OUTCOME_CATEGORY_ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =10
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daily_outcome_category`
--

DROP TABLE IF EXISTS `daily_outcome_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_outcome_category` (
  `DAILY_OUTCOME_CATEGORY_ID`  INT(3) UNSIGNED  NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME`              VARCHAR(30)      NOT NULL,
  `PARENT_OUTCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL,
  `CREATED_AT`                 TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`                 TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`                    INT(10) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`DAILY_OUTCOME_CATEGORY_ID`),
  UNIQUE KEY `id_name` (`DAILY_OUTCOME_CATEGORY_ID`, `CATEGORY_NAME`),
  KEY `PARENT_OUTCOME_CATEGORY_ID` (`PARENT_OUTCOME_CATEGORY_ID`),
  CONSTRAINT `daily_outcome_category_ibfk_1` FOREIGN KEY (`PARENT_OUTCOME_CATEGORY_ID`) REFERENCES `parent_outcome_category` (`PARENT_OUTCOME_CATEGORY_ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =70
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family` (
  `FAMILY_ID`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `FAMILY_NAME` VARCHAR(10)      NOT NULL,
  `CREATED_AT`  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`  TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`     INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`FAMILY_ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =2
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daily_outcome`
--

DROP TABLE IF EXISTS `daily_outcome`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_outcome` (
  `DAILY_OUTCOME_ID`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `OUTCOME_DATE`              DATE             NOT NULL,
  `DAILY_OUTCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL,
  `OUTCOME_NAME`              VARCHAR(100)     NOT NULL,
  `AMOUNT`                    INT(10) UNSIGNED NOT NULL,
  `QUANTITY`                  INT(10) UNSIGNED NOT NULL DEFAULT '1',
  `PAYMENT`                   VARCHAR(10)      NOT NULL,
  `IS_WASTE`                  TINYINT(1)       NOT NULL,
  `REMARKS`                   VARCHAR(256) DEFAULT NULL,
  `USER_ID`                   INT(11)          NOT NULL,
  `CREATED_AT`                TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`                TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`                   INT(10) UNSIGNED NOT NULL,
  `UPDATED_BY`                INT(11)          NOT NULL,
  PRIMARY KEY (`DAILY_OUTCOME_ID`),
  KEY `DAILY_OUTCOME_CATEGORY_ID` (`DAILY_OUTCOME_CATEGORY_ID`),
  KEY `UPDATED_BY` (`UPDATED_BY`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `daily_outcome_ibfk_1` FOREIGN KEY (`DAILY_OUTCOME_CATEGORY_ID`) REFERENCES `daily_outcome_category` (`DAILY_OUTCOME_CATEGORY_ID`),
  CONSTRAINT `daily_outcome_ibfk_2` FOREIGN KEY (`UPDATED_BY`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `daily_outcome_ibfk_3` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =5535
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parent_outcome_category`
--

DROP TABLE IF EXISTS `parent_outcome_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_outcome_category` (
  `PARENT_OUTCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME`              VARCHAR(30)      NOT NULL,
  `CREATED_AT`                 TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`                 TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`                    INT(10) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`PARENT_OUTCOME_CATEGORY_ID`),
  UNIQUE KEY `CATEGORY_NAME` (`CATEGORY_NAME`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =12
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `USER_ID`   INT(11)     NOT NULL,
  `ROLE_NAME` VARCHAR(10) NOT NULL,
  KEY `ROLE_NAME` (`ROLE_NAME`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`ROLE_NAME`) REFERENCES `role` (`ROLE_NAME`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_NAME`  VARCHAR(10)      NOT NULL,
  `CREATED_AT` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`    INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ROLE_NAME`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID`    INT(11)          NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(30)      NOT NULL,
  `LAST_NAME`  VARCHAR(30)      NOT NULL,
  `EMAIL`      VARCHAR(50)      NOT NULL,
  `PASSWORD`   VARCHAR(128)     NOT NULL,
  `CREATED_AT` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT` TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`    INT(10) UNSIGNED NOT NULL,
  `FAMILY_ID`  INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FAMILY_ID` (`FAMILY_ID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`FAMILY_ID`) REFERENCES `family` (`FAMILY_ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =3
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income` (
  `INCOME_ID`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `INCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL,
  `INCOME_NAME`        VARCHAR(50)      NOT NULL,
  `AMOUNT`             INT(10) UNSIGNED NOT NULL,
  `INCOME_DATE`        DATE             NOT NULL,
  `CREATED_AT`         TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`         TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`            INT(10) UNSIGNED NOT NULL,
  `FAMILY_ID`          INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`INCOME_ID`),
  KEY `FAMILY_ID` (`FAMILY_ID`),
  KEY `INCOME_CATEGORY_ID` (`INCOME_CATEGORY_ID`),
  CONSTRAINT `income_ibfk_1` FOREIGN KEY (`FAMILY_ID`) REFERENCES `family` (`FAMILY_ID`),
  CONSTRAINT `income_ibfk_2` FOREIGN KEY (`INCOME_CATEGORY_ID`) REFERENCES `income_category` (`INCOME_CATEGORY_ID`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monthly_outcome`
--

DROP TABLE IF EXISTS `monthly_outcome`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_outcome` (
  `MONTHLY_OUTCOME_ID`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `OUTCOME_DATE`                DATE             NOT NULL,
  `MONTHLY_OUTCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL,
  `OUTCOME_NAME`                VARCHAR(100) DEFAULT NULL,
  `AMOUNT`                      INT(10) UNSIGNED NOT NULL,
  `QUANTITY`                    DECIMAL(8, 3) UNSIGNED DEFAULT NULL,
  `REMARKS`                     VARCHAR(256) DEFAULT NULL,
  `FAMILY_ID`                   INT(10) UNSIGNED NOT NULL,
  `CREATED_AT`                  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`                  TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`                     INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `UPDATED_BY`                  INT(11)          NOT NULL,
  PRIMARY KEY (`MONTHLY_OUTCOME_ID`),
  KEY `FAMILY_ID` (`FAMILY_ID`),
  KEY `MONTHLY_OUTCOME_CATEGORY_ID` (`MONTHLY_OUTCOME_CATEGORY_ID`),
  KEY `UPDATED_BY` (`UPDATED_BY`),
  CONSTRAINT `monthly_outcome_ibfk_1` FOREIGN KEY (`FAMILY_ID`) REFERENCES `family` (`FAMILY_ID`),
  CONSTRAINT `monthly_outcome_ibfk_2` FOREIGN KEY (`MONTHLY_OUTCOME_CATEGORY_ID`) REFERENCES `monthly_outcome_category` (`MONTHLY_OUTCOME_CATEGORY_ID`),
  CONSTRAINT `monthly_outcome_ibfk_3` FOREIGN KEY (`UPDATED_BY`) REFERENCES `user` (`USER_ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =35
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `income_category`
--

DROP TABLE IF EXISTS `income_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income_category` (
  `INCOME_CATEGORY_ID` INT(3) UNSIGNED  NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME`      VARCHAR(30)      NOT NULL,
  `CREATED_AT`         TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_AT`         TIMESTAMP        NOT NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION`            INT(10) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`INCOME_CATEGORY_ID`),
  UNIQUE KEY `CATEGORY_NAME` (`CATEGORY_NAME`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =2
  DEFAULT CHARSET =utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2014-08-03  0:37:04