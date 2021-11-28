-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jdbc
-- ------------------------------------------------------
-- Server version	5.7.28

--
-- Table structure for table `jdbc_account`
--

DROP TABLE IF EXISTS `jdbc_account`;
CREATE TABLE `jdbc_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号名',
  `balance` decimal(15,2) NOT NULL COMMENT '账号余额',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '账号状态。1表示启用，0表示禁用',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
