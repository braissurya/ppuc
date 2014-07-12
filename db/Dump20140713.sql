CREATE DATABASE  IF NOT EXISTS `ppuc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ppuc`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ppuc
-- ------------------------------------------------------
-- Server version	5.6.14-log

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
-- Table structure for table `departmen`
--

DROP TABLE IF EXISTS `departmen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departmen` (
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode departmen',
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdivisi',
  `dept_nm` varchar(50) DEFAULT NULL COMMENT 'nama departmen',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user-id yang terakhir update dari table user',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tanggal terakhir update',
  `sys_jam_update` varchar(8) DEFAULT NULL COMMENT 'jam terakhir update format HH24:mm:ss',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`dept_kd`,`divisi_kd`,`subdiv_kd`),
  KEY `fk_dept_subdiv_idx` (`subdiv_kd`),
  KEY `fk_dept_divisi_idx` (`divisi_kd`),
  CONSTRAINT `fk_dept_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dept_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table list departmen';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmen`
--

LOCK TABLES `departmen` WRITE;
/*!40000 ALTER TABLE `departmen` DISABLE KEYS */;
INSERT INTO `departmen` VALUES ('1','OPT','EYE','OUTLET OPTIK EYEVOLUTION',NULL,NULL,NULL,'ADM','2014-07-13 01:11:55'),('1','OPT','MLW','OUTLET OPTIK melawai2',NULL,NULL,NULL,'ADM','2014-07-13 01:11:56'),('Z','HOL','ACC','UMUM',NULL,NULL,NULL,'ADM','2014-07-13 01:11:55');
/*!40000 ALTER TABLE `departmen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_biaya`
--

DROP TABLE IF EXISTS `detail_biaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_biaya` (
  `kd_biaya` varchar(30) NOT NULL COMMENT 'kode biaya',
  `kd_group` varchar(5) NOT NULL COMMENT 'kode group dari group biaya',
  `biaya_description` varchar(45) DEFAULT NULL,
  `f_putus` int(1) DEFAULT NULL COMMENT 'no => record yang dibentuk = qty yang diinput\nno ppuc yang digenerate sesuai jumlah quantity item',
  `f_used` int(1) DEFAULT NULL COMMENT 'yes => sudah direlasikan dengan hak approve. Satu biaya hanya bisa direlasikan dengan satu bagian tertentu\n\n',
  `acc_no` varchar(50) DEFAULT NULL COMMENT 'nomor account',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tgl creator',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam created',
  PRIMARY KEY (`kd_biaya`),
  KEY `fk_detbiaya_groupbiaya_idx` (`kd_group`),
  CONSTRAINT `fk_detbiaya_groupbiaya` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table detail biaya';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_biaya`
--

LOCK TABLES `detail_biaya` WRITE;
/*!40000 ALTER TABLE `detail_biaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_biaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divisi`
--

DROP TABLE IF EXISTS `divisi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `divisi` (
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi',
  `divisi_nm` varchar(50) DEFAULT NULL COMMENT 'nama divisi',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user-id terakhir update dari table user',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tanggal terakhir update',
  `sys_jam_update` varchar(8) DEFAULT NULL COMMENT 'jam terakhir update format HH24:mm:ss',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`divisi_kd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table divisi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisi`
--

LOCK TABLES `divisi` WRITE;
/*!40000 ALTER TABLE `divisi` DISABLE KEYS */;
INSERT INTO `divisi` VALUES ('ABC','ABC','ADM','2014-07-12 21:47:30','21:47','ADM','2014-07-12 15:06:35'),('HA','IT DIVISION','ADM','2014-07-12 19:38:25','19:38','ADM','2014-07-12 17:34:50'),('HOL','HOLDING','ADM','2014-07-13 00:12:18','00:12','ADM','2014-06-29 03:56:44'),('ISA','ABC D','ADM','2014-07-12 19:38:25','19:38','ADM','2014-07-12 17:34:50'),('OKA','adad',NULL,NULL,NULL,'ADM','2014-07-12 20:21:31'),('OPT','OPTIK','ADM','2014-07-13 00:11:31','00:11','ADM','2014-06-29 05:15:10'),('PL','ASIK','ADM','2014-07-12 19:38:25','19:38','ADM','2014-07-12 17:34:51');
/*!40000 ALTER TABLE `divisi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_biaya`
--

DROP TABLE IF EXISTS `group_biaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_biaya` (
  `kd_group` varchar(5) NOT NULL COMMENT 'kode group biaya',
  `nm_group` varchar(100) DEFAULT NULL COMMENT 'nama group biaya',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal created',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam created HH24:mm:ss',
  PRIMARY KEY (`kd_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table group divisi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_biaya`
--

LOCK TABLES `group_biaya` WRITE;
/*!40000 ALTER TABLE `group_biaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_biaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_lokasi_d`
--

DROP TABLE IF EXISTS `group_lokasi_d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_lokasi_d` (
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdiv',
  `group_lok` varchar(5) NOT NULL COMMENT 'kode group lokasi dari table group lokasi h',
  `lok_kd` varchar(5) NOT NULL COMMENT 'kode lokasi dari table lokasi',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  `sys_jam_create` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`group_lok`,`lok_kd`),
  KEY `fk_group_lokasi_d_lokasi1_idx` (`lok_kd`),
  KEY `fk_group_lokasi_d_group_lokasi_h1_idx` (`divisi_kd`,`subdiv_kd`,`group_lok`),
  CONSTRAINT `fk_group_lokasi_d_lokasi1` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_lokasi_d_group_lokasi_h1` FOREIGN KEY (`divisi_kd`, `subdiv_kd`, `group_lok`) REFERENCES `group_lokasi_h` (`divisi_kd`, `subdiv_kd`, `group_lok`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table group lokasi detail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_lokasi_d`
--

LOCK TABLES `group_lokasi_d` WRITE;
/*!40000 ALTER TABLE `group_lokasi_d` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_lokasi_d` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_lokasi_h`
--

DROP TABLE IF EXISTS `group_lokasi_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_lokasi_h` (
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdiv',
  `group_lok` varchar(5) NOT NULL COMMENT 'kode group lokasi',
  `group_desc` varchar(50) DEFAULT NULL COMMENT 'nama dari group lokasi',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  `sys_jam_create` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`group_lok`),
  KEY `fk_group_lokasi_h_subdivisi1_idx` (`subdiv_kd`),
  CONSTRAINT `fk_group_lokasi_h_divisi1` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_lokasi_h_subdivisi1` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table group lokasi header';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_lokasi_h`
--

LOCK TABLES `group_lokasi_h` WRITE;
/*!40000 ALTER TABLE `group_lokasi_h` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_lokasi_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_user` (
  `group_kd` varchar(5) NOT NULL COMMENT 'kode group user',
  `group_nm` varchar(50) DEFAULT NULL COMMENT 'nama group user',
  `id_role` int(11) DEFAULT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user-id creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal create',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam create HH24:mm:ss',
  PRIMARY KEY (`group_kd`),
  KEY `fk_groupuser_role_idx` (`id_role`),
  CONSTRAINT `fk_groupuser_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table group user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
INSERT INTO `group_user` VALUES ('ADMIN','ADMIN',1,'',NULL,''),('USR','USER',2,'ADM','2014-07-09 22:16:46','');
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hak_approve`
--

DROP TABLE IF EXISTS `hak_approve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hak_approve` (
  `user_id` varchar(50) NOT NULL COMMENT 'user id dari table user',
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdivisi',
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode departmen dari table departmen',
  `kd_group` varchar(5) NOT NULL COMMENT 'kode group dari group biaya',
  `kd_biaya` varchar(30) NOT NULL COMMENT 'kode biaya dari table detail biaya',
  `f_aktif` int(1) DEFAULT NULL COMMENT 'flag aktif\nno = jika sptgl sudah terisi\n',
  `drtgl` date DEFAULT NULL COMMENT 'dari tanggal ?',
  `sptgl` date DEFAULT NULL COMMENT 'sampai tanggal',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal created',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam created HH24:mm:ss',
  `sys_user_nonaktif` varchar(50) DEFAULT NULL COMMENT 'user yang menon aktifkan dari table user',
  `sys_tgl_nonaktif` datetime DEFAULT NULL COMMENT 'tanggal non aktif',
  `sys_jam_nonaktif` varchar(8) DEFAULT NULL COMMENT 'jam non aktif',
  PRIMARY KEY (`user_id`,`divisi_kd`,`subdiv_kd`,`dept_kd`,`kd_group`,`kd_biaya`),
  KEY `fk_happr_divisi_idx` (`divisi_kd`),
  KEY `fk_happr_subdiv_idx` (`subdiv_kd`),
  KEY `fk_happr_dept_idx` (`dept_kd`),
  KEY `fk_happr_groupbiaya_idx` (`kd_group`),
  KEY `fk_happr_detbiaya_idx` (`kd_biaya`),
  CONSTRAINT `fk_happr_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_happr_detbiaya` FOREIGN KEY (`kd_biaya`) REFERENCES `detail_biaya` (`kd_biaya`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_happr_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_happr_groupbiaya` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_happr_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table hak approve';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hak_approve`
--

LOCK TABLES `hak_approve` WRITE;
/*!40000 ALTER TABLE `hak_approve` DISABLE KEYS */;
/*!40000 ALTER TABLE `hak_approve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hak_biaya`
--

DROP TABLE IF EXISTS `hak_biaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hak_biaya` (
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdivisi',
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode departmen dari master departmen',
  `lok_kd` varchar(5) NOT NULL COMMENT 'kode lokasi dari table lokasi',
  `kd_group` varchar(5) NOT NULL COMMENT 'kode group dari group biaya',
  `kd_biaya` varchar(30) NOT NULL COMMENT 'kode biaya dari table detail biaya',
  `f_aktif` int(1) DEFAULT NULL COMMENT 'flag aktif',
  `drtgl` date DEFAULT NULL COMMENT 'tanggal mulai ?',
  `sptgl` date DEFAULT NULL COMMENT 'tanggal berakhir ?',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal created',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam created',
  `sys_user_nonaktif` varchar(50) DEFAULT NULL COMMENT 'user me non aktif kan dari table user',
  `sys_tgl_nonaktif` datetime DEFAULT NULL COMMENT 'tanggal non aktif',
  `sys_jam_nonaktif` varchar(8) DEFAULT NULL COMMENT 'jam non aktif',
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`dept_kd`,`lok_kd`,`kd_group`,`kd_biaya`),
  KEY `fk_hbiaya_divisi_idx` (`divisi_kd`),
  KEY `fk_hbiaya_subdiv_idx` (`subdiv_kd`),
  KEY `fk_hbiaya_groupbiaya_idx` (`kd_group`),
  KEY `fk_hbiaya_detbiaya_idx` (`kd_biaya`),
  KEY `fk_hak_biaya_departmen1_idx` (`dept_kd`),
  KEY `fk_hak_biaya_lokasi1_idx` (`lok_kd`),
  CONSTRAINT `fk_hbiaya_detbiaya` FOREIGN KEY (`kd_biaya`) REFERENCES `detail_biaya` (`kd_biaya`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_groupbiaya` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hak_biaya_departmen1` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hak_biaya_lokasi1` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table hak biaya';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hak_biaya`
--

LOCK TABLES `hak_biaya` WRITE;
/*!40000 ALTER TABLE `hak_biaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `hak_biaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hak_biaya_hist`
--

DROP TABLE IF EXISTS `hak_biaya_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hak_biaya_hist` (
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdivisi',
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode departmen dari master departmen',
  `lok_kd` varchar(5) NOT NULL COMMENT 'kode lokasi dari table lokasi',
  `kd_group` varchar(5) NOT NULL COMMENT 'kode group dari group biaya',
  `kd_biaya` varchar(30) NOT NULL COMMENT 'kode biaya dari table detail biaya',
  `f_aktif` int(1) DEFAULT NULL COMMENT 'flag aktif',
  `drtgl` date DEFAULT NULL COMMENT 'tanggal mulai ?',
  `sptgl` date DEFAULT NULL COMMENT 'tanggal berakhir ?',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal created',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam created',
  `sys_user_nonaktif` varchar(50) DEFAULT NULL COMMENT 'user me non aktif kan dari table user',
  `sys_tgl_nonaktif` datetime DEFAULT NULL COMMENT 'tanggal non aktif',
  `sys_jam_nonaktif` varchar(8) DEFAULT NULL COMMENT 'jam non aktif',
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`dept_kd`,`lok_kd`,`kd_group`,`kd_biaya`),
  KEY `fk_hbiaya_divisi_idx` (`divisi_kd`),
  KEY `fk_hbiaya_subdiv_idx` (`subdiv_kd`),
  KEY `fk_hbiaya_groupbiaya_idx` (`kd_group`),
  KEY `fk_hbiaya_detbiaya_idx` (`kd_biaya`),
  KEY `fk_hak_biaya_departmen1_idx` (`dept_kd`),
  KEY `fk_hak_biaya_lokasi1_idx` (`lok_kd`),
  CONSTRAINT `fk_hbiaya_divisi0` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_subdiv0` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_groupbiaya0` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_detbiaya0` FOREIGN KEY (`kd_biaya`) REFERENCES `detail_biaya` (`kd_biaya`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hak_biaya_departmen10` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hak_biaya_lokasi10` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table history hak biaya';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hak_biaya_hist`
--

LOCK TABLES `hak_biaya_hist` WRITE;
/*!40000 ALTER TABLE `hak_biaya_hist` DISABLE KEYS */;
/*!40000 ALTER TABLE `hak_biaya_hist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kota`
--

DROP TABLE IF EXISTS `kota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kota` (
  `propinsi` varchar(100) NOT NULL,
  `kota` varchar(100) NOT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  `sys_jam_create` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`propinsi`,`kota`),
  KEY `fk_kota_propinsi1_idx` (`propinsi`),
  CONSTRAINT `fk_kota_propinsi1` FOREIGN KEY (`propinsi`) REFERENCES `propinsi` (`propinsi`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table kota';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kota`
--

LOCK TABLES `kota` WRITE;
/*!40000 ALTER TABLE `kota` DISABLE KEYS */;
INSERT INTO `kota` VALUES ('Aceh','Ace','adm','2014-07-06 23:24:10',NULL),('Aceh','Aceh','adm','2014-07-06 23:24:10',NULL),('ACEH','BANDA ACEH','adm','2014-07-06 23:24:10',NULL),('ACEH','BINJAI','adm','2014-07-06 23:24:10',NULL),('ACEH','QWERTYUIOPASDFGHJKLZXCVBNM1234','adm','2014-07-06 23:24:10',NULL),('BALI','BADUNG','adm','2014-07-06 23:24:10',NULL),('BALI','DENPASAR','adm','2014-07-06 23:24:10',NULL),('Bali','Denpasar Moon','adm','2014-07-06 23:24:10',NULL),('Bali','Jimbaran','adm','2014-07-06 23:24:10',NULL),('Bali','Joger','adm','2014-07-06 23:24:10',NULL),('Bali','Ubud','adm','2014-07-06 23:24:10',NULL),('BANGKA BELITUNG','PANGKAL PINANG','adm','2014-07-06 23:24:10',NULL),('BANTEN','CENGKARENG','adm','2014-07-06 23:24:10',NULL),('BANTEN','TANGERANG','adm','2014-07-06 23:24:10',NULL),('BENGKULU','BENGKULU','adm','2014-07-06 23:24:10',NULL),('D.I.YOGYAKARTA','YOGYAKARTA','adm','2014-07-06 23:24:10',NULL),('DI Yogyakarta','Yogyakarta','adm','2014-07-06 23:24:10',NULL),('DKI JAKARTA','CILEUNGSI','adm','2014-07-06 23:24:10',NULL),('DKI Jakarta','Jakarta Barat','adm','2014-07-06 23:24:10',NULL),('DKI Jakarta','Jakarta Pusat','adm','2014-07-06 23:24:10',NULL),('DKI Jakarta','Jakarta Selatan','adm','2014-07-06 23:24:10',NULL),('DKI Jakarta','Jakarta Timur','adm','2014-07-06 23:24:10',NULL),('DKI Jakarta','Jakarta Utara','adm','2014-07-06 23:24:10',NULL),('DKI Jakarta','Kepulauan Seribu','adm','2014-07-06 23:24:10',NULL),('DKI JAKARTA KOTA','JAKARTA BARAT KOTA','adm','2014-07-06 23:24:10',NULL),('DKI JAKARTA KOTA','JAKARTA PUSAT KOTA','adm','2014-07-06 23:24:10',NULL),('DKI JAKARTA RAYA','ABC','adm','2014-07-06 23:24:10',NULL),('DKI JAKARTA RAYA','JAKBAR','adm','2014-07-06 23:24:10',NULL),('DKI JAKARTA RAYA','JAKTIM','adm','2014-07-06 23:24:10',NULL),('IRIANJAYA','Jayapura','adm','2014-07-06 23:24:10',NULL),('JAMBI','JAMBI','adm','2014-07-06 23:24:10',NULL),('Jawa Barat','Bandung','adm','2014-07-06 23:24:10',NULL),('JAWA BARAT','BEKASI','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','BOGOR','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','CIAMIS','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','CIKAMPEK','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','CILEGON','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','CIREBON','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','DEPOK','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','GARUT','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','KARAWANG','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','KUNINGAN','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','PURWAKARTA','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','SERANG','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','SUKABUMI','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','TANGERANG','adm','2014-07-06 23:24:11',NULL),('JAWA BARAT','TASIKMALAYA','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','BUMIAYU','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','CEPU','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','CILACAP','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','KEBUMEN','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','KISARAN','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','KLATEN','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','KUDUS','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','MAGELANG','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','PATI','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','PEKALONGAN','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','PURWODADI','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','PURWOKERTO','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','REMBANG','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','SEMARANG','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','SOLO','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','SRAGEN','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','SUKOHARJO','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','SURAKARTA','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','TEGAL','adm','2014-07-06 23:24:11',NULL),('JAWA TENGAH','WONOGIRI','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','BANGKALAN','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','GRESIK','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','JEMBER','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','KEDIRI','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','MADIUN','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','MAGETAN','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','MALANG','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','MOJOKERTO','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','NGANJUK','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','NGAWI','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','SIDOARJO','adm','2014-07-06 23:24:11',NULL),('JAWA TIMUR','SURABAYA','adm','2014-07-06 23:24:11',NULL),('KALIMANTAN BARAT','PONTIANAK','adm','2014-07-06 23:24:11',NULL),('KALIMANTAN SELATAN','BANJARMASIN','adm','2014-07-06 23:24:11',NULL),('KALIMANTAN TENGAH','PALANGKARAYA','adm','2014-07-06 23:24:11',NULL),('KALIMANTAN TIMUR','BALIKPAPAN','adm','2014-07-06 23:24:11',NULL),('KALIMANTAN TIMUR','SAMARINDA','adm','2014-07-06 23:24:11',NULL),('LAMPUNG','BANDAR LAMPUNG','adm','2014-07-06 23:24:11',NULL),('Lampung','Lampung Selatan','adm','2014-07-06 23:24:11',NULL),('LAMPUNG','TRIMULYO','adm','2014-07-06 23:24:11',NULL),('MALUKU','TERNATE','adm','2014-07-06 23:24:11',NULL),('NUSATENGGARA BARAT','LOMBOK','adm','2014-07-06 23:24:11',NULL),('NUSATENGGARA BARAT','MATARAM','adm','2014-07-06 23:24:11',NULL),('NUSATENGGARA TIMUR','FLORES','adm','2014-07-06 23:24:11',NULL),('NUSATENGGARA TIMUR','KUPANG','adm','2014-07-06 23:24:11',NULL),('RIAU','BATAM','adm','2014-07-06 23:24:11',NULL),('RIAU','DUMAI','adm','2014-07-06 23:24:11',NULL),('RIAU','PEKANBARU','adm','2014-07-06 23:24:11',NULL),('SULAWESI SELATAN','MAKASSAR','adm','2014-07-06 23:24:11',NULL),('SULAWESI SELATAN','UJUNG PANDANG','adm','2014-07-06 23:24:11',NULL),('SULAWESI TENGAH','PALU','adm','2014-07-06 23:24:11',NULL),('SULAWESI TENGGARA','KENDARI','adm','2014-07-06 23:24:11',NULL),('SULAWESI UTARA','MANADO','adm','2014-07-06 23:24:11',NULL),('sumatera barat','PADANG','adm','2014-07-06 23:24:11',NULL),('SUMATERA SELATAN','BATAM','adm','2014-07-06 23:24:11',NULL),('SUMATERA SELATAN','JAMBI','adm','2014-07-06 23:24:11',NULL),('SUMATERA SELATAN','PALEMBANG','adm','2014-07-06 23:24:11',NULL),('SUMATERA SELATAN','PEKAN BARU','adm','2014-07-06 23:24:11',NULL),('SUMATERA TENGAH','PEKANBARU','adm','2014-07-06 23:24:11',NULL),('SUMATERA UTARA','BALIGE','adm','2014-07-06 23:24:11',NULL),('SUMATERA UTARA','BINJAI','adm','2014-07-06 23:24:11',NULL),('SUMATERA UTARA','DURI','adm','2014-07-06 23:24:11',NULL),('SUMATERA UTARA','MEDAN','adm','2014-07-06 23:24:11',NULL),('TIMOR TIMUR','TIMOR LESTE','adm','2014-07-06 23:24:11',NULL);
/*!40000 ALTER TABLE `kota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lokasi`
--

DROP TABLE IF EXISTS `lokasi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lokasi` (
  `lok_kd` varchar(5) NOT NULL COMMENT 'kode lokasi',
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdivisi dari table subdivisi',
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode departmen dari table departmen',
  `lok_nm` varchar(50) DEFAULT NULL COMMENT 'nama lokasi',
  `propinsi` varchar(100) NOT NULL COMMENT 'nama propinsi dari table propinsi',
  `kota` varchar(100) NOT NULL COMMENT 'nama kota dari table kota',
  `email` varchar(100) DEFAULT NULL,
  `f_tutup` int(11) DEFAULT NULL COMMENT '??',
  `tgl_tutup` datetime DEFAULT NULL COMMENT 'tanggal tutup',
  `ctr_ppuc` int(11) DEFAULT '0' COMMENT 'untuk counter di ppuc no\n4 digit',
  `max_ctr_ppuc` int(11) DEFAULT '9999' COMMENT 'max counter ppuc',
  `ctr_batch` int(11) DEFAULT NULL COMMENT 'counter no batch ppuc',
  `max_ctr_batch` int(11) DEFAULT NULL COMMENT 'max counter no batch ppuc',
  `ctr_realisasi` int(11) DEFAULT NULL COMMENT 'counter no realisasi',
  `max_ctr_realisasi` int(11) DEFAULT NULL COMMENT 'max counter no realisasi',
  `curr_ctr_date` date DEFAULT NULL COMMENT 'untuk reset counter \nbisa per bulan\nper tahun',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user-id terakhir update dari table user',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tanggal update terakhir',
  `sys_jam_update` varchar(8) DEFAULT NULL COMMENT 'jam terakhir update format HH24:mm:ss',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`lok_kd`,`divisi_kd`,`subdiv_kd`,`dept_kd`),
  KEY `fk_lokasi_divisi_idx` (`divisi_kd`),
  KEY `fk_lokasi_subdiv_idx` (`subdiv_kd`),
  KEY `fk_lokasi_dept_idx` (`dept_kd`),
  KEY `fk_lokasi_kota1_idx` (`propinsi`,`kota`),
  CONSTRAINT `fk_lokasi_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lokasi_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lokasi_kota1` FOREIGN KEY (`propinsi`, `kota`) REFERENCES `kota` (`propinsi`, `kota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lokasi_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table lokasi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lokasi`
--

LOCK TABLES `lokasi` WRITE;
/*!40000 ALTER TABLE `lokasi` DISABLE KEYS */;
INSERT INTO `lokasi` VALUES ('ADDDD','OPT','EYE','1','adadad','Banten','CENGKARENG','adada@ada.aa',1,'2014-07-13 02:00:24',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ADM','2014-07-13 02:32:25','02:32','ADM','2014-07-13 01:56:18'),('SNYN','OPT','EYE','1','Senayan','Aceh','BANDA ACEH','brai@ad.ada',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ADM','2014-07-13 02:32:25','02:32','ADM','2014-07-13 01:29:26');
/*!40000 ALTER TABLE `lokasi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_fungsi`
--

DROP TABLE IF EXISTS `m_fungsi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_fungsi` (
  `kd_fungsi` varchar(5) NOT NULL COMMENT 'id table m-fungsi',
  `nm_fungsi` varchar(50) DEFAULT NULL COMMENT 'nama fungsi',
  `mail_seq` int(11) DEFAULT NULL COMMENT 'urutan proses pengiriman SMS dan email dari setiap transaksi',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user-id creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal create',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam create format HH24:mm:ss',
  PRIMARY KEY (`kd_fungsi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table m-fungsi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_fungsi`
--

LOCK TABLES `m_fungsi` WRITE;
/*!40000 ALTER TABLE `m_fungsi` DISABLE KEYS */;
INSERT INTO `m_fungsi` VALUES ('APV','APROVAL',1,'',NULL,''),('USR','User',2,'',NULL,'');
/*!40000 ALTER TABLE `m_fungsi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `sys_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id table menu',
  `parent` int(11) DEFAULT NULL COMMENT 'parent id menu ',
  `nama` varchar(50) DEFAULT NULL COMMENT 'nama menu',
  `link` varchar(200) DEFAULT NULL COMMENT 'link menu',
  `level` int(2) DEFAULT NULL,
  `urut` int(2) DEFAULT NULL COMMENT 'urutan menu pada level ini',
  `sys_path` varchar(50) DEFAULT NULL COMMENT 'untuk bantuan tree hierarchy',
  `f_aktif` int(1) DEFAULT NULL COMMENT 'flag aktif',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user create dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tgl created',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam create',
  `sys_user_nonaktif` varchar(50) DEFAULT NULL COMMENT 'user yang menonaktifkan dari table user',
  `sys_tgl_nonaktif` datetime DEFAULT NULL COMMENT 'tanggal menonaktifkan',
  `sys_jam_nonaktif` varchar(8) DEFAULT NULL COMMENT 'jam menonaktifkan',
  PRIMARY KEY (`sys_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='table list menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,0,'ROOT',NULL,0,1,'0.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'Admin',NULL,1,1,'0.1.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(3,2,'Audit Trail',NULL,2,1,'0.1.1.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(4,2,'Log Email',NULL,2,2,'0.1.1.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(5,2,'Log SMS',NULL,2,3,'0.1.1.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(6,1,'Master',NULL,1,2,'0.1.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(7,6,'Divisi','master/divisi',2,1,'0.1.2.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(8,6,'Group User','master/groupuser',2,2,'0.1.2.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(9,6,'Sub Divisi','master/subdivisi',2,3,'0.1.2.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(10,6,'Departmen','master/departmen',2,4,'0.1.2.4',1,NULL,NULL,NULL,NULL,NULL,NULL),(11,6,'Lokasi','master/lokasi',2,5,'0.1.2.5',1,NULL,NULL,NULL,NULL,NULL,NULL),(12,6,'Menu','master/menu',2,6,'0.1.2.6',1,NULL,NULL,NULL,NULL,NULL,NULL),(13,6,'Fungsi User','master/mfungsi',2,7,'0.1.2.7',1,NULL,NULL,NULL,NULL,NULL,NULL),(14,6,'Group User','master/groupuser',2,8,'0.1.2.8',1,NULL,NULL,NULL,NULL,NULL,NULL),(15,6,'User','master/user',2,9,'0.1.2.9',1,NULL,NULL,NULL,NULL,NULL,NULL),(16,6,'User Divisi','master/userdivisi',2,10,'0.1.2.10',1,NULL,NULL,NULL,NULL,NULL,NULL),(17,6,'Group Biaya','master/groupbiaya',2,11,'0.1.2.11',1,NULL,NULL,NULL,NULL,NULL,NULL),(18,6,'Detail Biaya','master/detailbiaya',2,12,'0.1.2.12',1,NULL,NULL,NULL,NULL,NULL,NULL),(19,6,'Hak Biaya','master/hakbiaya',2,13,'0.1.2.13',1,NULL,NULL,NULL,NULL,NULL,NULL),(20,6,'Hak Approve','master/hakapprove',2,14,'0.1.2.14',1,NULL,NULL,NULL,NULL,NULL,NULL),(21,1,'Transaksi',NULL,1,3,'0.1.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(22,21,'Entry PPUC',NULL,2,1,'0.1.3.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(23,21,'Confirm PPUC',NULL,2,2,'0.1.3.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(24,21,'Approval Divisi',NULL,2,3,'0.1.3.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(25,21,'Realisasi PPUC untuk Cabang',NULL,2,4,'0.1.3.4',1,NULL,NULL,NULL,NULL,NULL,NULL),(26,21,'Batal PPUC',NULL,2,5,'0.1.3.5',1,NULL,NULL,NULL,NULL,NULL,NULL),(27,21,'Confirm Realisasi PPUC untuk Cabang',NULL,2,6,'0.1.3.6',1,NULL,NULL,NULL,NULL,NULL,NULL),(28,21,'Entry PPUCVerifikasi Realisasi PPUC oleh OC',NULL,2,7,'0.1.3.7',1,NULL,NULL,NULL,NULL,NULL,NULL),(29,21,'Transfer Finance',NULL,2,8,'0.1.3.8',1,NULL,NULL,NULL,NULL,NULL,NULL),(32,1,'TEst','',1,6,'0.1.6',1,'ADM','2014-07-12 13:40:57',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `PPUC`.`menu_BINS`
BEFORE INSERT ON `PPUC`.`menu`
FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
SET NEW.sys_path =  CONCAT(IFNULL((select sys_path from menu where sys_menu_id = NEW.parent), '0'), '.', New.urut) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `PPUC`.`menu_BUPD`
BEFORE UPDATE ON `PPUC`.`menu`
FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
SET NEW.sys_path = CONCAT(IFNULL((select sys_path from menu where sys_menu_id = NEW.parent), '0'), '.',  New.urut) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `message_template`
--

DROP TABLE IF EXISTS `message_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_template` (
  `sys_id_template` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id untuk table message template',
  `nm_template` varchar(50) DEFAULT NULL COMMENT 'nama template',
  `parameter` varchar(400) DEFAULT NULL COMMENT 'parameter yang digunakan dalam template \n(dari table mana)',
  `keterangan` varchar(200) DEFAULT NULL COMMENT 'keterangan template',
  `f_aktif` varchar(45) DEFAULT NULL COMMENT 'flag aktif',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal created',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user update terakhir dari table user',
  `sys_tgl_update` varchar(45) DEFAULT NULL COMMENT 'tanggal update terakhir dari table user',
  PRIMARY KEY (`sys_id_template`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table message template';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_template`
--

LOCK TABLES `message_template` WRITE;
/*!40000 ALTER TABLE `message_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_template_detail`
--

DROP TABLE IF EXISTS `message_template_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_template_detail` (
  `id_template` int(11) NOT NULL,
  `sys_id_template_detail` int(11) NOT NULL AUTO_INCREMENT,
  `keterangan` varchar(200) DEFAULT NULL COMMENT 'keterangan template',
  `template_sms` text COMMENT 'template sms',
  `template_web` text COMMENT 'template web',
  `template_subject` text COMMENT 'template subject',
  `template_email` text COMMENT 'template email',
  `f_aktif` int(1) DEFAULT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal created',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user terakhir update',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tgl update terakhir',
  PRIMARY KEY (`sys_id_template_detail`),
  KEY `fk_msg_tmp_dtl_msg_tmp_idx` (`id_template`),
  CONSTRAINT `fk_msg_tmp_dtl_msg_tmp` FOREIGN KEY (`id_template`) REFERENCES `message_template` (`sys_id_template`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table message template detail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_template_detail`
--

LOCK TABLES `message_template_detail` WRITE;
/*!40000 ALTER TABLE `message_template_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_template_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ppuc_d`
--

DROP TABLE IF EXISTS `ppuc_d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ppuc_d` (
  `divisi_kd` varchar(3) NOT NULL,
  `subdiv_kd` varchar(3) NOT NULL,
  `dept_kd` varchar(3) NOT NULL,
  `lok_kd` varchar(5) NOT NULL,
  `sys_no_ppuc` varchar(9) NOT NULL,
  `tgl_ppuc` date NOT NULL,
  `kd_group` varchar(5) DEFAULT NULL,
  `kd_biaya` varchar(30) NOT NULL COMMENT 'dalam satu nomor PPUC tidak boleh ada kode biaya yang sama\nricky:\nunique tidak boleh ada yang sama dalam satu nomor ppuc',
  `no_batch` varchar(10) DEFAULT NULL COMMENT 'no batch dari ppuc-h',
  `qty` int(11) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `qty_old` int(11) DEFAULT NULL COMMENT 'terisi dengan qty request cabang jika ada revisi saat divisi approve\n',
  `harga_old` double DEFAULT NULL COMMENT 'terisi dengan harga inputan cabang jika ada revisi saat divisi approve\n',
  `total_old` double DEFAULT NULL COMMENT 'terisi dengan total perhitungan ppuc jika ada revisi saat divisi approve\n',
  `keterangan` text COMMENT 'keterangan yang diinput oleh cabang pada saat request PPUC (tidak mandatory)',
  `ket_approve` text COMMENT 'sebagai relasi dengan data table ''realisasi''',
  `no_realisasi` varchar(25) NOT NULL COMMENT 'sebagai relasi dengan data table ''realisasi''',
  `qty_real_cbg` int(11) DEFAULT NULL,
  `harga_real_cbg` double DEFAULT NULL,
  `total_real_cbg` double DEFAULT NULL,
  `qty_real_oc` int(11) DEFAULT NULL,
  `harga_real_oc` double DEFAULT NULL,
  `total_real_oc` double DEFAULT NULL,
  `nilai_charge` double DEFAULT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'keterangan yang diinput oleh Divisi pada saat approval (tidak mandatory)',
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`dept_kd`,`lok_kd`,`sys_no_ppuc`,`tgl_ppuc`,`kd_biaya`),
  KEY `fk_ppucd_divisi_idx` (`divisi_kd`),
  KEY `fk_ppucd_subdiv_idx` (`subdiv_kd`),
  KEY `fk_ppucd_dept_idx` (`dept_kd`),
  KEY `fk_ppucd_lokasi_idx` (`lok_kd`),
  KEY `fk_ppucd_groupbiaya_idx` (`kd_group`),
  KEY `fk_ppucd_detailbiaya_idx` (`kd_biaya`),
  KEY `fk_ppuc_d_realisasi1_idx` (`no_realisasi`),
  CONSTRAINT `fk_ppucd_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_detailbiaya` FOREIGN KEY (`kd_biaya`) REFERENCES `detail_biaya` (`kd_biaya`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_groupbiaya` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_lokasi` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppuc_d_realisasi1` FOREIGN KEY (`no_realisasi`) REFERENCES `realisasi` (`no_realisasi`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table ppuc-detail\n\n**KETERANGAN BELUM LENGKAP';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ppuc_d`
--

LOCK TABLES `ppuc_d` WRITE;
/*!40000 ALTER TABLE `ppuc_d` DISABLE KEYS */;
/*!40000 ALTER TABLE `ppuc_d` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ppuc_h`
--

DROP TABLE IF EXISTS `ppuc_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ppuc_h` (
  `divisi_kd` varchar(3) NOT NULL,
  `subdiv_kd` varchar(3) NOT NULL,
  `dept_kd` varchar(3) NOT NULL,
  `lok_kd` varchar(5) NOT NULL,
  `sys_no_ppuc` varchar(9) NOT NULL COMMENT 'generate dari system (kode lokasi + nomor counter) total 9 digit\n\n1 data 1 no ppuc\ncounter di table lokasi',
  `tgl_ppuc` date NOT NULL,
  `no_batch` varchar(10) NOT NULL COMMENT 'MMYYXXXXXX\nno batch pengajuan ppuc',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  `sys_jam_create` varchar(8) DEFAULT NULL,
  `sys_user_confirm` varchar(50) DEFAULT NULL,
  `sys_tgl_confirm` datetime DEFAULT NULL,
  `sys_jam_confirm` varchar(8) DEFAULT NULL,
  `sys_hp_asal_create` varchar(25) DEFAULT NULL COMMENT 'baca dari ''user-lokasi'' sesuai user-id login\n',
  `sys_hp_tujuan_create` varchar(25) DEFAULT NULL COMMENT 'baca dari ''user-lokasi'' sesuai user-id yang punya hak approve\n',
  `sys_email_asal_create` varchar(100) DEFAULT NULL COMMENT 'baca dari ''user-lokasi'' sesuai user-id login\n',
  `sys_email_tujuan_create` varchar(100) DEFAULT NULL COMMENT 'baca dari ''user-lokasi'' sesuai user-id yang punya hak approve',
  `sys_divisi_kd_apprv` varchar(5) DEFAULT NULL COMMENT 'baca dari ''user-divisi'' sesuai dengan user id yang punya hak approve\n',
  `sys_subdiv_kd_apprv` varchar(5) DEFAULT NULL COMMENT 'baca dari ''user-divisi'' sesuai dengan user id yang punya hak approve',
  `sys_dept_kd_apprv` varchar(5) DEFAULT NULL,
  `sys_user_approve` varchar(50) DEFAULT NULL COMMENT 'user approval',
  `sys_tgl_approve` datetime DEFAULT NULL,
  `sys_jam_approve` varchar(8) DEFAULT NULL,
  `f_approval` int(1) DEFAULT NULL,
  `sys_hp_asal_approve` varchar(25) DEFAULT NULL,
  `sys_hp_tujuan_approve` varchar(25) DEFAULT NULL,
  `sys_email_asal_approve` varchar(100) DEFAULT NULL,
  `sys_email_tujuan_approve` varchar(100) DEFAULT NULL,
  `no_realisasi` varchar(25) DEFAULT NULL COMMENT '??',
  `sys_user_realisasi` varchar(50) DEFAULT NULL,
  `sys_tgl_realisasi` datetime DEFAULT NULL,
  `sys_jam_realisasi` varchar(8) DEFAULT NULL,
  `sys_user_conf_real` varchar(50) DEFAULT NULL,
  `sys_tgl_conf_real` datetime DEFAULT NULL,
  `jam_conf_real` varchar(8) DEFAULT NULL,
  `sys_user_conf_oc` varchar(50) DEFAULT NULL,
  `sys_tgl_conf_oc` datetime DEFAULT NULL,
  `sys_jam_conf_oc` varchar(8) DEFAULT NULL,
  `sys_hp_asal_conf_oc` varchar(25) DEFAULT NULL,
  `sys_hp_tujuan_conf_oc` varchar(25) DEFAULT NULL,
  `sys_email_asal_conf_oc` varchar(100) DEFAULT NULL,
  `sys_email_tujuan_conf_oc` varchar(100) DEFAULT NULL,
  `f_batal` int(1) DEFAULT NULL,
  `sys_user_batal` varchar(50) DEFAULT NULL,
  `sys_tgl_batal` datetime DEFAULT NULL,
  `sys_jam_batal` varchar(8) DEFAULT NULL,
  `sys_alasan_batal` text,
  `sys_hp_asal_batal` varchar(25) DEFAULT NULL,
  `sys_hp_tujuan_batal` varchar(25) DEFAULT NULL,
  `sys_email_asal_batal` varchar(100) DEFAULT NULL,
  `sys_email_tujuan_batal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`dept_kd`,`lok_kd`,`sys_no_ppuc`,`tgl_ppuc`),
  KEY `fk_ppuch_divisi_idx` (`divisi_kd`),
  KEY `fk_ppuch_subdiv_idx` (`subdiv_kd`),
  KEY `fk_ppuch_dept_idx` (`dept_kd`),
  KEY `fk_ppuch_lokasi_idx` (`lok_kd`),
  CONSTRAINT `fk_ppuch_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppuch_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppuch_lokasi` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppuch_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table transaksi PPUC-H\n\n**KETERANGAN BELUM LENGKAP';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ppuc_h`
--

LOCK TABLES `ppuc_h` WRITE;
/*!40000 ALTER TABLE `ppuc_h` DISABLE KEYS */;
/*!40000 ALTER TABLE `ppuc_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propinsi`
--

DROP TABLE IF EXISTS `propinsi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propinsi` (
  `propinsi` varchar(100) NOT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  `sys_jam_create` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`propinsi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table propinsi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propinsi`
--

LOCK TABLES `propinsi` WRITE;
/*!40000 ALTER TABLE `propinsi` DISABLE KEYS */;
INSERT INTO `propinsi` VALUES ('Aceh','adm','2014-07-06 23:23:02',NULL),('BABEL','adm','2014-07-06 23:23:02',NULL),('Bali','adm','2014-07-06 23:23:02',NULL),('BANGKA BELITUNG','adm','2014-07-06 23:23:03',NULL),('Banten','adm','2014-07-06 23:23:03',NULL),('BEKASI','adm','2014-07-06 23:23:03',NULL),('Bengkulu','adm','2014-07-06 23:23:03',NULL),('D.I.YOGYAKARTA','adm','2014-07-06 23:23:03',NULL),('Daerah Istimewa Yogyakarta','adm','2014-07-06 23:23:03',NULL),('DI Yogyakarta','adm','2014-07-06 23:23:03',NULL),('DKI Jakarta','adm','2014-07-06 23:23:03',NULL),('DKI JAKARTA KOTA','adm','2014-07-06 23:23:03',NULL),('DKI JAKARTA RAYA','adm','2014-07-06 23:23:03',NULL),('Gorontalo','adm','2014-07-06 23:23:03',NULL),('IRIANJAYA','adm','2014-07-06 23:23:03',NULL),('Jambi','adm','2014-07-06 23:23:03',NULL),('Jawa Barat','adm','2014-07-06 23:23:03',NULL),('Jawa Tengah','adm','2014-07-06 23:23:03',NULL),('Jawa Timur','adm','2014-07-06 23:23:03',NULL),('Kalimantan Barat','adm','2014-07-06 23:23:03',NULL),('Kalimantan Selatan','adm','2014-07-06 23:23:03',NULL),('Kalimantan Tengah','adm','2014-07-06 23:23:03',NULL),('Kalimantan Timur','adm','2014-07-06 23:23:03',NULL),('Kepulauan Bangka Belitung','adm','2014-07-06 23:23:03',NULL),('Kepulauan Riau','adm','2014-07-06 23:23:03',NULL),('Lampung','adm','2014-07-06 23:23:03',NULL),('MADURA','adm','2014-07-06 23:23:03',NULL),('Maluku','adm','2014-07-06 23:23:03',NULL),('Maluku Utara','adm','2014-07-06 23:23:03',NULL),('Nusa Tenggara Barat','adm','2014-07-06 23:23:03',NULL),('Nusa Tenggara Timur','adm','2014-07-06 23:23:03',NULL),('NUSATENGGARA BARAT','adm','2014-07-06 23:23:03',NULL),('NUSATENGGARA TIMUR','adm','2014-07-06 23:23:03',NULL),('Papua','adm','2014-07-06 23:23:03',NULL),('Papua Barat','adm','2014-07-06 23:23:03',NULL),('QWERTYUIOPASDFGHJKLZXCVBNM1234','adm','2014-07-06 23:23:03',NULL),('Riau','adm','2014-07-06 23:23:03',NULL),('Sulawesi Barat','adm','2014-07-06 23:23:03',NULL),('Sulawesi Selatan','adm','2014-07-06 23:23:03',NULL),('Sulawesi Tengah','adm','2014-07-06 23:23:03',NULL),('Sulawesi Tenggara','adm','2014-07-06 23:23:03',NULL),('Sulawesi Utara','adm','2014-07-06 23:23:03',NULL),('Sumatera Barat','adm','2014-07-06 23:23:03',NULL),('Sumatera Selatan','adm','2014-07-06 23:23:03',NULL),('SUMATERA TENGAH','adm','2014-07-06 23:23:03',NULL),('Sumatera Utara','adm','2014-07-06 23:23:03',NULL),('TIMOR TIMUR','adm','2014-07-06 23:23:03',NULL),('YOGYAKARTA','adm','2014-07-06 23:23:03',NULL);
/*!40000 ALTER TABLE `propinsi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smsserver_calls`
--

DROP TABLE IF EXISTS `smsserver_calls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smsserver_calls` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `call_date` datetime NOT NULL,
  `gateway_id` varchar(64) NOT NULL,
  `caller_id` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smsserver_calls`
--

LOCK TABLES `smsserver_calls` WRITE;
/*!40000 ALTER TABLE `smsserver_calls` DISABLE KEYS */;
/*!40000 ALTER TABLE `smsserver_calls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smsserver_in`
--

DROP TABLE IF EXISTS `smsserver_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smsserver_in` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `process` int(11) NOT NULL,
  `originator` varchar(16) NOT NULL,
  `type` varchar(1) NOT NULL,
  `encoding` char(1) NOT NULL,
  `message_date` datetime NOT NULL,
  `receive_date` datetime NOT NULL,
  `text` varchar(1000) NOT NULL,
  `original_ref_no` varchar(64) DEFAULT NULL,
  `original_receive_date` datetime DEFAULT NULL,
  `gateway_id` varchar(64) DEFAULT NULL,
  `PROCESS_DATE` date DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `PROSES_KET` varchar(500) DEFAULT NULL,
  `id_refrence` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smsserver_in`
--

LOCK TABLES `smsserver_in` WRITE;
/*!40000 ALTER TABLE `smsserver_in` DISABLE KEYS */;
/*!40000 ALTER TABLE `smsserver_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smsserver_out`
--

DROP TABLE IF EXISTS `smsserver_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smsserver_out` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(1) NOT NULL DEFAULT 'O',
  `recipient` varchar(16) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `wap_url` varchar(100) DEFAULT NULL,
  `wap_expiry_date` datetime DEFAULT NULL,
  `wap_signal` varchar(1) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `originator` varchar(16) NOT NULL DEFAULT ' ',
  `encoding` varchar(1) NOT NULL DEFAULT '7',
  `status_report` int(1) NOT NULL DEFAULT '0',
  `flash_sms` int(1) NOT NULL DEFAULT '0',
  `src_port` int(6) NOT NULL DEFAULT '-1',
  `dst_port` int(6) NOT NULL DEFAULT '-1',
  `sent_date` datetime DEFAULT NULL,
  `ref_no` varchar(64) DEFAULT NULL,
  `priority` int(5) NOT NULL DEFAULT '0',
  `status` varchar(1) NOT NULL DEFAULT 'U',
  `errors` int(2) NOT NULL DEFAULT '0',
  `gateway_id` varchar(64) NOT NULL DEFAULT '*',
  `user_id` varchar(20) DEFAULT NULL,
  `id_refrence` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4291 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smsserver_out`
--

LOCK TABLES `smsserver_out` WRITE;
/*!40000 ALTER TABLE `smsserver_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `smsserver_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subdivisi`
--

DROP TABLE IF EXISTS `subdivisi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subdivisi` (
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdivisi',
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_nm` varchar(50) DEFAULT NULL COMMENT 'nama subdivisi',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user-id terakhir update dari table user',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tanggal update terakhir',
  `sys_jam_update` varchar(8) DEFAULT NULL COMMENT 'jam terakhir update format HH24:mm:ss',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`subdiv_kd`,`divisi_kd`),
  KEY `fk_subdiv_divisi_idx` (`divisi_kd`),
  CONSTRAINT `fk_subdiv_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table subdivisi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subdivisi`
--

LOCK TABLES `subdivisi` WRITE;
/*!40000 ALTER TABLE `subdivisi` DISABLE KEYS */;
INSERT INTO `subdivisi` VALUES ('ACC','HOL','ACCOUNTING','ADM','2014-07-12 21:25:35','21:25','ADM','2014-06-29 11:35:29'),('EYE','OPT','OPTIK EYEVOLUTION','ADM','2014-07-12 21:25:35','21:25','ADM','2014-06-29 11:35:30'),('MLW','OPT','OPTIK melawai','ADM','2014-07-12 21:47:03','21:47','ADM','2014-06-29 11:35:32');
/*!40000 ALTER TABLE `subdivisi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_akses_menu`
--

DROP TABLE IF EXISTS `sys_akses_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_akses_menu` (
  `group_kd` varchar(5) NOT NULL COMMENT 'kode group dari table group user ',
  `menu_id` int(11) NOT NULL COMMENT 'id menu dari table menu',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`group_kd`,`menu_id`),
  KEY `fk_akses_menu_idx` (`menu_id`),
  CONSTRAINT `fk_akses_groupuser` FOREIGN KEY (`group_kd`) REFERENCES `group_user` (`group_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_akses_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`sys_menu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table untuk list menu berdasarkan group user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_akses_menu`
--

LOCK TABLES `sys_akses_menu` WRITE;
/*!40000 ALTER TABLE `sys_akses_menu` DISABLE KEYS */;
INSERT INTO `sys_akses_menu` VALUES ('ADMIN',1,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',2,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',3,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',4,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',5,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',6,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',7,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',8,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',9,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',10,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',11,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',12,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',13,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',14,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',15,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',16,NULL,NULL),('ADMIN',17,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',18,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',19,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',20,NULL,NULL),('ADMIN',21,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',22,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',23,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',24,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',25,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',26,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',27,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',28,'SYSTEM','2014-07-09 00:34:49'),('ADMIN',29,'SYSTEM','2014-07-09 00:34:49'),('USR',1,NULL,NULL),('USR',2,NULL,NULL),('USR',3,NULL,NULL),('USR',4,NULL,NULL),('USR',5,NULL,NULL),('USR',21,NULL,NULL),('USR',22,NULL,NULL),('USR',23,NULL,NULL),('USR',24,NULL,NULL),('USR',25,NULL,NULL),('USR',26,NULL,NULL),('USR',27,NULL,NULL),('USR',28,NULL,NULL),('USR',29,NULL,NULL);
/*!40000 ALTER TABLE `sys_akses_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_audittrail`
--

DROP TABLE IF EXISTS `sys_audittrail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_audittrail` (
  `sys_id_audittrail` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_type_audit` varchar(10) DEFAULT NULL COMMENT 'ADD\nEDIT\nDELETE\nUPDATE',
  `sys_subtype_audit` varchar(10) DEFAULT NULL,
  `sys_model` varchar(100) DEFAULT NULL,
  `sys_model_id` varchar(100) DEFAULT NULL,
  `sys_ip` varchar(100) DEFAULT NULL,
  `sys_info` text,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_id_audittrail`)
) ENGINE=InnoDB AUTO_INCREMENT=490 DEFAULT CHARSET=utf8 COMMENT='table audit trail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audittrail`
--

LOCK TABLES `sys_audittrail` WRITE;
/*!40000 ALTER TABLE `sys_audittrail` DISABLE KEYS */;
INSERT INTO `sys_audittrail` VALUES (112,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-29 03:56:43'),(113,'TRANS','ADD','Divisi','HOL','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-06-29 03:56:44'),(114,'TRANS','ADD','Divisi','OPT','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-06-29 03:56:45'),(115,'TRANS','DELETE','Divisi','OPT','0:0:0:0:0:0:0:1','DELETE DIVISI','ADM','2014-06-29 03:56:57'),(116,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-29 05:15:09'),(117,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-06-29 05:15:09'),(118,'TRANS','ADD','Divisi','OPT','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-06-29 05:15:10'),(119,'EXIM','SUCCESS','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-29 11:35:29'),(120,'TRANS','ADD','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-06-29 11:35:30'),(121,'TRANS','ADD','Subdivisi','EYE/OPT','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-06-29 11:35:31'),(122,'TRANS','ADD','Subdivisi','MLW/OPT','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-06-29 11:35:32'),(123,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:40:51'),(124,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:47:35'),(125,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:49:37'),(126,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:50:52'),(127,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 17:02:42'),(128,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>, No DIVISI KD [ABC] found with this id.]','ADM','2014-06-29 17:08:17'),(129,'EXIM','FAILED','Subdivisi',NULL,'0:0:0:0:0:0:0:1','[File Upload is required]','ADM','2014-06-29 17:08:57'),(130,'EXIM','FAILED','Subdivisi',NULL,'0:0:0:0:0:0:0:1','[File Upload is required]','ADM','2014-06-29 17:09:33'),(131,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:30:40'),(132,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:31:58'),(133,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:32:22'),(134,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:36:42'),(135,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:36:58'),(136,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 22:02:59'),(137,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 22:04:24'),(138,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-30 22:04:48'),(141,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-30 22:10:14'),(142,'TRANS','ADD','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:10:14'),(143,'TRANS','ADD','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:10:15'),(144,'TRANS','ADD','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:10:15'),(145,'TRANS','ADD','Departmen','2/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:11:25'),(146,'TRANS','ADD','Departmen','3/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:13:27'),(147,'TRANS','ADD','Lokasi','OKA/Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD Lokasi','ADM','2014-07-06 23:27:41'),(148,'TRANS','ADD','Departmen','OIK/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-06 23:30:20'),(149,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-08 06:15:36'),(150,'LOGIN','FAILED','User','adn','0:0:0:0:0:0:0:1','LOGIN FAILED',NULL,'2014-07-08 06:23:20'),(151,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-08 06:23:31'),(152,'LOGIN','FAILED','User','adm','0:0:0:0:0:0:0:1','LOGIN FAILED','ADM','2014-07-08 06:23:43'),(153,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-08 06:23:48'),(154,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-08 22:00:21'),(155,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-08 22:55:22'),(156,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-08 23:17:31'),(157,'TRANS','UPDATE','Menu','6','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-08 23:59:36'),(158,'TRANS','UPDATE','Menu','7','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:00:14'),(159,'TRANS','UPDATE','Menu','5','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:00:30'),(160,'TRANS','UPDATE','Menu','5','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:00:51'),(161,'TRANS','UPDATE','Menu','5','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:01:09'),(162,'TRANS','UPDATE','Menu','16','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:01:24'),(163,'TRANS','UPDATE','Menu','17','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:01:38'),(164,'TRANS','UPDATE','Menu','18','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:01:49'),(165,'TRANS','UPDATE','Menu','19','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:02:08'),(166,'TRANS','ADD','Menu','30','0:0:0:0:0:0:0:1','ADD Menu','ADM','2014-07-09 00:05:42'),(167,'TRANS','UPDATE','Menu','6','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:06:07'),(168,'TRANS','UPDATE','Menu','6','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:06:08'),(169,'TRANS','UPDATE','Menu','30','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:06:55'),(170,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:09:50'),(171,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:11:51'),(172,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:15:42'),(173,'TRANS','UPDATE','Menu','6','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:18:00'),(174,'TRANS','UPDATE','Menu','3','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:20:02'),(175,'TRANS','UPDATE','Menu','4','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:20:18'),(176,'TRANS','UPDATE','Menu','4','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:20:18'),(177,'TRANS','UPDATE','Menu','5','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:20:33'),(178,'TRANS','UPDATE','Menu','6','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:20:46'),(179,'TRANS','UPDATE','Menu','7','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:21:03'),(180,'TRANS','UPDATE','Menu','30','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:21:26'),(181,'TRANS','UPDATE','Menu','7','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:22:29'),(182,'TRANS','UPDATE','Menu','11','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:23:08'),(183,'TRANS','UPDATE','Menu','10','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:23:26'),(184,'TRANS','UPDATE','Menu','15','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:24:04'),(185,'TRANS','UPDATE','Menu','9','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:24:39'),(186,'TRANS','UPDATE','Menu','16','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:25:14'),(187,'TRANS','ADD','Menu','31','0:0:0:0:0:0:0:1','ADD Menu','ADM','2014-07-09 00:26:13'),(188,'TRANS','UPDATE','Menu','30','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:26:37'),(189,'TRANS','UPDATE','Menu','31','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:26:52'),(190,'TRANS','UPDATE','Menu','8','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:27:40'),(191,'TRANS','UPDATE','Menu','13','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:27:59'),(192,'TRANS','UPDATE','Menu','12','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:28:22'),(193,'TRANS','UPDATE','Menu','17','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:28:44'),(194,'TRANS','UPDATE','Menu','18','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:28:59'),(195,'TRANS','UPDATE','Menu','19','0:0:0:0:0:0:0:1','UPDATE Menu','ADM','2014-07-09 00:29:12'),(196,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:30:21'),(197,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:31:58'),(198,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:35:15'),(199,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 00:47:18'),(200,'LOGIN','FAILED','User','adm','0:0:0:0:0:0:0:1','LOGIN FAILED','ADM','2014-07-09 08:38:27'),(201,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 08:38:37'),(202,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 08:56:42'),(203,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 11:03:32'),(204,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 11:54:40'),(205,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 13:20:17'),(206,'TRANS','ADD','GroupUser','gd','0:0:0:0:0:0:0:1','ADD Group User','ADM','2014-07-09 13:25:57'),(207,'TRANS','UPDATE','GroupUser','gd','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 13:29:18'),(208,'TRANS','UPDATE','GroupUser','gd','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 13:29:29'),(209,'TRANS','DELETE','GroupUser','gd','0:0:0:0:0:0:0:1','DELETE GROUP USER','ADM','2014-07-09 13:30:25'),(210,'TRANS','UPDATE','GroupUser','ADMIN','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 13:34:56'),(211,'TRANS','UPDATE','GroupUser','ADMIN','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 13:35:06'),(212,'TRANS','UPDATE','GroupUser','ADMIN','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 13:36:37'),(213,'TRANS','UPDATE','GroupUser','ADMIN','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 13:37:04'),(214,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 14:09:32'),(215,'TRANS','UPDATE','GroupUser','ADMIN','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 14:12:59'),(216,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 14:35:57'),(217,'LOGIN','FAILED','User','adm','0:0:0:0:0:0:0:1','LOGIN FAILED','ADM','2014-07-09 15:14:36'),(218,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 15:14:43'),(219,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 16:10:58'),(220,'TRANS','UPDATE','GroupUser','USER','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 16:47:39'),(221,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 16:54:54'),(222,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 17:25:09'),(223,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 21:55:03'),(224,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 22:13:06'),(225,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-09 22:14:22'),(226,'TRANS','DELETE','GroupUser','USER','0:0:0:0:0:0:0:1','DELETE GROUP USER','ADM','2014-07-09 22:15:15'),(227,'TRANS','ADD','GroupUser','USR','0:0:0:0:0:0:0:1','ADD Group User','ADM','2014-07-09 22:16:50'),(228,'TRANS','UPDATE','GroupUser','USR','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 22:23:16'),(229,'TRANS','UPDATE','GroupUser','USR','0:0:0:0:0:0:0:1','UPDATE Group User','ADM','2014-07-09 22:23:58'),(230,'TRANS','UPDATE','Lokasi','OKA/Z/ACC/HOL','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-09 22:42:06'),(231,'TRANS','ADD','Lokasi','Sip a/Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD Lokasi','ADM','2014-07-09 22:45:44'),(232,'TRANS','UPDATE','Lokasi','Sip a/Z/ACC/HOL','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-09 22:45:56'),(233,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-09 22:46:27'),(234,'TRANS','UPDATE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-09 22:47:35'),(235,'TRANS','UPDATE','Departmen','OIK/ACC/HOL','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-09 23:05:03'),(236,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-10 23:03:18'),(237,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 05:10:44'),(238,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 05:59:48'),(239,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 07:32:28'),(240,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 07:40:14'),(241,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 08:12:06'),(242,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 08:46:44'),(243,'TRANS','ADD','UserDivisi','ADM/HOL/ACC/OIK/null','0:0:0:0:0:0:0:1','ADD USERDIVISI','ADM','2014-07-12 09:51:29'),(244,'TRANS','UPDATE','UserDivisi','ADM/HOL/ACC/OIK/null','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 09:56:28'),(245,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 10:26:38'),(246,'TRANS','ADD','UserDivisi','ADM/HOL/ACC/Z/OKA','0:0:0:0:0:0:0:1','ADD USERDIVISI','ADM','2014-07-12 10:27:52'),(247,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 10:39:36'),(248,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 11:35:52'),(249,'TRANS','UPDATE','UserDivisi','2','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 11:36:30'),(250,'TRANS','UPDATE','UserDivisi','1','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 11:37:34'),(251,'LOGIN','FAILED','User','adm','0:0:0:0:0:0:0:1','LOGIN FAILED','ADM','2014-07-12 11:58:36'),(252,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 12:50:09'),(253,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 12:50:35'),(254,'TRANS','UPDATE','UserDivisi','2','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 12:50:54'),(255,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 12:58:31'),(256,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 13:04:05'),(257,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 13:20:35'),(258,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 13:24:25'),(259,'TRANS','UPDATE','UserDivisi','1','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 13:24:51'),(260,'TRANS','ADD','UserDivisi','null','0:0:0:0:0:0:0:1','ADD USERDIVISI','ADM','2014-07-12 13:30:02'),(261,'TRANS','ADD','UserDivisi','null','0:0:0:0:0:0:0:1','ADD USERDIVISI','ADM','2014-07-12 13:37:57'),(262,'TRANS','ADD','Menu','32','0:0:0:0:0:0:0:1','ADD Menu','ADM','2014-07-12 13:40:57'),(263,'TRANS','ADD','UserDivisi','null','0:0:0:0:0:0:0:1','ADD USERDIVISI','ADM','2014-07-12 13:43:16'),(264,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 13:53:40'),(265,'TRANS','ADD','UserDivisi','6','0:0:0:0:0:0:0:1','ADD USERDIVISI','ADM','2014-07-12 13:54:40'),(266,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 13:57:06'),(267,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 13:57:13'),(268,'TRANS','UPDATE','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 13:57:28'),(269,'TRANS','UPDATE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 13:58:05'),(270,'TRANS','UPDATE','Departmen','2/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 13:58:19'),(271,'TRANS','UPDATE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 13:58:28'),(272,'TRANS','UPDATE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 13:58:56'),(273,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 14:12:18'),(274,'TRANS','ADD','Departmen','4/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 14:12:56'),(275,'TRANS','UPDATE','UserDivisi','3','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 14:13:43'),(276,'TRANS','UPDATE','UserDivisi','4','0:0:0:0:0:0:0:1','UPDATE USERDIVISI','ADM','2014-07-12 14:14:46'),(277,'TRANS','DELETE','Departmen','2/MLW/OPT','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-12 14:20:20'),(278,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 14:33:53'),(279,'TRANS','UPDATE','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 14:55:55'),(280,'TRANS','UPDATE','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 14:56:28'),(281,'TRANS','ADD','Divisi','ABC','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 15:06:35'),(282,'TRANS','UPDATE','Divisi','ABC','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 15:08:58'),(283,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 15:09:27'),(284,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 15:09:28'),(285,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 15:09:28'),(286,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 15:12:12'),(287,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 15:12:12'),(288,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 15:12:12'),(289,'TRANS','UPDATE','Divisi','ABC','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 15:12:13'),(290,'TRANS','ADD','Divisi','ITD','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 15:12:13'),(291,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 15:19:26'),(292,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n pada baris ke-6<br/>, Divisi NM is required]','ADM','2014-07-12 15:19:35'),(293,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n pada baris ke-6<br/>, Divisi NM is required]','ADM','2014-07-12 15:22:06'),(294,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 15:22:34'),(298,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 15:51:16'),(299,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 15:51:50'),(303,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 16:10:13'),(304,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 16:17:14'),(305,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 16:21:34'),(306,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 16:41:50'),(307,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n pada baris ke-7<br/>, Divisi NM is required]','ADM','2014-07-12 16:42:10'),(308,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 16:42:33'),(312,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 16:50:40'),(316,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 17:07:08'),(317,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n pada baris ke-5<br/>, size must be between 0 and 3]','ADM','2014-07-12 17:07:22'),(318,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 17:14:07'),(319,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5]','ADM','2014-07-12 17:15:40'),(320,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 17:21:35'),(321,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 17:24:52'),(322,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 17:25:16'),(323,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 17:28:21'),(324,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 17:30:18'),(325,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-7 : Divisi NM is required]','ADM','2014-07-12 17:30:47'),(326,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-7 : Divisi NM is required]','ADM','2014-07-12 17:32:35'),(327,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-5 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-7 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-7 : Divisi NM is required]','ADM','2014-07-12 17:33:12'),(328,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 17:34:49'),(329,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 17:34:50'),(330,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 17:34:50'),(331,'TRANS','UPDATE','Divisi','ABC','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 17:34:50'),(332,'TRANS','ADD','Divisi','HA','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 17:34:50'),(333,'TRANS','ADD','Divisi','ISA','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 17:34:50'),(334,'TRANS','ADD','Divisi','PL','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 17:34:51'),(335,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 18:32:49'),(336,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 18:43:12'),(337,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 18:46:39'),(338,'TRANS','UPDATE','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 18:57:18'),(339,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 19:06:01'),(340,'TRANS','ADD','Divisi','knk','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 19:10:43'),(341,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 19:17:00'),(342,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 19:18:57'),(343,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 19:25:45'),(344,'TRANS','ADD','Subdivisi','ada/ABC','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-07-12 19:26:38'),(345,'TRANS','UPDATE','Subdivisi','ada/ABC','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 19:26:53'),(346,'EXIM','SUCCESS','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 19:30:24'),(347,'TRANS','UPDATE','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 19:30:24'),(348,'TRANS','UPDATE','Subdivisi','EYE/OPT','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 19:30:24'),(349,'TRANS','UPDATE','Subdivisi','MLW/OPT','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 19:30:24'),(350,'EXIM','FAILED','Subdivisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-07-12 19:30:52'),(351,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 19:31:50'),(352,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 19:32:00'),(353,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 19:38:24'),(354,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 19:38:25'),(355,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 19:38:25'),(356,'TRANS','UPDATE','Divisi','ABC','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 19:38:25'),(357,'TRANS','UPDATE','Divisi','HA','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 19:38:25'),(358,'TRANS','UPDATE','Divisi','ISA','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 19:38:25'),(359,'TRANS','UPDATE','Divisi','PL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 19:38:25'),(360,'TRANS','ADD','Divisi','ad','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 19:54:05'),(361,'TRANS','ADD','Subdivisi','ad/ISA','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-07-12 19:55:46'),(362,'TRANS','ADD','Divisi','ada','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 19:56:30'),(363,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : may not be null]','ADM','2014-07-12 19:56:57'),(364,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:02:24'),(365,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:05:12'),(366,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:14:42'),(367,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 20:15:22'),(368,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 20:19:02'),(369,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:19:15'),(370,'EXIM','SUCCESS','Divisi','subdivi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 20:21:30'),(371,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 20:21:30'),(372,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 20:21:30'),(373,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 20:21:30'),(374,'TRANS','ADD','Divisi','OKA','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-07-12 20:21:31'),(375,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:21:48'),(376,'EXIM','FAILED','Subdivisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-07-12 20:23:00'),(377,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:26:15'),(378,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:30:28'),(379,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:30:42'),(380,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:31:07'),(381,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:31:24'),(382,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:31:47'),(383,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:33:47'),(384,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:34:22'),(385,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:36:09'),(386,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[]','ADM','2014-07-12 20:38:28'),(387,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[divisi_nm is required]','ADM','2014-07-12 20:40:36'),(388,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required, divisi_nm is required]','ADM','2014-07-12 20:43:53'),(389,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-7 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-8 : divisi_nm is required, size must be between 0 and 3, divisi_nm is required]','ADM','2014-07-12 20:45:01'),(390,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-7 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-8 : divisi_nm is required, size must be between 0 and 3, divisi_nm is required]','ADM','2014-07-12 20:45:48'),(391,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 20:46:15'),(392,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-7 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:48:26'),(393,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:48:57'),(394,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-9 : divisi_nm is required]','ADM','2014-07-12 20:49:22'),(395,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : size must be between 0 and 3]','ADM','2014-07-12 20:53:57'),(396,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required]','ADM','2014-07-12 20:54:25'),(397,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : size must be between 0 and 3]','ADM','2014-07-12 20:55:27'),(398,'EXIM','FAILED','Subdivisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-07-12 20:56:26'),(399,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-5 : size must be between 0 and 3]','ADM','2014-07-12 20:56:44'),(400,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : size must be between 0 and 3,  (filename= divisi.csv) pada baris ke-9 : divisi_nm is required]','ADM','2014-07-12 21:00:45'),(401,'EXIM','FAILED','Divisi','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv) pada baris ke-8 : divisi_nm is required,  (filename= divisi.csv) pada baris ke-9 : divisi_kd must between 0-3 character,  (filename= divisi.csv) pada baris ke-9 : divisi_nm is required]','ADM','2014-07-12 21:03:52'),(402,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 21:16:48'),(403,'TRANS','UPDATE','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:22:36'),(404,'TRANS','ADD','Subdivisi','adw/ABC','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-07-12 21:24:51'),(405,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-5 : subdiv_kd must between 0-3 character]','ADM','2014-07-12 21:25:10'),(406,'EXIM','SUCCESS','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 21:25:35'),(407,'TRANS','UPDATE','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:25:35'),(408,'TRANS','UPDATE','Subdivisi','EYE/OPT','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:25:35'),(409,'TRANS','UPDATE','Subdivisi','MLW/OPT','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:25:35'),(410,'TRANS','ADD','Subdivisi','ada/OKA','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-07-12 21:25:35'),(411,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-6 : No DIVISI KD found.]','ADM','2014-07-12 21:27:38'),(412,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv) pada baris ke-6 : No DIVISI KD found.]','ADM','2014-07-12 21:27:55'),(413,'TRANS','DELETE','Divisi','ad','0:0:0:0:0:0:0:1','DELETE DIVISI','ADM','2014-07-12 21:28:13'),(414,'TRANS','DELETE','Divisi','knk','0:0:0:0:0:0:0:1','DELETE DIVISI','ADM','2014-07-12 21:28:18'),(415,'TRANS','DELETE','Subdivisi','ad/ISA','0:0:0:0:0:0:0:1','DELETE SUB DIVISI','ADM','2014-07-12 21:28:30'),(416,'TRANS','UPDATE','Subdivisi','adw/ABC','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:29:34'),(417,'TRANS','UPDATE','Subdivisi','adw/ABC','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:29:50'),(418,'TRANS','UPDATE','Subdivisi','MLW/OPT','0:0:0:0:0:0:0:1','UPDATE SUB DIVISI','ADM','2014-07-12 21:47:03'),(419,'TRANS','DELETE','Divisi','ada','0:0:0:0:0:0:0:1','DELETE DIVISI','ADM','2014-07-12 21:47:22'),(420,'TRANS','UPDATE','Divisi','ABC','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-12 21:47:30'),(421,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 22:32:13'),(422,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 22:40:46'),(423,'TRANS','ADD','Departmen','ada/adw/ABC','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 22:53:19'),(424,'TRANS','ADD','Departmen','ada/ada/ABC','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 22:53:49'),(425,'TRANS','UPDATE','Departmen','ada/ada/ABC','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 22:55:59'),(426,'TRANS','UPDATE','Departmen','ada/ada/ABC','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 22:56:18'),(427,'TRANS','ADD','Departmen','OPS/EYE/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 22:57:01'),(428,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 23:12:38'),(429,'TRANS','DELETE','Departmen','ada/ada/ABC','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-12 23:12:52'),(430,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 23:33:37'),(431,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-12 23:34:01'),(432,'TRANS','ADD','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 23:34:01'),(433,'TRANS','ADD','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 23:34:01'),(434,'TRANS','ADD','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-12 23:34:02'),(435,'TRANS','UPDATE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 23:34:18'),(436,'TRANS','UPDATE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-12 23:34:35'),(437,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-12 23:49:13'),(438,'TRANS','DELETE','Divisi','ITD','0:0:0:0:0:0:0:1','DELETE DIVISI','ADM','2014-07-13 00:10:57'),(439,'TRANS','UPDATE','Divisi','OPT','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-13 00:11:31'),(440,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-07-13 00:12:18'),(441,'TRANS','DELETE','Subdivisi','adw/ABC','0:0:0:0:0:0:0:1','DELETE SUB DIVISI','ADM','2014-07-13 00:12:40'),(442,'TRANS','DELETE','Subdivisi','ada/OKA','0:0:0:0:0:0:0:1','DELETE SUB DIVISI','ADM','2014-07-13 00:12:47'),(443,'TRANS','DELETE','Subdivisi','ada/ABC','0:0:0:0:0:0:0:1','DELETE SUB DIVISI','ADM','2014-07-13 00:12:53'),(444,'TRANS','ADD','Departmen','AQU/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:05:53'),(445,'TRANS','UPDATE','Departmen','AQU/ACC/HOL','0:0:0:0:0:0:0:1','UPDATE DEPARTMENT','ADM','2014-07-13 01:06:08'),(446,'TRANS','DELETE','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:07:56'),(447,'TRANS','DELETE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:08:03'),(448,'TRANS','DELETE','Departmen','AQU/ACC/HOL','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:08:11'),(449,'TRANS','DELETE','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:08:17'),(450,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-07-13 01:08:31'),(451,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-13 01:08:38'),(452,'TRANS','ADD','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:08:38'),(453,'TRANS','ADD','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:08:38'),(454,'TRANS','ADD','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:08:39'),(455,'TRANS','DELETE','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:11:27'),(456,'TRANS','DELETE','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:11:34'),(457,'TRANS','DELETE','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','DELETE DEPARTMENT','ADM','2014-07-13 01:11:41'),(458,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-13 01:11:55'),(459,'TRANS','ADD','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:11:55'),(460,'TRANS','ADD','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:11:55'),(461,'TRANS','ADD','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-13 01:11:56'),(462,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-13 01:23:24'),(463,'TRANS','ADD','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','ADD Lokasi','ADM','2014-07-13 01:29:26'),(464,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:36:52'),(465,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:40:23'),(466,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:40:35'),(467,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:43:30'),(468,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:43:43'),(469,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:52:28'),(470,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 01:54:10'),(471,'TRANS','ADD','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','ADD Lokasi','ADM','2014-07-13 01:56:18'),(472,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-13 02:11:18'),(473,'EXIM','FAILED','Lokasi','lokasi.csv','0:0:0:0:0:0:0:1','[(filename= lokasi.csv)\n Format kolom salah pada baris ke-3 :\nDate Format Not VALID]','ADM','2014-07-13 02:11:26'),(474,'EXIM','FAILED','Lokasi','lokasi.csv','0:0:0:0:0:0:0:1','[ (filename= lokasi.csv) pada baris ke-4 : dept_kd is required,  (filename= lokasi.csv) pada baris ke-4 : lok_nm is required,  (filename= lokasi.csv) pada baris ke-4 : divisi_kd is required,  (filename= lokasi.csv) pada baris ke-4 : subdiv_kd is required,  (filename= lokasi.csv) pada baris ke-4 : propinsi is required,  (filename= lokasi.csv) pada baris ke-4 : email is required,  (filename= lokasi.csv) pada baris ke-4 : kota is required]','ADM','2014-07-13 02:11:46'),(475,'EXIM','SUCCESS','Lokasi','lokasi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-13 02:12:09'),(476,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:12:09'),(477,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:12:09'),(478,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-13 02:24:23'),(479,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:24:34'),(480,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:24:49'),(481,'LOGIN','SUCCESS','User','ADM','0:0:0:0:0:0:0:1','LOGIN SUCCESS','ADM','2014-07-13 02:28:59'),(482,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:29:43'),(483,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:30:10'),(484,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:30:33'),(485,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:30:47'),(486,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:31:01'),(487,'EXIM','SUCCESS','Lokasi','lokasi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-07-13 02:32:24'),(488,'TRANS','UPDATE','Lokasi','ADDDD/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:32:25'),(489,'TRANS','UPDATE','Lokasi','SNYN/1/EYE/OPT','0:0:0:0:0:0:0:1','UPDATE LOKASI','ADM','2014-07-13 02:32:25');
/*!40000 ALTER TABLE `sys_audittrail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_audittrail_detail`
--

DROP TABLE IF EXISTS `sys_audittrail_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_audittrail_detail` (
  `sys_id_audittrail` bigint(20) NOT NULL,
  `sys_id_audittrail_detail` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_field` varchar(50) DEFAULT NULL,
  `sys_before` text,
  `sys_after` text,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_id_audittrail_detail`),
  KEY `fk_auditraildet_audittrail_idx` (`sys_id_audittrail`),
  CONSTRAINT `fk_auditraildet_audittrail` FOREIGN KEY (`sys_id_audittrail`) REFERENCES `sys_audittrail` (`sys_id_audittrail`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1590 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audittrail_detail`
--

LOCK TABLES `sys_audittrail_detail` WRITE;
/*!40000 ALTER TABLE `sys_audittrail_detail` DISABLE KEYS */;
INSERT INTO `sys_audittrail_detail` VALUES (113,279,'upload','com.melawai.ppuc.model.Upload@3a2bb9b6',NULL,'ADM','2014-06-29 03:56:44'),(113,280,'user_create','ADM',NULL,'ADM','2014-06-29 03:56:44'),(113,281,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 03:56:44'),(113,282,'tgl_create','Sun Jun 29 03:56:44 ICT 2014',NULL,'ADM','2014-06-29 03:56:45'),(113,283,'divisi_kd','HOL',NULL,'ADM','2014-06-29 03:56:45'),(113,284,'divisi_nm','HOLDING',NULL,'ADM','2014-06-29 03:56:45'),(114,285,'upload','com.melawai.ppuc.model.Upload@11afd356',NULL,'ADM','2014-06-29 03:56:45'),(114,286,'tgl_create','Sun Jun 29 03:56:45 ICT 2014',NULL,'ADM','2014-06-29 03:56:46'),(114,287,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 03:56:46'),(114,288,'divisi_nm','OPTIK',NULL,'ADM','2014-06-29 03:56:46'),(114,289,'user_create','ADM',NULL,'ADM','2014-06-29 03:56:46'),(114,290,'divisi_kd','OPT',NULL,'ADM','2014-06-29 03:56:46'),(115,291,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 03:56:57'),(115,292,'upload','com.melawai.ppuc.model.Upload@15e3592b',NULL,'ADM','2014-06-29 03:56:57'),(115,293,'divisi_nm','OPTIK',NULL,'ADM','2014-06-29 03:56:57'),(115,294,'divisi_kd','OPT',NULL,'ADM','2014-06-29 03:56:57'),(115,295,'user_create','ADM',NULL,'ADM','2014-06-29 03:56:57'),(115,296,'tgl_create','Sun Jun 29 03:56:45 ICT 2014',NULL,'ADM','2014-06-29 03:56:57'),(117,297,'upload','com.melawai.ppuc.model.Upload@3899bc9a','com.melawai.ppuc.model.Upload@752d3f7d','ADM','2014-06-29 05:15:09'),(117,298,'jam_update','05:15',NULL,'ADM','2014-06-29 05:15:09'),(117,299,'user_create',NULL,'ADM','ADM','2014-06-29 05:15:09'),(117,300,'user_update','ADM',NULL,'ADM','2014-06-29 05:15:09'),(117,301,'tgl_update','Sun Jun 29 05:15:09 ICT 2014',NULL,'ADM','2014-06-29 05:15:09'),(118,302,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 05:15:10'),(118,303,'tgl_create','Sun Jun 29 05:15:10 ICT 2014',NULL,'ADM','2014-06-29 05:15:10'),(118,304,'divisi_nm','OPTIK',NULL,'ADM','2014-06-29 05:15:10'),(118,305,'user_create','ADM',NULL,'ADM','2014-06-29 05:15:10'),(118,306,'upload','com.melawai.ppuc.model.Upload@59162f2f',NULL,'ADM','2014-06-29 05:15:10'),(118,307,'divisi_kd','OPT',NULL,'ADM','2014-06-29 05:15:10'),(120,308,'divisi_kd','HOL',NULL,'ADM','2014-06-29 11:35:30'),(120,309,'upload','com.melawai.ppuc.model.Upload@4df80836',NULL,'ADM','2014-06-29 11:35:30'),(120,310,'subdiv_nm','ACCOUNTING',NULL,'ADM','2014-06-29 11:35:30'),(120,311,'subdiv_kd','ACC',NULL,'ADM','2014-06-29 11:35:30'),(120,312,'user_create','ADM',NULL,'ADM','2014-06-29 11:35:30'),(120,313,'tgl_create','Sun Jun 29 11:35:29 ICT 2014',NULL,'ADM','2014-06-29 11:35:30'),(121,314,'upload','com.melawai.ppuc.model.Upload@4defbda8',NULL,'ADM','2014-06-29 11:35:31'),(121,315,'subdiv_nm','OPTIK REVOLUTION',NULL,'ADM','2014-06-29 11:35:31'),(121,316,'tgl_create','Sun Jun 29 11:35:30 ICT 2014',NULL,'ADM','2014-06-29 11:35:31'),(121,317,'user_create','ADM',NULL,'ADM','2014-06-29 11:35:31'),(121,318,'subdiv_kd','EYE',NULL,'ADM','2014-06-29 11:35:32'),(121,319,'divisi_kd','OPT',NULL,'ADM','2014-06-29 11:35:32'),(122,320,'user_create','ADM',NULL,'ADM','2014-06-29 11:35:32'),(122,321,'tgl_create','Sun Jun 29 11:35:32 ICT 2014',NULL,'ADM','2014-06-29 11:35:32'),(122,322,'subdiv_kd','MLW',NULL,'ADM','2014-06-29 11:35:32'),(122,323,'subdiv_nm','OPTIK MELAWAI',NULL,'ADM','2014-06-29 11:35:32'),(122,324,'upload','com.melawai.ppuc.model.Upload@486aee33',NULL,'ADM','2014-06-29 11:35:32'),(122,325,'divisi_kd','OPT',NULL,'ADM','2014-06-29 11:35:32'),(142,342,'dept_nm','UMUM',NULL,'ADM','2014-06-30 22:10:14'),(142,343,'tgl_create','Mon Jun 30 22:10:14 ICT 2014',NULL,'ADM','2014-06-30 22:10:14'),(142,344,'divisi_kd','HOL',NULL,'ADM','2014-06-30 22:10:14'),(142,345,'dept_kd','Z',NULL,'ADM','2014-06-30 22:10:14'),(142,346,'user_create','ADM',NULL,'ADM','2014-06-30 22:10:14'),(142,347,'upload','com.melawai.ppuc.model.Upload@4c693c42',NULL,'ADM','2014-06-30 22:10:14'),(142,348,'subdiv_kd','ACC',NULL,'ADM','2014-06-30 22:10:14'),(142,349,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:10:14'),(143,350,'dept_kd','1',NULL,'ADM','2014-06-30 22:10:15'),(143,351,'subdiv_kd','EYE',NULL,'ADM','2014-06-30 22:10:15'),(143,352,'user_create','ADM',NULL,'ADM','2014-06-30 22:10:15'),(143,353,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:10:15'),(143,354,'tgl_create','Mon Jun 30 22:10:15 ICT 2014',NULL,'ADM','2014-06-30 22:10:15'),(143,355,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:10:15'),(143,356,'upload','com.melawai.ppuc.model.Upload@44d0dc1d',NULL,'ADM','2014-06-30 22:10:15'),(143,357,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-06-30 22:10:15'),(144,358,'upload','com.melawai.ppuc.model.Upload@634284f3',NULL,'ADM','2014-06-30 22:10:15'),(144,359,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-06-30 22:10:15'),(144,360,'tgl_create','Mon Jun 30 22:10:15 ICT 2014',NULL,'ADM','2014-06-30 22:10:15'),(144,361,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:10:15'),(144,362,'subdiv_kd','MLW',NULL,'ADM','2014-06-30 22:10:15'),(144,363,'user_create','ADM',NULL,'ADM','2014-06-30 22:10:15'),(144,364,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:10:15'),(144,365,'dept_kd','1',NULL,'ADM','2014-06-30 22:10:15'),(145,366,'user_update','',NULL,'ADM','2014-06-30 22:11:25'),(145,367,'jam_update','',NULL,'ADM','2014-06-30 22:11:26'),(145,368,'upload','com.melawai.ppuc.model.Upload@207dd291',NULL,'ADM','2014-06-30 22:11:26'),(145,369,'dept_nm','TEST',NULL,'ADM','2014-06-30 22:11:26'),(145,370,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:11:26'),(145,371,'tgl_create','Mon Jun 30 22:11:25 ICT 2014',NULL,'ADM','2014-06-30 22:11:26'),(145,372,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:11:26'),(145,373,'dept_kd','2',NULL,'ADM','2014-06-30 22:11:26'),(145,374,'subdiv_kd','MLW',NULL,'ADM','2014-06-30 22:11:26'),(145,375,'user_create','ADM',NULL,'ADM','2014-06-30 22:11:26'),(146,376,'user_update','',NULL,'ADM','2014-06-30 22:13:27'),(146,377,'user_create','ADM',NULL,'ADM','2014-06-30 22:13:27'),(146,378,'subdiv_kd','MLW',NULL,'ADM','2014-06-30 22:13:27'),(146,379,'tgl_create','Mon Jun 30 22:13:27 ICT 2014',NULL,'ADM','2014-06-30 22:13:27'),(146,380,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:13:27'),(146,381,'jam_update','',NULL,'ADM','2014-06-30 22:13:27'),(146,382,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:13:27'),(146,383,'dept_kd','3',NULL,'ADM','2014-06-30 22:13:27'),(146,384,'dept_nm','OK',NULL,'ADM','2014-06-30 22:13:27'),(146,385,'upload','com.melawai.ppuc.model.Upload@2a0a81fe',NULL,'ADM','2014-06-30 22:13:27'),(147,386,'email','',NULL,'ADM','2014-07-06 23:27:41'),(147,387,'propinsi','Jawa Barat',NULL,'ADM','2014-07-06 23:27:42'),(147,388,'user_create','ADM',NULL,'ADM','2014-07-06 23:27:42'),(147,389,'serialVersionUID','7503294106098421175',NULL,'ADM','2014-07-06 23:27:42'),(147,390,'tgl_create','Sun Jul 06 23:27:40 ICT 2014',NULL,'ADM','2014-07-06 23:27:42'),(147,391,'lok_kd','OKA',NULL,'ADM','2014-07-06 23:27:42'),(147,392,'upload','com.melawai.ppuc.model.Upload@52415085',NULL,'ADM','2014-07-06 23:27:42'),(147,393,'kota','CIREBON',NULL,'ADM','2014-07-06 23:27:42'),(147,394,'lok_nm','123 ada',NULL,'ADM','2014-07-06 23:27:42'),(147,395,'dept_kd','Z',NULL,'ADM','2014-07-06 23:27:42'),(147,396,'divisi_kd','HOL',NULL,'ADM','2014-07-06 23:27:42'),(147,397,'subdiv_kd','ACC',NULL,'ADM','2014-07-06 23:27:42'),(148,398,'dept_nm','OK DEH',NULL,'ADM','2014-07-06 23:30:20'),(148,399,'user_create','ADM',NULL,'ADM','2014-07-06 23:30:20'),(148,400,'subdiv_kd','ACC',NULL,'ADM','2014-07-06 23:30:20'),(148,401,'divisi_kd','HOL',NULL,'ADM','2014-07-06 23:30:20'),(148,402,'tgl_create','Sun Jul 06 23:30:20 ICT 2014',NULL,'ADM','2014-07-06 23:30:20'),(148,403,'dept_kd','OIK',NULL,'ADM','2014-07-06 23:30:20'),(148,404,'upload','com.melawai.ppuc.model.Upload@3af93f2a',NULL,'ADM','2014-07-06 23:30:20'),(148,405,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-06 23:30:20'),(157,406,'jam_create','',NULL,'ADM','2014-07-08 23:59:36'),(157,407,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@2fb93d7b','ADM','2014-07-08 23:59:36'),(157,408,'itemId','6',NULL,'ADM','2014-07-08 23:59:37'),(157,409,'level','2','1','ADM','2014-07-08 23:59:37'),(157,410,'parent','2','1','ADM','2014-07-08 23:59:37'),(157,411,'parent_nama',NULL,'ROOT','ADM','2014-07-08 23:59:37'),(157,412,'link','',NULL,'ADM','2014-07-08 23:59:37'),(157,413,'path',NULL,'0.1.2','ADM','2014-07-08 23:59:37'),(157,414,'user_create','',NULL,'ADM','2014-07-08 23:59:38'),(158,415,'jam_create','',NULL,'ADM','2014-07-09 00:00:14'),(158,416,'level','3','2','ADM','2014-07-09 00:00:14'),(158,417,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@27f0c86d','ADM','2014-07-09 00:00:14'),(158,418,'user_create','',NULL,'ADM','2014-07-09 00:00:14'),(158,419,'path',NULL,'0.1.2.1','ADM','2014-07-09 00:00:14'),(158,420,'parent_nama',NULL,'Master','ADM','2014-07-09 00:00:14'),(158,421,'itemId','7',NULL,'ADM','2014-07-09 00:00:14'),(159,422,'jam_create','',NULL,'ADM','2014-07-09 00:00:30'),(159,423,'path',NULL,'0.1.1.3','ADM','2014-07-09 00:00:30'),(159,424,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@28d7afb5','ADM','2014-07-09 00:00:30'),(159,425,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:00:30'),(159,426,'user_create','',NULL,'ADM','2014-07-09 00:00:30'),(159,427,'itemId','5',NULL,'ADM','2014-07-09 00:00:30'),(160,428,'itemId','5',NULL,'ADM','2014-07-09 00:00:51'),(160,429,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:00:51'),(160,430,'level','3','2','ADM','2014-07-09 00:00:51'),(160,431,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@3475df85','ADM','2014-07-09 00:00:51'),(160,432,'parent','6','2','ADM','2014-07-09 00:00:51'),(160,433,'path',NULL,'0.1.1.3','ADM','2014-07-09 00:00:51'),(161,434,'path',NULL,'0.1.1.2.3','ADM','2014-07-09 00:01:09'),(161,435,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@24616781','ADM','2014-07-09 00:01:09'),(161,436,'parent','2','6','ADM','2014-07-09 00:01:09'),(161,437,'level','2','3','ADM','2014-07-09 00:01:09'),(161,438,'parent_nama',NULL,'Master','ADM','2014-07-09 00:01:09'),(161,439,'itemId','5',NULL,'ADM','2014-07-09 00:01:09'),(162,440,'level','3','2','ADM','2014-07-09 00:01:24'),(162,441,'parent_nama',NULL,'Master','ADM','2014-07-09 00:01:24'),(162,442,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@2170925f','ADM','2014-07-09 00:01:24'),(162,443,'path',NULL,'0.1.2.10','ADM','2014-07-09 00:01:24'),(162,444,'itemId','16',NULL,'ADM','2014-07-09 00:01:25'),(162,445,'jam_create','',NULL,'ADM','2014-07-09 00:01:25'),(162,446,'user_create','',NULL,'ADM','2014-07-09 00:01:25'),(163,447,'jam_create','',NULL,'ADM','2014-07-09 00:01:38'),(163,448,'path',NULL,'0.1.2.11','ADM','2014-07-09 00:01:38'),(163,449,'itemId','17',NULL,'ADM','2014-07-09 00:01:38'),(163,450,'level','3','2','ADM','2014-07-09 00:01:38'),(163,451,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@308a82d4','ADM','2014-07-09 00:01:38'),(163,452,'user_create','',NULL,'ADM','2014-07-09 00:01:38'),(163,453,'parent_nama',NULL,'Master','ADM','2014-07-09 00:01:38'),(164,454,'user_create','',NULL,'ADM','2014-07-09 00:01:49'),(164,455,'level','3','2','ADM','2014-07-09 00:01:49'),(164,456,'itemId','18',NULL,'ADM','2014-07-09 00:01:49'),(164,457,'parent_nama',NULL,'Master','ADM','2014-07-09 00:01:49'),(164,458,'path',NULL,'0.1.2.12','ADM','2014-07-09 00:01:49'),(164,459,'jam_create','',NULL,'ADM','2014-07-09 00:01:49'),(164,460,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@4b249012','ADM','2014-07-09 00:01:49'),(165,461,'level','3','2','ADM','2014-07-09 00:02:08'),(165,462,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@241bfc05','ADM','2014-07-09 00:02:08'),(165,463,'itemId','19',NULL,'ADM','2014-07-09 00:02:08'),(165,464,'jam_create','',NULL,'ADM','2014-07-09 00:02:08'),(165,465,'path',NULL,'0.1.2.13','ADM','2014-07-09 00:02:08'),(165,466,'user_create','',NULL,'ADM','2014-07-09 00:02:08'),(165,467,'parent_nama',NULL,'Master','ADM','2014-07-09 00:02:08'),(166,468,'urut','1',NULL,'ADM','2014-07-09 00:05:42'),(166,469,'user_create','ADM',NULL,'ADM','2014-07-09 00:05:42'),(166,470,'level','3',NULL,'ADM','2014-07-09 00:05:42'),(166,471,'parent','6',NULL,'ADM','2014-07-09 00:05:42'),(166,472,'serialVersionUID','2062694598974079208',NULL,'ADM','2014-07-09 00:05:42'),(166,473,'nama','User Management',NULL,'ADM','2014-07-09 00:05:42'),(166,474,'tgl_create','Wed Jul 09 00:05:41 ICT 2014',NULL,'ADM','2014-07-09 00:05:42'),(166,475,'link','',NULL,'ADM','2014-07-09 00:05:42'),(166,476,'f_aktif','1',NULL,'ADM','2014-07-09 00:05:42'),(167,477,'urut','3','2','ADM','2014-07-09 00:06:07'),(167,478,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:06:07'),(167,479,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@1d97b9c0','ADM','2014-07-09 00:06:07'),(167,480,'path',NULL,'0.1.1.2','ADM','2014-07-09 00:06:07'),(167,481,'itemId','6',NULL,'ADM','2014-07-09 00:06:07'),(168,482,'itemId','6',NULL,'ADM','2014-07-09 00:06:08'),(168,483,'path',NULL,'0.1.1.2','ADM','2014-07-09 00:06:08'),(168,484,'urut','3','2','ADM','2014-07-09 00:06:08'),(168,485,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:06:08'),(168,486,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@3423c3ed','ADM','2014-07-09 00:06:08'),(169,487,'jam_create','',NULL,'ADM','2014-07-09 00:06:55'),(169,488,'path',NULL,'0.1.1.2.1','ADM','2014-07-09 00:06:55'),(169,489,'parent_nama',NULL,'Master','ADM','2014-07-09 00:06:55'),(169,490,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@36ecbef5','ADM','2014-07-09 00:06:55'),(169,491,'itemId','30',NULL,'ADM','2014-07-09 00:06:55'),(173,492,'path',NULL,'0.1.1.3','ADM','2014-07-09 00:18:00'),(173,493,'itemId','6',NULL,'ADM','2014-07-09 00:18:00'),(173,494,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@34457c45','ADM','2014-07-09 00:18:00'),(173,495,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:18:00'),(173,496,'urut','4','3','ADM','2014-07-09 00:18:00'),(174,497,'itemId','3',NULL,'ADM','2014-07-09 00:20:02'),(174,498,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:20:02'),(174,499,'path',NULL,'0.1.1.1','ADM','2014-07-09 00:20:02'),(174,500,'jam_create','',NULL,'ADM','2014-07-09 00:20:02'),(174,501,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@a891acb','ADM','2014-07-09 00:20:02'),(174,502,'urut','2','1','ADM','2014-07-09 00:20:02'),(175,503,'jam_create','',NULL,'ADM','2014-07-09 00:20:18'),(175,504,'itemId','4',NULL,'ADM','2014-07-09 00:20:18'),(175,505,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:20:18'),(175,506,'urut','3','2','ADM','2014-07-09 00:20:18'),(175,507,'path',NULL,'0.1.1.2','ADM','2014-07-09 00:20:18'),(175,508,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@6f40bada','ADM','2014-07-09 00:20:18'),(176,509,'itemId','4',NULL,'ADM','2014-07-09 00:20:18'),(176,510,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:20:18'),(176,511,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@6c6eab00','ADM','2014-07-09 00:20:18'),(176,512,'path',NULL,'0.1.1.3','ADM','2014-07-09 00:20:18'),(177,513,'urut','4','3','ADM','2014-07-09 00:20:33'),(177,514,'itemId','5',NULL,'ADM','2014-07-09 00:20:33'),(177,515,'path',NULL,'0.1.1.3','ADM','2014-07-09 00:20:33'),(177,516,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@41c74a27','ADM','2014-07-09 00:20:33'),(177,517,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:20:33'),(178,518,'itemId','6',NULL,'ADM','2014-07-09 00:20:46'),(178,519,'path',NULL,'0.1.1.4','ADM','2014-07-09 00:20:46'),(178,520,'parent_nama',NULL,'Admin','ADM','2014-07-09 00:20:46'),(178,521,'urut','1','4','ADM','2014-07-09 00:20:46'),(178,522,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@1c21ecf4','ADM','2014-07-09 00:20:47'),(179,523,'parent_nama',NULL,'Master','ADM','2014-07-09 00:21:03'),(179,524,'path',NULL,'0.1.1.4.1','ADM','2014-07-09 00:21:04'),(179,525,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@2f96ca38','ADM','2014-07-09 00:21:04'),(179,526,'itemId','7',NULL,'ADM','2014-07-09 00:21:04'),(180,527,'parent_nama',NULL,'Master','ADM','2014-07-09 00:21:26'),(180,528,'itemId','30',NULL,'ADM','2014-07-09 00:21:26'),(180,529,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@480cc79e','ADM','2014-07-09 00:21:27'),(180,530,'path',NULL,'0.1.1.4.1','ADM','2014-07-09 00:21:27'),(181,531,'parent_nama',NULL,'Master','ADM','2014-07-09 00:22:29'),(181,532,'itemId','7',NULL,'ADM','2014-07-09 00:22:29'),(181,533,'path',NULL,'0.1.1.1.1','ADM','2014-07-09 00:22:29'),(181,534,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@77604b8c','ADM','2014-07-09 00:22:29'),(181,535,'urut','3','1','ADM','2014-07-09 00:22:29'),(182,536,'itemId','11',NULL,'ADM','2014-07-09 00:23:08'),(182,537,'level','3','2','ADM','2014-07-09 00:23:08'),(182,538,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@8bffd77','ADM','2014-07-09 00:23:08'),(182,539,'urut','6','5','ADM','2014-07-09 00:23:08'),(182,540,'path',NULL,'0.1.1.4.5','ADM','2014-07-09 00:23:08'),(182,541,'parent_nama',NULL,'Master','ADM','2014-07-09 00:23:08'),(182,542,'jam_create','',NULL,'ADM','2014-07-09 00:23:08'),(183,543,'itemId','10',NULL,'ADM','2014-07-09 00:23:26'),(183,544,'urut','5','4','ADM','2014-07-09 00:23:26'),(183,545,'path',NULL,'0.1.1.4.4','ADM','2014-07-09 00:23:26'),(183,546,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@9f07cbb','ADM','2014-07-09 00:23:26'),(183,547,'parent_nama',NULL,'Master','ADM','2014-07-09 00:23:26'),(183,548,'jam_create','',NULL,'ADM','2014-07-09 00:23:26'),(183,549,'level','3','2','ADM','2014-07-09 00:23:26'),(184,550,'jam_create','',NULL,'ADM','2014-07-09 00:24:04'),(184,551,'parent','30','6','ADM','2014-07-09 00:24:04'),(184,552,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@646db148','ADM','2014-07-09 00:24:04'),(184,553,'itemId','15',NULL,'ADM','2014-07-09 00:24:04'),(184,554,'urut','4','9','ADM','2014-07-09 00:24:04'),(184,555,'path',NULL,'0.1.1.4.9','ADM','2014-07-09 00:24:04'),(184,556,'parent_nama',NULL,'Master','ADM','2014-07-09 00:24:04'),(184,557,'level','4','2','ADM','2014-07-09 00:24:04'),(185,558,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@45530365','ADM','2014-07-09 00:24:39'),(185,559,'urut','4','3','ADM','2014-07-09 00:24:39'),(185,560,'path',NULL,'0.1.1.4.3','ADM','2014-07-09 00:24:39'),(185,561,'parent_nama',NULL,'Master','ADM','2014-07-09 00:24:39'),(185,562,'level','3','2','ADM','2014-07-09 00:24:39'),(185,563,'itemId','9',NULL,'ADM','2014-07-09 00:24:39'),(185,564,'jam_create','',NULL,'ADM','2014-07-09 00:24:39'),(186,565,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@13a646f9','ADM','2014-07-09 00:25:14'),(186,566,'parent_nama',NULL,'Master','ADM','2014-07-09 00:25:14'),(186,567,'itemId','16',NULL,'ADM','2014-07-09 00:25:14'),(186,568,'parent','30','6','ADM','2014-07-09 00:25:14'),(186,569,'level','4','3','ADM','2014-07-09 00:25:14'),(186,570,'path',NULL,'0.1.1.4.10','ADM','2014-07-09 00:25:14'),(187,571,'level','3',NULL,'ADM','2014-07-09 00:26:13'),(187,572,'link','',NULL,'ADM','2014-07-09 00:26:13'),(187,573,'tgl_create','Wed Jul 09 00:26:13 ICT 2014',NULL,'ADM','2014-07-09 00:26:13'),(187,574,'user_create','ADM',NULL,'ADM','2014-07-09 00:26:13'),(187,575,'urut','3',NULL,'ADM','2014-07-09 00:26:13'),(187,576,'serialVersionUID','2062694598974079208',NULL,'ADM','2014-07-09 00:26:13'),(187,577,'f_aktif','1',NULL,'ADM','2014-07-09 00:26:13'),(187,578,'parent','6',NULL,'ADM','2014-07-09 00:26:13'),(187,579,'nama','Managemen Transaksi',NULL,'ADM','2014-07-09 00:26:13'),(188,580,'nama','Management User','User Management','ADM','2014-07-09 00:26:37'),(188,581,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@4e002271','ADM','2014-07-09 00:26:37'),(188,582,'parent_nama',NULL,'Master','ADM','2014-07-09 00:26:37'),(188,583,'path',NULL,'0.1.1.1.1','ADM','2014-07-09 00:26:37'),(188,584,'itemId','30',NULL,'ADM','2014-07-09 00:26:37'),(189,585,'parent_nama',NULL,'Master','ADM','2014-07-09 00:26:52'),(189,586,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@1dd53ccd','ADM','2014-07-09 00:26:52'),(189,587,'urut','2','3','ADM','2014-07-09 00:26:52'),(189,588,'itemId','31',NULL,'ADM','2014-07-09 00:26:52'),(189,589,'path',NULL,'0.1.1.1.3','ADM','2014-07-09 00:26:53'),(189,590,'jam_create','',NULL,'ADM','2014-07-09 00:26:53'),(190,591,'level','4','2','ADM','2014-07-09 00:27:40'),(190,592,'itemId','8',NULL,'ADM','2014-07-09 00:27:40'),(190,593,'path',NULL,'0.1.1.4.2','ADM','2014-07-09 00:27:40'),(190,594,'parent','30','6','ADM','2014-07-09 00:27:40'),(190,595,'parent_nama',NULL,'Master','ADM','2014-07-09 00:27:40'),(190,596,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@2f324d38','ADM','2014-07-09 00:27:40'),(190,597,'jam_create','',NULL,'ADM','2014-07-09 00:27:40'),(191,598,'parent','30','6','ADM','2014-07-09 00:27:59'),(191,599,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@6430c27f','ADM','2014-07-09 00:27:59'),(191,600,'itemId','13',NULL,'ADM','2014-07-09 00:27:59'),(191,601,'parent_nama',NULL,'Master','ADM','2014-07-09 00:27:59'),(191,602,'path',NULL,'0.1.1.4.7','ADM','2014-07-09 00:27:59'),(191,603,'jam_create','',NULL,'ADM','2014-07-09 00:27:59'),(191,604,'level','4','2','ADM','2014-07-09 00:27:59'),(192,605,'level','3','2','ADM','2014-07-09 00:28:22'),(192,606,'parent_nama',NULL,'Master','ADM','2014-07-09 00:28:22'),(192,607,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@48856ce9','ADM','2014-07-09 00:28:22'),(192,608,'itemId','12',NULL,'ADM','2014-07-09 00:28:22'),(192,609,'path',NULL,'0.1.1.4.6','ADM','2014-07-09 00:28:22'),(192,610,'jam_create','',NULL,'ADM','2014-07-09 00:28:22'),(193,611,'path',NULL,'0.1.1.4.11','ADM','2014-07-09 00:28:44'),(193,612,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@2ab630bb','ADM','2014-07-09 00:28:44'),(193,613,'itemId','17',NULL,'ADM','2014-07-09 00:28:44'),(193,614,'parent_nama',NULL,'Master','ADM','2014-07-09 00:28:44'),(194,615,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@20ed6b0a','ADM','2014-07-09 00:29:00'),(194,616,'path',NULL,'0.1.1.4.12','ADM','2014-07-09 00:29:00'),(194,617,'itemId','18',NULL,'ADM','2014-07-09 00:29:00'),(194,618,'parent_nama',NULL,'Master','ADM','2014-07-09 00:29:00'),(195,619,'menu_parent',NULL,'com.melawai.ppuc.model.Menu@4d10f9ac','ADM','2014-07-09 00:29:12'),(195,620,'itemId','19',NULL,'ADM','2014-07-09 00:29:12'),(195,621,'path',NULL,'0.1.1.4.13','ADM','2014-07-09 00:29:12'),(195,622,'parent_nama',NULL,'Master','ADM','2014-07-09 00:29:12'),(206,623,'serialVersionUID','-8946818516215179670',NULL,'ADM','2014-07-09 13:25:57'),(206,624,'group_kd','gd',NULL,'ADM','2014-07-09 13:26:01'),(206,625,'jam_create','',NULL,'ADM','2014-07-09 13:26:01'),(206,626,'user_create','',NULL,'ADM','2014-07-09 13:26:01'),(206,627,'menus','[com.melawai.ppuc.model.Menu@23248bd0, com.melawai.ppuc.model.Menu@345716b6, com.melawai.ppuc.model.Menu@41942192, com.melawai.ppuc.model.Menu@642819e0, com.melawai.ppuc.model.Menu@4baf47f, com.melawai.ppuc.model.Menu@e09c046, com.melawai.ppuc.model.Menu@222e3add, com.melawai.ppuc.model.Menu@91a94b3, com.melawai.ppuc.model.Menu@30187870, com.melawai.ppuc.model.Menu@168b19bb, com.melawai.ppuc.model.Menu@6464f8d, com.melawai.ppuc.model.Menu@7210b532, com.melawai.ppuc.model.Menu@26e8201f, com.melawai.ppuc.model.Menu@5194e72d, com.melawai.ppuc.model.Menu@6d56533, com.melawai.ppuc.model.Menu@23e6fac6, com.melawai.ppuc.model.Menu@1159ef94, com.melawai.ppuc.model.Menu@277fe672, com.melawai.ppuc.model.Menu@3df25aa0, com.melawai.ppuc.model.Menu@721bde25, com.melawai.ppuc.model.Menu@39589aa, com.melawai.ppuc.model.Menu@4e80f9bc, com.melawai.ppuc.model.Menu@758bcde7, com.melawai.ppuc.model.Menu@2d7b34fb, com.melawai.ppuc.model.Menu@73d36210, com.melawai.ppuc.model.Menu@3acb43d8, com.melawai.ppuc.model.Menu@76d3380f, com.melawai.ppuc.model.Menu@25059dbb, com.melawai.ppuc.model.Menu@13b672fa]',NULL,'ADM','2014-07-09 13:26:01'),(206,628,'id_role','1',NULL,'ADM','2014-07-09 13:26:01'),(206,629,'group_nm','test',NULL,'ADM','2014-07-09 13:26:01'),(207,630,'menus','[com.melawai.ppuc.model.Menu@424255b1, com.melawai.ppuc.model.Menu@1107f973, com.melawai.ppuc.model.Menu@2289f5c1, com.melawai.ppuc.model.Menu@f65639e, com.melawai.ppuc.model.Menu@496f21f7, com.melawai.ppuc.model.Menu@1f1f04cb, com.melawai.ppuc.model.Menu@2d73bb63, com.melawai.ppuc.model.Menu@91377e5, com.melawai.ppuc.model.Menu@5d24600a, com.melawai.ppuc.model.Menu@7f1d604b, com.melawai.ppuc.model.Menu@619d1d1f, com.melawai.ppuc.model.Menu@11e3104a, com.melawai.ppuc.model.Menu@52427372, com.melawai.ppuc.model.Menu@8a96b8f, com.melawai.ppuc.model.Menu@2ad47dba, com.melawai.ppuc.model.Menu@648a544d, com.melawai.ppuc.model.Menu@35a4becc, com.melawai.ppuc.model.Menu@52ea5e97, com.melawai.ppuc.model.Menu@18e83608, com.melawai.ppuc.model.Menu@353353fe, com.melawai.ppuc.model.Menu@3ccf63fb, com.melawai.ppuc.model.Menu@53ad12ed, com.melawai.ppuc.model.Menu@7b5b086, com.melawai.ppuc.model.Menu@2958315e, com.melawai.ppuc.model.Menu@5d112786, com.melawai.ppuc.model.Menu@1139fe26, com.melawai.ppuc.model.Menu@765c799f, com.melawai.ppuc.model.Menu@3134f26e, com.melawai.ppuc.model.Menu@b182cff]','[com.melawai.ppuc.model.Menu@60321f09, com.melawai.ppuc.model.Menu@7a93b935, com.melawai.ppuc.model.Menu@78648571, com.melawai.ppuc.model.Menu@f74fb77, com.melawai.ppuc.model.Menu@4929498e, com.melawai.ppuc.model.Menu@359c2f28, com.melawai.ppuc.model.Menu@20dc0697, com.melawai.ppuc.model.Menu@4d34b45b, com.melawai.ppuc.model.Menu@3f2ce9f6, com.melawai.ppuc.model.Menu@1db435e1, com.melawai.ppuc.model.Menu@223d5603, com.melawai.ppuc.model.Menu@68d9f084, com.melawai.ppuc.model.Menu@3c3d9fe3, com.melawai.ppuc.model.Menu@71cc0efa, com.melawai.ppuc.model.Menu@beb7974, com.melawai.ppuc.model.Menu@1072b4c9, com.melawai.ppuc.model.Menu@5abb008e, com.melawai.ppuc.model.Menu@2b21992b, com.melawai.ppuc.model.Menu@2aceec2c, com.melawai.ppuc.model.Menu@76f352a8, com.melawai.ppuc.model.Menu@60b7d09a, com.melawai.ppuc.model.Menu@43e06011, com.melawai.ppuc.model.Menu@3fc31ee7, com.melawai.ppuc.model.Menu@2321f065, com.melawai.ppuc.model.Menu@92d88e8, com.melawai.ppuc.model.Menu@c773c0d, com.melawai.ppuc.model.Menu@68077fdf, com.melawai.ppuc.model.Menu@445c3ed4, com.melawai.ppuc.model.Menu@420f35c]','ADM','2014-07-09 13:29:18'),(207,631,'itemId','gd',NULL,'ADM','2014-07-09 13:29:19'),(207,632,'role',NULL,'com.melawai.ppuc.model.Role@23c9aedb','ADM','2014-07-09 13:29:19'),(208,633,'menus','[com.melawai.ppuc.model.Menu@7831d572, com.melawai.ppuc.model.Menu@fb46d04, com.melawai.ppuc.model.Menu@e6129aa, com.melawai.ppuc.model.Menu@cf65f46, com.melawai.ppuc.model.Menu@2e8f350, com.melawai.ppuc.model.Menu@bbe0aae, com.melawai.ppuc.model.Menu@69af2b87, com.melawai.ppuc.model.Menu@6b52e245, com.melawai.ppuc.model.Menu@e85570f, com.melawai.ppuc.model.Menu@5416a13b, com.melawai.ppuc.model.Menu@19b3539e, com.melawai.ppuc.model.Menu@4c36b740, com.melawai.ppuc.model.Menu@1c38f1d7, com.melawai.ppuc.model.Menu@5e8d68ba, com.melawai.ppuc.model.Menu@15d6bbd5, com.melawai.ppuc.model.Menu@44c5a826, com.melawai.ppuc.model.Menu@ca27a10, com.melawai.ppuc.model.Menu@7efbb6ea, com.melawai.ppuc.model.Menu@3fa5fdc7, com.melawai.ppuc.model.Menu@2ab83476, com.melawai.ppuc.model.Menu@237c44e3, com.melawai.ppuc.model.Menu@338ea548, com.melawai.ppuc.model.Menu@5a053469, com.melawai.ppuc.model.Menu@bb407ab, com.melawai.ppuc.model.Menu@5863718d, com.melawai.ppuc.model.Menu@68b40d50, com.melawai.ppuc.model.Menu@4d634e4, com.melawai.ppuc.model.Menu@b2a6737, com.melawai.ppuc.model.Menu@cde519b]','[]','ADM','2014-07-09 13:29:29'),(208,634,'role',NULL,'com.melawai.ppuc.model.Role@2d1389bf','ADM','2014-07-09 13:29:29'),(208,635,'itemId','gd',NULL,'ADM','2014-07-09 13:29:29'),(209,636,'menus','[com.melawai.ppuc.model.Menu@68ee743a]',NULL,'ADM','2014-07-09 13:30:25'),(209,637,'group_nm','test',NULL,'ADM','2014-07-09 13:30:26'),(209,638,'serialVersionUID','-8946818516215179670',NULL,'ADM','2014-07-09 13:30:26'),(209,639,'role','com.melawai.ppuc.model.Role@6bc06bf9',NULL,'ADM','2014-07-09 13:30:26'),(209,640,'jam_create','',NULL,'ADM','2014-07-09 13:30:26'),(209,641,'user_create','',NULL,'ADM','2014-07-09 13:30:26'),(209,642,'id_role','1',NULL,'ADM','2014-07-09 13:30:26'),(209,643,'group_kd','gd',NULL,'ADM','2014-07-09 13:30:26'),(210,644,'itemId','ADMIN',NULL,'ADM','2014-07-09 13:34:56'),(210,645,'menus','[com.melawai.ppuc.model.Menu@62f32dc9, com.melawai.ppuc.model.Menu@4b4319df, com.melawai.ppuc.model.Menu@2257a613, com.melawai.ppuc.model.Menu@28583b02, com.melawai.ppuc.model.Menu@388a14ff, com.melawai.ppuc.model.Menu@71688e58, com.melawai.ppuc.model.Menu@7596d93, com.melawai.ppuc.model.Menu@28d1aa, com.melawai.ppuc.model.Menu@77dceffa, com.melawai.ppuc.model.Menu@4a0f4390, com.melawai.ppuc.model.Menu@301cc8ec, com.melawai.ppuc.model.Menu@31cb16a1, com.melawai.ppuc.model.Menu@c3abd91, com.melawai.ppuc.model.Menu@647380dc, com.melawai.ppuc.model.Menu@5b111f09, com.melawai.ppuc.model.Menu@410cb694, com.melawai.ppuc.model.Menu@29a6cfe9, com.melawai.ppuc.model.Menu@69bdf5c, com.melawai.ppuc.model.Menu@63651467, com.melawai.ppuc.model.Menu@11ea92c, com.melawai.ppuc.model.Menu@3f48847, com.melawai.ppuc.model.Menu@2b1eef58, com.melawai.ppuc.model.Menu@7bf39085, com.melawai.ppuc.model.Menu@33954b56, com.melawai.ppuc.model.Menu@e85158f, com.melawai.ppuc.model.Menu@434a66bb, com.melawai.ppuc.model.Menu@499ea180, com.melawai.ppuc.model.Menu@4d7d0042, com.melawai.ppuc.model.Menu@499c14cc]','[com.melawai.ppuc.model.Menu@261982d5, com.melawai.ppuc.model.Menu@54dc817d, com.melawai.ppuc.model.Menu@58b96111, com.melawai.ppuc.model.Menu@7293d098, com.melawai.ppuc.model.Menu@4663e5ec, com.melawai.ppuc.model.Menu@488c0d0e, com.melawai.ppuc.model.Menu@62ad3757, com.melawai.ppuc.model.Menu@5a0c635d, com.melawai.ppuc.model.Menu@634f9cda, com.melawai.ppuc.model.Menu@7fc2df25, com.melawai.ppuc.model.Menu@52c837aa, com.melawai.ppuc.model.Menu@56bea45b, com.melawai.ppuc.model.Menu@7e187eda, com.melawai.ppuc.model.Menu@7a305ae2, com.melawai.ppuc.model.Menu@7c9eea19, com.melawai.ppuc.model.Menu@251b4f3a, com.melawai.ppuc.model.Menu@23ee7bde, com.melawai.ppuc.model.Menu@7e063e3f, com.melawai.ppuc.model.Menu@4be0dabc, com.melawai.ppuc.model.Menu@1738938f, com.melawai.ppuc.model.Menu@35f9d32, com.melawai.ppuc.model.Menu@7a493f58, com.melawai.ppuc.model.Menu@5edde920, com.melawai.ppuc.model.Menu@3af46488, com.melawai.ppuc.model.Menu@2f43af5, com.melawai.ppuc.model.Menu@704aac56, com.melawai.ppuc.model.Menu@36787bb2, com.melawai.ppuc.model.Menu@2000ff0e, com.melawai.ppuc.model.Menu@2169008c]','ADM','2014-07-09 13:34:56'),(210,646,'role',NULL,'com.melawai.ppuc.model.Role@1ffdf973','ADM','2014-07-09 13:34:56'),(211,647,'itemId','ADMIN',NULL,'ADM','2014-07-09 13:35:06'),(211,648,'role',NULL,'com.melawai.ppuc.model.Role@1d7470eb','ADM','2014-07-09 13:35:06'),(211,649,'menus','[com.melawai.ppuc.model.Menu@34856595, com.melawai.ppuc.model.Menu@21d03423, com.melawai.ppuc.model.Menu@6c0ef72c, com.melawai.ppuc.model.Menu@4a85a120, com.melawai.ppuc.model.Menu@b156219, com.melawai.ppuc.model.Menu@28db5cfe, com.melawai.ppuc.model.Menu@59b63ca6, com.melawai.ppuc.model.Menu@4b47e44d, com.melawai.ppuc.model.Menu@5cdda1d7, com.melawai.ppuc.model.Menu@5aac59e2, com.melawai.ppuc.model.Menu@694132ef, com.melawai.ppuc.model.Menu@37771ee5, com.melawai.ppuc.model.Menu@6d8d68d5, com.melawai.ppuc.model.Menu@5eddb023, com.melawai.ppuc.model.Menu@2c56fa7d, com.melawai.ppuc.model.Menu@2573b49, com.melawai.ppuc.model.Menu@2cf536d2, com.melawai.ppuc.model.Menu@16e22c0d, com.melawai.ppuc.model.Menu@5aba1537, com.melawai.ppuc.model.Menu@6ec6fc69, com.melawai.ppuc.model.Menu@49e58a50, com.melawai.ppuc.model.Menu@7cdbb016, com.melawai.ppuc.model.Menu@3b05b464, com.melawai.ppuc.model.Menu@73862f81, com.melawai.ppuc.model.Menu@6e98f967, com.melawai.ppuc.model.Menu@7d1e11ea, com.melawai.ppuc.model.Menu@412a59d2, com.melawai.ppuc.model.Menu@436f0b6a, com.melawai.ppuc.model.Menu@2f567ebc]','[com.melawai.ppuc.model.Menu@579a80eb, com.melawai.ppuc.model.Menu@6085f13b, com.melawai.ppuc.model.Menu@79a089fe, com.melawai.ppuc.model.Menu@1ac3c114, com.melawai.ppuc.model.Menu@29b915c6, com.melawai.ppuc.model.Menu@36448f90, com.melawai.ppuc.model.Menu@4f2d52c5, com.melawai.ppuc.model.Menu@28952c1f, com.melawai.ppuc.model.Menu@5983bc09, com.melawai.ppuc.model.Menu@57ae20c8, com.melawai.ppuc.model.Menu@68ea5770, com.melawai.ppuc.model.Menu@7112afdf, com.melawai.ppuc.model.Menu@1d89378, com.melawai.ppuc.model.Menu@31b9ac3a, com.melawai.ppuc.model.Menu@14da2d57, com.melawai.ppuc.model.Menu@7fd6b572, com.melawai.ppuc.model.Menu@692290ef, com.melawai.ppuc.model.Menu@5c5970d5, com.melawai.ppuc.model.Menu@73fedf50, com.melawai.ppuc.model.Menu@61f738ae, com.melawai.ppuc.model.Menu@2daa59c1, com.melawai.ppuc.model.Menu@aeaa553, com.melawai.ppuc.model.Menu@330bf1be, com.melawai.ppuc.model.Menu@552c0b20, com.melawai.ppuc.model.Menu@3e8e8d8f, com.melawai.ppuc.model.Menu@efc75f, com.melawai.ppuc.model.Menu@7e0a2e73, com.melawai.ppuc.model.Menu@4e6fc0aa]','ADM','2014-07-09 13:35:06'),(212,650,'role',NULL,'com.melawai.ppuc.model.Role@28c74645','ADM','2014-07-09 13:36:37'),(212,651,'itemId','ADMIN',NULL,'ADM','2014-07-09 13:36:38'),(212,652,'menus','[com.melawai.ppuc.model.Menu@1c8ff1ae, com.melawai.ppuc.model.Menu@2e43e528, com.melawai.ppuc.model.Menu@6b79bcd2, com.melawai.ppuc.model.Menu@55bb61e, com.melawai.ppuc.model.Menu@48116e51, com.melawai.ppuc.model.Menu@7061accd, com.melawai.ppuc.model.Menu@1c98005f, com.melawai.ppuc.model.Menu@3f406ba3, com.melawai.ppuc.model.Menu@1e5abac6, com.melawai.ppuc.model.Menu@569c2cbb, com.melawai.ppuc.model.Menu@273cd569, com.melawai.ppuc.model.Menu@adef49f, com.melawai.ppuc.model.Menu@338df84c, com.melawai.ppuc.model.Menu@2da86005, com.melawai.ppuc.model.Menu@937ffae, com.melawai.ppuc.model.Menu@3b72fd3c, com.melawai.ppuc.model.Menu@7a4f8ca1, com.melawai.ppuc.model.Menu@7c99dcc2, com.melawai.ppuc.model.Menu@596c8476, com.melawai.ppuc.model.Menu@636c8cd7, com.melawai.ppuc.model.Menu@6b92aa3f, com.melawai.ppuc.model.Menu@69e74545, com.melawai.ppuc.model.Menu@4e74eb54, com.melawai.ppuc.model.Menu@5e060009, com.melawai.ppuc.model.Menu@5bec7f18, com.melawai.ppuc.model.Menu@38c2fce, com.melawai.ppuc.model.Menu@68967f33, com.melawai.ppuc.model.Menu@707922e9, com.melawai.ppuc.model.Menu@20e328af]','[com.melawai.ppuc.model.Menu@21840a07, com.melawai.ppuc.model.Menu@63ae62c1, com.melawai.ppuc.model.Menu@4dd5a007, com.melawai.ppuc.model.Menu@3fd537d, com.melawai.ppuc.model.Menu@6c743596, com.melawai.ppuc.model.Menu@416a427a, com.melawai.ppuc.model.Menu@27327923, com.melawai.ppuc.model.Menu@62aefcef, com.melawai.ppuc.model.Menu@4e5fe186, com.melawai.ppuc.model.Menu@78cf4c9c, com.melawai.ppuc.model.Menu@71aed3ba, com.melawai.ppuc.model.Menu@cce92a5, com.melawai.ppuc.model.Menu@4dfd9534, com.melawai.ppuc.model.Menu@434eb0ec, com.melawai.ppuc.model.Menu@63417879, com.melawai.ppuc.model.Menu@5f4c82d7, com.melawai.ppuc.model.Menu@18222222, com.melawai.ppuc.model.Menu@68eef28e, com.melawai.ppuc.model.Menu@1f767e74, com.melawai.ppuc.model.Menu@1c6801cf, com.melawai.ppuc.model.Menu@704ecb9a, com.melawai.ppuc.model.Menu@45192510, com.melawai.ppuc.model.Menu@75d060e0, com.melawai.ppuc.model.Menu@43884e8d, com.melawai.ppuc.model.Menu@29dd2d9e, com.melawai.ppuc.model.Menu@77ddf58a, com.melawai.ppuc.model.Menu@d237481, com.melawai.ppuc.model.Menu@12b5c7e4, com.melawai.ppuc.model.Menu@5c565354]','ADM','2014-07-09 13:36:38'),(213,653,'menus','[com.melawai.ppuc.model.Menu@452fa723, com.melawai.ppuc.model.Menu@3b860e51, com.melawai.ppuc.model.Menu@5e16065e, com.melawai.ppuc.model.Menu@77fc3593, com.melawai.ppuc.model.Menu@4f21836f, com.melawai.ppuc.model.Menu@213c14ff, com.melawai.ppuc.model.Menu@6f868264, com.melawai.ppuc.model.Menu@67daac6f, com.melawai.ppuc.model.Menu@456ee0ad, com.melawai.ppuc.model.Menu@7260a177, com.melawai.ppuc.model.Menu@2608c64b, com.melawai.ppuc.model.Menu@a12796f, com.melawai.ppuc.model.Menu@42e26b93, com.melawai.ppuc.model.Menu@1f04a233, com.melawai.ppuc.model.Menu@6934d22d, com.melawai.ppuc.model.Menu@ad2be51, com.melawai.ppuc.model.Menu@11ccbd64, com.melawai.ppuc.model.Menu@18a5f95d, com.melawai.ppuc.model.Menu@3896554f, com.melawai.ppuc.model.Menu@15bad28f, com.melawai.ppuc.model.Menu@1c55b56e, com.melawai.ppuc.model.Menu@3efa574a, com.melawai.ppuc.model.Menu@2578db93, com.melawai.ppuc.model.Menu@2197a31d, com.melawai.ppuc.model.Menu@6a55d625, com.melawai.ppuc.model.Menu@295e4dad, com.melawai.ppuc.model.Menu@6e3dae12, com.melawai.ppuc.model.Menu@136f5849, com.melawai.ppuc.model.Menu@730d2a96]','[com.melawai.ppuc.model.Menu@656718dc, com.melawai.ppuc.model.Menu@53914786, com.melawai.ppuc.model.Menu@66eed946, com.melawai.ppuc.model.Menu@4afab775, com.melawai.ppuc.model.Menu@122288c8, com.melawai.ppuc.model.Menu@19420bc5, com.melawai.ppuc.model.Menu@3f12bf77, com.melawai.ppuc.model.Menu@67d83dfa, com.melawai.ppuc.model.Menu@25cd1d59, com.melawai.ppuc.model.Menu@3d41d172, com.melawai.ppuc.model.Menu@2c1db2c9, com.melawai.ppuc.model.Menu@51c4c0bf, com.melawai.ppuc.model.Menu@4c4a6589, com.melawai.ppuc.model.Menu@28502c80, com.melawai.ppuc.model.Menu@27999c2d, com.melawai.ppuc.model.Menu@55dc62aa, com.melawai.ppuc.model.Menu@77d1b2ef, com.melawai.ppuc.model.Menu@683aa65d, com.melawai.ppuc.model.Menu@62805920, com.melawai.ppuc.model.Menu@585b7665, com.melawai.ppuc.model.Menu@5cba0e34, com.melawai.ppuc.model.Menu@3afaa77b, com.melawai.ppuc.model.Menu@1e099a7d, com.melawai.ppuc.model.Menu@87d93f3, com.melawai.ppuc.model.Menu@6c7c3adf, com.melawai.ppuc.model.Menu@4ffd3e1d, com.melawai.ppuc.model.Menu@7af90af1, com.melawai.ppuc.model.Menu@743d9349]','ADM','2014-07-09 13:37:04'),(213,654,'role',NULL,'com.melawai.ppuc.model.Role@74034d3b','ADM','2014-07-09 13:37:04'),(213,655,'itemId','ADMIN',NULL,'ADM','2014-07-09 13:37:04'),(215,656,'itemId','ADMIN',NULL,'ADM','2014-07-09 14:12:59'),(215,657,'role',NULL,'com.melawai.ppuc.model.Role@49634e54','ADM','2014-07-09 14:12:59'),(215,658,'menus','[com.melawai.ppuc.model.Menu@5a1b02b0, com.melawai.ppuc.model.Menu@234d9f07, com.melawai.ppuc.model.Menu@3d0396ac, com.melawai.ppuc.model.Menu@3699157f, com.melawai.ppuc.model.Menu@7c5260d9, com.melawai.ppuc.model.Menu@4548653, com.melawai.ppuc.model.Menu@493eb55d, com.melawai.ppuc.model.Menu@33f5123c, com.melawai.ppuc.model.Menu@1e803bca, com.melawai.ppuc.model.Menu@74d55a6a, com.melawai.ppuc.model.Menu@63232112, com.melawai.ppuc.model.Menu@17505797, com.melawai.ppuc.model.Menu@1ba68676, com.melawai.ppuc.model.Menu@51c5bb28, com.melawai.ppuc.model.Menu@c826909, com.melawai.ppuc.model.Menu@45bdd449, com.melawai.ppuc.model.Menu@31bc2864, com.melawai.ppuc.model.Menu@37ffd6be, com.melawai.ppuc.model.Menu@7d6b70aa, com.melawai.ppuc.model.Menu@18b1e93a, com.melawai.ppuc.model.Menu@4846eb80, com.melawai.ppuc.model.Menu@280f4592, com.melawai.ppuc.model.Menu@2a088ca, com.melawai.ppuc.model.Menu@7974871e, com.melawai.ppuc.model.Menu@515300dd, com.melawai.ppuc.model.Menu@205dd6e1, com.melawai.ppuc.model.Menu@70c65e60, com.melawai.ppuc.model.Menu@6f5e2a77, com.melawai.ppuc.model.Menu@f3623c0]','[com.melawai.ppuc.model.Menu@2b651a0d, com.melawai.ppuc.model.Menu@7a9161bc, com.melawai.ppuc.model.Menu@5ea7bc81, com.melawai.ppuc.model.Menu@5648e9b3, com.melawai.ppuc.model.Menu@4ce71306, com.melawai.ppuc.model.Menu@5692165b, com.melawai.ppuc.model.Menu@10f9dcc4, com.melawai.ppuc.model.Menu@40cd091, com.melawai.ppuc.model.Menu@6550e1aa, com.melawai.ppuc.model.Menu@210f93dd, com.melawai.ppuc.model.Menu@5b4a320, com.melawai.ppuc.model.Menu@16418ccd, com.melawai.ppuc.model.Menu@2982f225, com.melawai.ppuc.model.Menu@53e7706d, com.melawai.ppuc.model.Menu@7f862f23, com.melawai.ppuc.model.Menu@4280e43d, com.melawai.ppuc.model.Menu@1c0882e7, com.melawai.ppuc.model.Menu@72ca1a11, com.melawai.ppuc.model.Menu@32758cf7, com.melawai.ppuc.model.Menu@4379c602, com.melawai.ppuc.model.Menu@6fb6cfe9, com.melawai.ppuc.model.Menu@4b0c034b, com.melawai.ppuc.model.Menu@1ac576b, com.melawai.ppuc.model.Menu@599f32a8, com.melawai.ppuc.model.Menu@62b6e18f, com.melawai.ppuc.model.Menu@5492a5ea, com.melawai.ppuc.model.Menu@67cad106, com.melawai.ppuc.model.Menu@34611626, com.melawai.ppuc.model.Menu@51f533a7]','ADM','2014-07-09 14:12:59'),(220,659,'menus','[com.melawai.ppuc.model.Menu@58e58b51, com.melawai.ppuc.model.Menu@461da06f, com.melawai.ppuc.model.Menu@430ffb5f, com.melawai.ppuc.model.Menu@4e403c5e, com.melawai.ppuc.model.Menu@5b3b6774, com.melawai.ppuc.model.Menu@19051f77, com.melawai.ppuc.model.Menu@1f54ca76, com.melawai.ppuc.model.Menu@77b8190b, com.melawai.ppuc.model.Menu@57745e94, com.melawai.ppuc.model.Menu@18ed7367, com.melawai.ppuc.model.Menu@d337bfa, com.melawai.ppuc.model.Menu@2f1060db, com.melawai.ppuc.model.Menu@5c46e200, com.melawai.ppuc.model.Menu@319f9d54, com.melawai.ppuc.model.Menu@66120f3f, com.melawai.ppuc.model.Menu@2ba72573, com.melawai.ppuc.model.Menu@6a8bb768, com.melawai.ppuc.model.Menu@7ab6437d, com.melawai.ppuc.model.Menu@5409027b, com.melawai.ppuc.model.Menu@1b820457, com.melawai.ppuc.model.Menu@74eaf9dc, com.melawai.ppuc.model.Menu@6eba167b, com.melawai.ppuc.model.Menu@7b1a1e07, com.melawai.ppuc.model.Menu@6fa99cb4, com.melawai.ppuc.model.Menu@686f26b1, com.melawai.ppuc.model.Menu@59556407, com.melawai.ppuc.model.Menu@751a3562, com.melawai.ppuc.model.Menu@ba6f0fe, com.melawai.ppuc.model.Menu@7d17b9ab]','[]','ADM','2014-07-09 16:47:39'),(220,660,'itemId','USER',NULL,'ADM','2014-07-09 16:47:40'),(220,661,'role',NULL,'com.melawai.ppuc.model.Role@7cfba0bc','ADM','2014-07-09 16:47:40'),(226,662,'role','com.melawai.ppuc.model.Role@34f8af6a',NULL,'ADM','2014-07-09 22:15:15'),(226,663,'jam_create','',NULL,'ADM','2014-07-09 22:15:16'),(226,664,'serialVersionUID','-8946818516215179670',NULL,'ADM','2014-07-09 22:15:16'),(226,665,'group_nm','USER',NULL,'ADM','2014-07-09 22:15:16'),(226,666,'menus','[com.melawai.ppuc.model.Menu@7f6ee4e0, com.melawai.ppuc.model.Menu@49746f7c, com.melawai.ppuc.model.Menu@7b435b90, com.melawai.ppuc.model.Menu@3308a29, com.melawai.ppuc.model.Menu@67be8b61, com.melawai.ppuc.model.Menu@eb0c27d, com.melawai.ppuc.model.Menu@76b0a413, com.melawai.ppuc.model.Menu@46e41845, com.melawai.ppuc.model.Menu@28f57e5f, com.melawai.ppuc.model.Menu@d3ba3fb, com.melawai.ppuc.model.Menu@468aba86, com.melawai.ppuc.model.Menu@3ddbd798, com.melawai.ppuc.model.Menu@2c275be2, com.melawai.ppuc.model.Menu@4c016913, com.melawai.ppuc.model.Menu@70998560, com.melawai.ppuc.model.Menu@6f039b60, com.melawai.ppuc.model.Menu@55cdf490, com.melawai.ppuc.model.Menu@447345f2, com.melawai.ppuc.model.Menu@6bf535f9, com.melawai.ppuc.model.Menu@2faaa5ce, com.melawai.ppuc.model.Menu@6c6b8fd4, com.melawai.ppuc.model.Menu@9afdee8, com.melawai.ppuc.model.Menu@7953564f, com.melawai.ppuc.model.Menu@4e479ac3, com.melawai.ppuc.model.Menu@3f01a05b, com.melawai.ppuc.model.Menu@3c6d6ae, com.melawai.ppuc.model.Menu@73383b71, com.melawai.ppuc.model.Menu@70c6b2cf, com.melawai.ppuc.model.Menu@5056de1]',NULL,'ADM','2014-07-09 22:15:16'),(226,667,'id_role','2',NULL,'ADM','2014-07-09 22:15:16'),(226,668,'group_kd','USER',NULL,'ADM','2014-07-09 22:15:16'),(226,669,'user_create','',NULL,'ADM','2014-07-09 22:15:16'),(227,670,'jam_create','',NULL,'ADM','2014-07-09 22:16:50'),(227,671,'id_role','2',NULL,'ADM','2014-07-09 22:16:50'),(227,672,'user_create','',NULL,'ADM','2014-07-09 22:16:50'),(227,673,'group_nm','USER',NULL,'ADM','2014-07-09 22:16:50'),(227,674,'group_kd','USR',NULL,'ADM','2014-07-09 22:16:50'),(227,675,'serialVersionUID','-8946818516215179670',NULL,'ADM','2014-07-09 22:16:50'),(227,676,'menus','[com.melawai.ppuc.model.Menu@74c1f0b8, com.melawai.ppuc.model.Menu@68a2fbea, com.melawai.ppuc.model.Menu@2448f551, com.melawai.ppuc.model.Menu@31e1ab73, com.melawai.ppuc.model.Menu@56bf249a, com.melawai.ppuc.model.Menu@1efc26f4, com.melawai.ppuc.model.Menu@3c616d10, com.melawai.ppuc.model.Menu@203f5468, com.melawai.ppuc.model.Menu@1dbe8862, com.melawai.ppuc.model.Menu@47ebe92f, com.melawai.ppuc.model.Menu@511a318c, com.melawai.ppuc.model.Menu@eab07ed, com.melawai.ppuc.model.Menu@7e956020, com.melawai.ppuc.model.Menu@da15cd, com.melawai.ppuc.model.Menu@6dcd462a, com.melawai.ppuc.model.Menu@3fb9a7b7, com.melawai.ppuc.model.Menu@35b20010, com.melawai.ppuc.model.Menu@392235fa, com.melawai.ppuc.model.Menu@7505cd63, com.melawai.ppuc.model.Menu@4fef5a9a, com.melawai.ppuc.model.Menu@6b255d75, com.melawai.ppuc.model.Menu@621ae347, com.melawai.ppuc.model.Menu@533f7ca2, com.melawai.ppuc.model.Menu@6d0f9a60, com.melawai.ppuc.model.Menu@1b604c90, com.melawai.ppuc.model.Menu@4f428ffa, com.melawai.ppuc.model.Menu@1afa8ebd, com.melawai.ppuc.model.Menu@37b12821, com.melawai.ppuc.model.Menu@4fc1ab17]',NULL,'ADM','2014-07-09 22:16:50'),(228,677,'menus','[com.melawai.ppuc.model.Menu@68310b8d, com.melawai.ppuc.model.Menu@6bed8b6b, com.melawai.ppuc.model.Menu@385c5528, com.melawai.ppuc.model.Menu@35dad200, com.melawai.ppuc.model.Menu@3111199f, com.melawai.ppuc.model.Menu@59a92ee3, com.melawai.ppuc.model.Menu@72456711, com.melawai.ppuc.model.Menu@2a71c7b3, com.melawai.ppuc.model.Menu@13edce8a, com.melawai.ppuc.model.Menu@6192d03e, com.melawai.ppuc.model.Menu@6da5c87d, com.melawai.ppuc.model.Menu@1f0dbec8, com.melawai.ppuc.model.Menu@3f684c65, com.melawai.ppuc.model.Menu@586f9b68, com.melawai.ppuc.model.Menu@743f634, com.melawai.ppuc.model.Menu@7ed8d3a5, com.melawai.ppuc.model.Menu@4d2f36b2, com.melawai.ppuc.model.Menu@56b007b4, com.melawai.ppuc.model.Menu@3ec9e6e2, com.melawai.ppuc.model.Menu@39551fa2, com.melawai.ppuc.model.Menu@38fe216, com.melawai.ppuc.model.Menu@5b44142d, com.melawai.ppuc.model.Menu@5288c52a, com.melawai.ppuc.model.Menu@114872bb, com.melawai.ppuc.model.Menu@2b645bda, com.melawai.ppuc.model.Menu@49ca5b77, com.melawai.ppuc.model.Menu@43b077a, com.melawai.ppuc.model.Menu@3f67dcc1, com.melawai.ppuc.model.Menu@3bce236c]','[com.melawai.ppuc.model.Menu@5a77a620, com.melawai.ppuc.model.Menu@653bad46, com.melawai.ppuc.model.Menu@30ed0296, com.melawai.ppuc.model.Menu@1844def2, com.melawai.ppuc.model.Menu@5188ee50, com.melawai.ppuc.model.Menu@74d5f001, com.melawai.ppuc.model.Menu@9800d94, com.melawai.ppuc.model.Menu@35fb746b, com.melawai.ppuc.model.Menu@f98387d, com.melawai.ppuc.model.Menu@52a49e8a, com.melawai.ppuc.model.Menu@35a4a069, com.melawai.ppuc.model.Menu@4b1f6902, com.melawai.ppuc.model.Menu@7b2728d5, com.melawai.ppuc.model.Menu@47e9f71d, com.melawai.ppuc.model.Menu@515eb5cd, com.melawai.ppuc.model.Menu@20f1cf77, com.melawai.ppuc.model.Menu@636b9e86, com.melawai.ppuc.model.Menu@2e74a268, com.melawai.ppuc.model.Menu@6b5271ab, com.melawai.ppuc.model.Menu@71a4c898, com.melawai.ppuc.model.Menu@796dad71, com.melawai.ppuc.model.Menu@f9613ff, com.melawai.ppuc.model.Menu@45fad257, com.melawai.ppuc.model.Menu@560771a5, com.melawai.ppuc.model.Menu@2b633c3, com.melawai.ppuc.model.Menu@8004899, com.melawai.ppuc.model.Menu@4a9e38e9, com.melawai.ppuc.model.Menu@59aa6f44, com.melawai.ppuc.model.Menu@446f0759]','ADM','2014-07-09 22:23:16'),(228,678,'itemId','USR',NULL,'ADM','2014-07-09 22:23:16'),(228,679,'role',NULL,'com.melawai.ppuc.model.Role@21a3dae2','ADM','2014-07-09 22:23:17'),(229,680,'itemId','USR',NULL,'ADM','2014-07-09 22:23:58'),(229,681,'menus','[com.melawai.ppuc.model.Menu@550ba5b5, com.melawai.ppuc.model.Menu@6fac39b1, com.melawai.ppuc.model.Menu@13ffccbe, com.melawai.ppuc.model.Menu@7edad833, com.melawai.ppuc.model.Menu@51a83d55, com.melawai.ppuc.model.Menu@7c52c154, com.melawai.ppuc.model.Menu@1d12b190, com.melawai.ppuc.model.Menu@364773d9, com.melawai.ppuc.model.Menu@d06c766, com.melawai.ppuc.model.Menu@380d0038, com.melawai.ppuc.model.Menu@5d897947, com.melawai.ppuc.model.Menu@6c79544a, com.melawai.ppuc.model.Menu@118cfde9, com.melawai.ppuc.model.Menu@3f71d4ff, com.melawai.ppuc.model.Menu@4a56d1e3, com.melawai.ppuc.model.Menu@9edb436, com.melawai.ppuc.model.Menu@54d24a51, com.melawai.ppuc.model.Menu@3a0d3758, com.melawai.ppuc.model.Menu@39ac902e, com.melawai.ppuc.model.Menu@702dd996, com.melawai.ppuc.model.Menu@52284063, com.melawai.ppuc.model.Menu@509b4db8, com.melawai.ppuc.model.Menu@40b9460, com.melawai.ppuc.model.Menu@143a2cb3, com.melawai.ppuc.model.Menu@774ca624, com.melawai.ppuc.model.Menu@492fc2ac, com.melawai.ppuc.model.Menu@5e95cfbd, com.melawai.ppuc.model.Menu@3d79b1ce, com.melawai.ppuc.model.Menu@886aea]','[com.melawai.ppuc.model.Menu@7c2328eb, com.melawai.ppuc.model.Menu@68539bf8, com.melawai.ppuc.model.Menu@4924ec4a, com.melawai.ppuc.model.Menu@171513ca, com.melawai.ppuc.model.Menu@68c63e9c, com.melawai.ppuc.model.Menu@2f38a981, com.melawai.ppuc.model.Menu@31006c5f, com.melawai.ppuc.model.Menu@12caea1b, com.melawai.ppuc.model.Menu@47cf9c40, com.melawai.ppuc.model.Menu@f1b5295, com.melawai.ppuc.model.Menu@4acabbf2, com.melawai.ppuc.model.Menu@43f9333a, com.melawai.ppuc.model.Menu@1d9247b3, com.melawai.ppuc.model.Menu@6ea147ef, com.melawai.ppuc.model.Menu@1e79d4a7, com.melawai.ppuc.model.Menu@50782f92, com.melawai.ppuc.model.Menu@27b4384, com.melawai.ppuc.model.Menu@6a8d9061, com.melawai.ppuc.model.Menu@740206ed, com.melawai.ppuc.model.Menu@3114ed1b, com.melawai.ppuc.model.Menu@54daa0c9, com.melawai.ppuc.model.Menu@5d7217a4, com.melawai.ppuc.model.Menu@6d6e3fe9, com.melawai.ppuc.model.Menu@6126121f, com.melawai.ppuc.model.Menu@a6fe30d, com.melawai.ppuc.model.Menu@37a36fd5, com.melawai.ppuc.model.Menu@4b03257c, com.melawai.ppuc.model.Menu@3b92165d]','ADM','2014-07-09 22:23:58'),(229,682,'role',NULL,'com.melawai.ppuc.model.Role@25c28ea7','ADM','2014-07-09 22:23:58'),(230,683,'itemId','OKA/Z/ACC/HOL',NULL,'ADM','2014-07-09 22:42:07'),(230,684,'tgl_create',NULL,'Sun Jul 06 23:27:40 ICT 2014','ADM','2014-07-09 22:42:07'),(230,685,'user_create',NULL,'ADM','ADM','2014-07-09 22:42:07'),(230,686,'jam_update','22:42',NULL,'ADM','2014-07-09 22:42:07'),(230,687,'tgl_update','Wed Jul 09 22:42:06 ICT 2014',NULL,'ADM','2014-07-09 22:42:08'),(230,688,'upload','com.melawai.ppuc.model.Upload@21f39b86','com.melawai.ppuc.model.Upload@678bd4','ADM','2014-07-09 22:42:08'),(230,689,'user_update','ADM',NULL,'ADM','2014-07-09 22:42:08'),(230,690,'f_tutup','1',NULL,'ADM','2014-07-09 22:42:08'),(231,691,'user_create','ADM',NULL,'ADM','2014-07-09 22:45:44'),(231,692,'lok_kd','Sip a',NULL,'ADM','2014-07-09 22:45:46'),(231,693,'propinsi','Bali',NULL,'ADM','2014-07-09 22:45:46'),(231,694,'lok_nm','Sip DAH',NULL,'ADM','2014-07-09 22:45:46'),(231,695,'divisi_kd','HOL',NULL,'ADM','2014-07-09 22:45:46'),(231,696,'serialVersionUID','7503294106098421175',NULL,'ADM','2014-07-09 22:45:46'),(231,697,'email','ada@asa.ada',NULL,'ADM','2014-07-09 22:45:46'),(231,698,'dept_kd','Z',NULL,'ADM','2014-07-09 22:45:46'),(231,699,'subdiv_kd','ACC',NULL,'ADM','2014-07-09 22:45:46'),(231,700,'kota','DENPASAR',NULL,'ADM','2014-07-09 22:45:46'),(231,701,'upload','com.melawai.ppuc.model.Upload@1f15227c',NULL,'ADM','2014-07-09 22:45:46'),(231,702,'tgl_create','Wed Jul 09 22:45:44 ICT 2014',NULL,'ADM','2014-07-09 22:45:46'),(232,703,'jam_update','22:45',NULL,'ADM','2014-07-09 22:45:56'),(232,704,'f_tutup','1',NULL,'ADM','2014-07-09 22:45:56'),(232,705,'tgl_create',NULL,'Wed Jul 09 22:45:44 ICT 2014','ADM','2014-07-09 22:45:56'),(232,706,'itemId','Sip a/Z/ACC/HOL',NULL,'ADM','2014-07-09 22:45:56'),(232,707,'tgl_update','Wed Jul 09 22:45:56 ICT 2014',NULL,'ADM','2014-07-09 22:45:56'),(232,708,'upload','com.melawai.ppuc.model.Upload@5b458bbc','com.melawai.ppuc.model.Upload@32d91274','ADM','2014-07-09 22:45:56'),(232,709,'user_create',NULL,'ADM','ADM','2014-07-09 22:45:56'),(232,710,'user_update','ADM',NULL,'ADM','2014-07-09 22:45:56'),(233,711,'tgl_update','Wed Jul 09 22:46:26 ICT 2014','Sun Jun 29 05:15:09 ICT 2014','ADM','2014-07-09 22:46:27'),(233,712,'upload','com.melawai.ppuc.model.Upload@215f90fe','com.melawai.ppuc.model.Upload@92824d0','ADM','2014-07-09 22:46:27'),(233,713,'itemId','HOL',NULL,'ADM','2014-07-09 22:46:27'),(233,714,'jam_update','22:46','05:15','ADM','2014-07-09 22:46:27'),(234,715,'upload','com.melawai.ppuc.model.Upload@2464e310','com.melawai.ppuc.model.Upload@5b77421a','ADM','2014-07-09 22:47:35'),(234,716,'tgl_create',NULL,'Mon Jun 30 22:10:15 ICT 2014','ADM','2014-07-09 22:47:35'),(234,717,'tgl_update','Wed Jul 09 22:47:35 ICT 2014',NULL,'ADM','2014-07-09 22:47:35'),(234,718,'itemId','1//OPT',NULL,'ADM','2014-07-09 22:47:35'),(234,719,'user_create',NULL,'ADM','ADM','2014-07-09 22:47:35'),(234,720,'dept_nm','OUTLET OPTIK EYEVOLUTION','OUTLET OPTIK melawai2','ADM','2014-07-09 22:47:35'),(234,721,'jam_update','22:47',NULL,'ADM','2014-07-09 22:47:35'),(234,722,'user_update','ADM',NULL,'ADM','2014-07-09 22:47:35'),(235,723,'tgl_create',NULL,'Sun Jul 06 23:30:20 ICT 2014','ADM','2014-07-09 23:05:03'),(235,724,'itemId','OIK/ACC/HOL',NULL,'ADM','2014-07-09 23:05:03'),(235,725,'tgl_update','Wed Jul 09 23:05:03 ICT 2014',NULL,'ADM','2014-07-09 23:05:03'),(235,726,'dept_nm','OK DE','OK DEH','ADM','2014-07-09 23:05:03'),(235,727,'user_update','ADM',NULL,'ADM','2014-07-09 23:05:03'),(235,728,'user_create',NULL,'ADM','ADM','2014-07-09 23:05:03'),(235,729,'jam_update','23:05',NULL,'ADM','2014-07-09 23:05:03'),(235,730,'upload','com.melawai.ppuc.model.Upload@69597c46','com.melawai.ppuc.model.Upload@71ee0db2','ADM','2014-07-09 23:05:03'),(243,731,'dept_kd','OIK',NULL,'ADM','2014-07-12 09:51:29'),(243,732,'jam_create','',NULL,'ADM','2014-07-12 09:51:29'),(243,733,'user_create','ADM',NULL,'ADM','2014-07-12 09:51:29'),(243,734,'divisi_kd','HOL',NULL,'ADM','2014-07-12 09:51:29'),(243,735,'tgl_create','Sat Jul 12 09:51:28 ICT 2014',NULL,'ADM','2014-07-12 09:51:29'),(243,736,'user_id','ADM',NULL,'ADM','2014-07-12 09:51:29'),(243,737,'subdiv_kd','ACC',NULL,'ADM','2014-07-12 09:51:29'),(244,738,'tgl_create',NULL,'Sat Jul 12 09:51:28 ICT 2014','ADM','2014-07-12 09:56:28'),(244,739,'user_create','','ADM','ADM','2014-07-12 09:56:28'),(246,740,'lok_kd','OKA',NULL,'ADM','2014-07-12 10:27:52'),(246,741,'divisi_kd','HOL',NULL,'ADM','2014-07-12 10:27:52'),(246,742,'user_create','ADM',NULL,'ADM','2014-07-12 10:27:52'),(246,743,'tgl_create','Sat Jul 12 10:27:52 ICT 2014',NULL,'ADM','2014-07-12 10:27:52'),(246,744,'dept_kd','Z',NULL,'ADM','2014-07-12 10:27:52'),(246,745,'user_id','ADM',NULL,'ADM','2014-07-12 10:27:52'),(246,746,'jam_create','',NULL,'ADM','2014-07-12 10:27:56'),(246,747,'subdiv_kd','ACC',NULL,'ADM','2014-07-12 10:27:56'),(249,748,'lok_kd','Sip a','OKA','ADM','2014-07-12 11:36:30'),(249,749,'itemId','2',NULL,'ADM','2014-07-12 11:36:30'),(250,750,'lok_kd','Sip a',NULL,'ADM','2014-07-12 11:37:34'),(250,751,'dept_kd','Z','OIK','ADM','2014-07-12 11:37:36'),(250,752,'itemId','1',NULL,'ADM','2014-07-12 11:37:36'),(254,753,'itemId','2',NULL,'ADM','2014-07-12 12:50:54'),(259,754,'itemId','1',NULL,'ADM','2014-07-12 13:24:51'),(259,755,'lok_kd','OKA','Sip a','ADM','2014-07-12 13:24:51'),(260,756,'lok_kd',' ',NULL,'ADM','2014-07-12 13:30:02'),(260,757,'subdiv_kd','ACC',NULL,'ADM','2014-07-12 13:30:02'),(260,758,'dept_kd','Z',NULL,'ADM','2014-07-12 13:30:02'),(260,759,'divisi_kd','HOL',NULL,'ADM','2014-07-12 13:30:02'),(260,760,'user_id','ADM',NULL,'ADM','2014-07-12 13:30:02'),(260,761,'jam_create','',NULL,'ADM','2014-07-12 13:30:02'),(260,762,'tgl_create','Sat Jul 12 13:30:02 ICT 2014',NULL,'ADM','2014-07-12 13:30:02'),(260,763,'user_create','ADM',NULL,'ADM','2014-07-12 13:30:02'),(261,764,'tgl_create','Sat Jul 12 13:37:57 ICT 2014',NULL,'ADM','2014-07-12 13:37:57'),(261,765,'user_id','ADM',NULL,'ADM','2014-07-12 13:37:57'),(261,766,'subdiv_kd','MLW',NULL,'ADM','2014-07-12 13:37:57'),(261,767,'lok_kd',' ',NULL,'ADM','2014-07-12 13:37:57'),(261,768,'user_create','ADM',NULL,'ADM','2014-07-12 13:37:57'),(261,769,'dept_kd','3',NULL,'ADM','2014-07-12 13:37:57'),(261,770,'jam_create','',NULL,'ADM','2014-07-12 13:37:57'),(261,771,'divisi_kd','OPT',NULL,'ADM','2014-07-12 13:37:57'),(262,772,'urut','6',NULL,'ADM','2014-07-12 13:40:57'),(262,773,'serialVersionUID','2062694598974079208',NULL,'ADM','2014-07-12 13:40:57'),(262,774,'user_create','ADM',NULL,'ADM','2014-07-12 13:40:57'),(262,775,'link','',NULL,'ADM','2014-07-12 13:40:57'),(262,776,'level','1',NULL,'ADM','2014-07-12 13:40:57'),(262,777,'nama','TEst',NULL,'ADM','2014-07-12 13:40:57'),(262,778,'tgl_create','Sat Jul 12 13:40:57 ICT 2014',NULL,'ADM','2014-07-12 13:40:57'),(262,779,'f_aktif','1',NULL,'ADM','2014-07-12 13:40:57'),(262,780,'parent','1',NULL,'ADM','2014-07-12 13:40:57'),(263,781,'lok_kd',' ',NULL,'ADM','2014-07-12 13:43:16'),(263,782,'subdiv_kd','MLW',NULL,'ADM','2014-07-12 13:43:16'),(263,783,'tgl_create','Sat Jul 12 13:42:47 ICT 2014',NULL,'ADM','2014-07-12 13:43:16'),(263,784,'jam_create','',NULL,'ADM','2014-07-12 13:43:16'),(263,785,'dept_kd','1',NULL,'ADM','2014-07-12 13:43:16'),(263,786,'user_create','ADM',NULL,'ADM','2014-07-12 13:43:16'),(263,787,'user_id','ADM',NULL,'ADM','2014-07-12 13:43:16'),(263,788,'divisi_kd','OPT',NULL,'ADM','2014-07-12 13:43:16'),(265,789,'lok_kd',' ',NULL,'ADM','2014-07-12 13:54:40'),(265,790,'subdiv_kd','ACC',NULL,'ADM','2014-07-12 13:54:40'),(265,791,'tgl_create','Sat Jul 12 13:54:19 ICT 2014',NULL,'ADM','2014-07-12 13:54:40'),(265,792,'jam_create','',NULL,'ADM','2014-07-12 13:54:40'),(265,793,'user_create','ADM',NULL,'ADM','2014-07-12 13:54:40'),(265,794,'user_id','ADM',NULL,'ADM','2014-07-12 13:54:40'),(265,795,'dept_kd','OIK',NULL,'ADM','2014-07-12 13:54:40'),(265,796,'divisi_kd','HOL',NULL,'ADM','2014-07-12 13:54:40'),(266,797,'user_update','ADM',NULL,'ADM','2014-07-12 13:57:06'),(266,798,'jam_update','13:57',NULL,'ADM','2014-07-12 13:57:06'),(266,799,'tgl_update','Sat Jul 12 13:57:06 ICT 2014',NULL,'ADM','2014-07-12 13:57:06'),(266,800,'upload','com.melawai.ppuc.model.Upload@628093a2','com.melawai.ppuc.model.Upload@675ca333','ADM','2014-07-12 13:57:06'),(266,801,'itemId','OPT',NULL,'ADM','2014-07-12 13:57:06'),(266,802,'divisi_nm','OPTIKS','OPTIK','ADM','2014-07-12 13:57:06'),(267,803,'divisi_nm','OPTIK','OPTIKS','ADM','2014-07-12 13:57:13'),(267,804,'tgl_update','Sat Jul 12 13:57:13 ICT 2014','Sat Jul 12 13:57:06 ICT 2014','ADM','2014-07-12 13:57:13'),(267,805,'itemId','OPT',NULL,'ADM','2014-07-12 13:57:13'),(267,806,'upload','com.melawai.ppuc.model.Upload@6c22d853','com.melawai.ppuc.model.Upload@63a8689b','ADM','2014-07-12 13:57:13'),(268,807,'upload','com.melawai.ppuc.model.Upload@621579f9','com.melawai.ppuc.model.Upload@6ffafcbd','ADM','2014-07-12 13:57:28'),(268,808,'jam_update','13:57',NULL,'ADM','2014-07-12 13:57:28'),(268,809,'tgl_update','Sat Jul 12 13:57:28 ICT 2014',NULL,'ADM','2014-07-12 13:57:28'),(268,810,'itemId','ACC/HOL',NULL,'ADM','2014-07-12 13:57:28'),(268,811,'tgl_create',NULL,'Sun Jun 29 11:35:29 ICT 2014','ADM','2014-07-12 13:57:28'),(268,812,'user_update','ADM',NULL,'ADM','2014-07-12 13:57:28'),(268,813,'user_create',NULL,'ADM','ADM','2014-07-12 13:57:28'),(269,814,'itemId','1/MLW/OPT',NULL,'ADM','2014-07-12 13:58:05'),(269,815,'user_create',NULL,'ADM','ADM','2014-07-12 13:58:05'),(269,816,'tgl_create',NULL,'Mon Jun 30 22:10:15 ICT 2014','ADM','2014-07-12 13:58:05'),(269,817,'upload','com.melawai.ppuc.model.Upload@4eff3c8d','com.melawai.ppuc.model.Upload@56e0757f','ADM','2014-07-12 13:58:05'),(269,818,'tgl_update','Sat Jul 12 13:58:05 ICT 2014','Wed Jul 09 22:47:35 ICT 2014','ADM','2014-07-12 13:58:05'),(269,819,'jam_update','13:58','22:47','ADM','2014-07-12 13:58:05'),(270,820,'upload','com.melawai.ppuc.model.Upload@43cf6aec','com.melawai.ppuc.model.Upload@6674cebb','ADM','2014-07-12 13:58:19'),(270,821,'tgl_create',NULL,'Mon Jun 30 22:11:25 ICT 2014','ADM','2014-07-12 13:58:19'),(270,822,'tgl_update','Sat Jul 12 13:58:18 ICT 2014',NULL,'ADM','2014-07-12 13:58:19'),(270,823,'jam_update','13:58','','ADM','2014-07-12 13:58:19'),(270,824,'dept_nm','OUTLET OPTIK EYEVOLUTION','TEST','ADM','2014-07-12 13:58:19'),(270,825,'itemId','1/MLW/OPT',NULL,'ADM','2014-07-12 13:58:19'),(270,826,'user_create',NULL,'ADM','ADM','2014-07-12 13:58:19'),(270,827,'user_update','ADM','','ADM','2014-07-12 13:58:19'),(271,828,'tgl_create',NULL,'Mon Jun 30 22:10:15 ICT 2014','ADM','2014-07-12 13:58:28'),(271,829,'itemId','2/MLW/OPT',NULL,'ADM','2014-07-12 13:58:28'),(271,830,'user_create',NULL,'ADM','ADM','2014-07-12 13:58:28'),(271,831,'tgl_update','Sat Jul 12 13:58:28 ICT 2014','Sat Jul 12 13:58:05 ICT 2014','ADM','2014-07-12 13:58:28'),(271,832,'upload','com.melawai.ppuc.model.Upload@3986ebfe','com.melawai.ppuc.model.Upload@48ef8e33','ADM','2014-07-12 13:58:28'),(272,833,'tgl_update','Sat Jul 12 13:58:56 ICT 2014','Sat Jul 12 13:58:28 ICT 2014','ADM','2014-07-12 13:58:56'),(272,834,'itemId','3/MLW/OPT',NULL,'ADM','2014-07-12 13:58:56'),(272,835,'user_create',NULL,'ADM','ADM','2014-07-12 13:58:56'),(272,836,'dept_nm','OK','OUTLET OPTIK EYEVOLUTION','ADM','2014-07-12 13:58:56'),(272,837,'tgl_create',NULL,'Mon Jun 30 22:10:15 ICT 2014','ADM','2014-07-12 13:58:56'),(272,838,'upload','com.melawai.ppuc.model.Upload@63849e3','com.melawai.ppuc.model.Upload@597ad945','ADM','2014-07-12 13:58:56'),(274,839,'user_create','ADM',NULL,'ADM','2014-07-12 14:12:56'),(274,840,'divisi_kd','OPT',NULL,'ADM','2014-07-12 14:12:56'),(274,841,'tgl_create','Sat Jul 12 14:12:55 ICT 2014',NULL,'ADM','2014-07-12 14:12:56'),(274,842,'dept_kd','4',NULL,'ADM','2014-07-12 14:12:56'),(274,843,'subdiv_kd','MLW',NULL,'ADM','2014-07-12 14:12:56'),(274,844,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 14:12:56'),(274,845,'upload','com.melawai.ppuc.model.Upload@6e29982d',NULL,'ADM','2014-07-12 14:12:56'),(274,846,'itemId','3/MLW/OPT',NULL,'ADM','2014-07-12 14:12:56'),(274,847,'dept_nm','OK',NULL,'ADM','2014-07-12 14:12:56'),(275,848,'lok_kd',' ',NULL,'ADM','2014-07-12 14:13:43'),(275,849,'itemId','3',NULL,'ADM','2014-07-12 14:13:43'),(276,850,'lok_kd',' ',NULL,'ADM','2014-07-12 14:14:46'),(276,851,'dept_kd','4','3','ADM','2014-07-12 14:14:49'),(276,852,'itemId','4',NULL,'ADM','2014-07-12 14:14:49'),(277,853,'subdiv_kd','MLW',NULL,'ADM','2014-07-12 14:20:20'),(277,854,'user_create','ADM',NULL,'ADM','2014-07-12 14:20:20'),(277,855,'user_update','ADM',NULL,'ADM','2014-07-12 14:20:20'),(277,856,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 14:20:20'),(277,857,'dept_kd','2',NULL,'ADM','2014-07-12 14:20:20'),(277,858,'tgl_create','Mon Jun 30 22:11:25 ICT 2014',NULL,'ADM','2014-07-12 14:20:20'),(277,859,'upload','com.melawai.ppuc.model.Upload@5b968ded',NULL,'ADM','2014-07-12 14:20:20'),(277,860,'jam_update','13:58',NULL,'ADM','2014-07-12 14:20:20'),(277,861,'tgl_update','Sat Jul 12 13:58:18 ICT 2014',NULL,'ADM','2014-07-12 14:20:20'),(277,862,'divisi_kd','OPT',NULL,'ADM','2014-07-12 14:20:21'),(277,863,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-07-12 14:20:21'),(279,864,'itemId','1/EYE/OPT',NULL,'ADM','2014-07-12 14:55:55'),(279,865,'tgl_create',NULL,'Mon Jun 30 22:10:15 ICT 2014','ADM','2014-07-12 14:55:55'),(279,866,'tgl_update','Sat Jul 12 14:55:55 ICT 2014',NULL,'ADM','2014-07-12 14:55:55'),(279,867,'upload','com.melawai.ppuc.model.Upload@380037c5','com.melawai.ppuc.model.Upload@164d833c','ADM','2014-07-12 14:55:55'),(279,868,'user_create',NULL,'ADM','ADM','2014-07-12 14:55:55'),(279,869,'jam_update','14:55',NULL,'ADM','2014-07-12 14:55:55'),(279,870,'user_update','ADM',NULL,'ADM','2014-07-12 14:55:55'),(280,871,'tgl_create',NULL,'Mon Jun 30 22:10:15 ICT 2014','ADM','2014-07-12 14:56:28'),(280,872,'user_create',NULL,'ADM','ADM','2014-07-12 14:56:28'),(280,873,'upload','com.melawai.ppuc.model.Upload@40a3723','com.melawai.ppuc.model.Upload@3aa9dce7','ADM','2014-07-12 14:56:28'),(280,874,'itemId','1/EYE/OPT',NULL,'ADM','2014-07-12 14:56:28'),(280,875,'jam_update','14:56','14:55','ADM','2014-07-12 14:56:28'),(280,876,'tgl_update','Sat Jul 12 14:56:28 ICT 2014','Sat Jul 12 14:55:55 ICT 2014','ADM','2014-07-12 14:56:28'),(280,877,'dept_nm','OUTLET OPTIK EYE','OUTLET OPTIK EYEVOLUTION','ADM','2014-07-12 14:56:28'),(281,878,'upload','com.melawai.ppuc.model.Upload@7279e99a',NULL,'ADM','2014-07-12 15:06:35'),(281,879,'divisi_nm','ABC DIVISION',NULL,'ADM','2014-07-12 15:06:35'),(281,880,'user_create','ADM',NULL,'ADM','2014-07-12 15:06:35'),(281,881,'tgl_create','Sat Jul 12 15:06:35 ICT 2014',NULL,'ADM','2014-07-12 15:06:35'),(281,882,'divisi_kd','ABC',NULL,'ADM','2014-07-12 15:06:35'),(281,883,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 15:06:35'),(282,884,'tgl_create',NULL,'Sat Jul 12 15:06:35 ICT 2014','ADM','2014-07-12 15:08:58'),(282,885,'tgl_update','Sat Jul 12 15:08:58 ICT 2014',NULL,'ADM','2014-07-12 15:08:58'),(282,886,'itemId','ABC',NULL,'ADM','2014-07-12 15:08:58'),(282,887,'user_update','ADM',NULL,'ADM','2014-07-12 15:08:58'),(282,888,'upload','com.melawai.ppuc.model.Upload@91b8a43','com.melawai.ppuc.model.Upload@6f123960','ADM','2014-07-12 15:08:58'),(282,889,'user_create',NULL,'ADM','ADM','2014-07-12 15:08:58'),(282,890,'jam_update','15:08',NULL,'ADM','2014-07-12 15:08:58'),(284,891,'user_create',NULL,'ADM','ADM','2014-07-12 15:09:28'),(284,892,'tgl_update','Sat Jul 12 15:09:28 ICT 2014','Wed Jul 09 22:46:26 ICT 2014','ADM','2014-07-12 15:09:28'),(284,893,'upload','com.melawai.ppuc.model.Upload@569d1e97','com.melawai.ppuc.model.Upload@654377ed','ADM','2014-07-12 15:09:28'),(284,894,'jam_update','15:09','22:46','ADM','2014-07-12 15:09:28'),(285,895,'jam_update','15:09','13:57','ADM','2014-07-12 15:09:28'),(285,896,'user_create',NULL,'ADM','ADM','2014-07-12 15:09:28'),(285,897,'upload','com.melawai.ppuc.model.Upload@17e095c3','com.melawai.ppuc.model.Upload@19884174','ADM','2014-07-12 15:09:28'),(285,898,'tgl_update','Sat Jul 12 15:09:28 ICT 2014','Sat Jul 12 13:57:13 ICT 2014','ADM','2014-07-12 15:09:28'),(287,899,'upload','com.melawai.ppuc.model.Upload@7b6ca0b3','com.melawai.ppuc.model.Upload@18aa8713','ADM','2014-07-12 15:12:12'),(287,900,'tgl_update','Sat Jul 12 15:12:12 ICT 2014','Sat Jul 12 15:09:28 ICT 2014','ADM','2014-07-12 15:12:12'),(287,901,'jam_update','15:12','15:09','ADM','2014-07-12 15:12:12'),(287,902,'user_create',NULL,'ADM','ADM','2014-07-12 15:12:12'),(287,903,'divisi_nm','OPTIK','HOLDING','ADM','2014-07-12 15:12:12'),(288,904,'tgl_update','Sat Jul 12 15:12:12 ICT 2014','Sat Jul 12 15:09:28 ICT 2014','ADM','2014-07-12 15:12:12'),(288,905,'upload','com.melawai.ppuc.model.Upload@12001b8f','com.melawai.ppuc.model.Upload@45115284','ADM','2014-07-12 15:12:12'),(288,906,'jam_update','15:12','15:09','ADM','2014-07-12 15:12:12'),(288,907,'user_create',NULL,'ADM','ADM','2014-07-12 15:12:13'),(289,908,'tgl_update','Sat Jul 12 15:12:13 ICT 2014','Sat Jul 12 15:08:58 ICT 2014','ADM','2014-07-12 15:12:13'),(289,909,'user_create',NULL,'ADM','ADM','2014-07-12 15:12:13'),(289,910,'divisi_nm','ABC D','ABC DIVISION','ADM','2014-07-12 15:12:13'),(289,911,'upload','com.melawai.ppuc.model.Upload@207123cd','com.melawai.ppuc.model.Upload@63e5785e','ADM','2014-07-12 15:12:13'),(289,912,'jam_update','15:12','15:08','ADM','2014-07-12 15:12:13'),(290,913,'user_create','ADM',NULL,'ADM','2014-07-12 15:12:13'),(290,914,'upload','com.melawai.ppuc.model.Upload@348022a0',NULL,'ADM','2014-07-12 15:12:13'),(290,915,'divisi_nm','IT DIVISION',NULL,'ADM','2014-07-12 15:12:13'),(290,916,'tgl_create','Sat Jul 12 15:12:13 ICT 2014',NULL,'ADM','2014-07-12 15:12:13'),(290,917,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 15:12:13'),(290,918,'divisi_kd','ITD',NULL,'ADM','2014-07-12 15:12:13'),(329,967,'upload','com.melawai.ppuc.model.Upload@3c5ffd92','com.melawai.ppuc.model.Upload@42009b35','ADM','2014-07-12 17:34:50'),(329,968,'user_create',NULL,'ADM','ADM','2014-07-12 17:34:50'),(329,969,'jam_update','17:34','15:12','ADM','2014-07-12 17:34:50'),(329,970,'tgl_update','Sat Jul 12 17:34:49 ICT 2014','Sat Jul 12 15:12:12 ICT 2014','ADM','2014-07-12 17:34:50'),(330,971,'upload','com.melawai.ppuc.model.Upload@6af0982d','com.melawai.ppuc.model.Upload@5996e934','ADM','2014-07-12 17:34:50'),(330,972,'user_create',NULL,'ADM','ADM','2014-07-12 17:34:50'),(330,973,'tgl_update','Sat Jul 12 17:34:50 ICT 2014','Sat Jul 12 15:12:12 ICT 2014','ADM','2014-07-12 17:34:50'),(330,974,'jam_update','17:34','15:12','ADM','2014-07-12 17:34:50'),(331,975,'upload','com.melawai.ppuc.model.Upload@526a31cc','com.melawai.ppuc.model.Upload@39eb7259','ADM','2014-07-12 17:34:50'),(331,976,'user_create',NULL,'ADM','ADM','2014-07-12 17:34:50'),(331,977,'jam_update','17:34','15:12','ADM','2014-07-12 17:34:50'),(331,978,'tgl_update','Sat Jul 12 17:34:50 ICT 2014','Sat Jul 12 15:12:13 ICT 2014','ADM','2014-07-12 17:34:50'),(332,979,'user_create','ADM',NULL,'ADM','2014-07-12 17:34:50'),(332,980,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 17:34:50'),(332,981,'upload','com.melawai.ppuc.model.Upload@3f840be0',NULL,'ADM','2014-07-12 17:34:50'),(332,982,'divisi_kd','HA',NULL,'ADM','2014-07-12 17:34:50'),(332,983,'tgl_create','Sat Jul 12 17:34:50 ICT 2014',NULL,'ADM','2014-07-12 17:34:50'),(332,984,'divisi_nm','IT DIVISION',NULL,'ADM','2014-07-12 17:34:50'),(333,985,'tgl_create','Sat Jul 12 17:34:50 ICT 2014',NULL,'ADM','2014-07-12 17:34:50'),(333,986,'user_create','ADM',NULL,'ADM','2014-07-12 17:34:50'),(333,987,'divisi_nm','ABC D',NULL,'ADM','2014-07-12 17:34:50'),(333,988,'upload','com.melawai.ppuc.model.Upload@58974d88',NULL,'ADM','2014-07-12 17:34:51'),(333,989,'divisi_kd','ISA',NULL,'ADM','2014-07-12 17:34:51'),(333,990,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 17:34:51'),(334,991,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 17:34:51'),(334,992,'user_create','ADM',NULL,'ADM','2014-07-12 17:34:51'),(334,993,'upload','com.melawai.ppuc.model.Upload@2f3bd47c',NULL,'ADM','2014-07-12 17:34:51'),(334,994,'divisi_nm','ASIK',NULL,'ADM','2014-07-12 17:34:51'),(334,995,'divisi_kd','PL',NULL,'ADM','2014-07-12 17:34:51'),(334,996,'tgl_create','Sat Jul 12 17:34:51 ICT 2014',NULL,'ADM','2014-07-12 17:34:51'),(338,997,'user_create',NULL,'ADM','ADM','2014-07-12 18:57:18'),(338,998,'tgl_create',NULL,'Sun Jun 29 11:35:29 ICT 2014','ADM','2014-07-12 18:57:18'),(338,999,'tgl_update','Sat Jul 12 18:57:18 ICT 2014','Sat Jul 12 13:57:28 ICT 2014','ADM','2014-07-12 18:57:18'),(338,1000,'jam_update','18:57','13:57','ADM','2014-07-12 18:57:18'),(338,1001,'upload','com.melawai.ppuc.model.Upload@41c1aa78','com.melawai.ppuc.model.Upload@119ece02','ADM','2014-07-12 18:57:18'),(338,1002,'itemId','ACC/HOL',NULL,'ADM','2014-07-12 18:57:18'),(340,1003,'divisi_kd','knk',NULL,'ADM','2014-07-12 19:10:43'),(340,1004,'divisi_nm','',NULL,'ADM','2014-07-12 19:10:43'),(340,1005,'upload','com.melawai.ppuc.model.Upload@5f931238',NULL,'ADM','2014-07-12 19:10:43'),(340,1006,'user_create','ADM',NULL,'ADM','2014-07-12 19:10:43'),(340,1007,'tgl_create','Sat Jul 12 19:10:43 ICT 2014',NULL,'ADM','2014-07-12 19:10:43'),(340,1008,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 19:10:43'),(344,1009,'user_create','ADM',NULL,'ADM','2014-07-12 19:26:38'),(344,1010,'tgl_create','Sat Jul 12 19:26:38 ICT 2014',NULL,'ADM','2014-07-12 19:26:38'),(344,1011,'subdiv_kd','ada',NULL,'ADM','2014-07-12 19:26:38'),(344,1012,'upload','com.melawai.ppuc.model.Upload@74c423a4',NULL,'ADM','2014-07-12 19:26:38'),(344,1013,'divisi_kd','ABC',NULL,'ADM','2014-07-12 19:26:38'),(344,1014,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-12 19:26:38'),(344,1015,'subdiv_nm','ada',NULL,'ADM','2014-07-12 19:26:38'),(345,1016,'user_update','ADM',NULL,'ADM','2014-07-12 19:26:53'),(345,1017,'itemId','ada/ABC',NULL,'ADM','2014-07-12 19:26:53'),(345,1018,'tgl_create',NULL,'Sat Jul 12 19:26:38 ICT 2014','ADM','2014-07-12 19:26:53'),(345,1019,'user_create',NULL,'ADM','ADM','2014-07-12 19:26:53'),(345,1020,'jam_update','19:26',NULL,'ADM','2014-07-12 19:26:53'),(345,1021,'tgl_update','Sat Jul 12 19:26:53 ICT 2014',NULL,'ADM','2014-07-12 19:26:53'),(345,1022,'subdiv_nm','ada ada aja','ada','ADM','2014-07-12 19:26:53'),(345,1023,'upload','com.melawai.ppuc.model.Upload@74a6e503','com.melawai.ppuc.model.Upload@790563c9','ADM','2014-07-12 19:26:53'),(347,1024,'jam_update','19:30','18:57','ADM','2014-07-12 19:30:24'),(347,1025,'tgl_update','Sat Jul 12 19:30:24 ICT 2014','Sat Jul 12 18:57:18 ICT 2014','ADM','2014-07-12 19:30:24'),(347,1026,'upload','com.melawai.ppuc.model.Upload@2fa600ed','com.melawai.ppuc.model.Upload@3b86e00b','ADM','2014-07-12 19:30:24'),(347,1027,'user_create',NULL,'ADM','ADM','2014-07-12 19:30:24'),(348,1028,'user_update','ADM',NULL,'ADM','2014-07-12 19:30:24'),(348,1029,'tgl_update','Sat Jul 12 19:30:24 ICT 2014',NULL,'ADM','2014-07-12 19:30:24'),(348,1030,'upload','com.melawai.ppuc.model.Upload@41ee1d41','com.melawai.ppuc.model.Upload@77c2b837','ADM','2014-07-12 19:30:24'),(348,1031,'subdiv_nm','OPTIK EYEVOLUTION','OPTIK REVOLUTION','ADM','2014-07-12 19:30:24'),(348,1032,'jam_update','19:30',NULL,'ADM','2014-07-12 19:30:24'),(348,1033,'user_create',NULL,'ADM','ADM','2014-07-12 19:30:24'),(349,1034,'user_create',NULL,'ADM','ADM','2014-07-12 19:30:24'),(349,1035,'jam_update','19:30',NULL,'ADM','2014-07-12 19:30:24'),(349,1036,'tgl_update','Sat Jul 12 19:30:24 ICT 2014',NULL,'ADM','2014-07-12 19:30:24'),(349,1037,'upload','com.melawai.ppuc.model.Upload@4e5d9e9c','com.melawai.ppuc.model.Upload@64583bf5','ADM','2014-07-12 19:30:24'),(349,1038,'subdiv_nm','OPTIK melawai2','OPTIK MELAWAI','ADM','2014-07-12 19:30:24'),(349,1039,'user_update','ADM',NULL,'ADM','2014-07-12 19:30:24'),(354,1040,'tgl_update','Sat Jul 12 19:38:25 ICT 2014','Sat Jul 12 17:34:49 ICT 2014','ADM','2014-07-12 19:38:25'),(354,1041,'jam_update','19:38','17:34','ADM','2014-07-12 19:38:25'),(354,1042,'upload','com.melawai.ppuc.model.Upload@722dd396','com.melawai.ppuc.model.Upload@1e9e576a','ADM','2014-07-12 19:38:25'),(354,1043,'user_create',NULL,'ADM','ADM','2014-07-12 19:38:25'),(355,1044,'jam_update','19:38','17:34','ADM','2014-07-12 19:38:25'),(355,1045,'upload','com.melawai.ppuc.model.Upload@450849c5','com.melawai.ppuc.model.Upload@232347eb','ADM','2014-07-12 19:38:25'),(355,1046,'tgl_update','Sat Jul 12 19:38:25 ICT 2014','Sat Jul 12 17:34:50 ICT 2014','ADM','2014-07-12 19:38:25'),(355,1047,'user_create',NULL,'ADM','ADM','2014-07-12 19:38:25'),(356,1048,'tgl_update','Sat Jul 12 19:38:25 ICT 2014','Sat Jul 12 17:34:50 ICT 2014','ADM','2014-07-12 19:38:25'),(356,1049,'jam_update','19:38','17:34','ADM','2014-07-12 19:38:25'),(356,1050,'upload','com.melawai.ppuc.model.Upload@42d2adca','com.melawai.ppuc.model.Upload@158fcb0c','ADM','2014-07-12 19:38:25'),(356,1051,'user_create',NULL,'ADM','ADM','2014-07-12 19:38:25'),(357,1052,'user_update','ADM',NULL,'ADM','2014-07-12 19:38:25'),(357,1053,'user_create',NULL,'ADM','ADM','2014-07-12 19:38:25'),(357,1054,'jam_update','19:38',NULL,'ADM','2014-07-12 19:38:25'),(357,1055,'upload','com.melawai.ppuc.model.Upload@2db831c5','com.melawai.ppuc.model.Upload@17cb93f6','ADM','2014-07-12 19:38:25'),(357,1056,'tgl_update','Sat Jul 12 19:38:25 ICT 2014',NULL,'ADM','2014-07-12 19:38:25'),(358,1057,'jam_update','19:38',NULL,'ADM','2014-07-12 19:38:25'),(358,1058,'upload','com.melawai.ppuc.model.Upload@13b6c9d5','com.melawai.ppuc.model.Upload@457cc90f','ADM','2014-07-12 19:38:25'),(358,1059,'user_create',NULL,'ADM','ADM','2014-07-12 19:38:25'),(358,1060,'tgl_update','Sat Jul 12 19:38:25 ICT 2014',NULL,'ADM','2014-07-12 19:38:25'),(358,1061,'user_update','ADM',NULL,'ADM','2014-07-12 19:38:25'),(359,1062,'user_create',NULL,'ADM','ADM','2014-07-12 19:38:25'),(359,1063,'upload','com.melawai.ppuc.model.Upload@63c82e1c','com.melawai.ppuc.model.Upload@6b4b6371','ADM','2014-07-12 19:38:25'),(359,1064,'user_update','ADM',NULL,'ADM','2014-07-12 19:38:25'),(359,1065,'jam_update','19:38',NULL,'ADM','2014-07-12 19:38:25'),(359,1066,'tgl_update','Sat Jul 12 19:38:25 ICT 2014',NULL,'ADM','2014-07-12 19:38:25'),(360,1067,'tgl_create','Sat Jul 12 19:54:05 ICT 2014',NULL,'ADM','2014-07-12 19:54:05'),(360,1068,'upload','com.melawai.ppuc.model.Upload@5d16a60d',NULL,'ADM','2014-07-12 19:54:05'),(360,1069,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 19:54:05'),(360,1070,'divisi_nm','',NULL,'ADM','2014-07-12 19:54:05'),(360,1071,'divisi_kd','ad',NULL,'ADM','2014-07-12 19:54:05'),(360,1072,'user_create','ADM',NULL,'ADM','2014-07-12 19:54:05'),(361,1073,'subdiv_nm','',NULL,'ADM','2014-07-12 19:55:46'),(361,1074,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-12 19:55:46'),(361,1075,'tgl_create','Sat Jul 12 19:55:46 ICT 2014',NULL,'ADM','2014-07-12 19:55:46'),(361,1076,'divisi_kd','ISA',NULL,'ADM','2014-07-12 19:55:46'),(361,1077,'subdiv_kd','ad',NULL,'ADM','2014-07-12 19:55:46'),(361,1078,'upload','com.melawai.ppuc.model.Upload@417579dd',NULL,'ADM','2014-07-12 19:55:46'),(361,1079,'user_create','ADM',NULL,'ADM','2014-07-12 19:55:47'),(362,1080,'tgl_create','Sat Jul 12 19:56:30 ICT 2014',NULL,'ADM','2014-07-12 19:56:30'),(362,1081,'divisi_kd','ada',NULL,'ADM','2014-07-12 19:56:30'),(362,1082,'divisi_nm','aadadaaad',NULL,'ADM','2014-07-12 19:56:30'),(362,1083,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 19:56:30'),(362,1084,'upload','com.melawai.ppuc.model.Upload@57d5a77c',NULL,'ADM','2014-07-12 19:56:30'),(362,1085,'user_create','ADM',NULL,'ADM','2014-07-12 19:56:30'),(371,1086,'jam_update','20:21','19:38','ADM','2014-07-12 20:21:30'),(371,1087,'tgl_update','Sat Jul 12 20:21:30 ICT 2014','Sat Jul 12 19:38:25 ICT 2014','ADM','2014-07-12 20:21:30'),(371,1088,'upload','com.melawai.ppuc.model.Upload@2d5a55b1','com.melawai.ppuc.model.Upload@5afeeba','ADM','2014-07-12 20:21:30'),(371,1089,'user_create',NULL,'ADM','ADM','2014-07-12 20:21:30'),(371,1090,'divisi_nm','ACC','OPTIK','ADM','2014-07-12 20:21:30'),(372,1091,'user_create',NULL,'ADM','ADM','2014-07-12 20:21:30'),(372,1092,'jam_update','20:21','19:38','ADM','2014-07-12 20:21:30'),(372,1093,'tgl_update','Sat Jul 12 20:21:30 ICT 2014','Sat Jul 12 19:38:25 ICT 2014','ADM','2014-07-12 20:21:30'),(372,1094,'upload','com.melawai.ppuc.model.Upload@34b8a74a','com.melawai.ppuc.model.Upload@46ef0650','ADM','2014-07-12 20:21:30'),(372,1095,'divisi_nm','EYE','OPTIK','ADM','2014-07-12 20:21:30'),(373,1096,'user_create',NULL,'ADM','ADM','2014-07-12 20:21:30'),(373,1097,'upload','com.melawai.ppuc.model.Upload@11af7aa6','com.melawai.ppuc.model.Upload@179d315c','ADM','2014-07-12 20:21:30'),(373,1098,'divisi_nm','MLW','EYE','ADM','2014-07-12 20:21:30'),(374,1099,'user_create','ADM',NULL,'ADM','2014-07-12 20:21:31'),(374,1100,'divisi_kd','OKA',NULL,'ADM','2014-07-12 20:21:31'),(374,1101,'divisi_nm','adad',NULL,'ADM','2014-07-12 20:21:31'),(374,1102,'tgl_create','Sat Jul 12 20:21:31 ICT 2014',NULL,'ADM','2014-07-12 20:21:31'),(374,1103,'upload','com.melawai.ppuc.model.Upload@465501f7',NULL,'ADM','2014-07-12 20:21:31'),(374,1104,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 20:21:31'),(403,1105,'user_create',NULL,'ADM','ADM','2014-07-12 21:22:36'),(403,1106,'tgl_update','Sat Jul 12 21:22:36 ICT 2014','Sat Jul 12 19:30:24 ICT 2014','ADM','2014-07-12 21:22:36'),(403,1107,'tgl_create',NULL,'Sun Jun 29 11:35:29 ICT 2014','ADM','2014-07-12 21:22:36'),(403,1108,'itemId','ACC/HOL',NULL,'ADM','2014-07-12 21:22:36'),(403,1109,'upload','com.melawai.ppuc.model.Upload@76390ac3','com.melawai.ppuc.model.Upload@1af1c4d8','ADM','2014-07-12 21:22:36'),(403,1110,'jam_update','21:22','19:30','ADM','2014-07-12 21:22:36'),(404,1111,'divisi_kd','ABC',NULL,'ADM','2014-07-12 21:24:51'),(404,1112,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-12 21:24:51'),(404,1113,'subdiv_nm','adadad',NULL,'ADM','2014-07-12 21:24:51'),(404,1114,'tgl_create','Sat Jul 12 21:24:51 ICT 2014',NULL,'ADM','2014-07-12 21:24:51'),(404,1115,'user_create','ADM',NULL,'ADM','2014-07-12 21:24:51'),(404,1116,'subdiv_kd','adw',NULL,'ADM','2014-07-12 21:24:51'),(404,1117,'upload','com.melawai.ppuc.model.Upload@69584596',NULL,'ADM','2014-07-12 21:24:51'),(407,1118,'jam_update','21:25','21:22','ADM','2014-07-12 21:25:35'),(407,1119,'upload','com.melawai.ppuc.model.Upload@4e8e7f53','com.melawai.ppuc.model.Upload@6d494a6f','ADM','2014-07-12 21:25:35'),(407,1120,'user_create',NULL,'ADM','ADM','2014-07-12 21:25:35'),(407,1121,'tgl_update','Sat Jul 12 21:25:35 ICT 2014','Sat Jul 12 21:22:36 ICT 2014','ADM','2014-07-12 21:25:35'),(408,1122,'upload','com.melawai.ppuc.model.Upload@1d798e97','com.melawai.ppuc.model.Upload@17806a9f','ADM','2014-07-12 21:25:35'),(408,1123,'tgl_update','Sat Jul 12 21:25:35 ICT 2014','Sat Jul 12 19:30:24 ICT 2014','ADM','2014-07-12 21:25:35'),(408,1124,'jam_update','21:25','19:30','ADM','2014-07-12 21:25:35'),(408,1125,'user_create',NULL,'ADM','ADM','2014-07-12 21:25:35'),(409,1126,'upload','com.melawai.ppuc.model.Upload@12d2c996','com.melawai.ppuc.model.Upload@4cb0a081','ADM','2014-07-12 21:25:35'),(409,1127,'jam_update','21:25','19:30','ADM','2014-07-12 21:25:35'),(409,1128,'user_create',NULL,'ADM','ADM','2014-07-12 21:25:35'),(409,1129,'tgl_update','Sat Jul 12 21:25:35 ICT 2014','Sat Jul 12 19:30:24 ICT 2014','ADM','2014-07-12 21:25:35'),(410,1130,'subdiv_kd','ada',NULL,'ADM','2014-07-12 21:25:35'),(410,1131,'tgl_create','Sat Jul 12 21:25:35 ICT 2014',NULL,'ADM','2014-07-12 21:25:35'),(410,1132,'user_create','ADM',NULL,'ADM','2014-07-12 21:25:35'),(410,1133,'divisi_kd','OKA',NULL,'ADM','2014-07-12 21:25:35'),(410,1134,'upload','com.melawai.ppuc.model.Upload@407b11d6',NULL,'ADM','2014-07-12 21:25:35'),(410,1135,'subdiv_nm','adadadad',NULL,'ADM','2014-07-12 21:25:35'),(410,1136,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-12 21:25:35'),(413,1137,'tgl_create','Sat Jul 12 19:54:05 ICT 2014',NULL,'ADM','2014-07-12 21:28:13'),(413,1138,'upload','com.melawai.ppuc.model.Upload@4bc2df54',NULL,'ADM','2014-07-12 21:28:13'),(413,1139,'user_create','ADM',NULL,'ADM','2014-07-12 21:28:13'),(413,1140,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 21:28:13'),(413,1141,'divisi_nm','',NULL,'ADM','2014-07-12 21:28:13'),(413,1142,'divisi_kd','ad',NULL,'ADM','2014-07-12 21:28:13'),(414,1143,'tgl_create','Sat Jul 12 19:10:43 ICT 2014',NULL,'ADM','2014-07-12 21:28:18'),(414,1144,'upload','com.melawai.ppuc.model.Upload@3b885ac8',NULL,'ADM','2014-07-12 21:28:18'),(414,1145,'user_create','ADM',NULL,'ADM','2014-07-12 21:28:18'),(414,1146,'divisi_kd','knk',NULL,'ADM','2014-07-12 21:28:18'),(414,1147,'divisi_nm','',NULL,'ADM','2014-07-12 21:28:18'),(414,1148,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 21:28:18'),(415,1149,'upload','com.melawai.ppuc.model.Upload@5562648d',NULL,'ADM','2014-07-12 21:28:30'),(415,1150,'divisi_kd','ISA',NULL,'ADM','2014-07-12 21:28:30'),(415,1151,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-12 21:28:30'),(415,1152,'subdiv_nm','',NULL,'ADM','2014-07-12 21:28:30'),(415,1153,'tgl_create','Sat Jul 12 19:55:46 ICT 2014',NULL,'ADM','2014-07-12 21:28:30'),(415,1154,'subdiv_kd','ad',NULL,'ADM','2014-07-12 21:28:30'),(415,1155,'user_create','ADM',NULL,'ADM','2014-07-12 21:28:30'),(416,1156,'itemId','adw/ABC',NULL,'ADM','2014-07-12 21:29:34'),(416,1157,'jam_update','21:29',NULL,'ADM','2014-07-12 21:29:34'),(416,1158,'user_create',NULL,'ADM','ADM','2014-07-12 21:29:34'),(416,1159,'upload','com.melawai.ppuc.model.Upload@74ab727c','com.melawai.ppuc.model.Upload@23e966bb','ADM','2014-07-12 21:29:34'),(416,1160,'subdiv_nm','ABD','adadad','ADM','2014-07-12 21:29:34'),(416,1161,'tgl_create',NULL,'Sat Jul 12 21:24:51 ICT 2014','ADM','2014-07-12 21:29:34'),(416,1162,'tgl_update','Sat Jul 12 21:29:34 ICT 2014',NULL,'ADM','2014-07-12 21:29:34'),(416,1163,'user_update','ADM',NULL,'ADM','2014-07-12 21:29:34'),(417,1164,'itemId','adw/ABC',NULL,'ADM','2014-07-12 21:29:50'),(417,1165,'tgl_create',NULL,'Sat Jul 12 21:24:51 ICT 2014','ADM','2014-07-12 21:29:50'),(417,1166,'tgl_update','Sat Jul 12 21:29:50 ICT 2014','Sat Jul 12 21:29:34 ICT 2014','ADM','2014-07-12 21:29:50'),(417,1167,'user_create',NULL,'ADM','ADM','2014-07-12 21:29:50'),(417,1168,'upload','com.melawai.ppuc.model.Upload@261f6c77','com.melawai.ppuc.model.Upload@590a0c2e','ADM','2014-07-12 21:29:50'),(417,1169,'subdiv_nm','ABDC','ABD','ADM','2014-07-12 21:29:50'),(418,1170,'user_create',NULL,'ADM','ADM','2014-07-12 21:47:03'),(418,1171,'subdiv_nm','OPTIK melawai','OPTIK melawai2','ADM','2014-07-12 21:47:03'),(418,1172,'upload','com.melawai.ppuc.model.Upload@2ecf43d6','com.melawai.ppuc.model.Upload@296eae9c','ADM','2014-07-12 21:47:03'),(418,1173,'itemId','MLW/OPT',NULL,'ADM','2014-07-12 21:47:03'),(418,1174,'jam_update','21:47','21:25','ADM','2014-07-12 21:47:03'),(418,1175,'tgl_create',NULL,'Sun Jun 29 11:35:32 ICT 2014','ADM','2014-07-12 21:47:03'),(418,1176,'tgl_update','Sat Jul 12 21:47:03 ICT 2014','Sat Jul 12 21:25:35 ICT 2014','ADM','2014-07-12 21:47:03'),(419,1177,'upload','com.melawai.ppuc.model.Upload@71e92da3',NULL,'ADM','2014-07-12 21:47:22'),(419,1178,'user_create','ADM',NULL,'ADM','2014-07-12 21:47:23'),(419,1179,'tgl_create','Sat Jul 12 19:56:30 ICT 2014',NULL,'ADM','2014-07-12 21:47:23'),(419,1180,'divisi_nm','aadadaaad',NULL,'ADM','2014-07-12 21:47:23'),(419,1181,'divisi_kd','ada',NULL,'ADM','2014-07-12 21:47:23'),(419,1182,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-12 21:47:23'),(420,1183,'divisi_nm','ABC','ABC D','ADM','2014-07-12 21:47:30'),(420,1184,'tgl_create',NULL,'Sat Jul 12 15:06:35 ICT 2014','ADM','2014-07-12 21:47:30'),(420,1185,'user_create',NULL,'ADM','ADM','2014-07-12 21:47:30'),(420,1186,'itemId','ABC',NULL,'ADM','2014-07-12 21:47:30'),(420,1187,'tgl_update','Sat Jul 12 21:47:30 ICT 2014','Sat Jul 12 19:38:25 ICT 2014','ADM','2014-07-12 21:47:30'),(420,1188,'upload','com.melawai.ppuc.model.Upload@4f439750','com.melawai.ppuc.model.Upload@5e832dd7','ADM','2014-07-12 21:47:30'),(420,1189,'jam_update','21:47','19:38','ADM','2014-07-12 21:47:30'),(423,1190,'tgl_create','Sat Jul 12 22:53:19 ICT 2014',NULL,'ADM','2014-07-12 22:53:19'),(423,1191,'divisi_kd','ABC',NULL,'ADM','2014-07-12 22:53:20'),(423,1192,'dept_nm','ada',NULL,'ADM','2014-07-12 22:53:20'),(423,1193,'subdiv_kd','adw',NULL,'ADM','2014-07-12 22:53:20'),(423,1194,'dept_kd','ada',NULL,'ADM','2014-07-12 22:53:20'),(423,1195,'upload','com.melawai.ppuc.model.Upload@1dbd03a1',NULL,'ADM','2014-07-12 22:53:20'),(423,1196,'user_create','ADM',NULL,'ADM','2014-07-12 22:53:20'),(423,1197,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 22:53:20'),(424,1198,'divisi_kd','ABC',NULL,'ADM','2014-07-12 22:53:49'),(424,1199,'subdiv_kd','ada',NULL,'ADM','2014-07-12 22:53:49'),(424,1200,'upload','com.melawai.ppuc.model.Upload@12c1a91d',NULL,'ADM','2014-07-12 22:53:49'),(424,1201,'dept_kd','ada',NULL,'ADM','2014-07-12 22:53:49'),(424,1202,'tgl_create','Sat Jul 12 22:53:49 ICT 2014',NULL,'ADM','2014-07-12 22:53:49'),(424,1203,'user_create','ADM',NULL,'ADM','2014-07-12 22:53:49'),(424,1204,'dept_nm','adadadad',NULL,'ADM','2014-07-12 22:53:49'),(424,1205,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 22:53:50'),(425,1206,'jam_update','22:55',NULL,'ADM','2014-07-12 22:55:59'),(425,1207,'user_create',NULL,'ADM','ADM','2014-07-12 22:55:59'),(425,1208,'tgl_update','Sat Jul 12 22:55:58 ICT 2014',NULL,'ADM','2014-07-12 22:55:59'),(425,1209,'upload','com.melawai.ppuc.model.Upload@245f9925','com.melawai.ppuc.model.Upload@3f5ecb','ADM','2014-07-12 22:55:59'),(425,1210,'itemId','ada/ada/ABC',NULL,'ADM','2014-07-12 22:55:59'),(425,1211,'tgl_create',NULL,'Sat Jul 12 22:53:49 ICT 2014','ADM','2014-07-12 22:55:59'),(425,1212,'user_update','ADM',NULL,'ADM','2014-07-12 22:55:59'),(426,1213,'itemId','ada/ada/ABC',NULL,'ADM','2014-07-12 22:56:18'),(426,1214,'user_create',NULL,'ADM','ADM','2014-07-12 22:56:18'),(426,1215,'tgl_create',NULL,'Sat Jul 12 22:53:49 ICT 2014','ADM','2014-07-12 22:56:18'),(426,1216,'jam_update','22:56','22:55','ADM','2014-07-12 22:56:18'),(426,1217,'upload','com.melawai.ppuc.model.Upload@6791fe86','com.melawai.ppuc.model.Upload@21dd4489','ADM','2014-07-12 22:56:18'),(426,1218,'tgl_update','Sat Jul 12 22:56:18 ICT 2014','Sat Jul 12 22:55:58 ICT 2014','ADM','2014-07-12 22:56:18'),(427,1219,'divisi_kd','OPT',NULL,'ADM','2014-07-12 22:57:01'),(427,1220,'tgl_create','Sat Jul 12 22:57:01 ICT 2014',NULL,'ADM','2014-07-12 22:57:02'),(427,1221,'user_create','ADM',NULL,'ADM','2014-07-12 22:57:02'),(427,1222,'dept_nm','AJIB',NULL,'ADM','2014-07-12 22:57:02'),(427,1223,'subdiv_kd','EYE',NULL,'ADM','2014-07-12 22:57:02'),(427,1224,'upload','com.melawai.ppuc.model.Upload@4bf75fd7',NULL,'ADM','2014-07-12 22:57:02'),(427,1225,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 22:57:02'),(427,1226,'dept_kd','OPS',NULL,'ADM','2014-07-12 22:57:02'),(429,1227,'user_create','ADM',NULL,'ADM','2014-07-12 23:12:52'),(429,1228,'subdiv_kd','ada',NULL,'ADM','2014-07-12 23:12:52'),(429,1229,'tgl_create','Sat Jul 12 22:53:49 ICT 2014',NULL,'ADM','2014-07-12 23:12:52'),(429,1230,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 23:12:52'),(429,1231,'dept_nm','adadadad',NULL,'ADM','2014-07-12 23:12:52'),(429,1232,'dept_kd','ada',NULL,'ADM','2014-07-12 23:12:52'),(429,1233,'tgl_update','Sat Jul 12 22:56:18 ICT 2014',NULL,'ADM','2014-07-12 23:12:52'),(429,1234,'jam_update','22:56',NULL,'ADM','2014-07-12 23:12:52'),(429,1235,'user_update','ADM',NULL,'ADM','2014-07-12 23:12:52'),(429,1236,'upload','com.melawai.ppuc.model.Upload@4f63dcb4',NULL,'ADM','2014-07-12 23:12:52'),(429,1237,'divisi_kd','ABC',NULL,'ADM','2014-07-12 23:12:52'),(432,1238,'subdiv_kd','ACC',NULL,'ADM','2014-07-12 23:34:01'),(432,1239,'tgl_create','Sat Jul 12 23:34:01 ICT 2014',NULL,'ADM','2014-07-12 23:34:01'),(432,1240,'dept_nm','UMUM',NULL,'ADM','2014-07-12 23:34:01'),(432,1241,'user_create','ADM',NULL,'ADM','2014-07-12 23:34:01'),(432,1242,'divisi_kd','HOL',NULL,'ADM','2014-07-12 23:34:01'),(432,1243,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 23:34:01'),(432,1244,'upload','com.melawai.ppuc.model.Upload@6365eecd',NULL,'ADM','2014-07-12 23:34:01'),(432,1245,'dept_kd','Z',NULL,'ADM','2014-07-12 23:34:01'),(433,1246,'upload','com.melawai.ppuc.model.Upload@260e9555',NULL,'ADM','2014-07-12 23:34:01'),(433,1247,'user_create','ADM',NULL,'ADM','2014-07-12 23:34:02'),(433,1248,'tgl_create','Sat Jul 12 23:34:01 ICT 2014',NULL,'ADM','2014-07-12 23:34:02'),(433,1249,'dept_kd','1',NULL,'ADM','2014-07-12 23:34:02'),(433,1250,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 23:34:02'),(433,1251,'subdiv_kd','EYE',NULL,'ADM','2014-07-12 23:34:02'),(433,1252,'divisi_kd','OPT',NULL,'ADM','2014-07-12 23:34:02'),(433,1253,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-07-12 23:34:02'),(434,1254,'user_create','ADM',NULL,'ADM','2014-07-12 23:34:02'),(434,1255,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-07-12 23:34:02'),(434,1256,'subdiv_kd','MLW',NULL,'ADM','2014-07-12 23:34:02'),(434,1257,'divisi_kd','OPT',NULL,'ADM','2014-07-12 23:34:02'),(434,1258,'upload','com.melawai.ppuc.model.Upload@54662fc0',NULL,'ADM','2014-07-12 23:34:02'),(434,1259,'dept_kd','1',NULL,'ADM','2014-07-12 23:34:02'),(434,1260,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-12 23:34:02'),(434,1261,'tgl_create','Sat Jul 12 23:34:02 ICT 2014',NULL,'ADM','2014-07-12 23:34:02'),(435,1262,'user_update','ADM',NULL,'ADM','2014-07-12 23:34:18'),(435,1263,'upload','com.melawai.ppuc.model.Upload@6ed9870e','com.melawai.ppuc.model.Upload@b31e0fd','ADM','2014-07-12 23:34:18'),(435,1264,'itemId','1/MLW/OPT',NULL,'ADM','2014-07-12 23:34:18'),(435,1265,'user_create',NULL,'ADM','ADM','2014-07-12 23:34:18'),(435,1266,'jam_update','23:34',NULL,'ADM','2014-07-12 23:34:18'),(435,1267,'tgl_create',NULL,'Sat Jul 12 23:34:02 ICT 2014','ADM','2014-07-12 23:34:18'),(435,1268,'tgl_update','Sat Jul 12 23:34:18 ICT 2014',NULL,'ADM','2014-07-12 23:34:18'),(436,1269,'itemId','1/MLW/OPT',NULL,'ADM','2014-07-12 23:34:35'),(436,1270,'tgl_update','Sat Jul 12 23:34:35 ICT 2014','Sat Jul 12 23:34:18 ICT 2014','ADM','2014-07-12 23:34:35'),(436,1271,'upload','com.melawai.ppuc.model.Upload@171d522e','com.melawai.ppuc.model.Upload@5fe55dd','ADM','2014-07-12 23:34:35'),(436,1272,'tgl_create',NULL,'Sat Jul 12 23:34:02 ICT 2014','ADM','2014-07-12 23:34:35'),(436,1273,'user_create',NULL,'ADM','ADM','2014-07-12 23:34:35'),(438,1274,'divisi_nm','IT DIVISION',NULL,'ADM','2014-07-13 00:10:57'),(438,1275,'tgl_create','Sat Jul 12 15:12:13 ICT 2014',NULL,'ADM','2014-07-13 00:10:57'),(438,1276,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-07-13 00:10:57'),(438,1277,'divisi_kd','ITD',NULL,'ADM','2014-07-13 00:10:57'),(438,1278,'user_create','ADM',NULL,'ADM','2014-07-13 00:10:57'),(438,1279,'upload','com.melawai.ppuc.model.Upload@7565be3d',NULL,'ADM','2014-07-13 00:10:57'),(439,1280,'upload','com.melawai.ppuc.model.Upload@687e2a1d','com.melawai.ppuc.model.Upload@32ff0b83','ADM','2014-07-13 00:11:31'),(439,1281,'user_create',NULL,'ADM','ADM','2014-07-13 00:11:31'),(439,1282,'tgl_create',NULL,'Sun Jun 29 05:15:10 ICT 2014','ADM','2014-07-13 00:11:31'),(439,1283,'tgl_update','Sun Jul 13 00:11:31 ICT 2014','Sat Jul 12 20:21:30 ICT 2014','ADM','2014-07-13 00:11:31'),(439,1284,'itemId','OPT',NULL,'ADM','2014-07-13 00:11:31'),(439,1285,'jam_update','00:11','20:21','ADM','2014-07-13 00:11:31'),(439,1286,'divisi_nm','OPTIK','MLW','ADM','2014-07-13 00:11:31'),(440,1287,'itemId','HOL',NULL,'ADM','2014-07-13 00:12:18'),(440,1288,'user_create',NULL,'ADM','ADM','2014-07-13 00:12:18'),(440,1289,'jam_update','00:12','20:21','ADM','2014-07-13 00:12:18'),(440,1290,'tgl_update','Sun Jul 13 00:12:18 ICT 2014','Sat Jul 12 20:21:30 ICT 2014','ADM','2014-07-13 00:12:18'),(440,1291,'divisi_nm','HOLDING','ACC','ADM','2014-07-13 00:12:18'),(440,1292,'upload','com.melawai.ppuc.model.Upload@231d7d86','com.melawai.ppuc.model.Upload@651bfa6c','ADM','2014-07-13 00:12:18'),(440,1293,'tgl_create',NULL,'Sun Jun 29 03:56:44 ICT 2014','ADM','2014-07-13 00:12:18'),(441,1294,'jam_update','21:29',NULL,'ADM','2014-07-13 00:12:40'),(441,1295,'upload','com.melawai.ppuc.model.Upload@1080fca3',NULL,'ADM','2014-07-13 00:12:40'),(441,1296,'tgl_create','Sat Jul 12 21:24:51 ICT 2014',NULL,'ADM','2014-07-13 00:12:40'),(441,1297,'user_create','ADM',NULL,'ADM','2014-07-13 00:12:40'),(441,1298,'tgl_update','Sat Jul 12 21:29:50 ICT 2014',NULL,'ADM','2014-07-13 00:12:41'),(441,1299,'subdiv_kd','adw',NULL,'ADM','2014-07-13 00:12:41'),(441,1300,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-13 00:12:41'),(441,1301,'divisi_kd','ABC',NULL,'ADM','2014-07-13 00:12:41'),(441,1302,'user_update','ADM',NULL,'ADM','2014-07-13 00:12:41'),(441,1303,'subdiv_nm','ABDC',NULL,'ADM','2014-07-13 00:12:41'),(442,1304,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-13 00:12:47'),(442,1305,'user_create','ADM',NULL,'ADM','2014-07-13 00:12:47'),(442,1306,'divisi_kd','OKA',NULL,'ADM','2014-07-13 00:12:47'),(442,1307,'tgl_create','Sat Jul 12 21:25:35 ICT 2014',NULL,'ADM','2014-07-13 00:12:47'),(442,1308,'subdiv_kd','ada',NULL,'ADM','2014-07-13 00:12:47'),(442,1309,'subdiv_nm','adadadad',NULL,'ADM','2014-07-13 00:12:47'),(442,1310,'upload','com.melawai.ppuc.model.Upload@58ab45b9',NULL,'ADM','2014-07-13 00:12:47'),(443,1311,'subdiv_kd','ada',NULL,'ADM','2014-07-13 00:12:53'),(443,1312,'tgl_update','Sat Jul 12 19:26:53 ICT 2014',NULL,'ADM','2014-07-13 00:12:54'),(443,1313,'tgl_create','Sat Jul 12 19:26:38 ICT 2014',NULL,'ADM','2014-07-13 00:12:54'),(443,1314,'divisi_kd','ABC',NULL,'ADM','2014-07-13 00:12:54'),(443,1315,'user_update','ADM',NULL,'ADM','2014-07-13 00:12:54'),(443,1316,'upload','com.melawai.ppuc.model.Upload@51016a32',NULL,'ADM','2014-07-13 00:12:54'),(443,1317,'user_create','ADM',NULL,'ADM','2014-07-13 00:12:54'),(443,1318,'serialVersionUID','-4358864468672803061',NULL,'ADM','2014-07-13 00:12:54'),(443,1319,'jam_update','19:26',NULL,'ADM','2014-07-13 00:12:54'),(443,1320,'subdiv_nm','ada ada aja',NULL,'ADM','2014-07-13 00:12:54'),(444,1321,'divisi_kd','HOL',NULL,'ADM','2014-07-13 01:05:53'),(444,1322,'subdiv_kd','ACC',NULL,'ADM','2014-07-13 01:05:56'),(444,1323,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:05:56'),(444,1324,'user_create','ADM',NULL,'ADM','2014-07-13 01:05:56'),(444,1325,'upload','com.melawai.ppuc.model.Upload@20e8fb99',NULL,'ADM','2014-07-13 01:05:56'),(444,1326,'dept_nm','AWUA',NULL,'ADM','2014-07-13 01:05:56'),(444,1327,'tgl_create','Sun Jul 13 01:05:53 ICT 2014',NULL,'ADM','2014-07-13 01:05:56'),(444,1328,'dept_kd','AQU',NULL,'ADM','2014-07-13 01:05:56'),(445,1329,'itemId','AQU/ACC/HOL',NULL,'ADM','2014-07-13 01:06:08'),(445,1330,'tgl_update','Sun Jul 13 01:06:08 ICT 2014',NULL,'ADM','2014-07-13 01:06:08'),(445,1331,'upload','com.melawai.ppuc.model.Upload@968d77b','com.melawai.ppuc.model.Upload@421ad110','ADM','2014-07-13 01:06:08'),(445,1332,'jam_update','01:06',NULL,'ADM','2014-07-13 01:06:08'),(445,1333,'user_update','ADM',NULL,'ADM','2014-07-13 01:06:08'),(445,1334,'user_create',NULL,'ADM','ADM','2014-07-13 01:06:08'),(445,1335,'tgl_create',NULL,'Sun Jul 13 01:05:53 ICT 2014','ADM','2014-07-13 01:06:08'),(446,1336,'dept_nm','UMUM',NULL,'ADM','2014-07-13 01:07:56'),(446,1337,'divisi_kd','HOL',NULL,'ADM','2014-07-13 01:07:57'),(446,1338,'user_create','ADM',NULL,'ADM','2014-07-13 01:07:57'),(446,1339,'dept_kd','Z',NULL,'ADM','2014-07-13 01:07:57'),(446,1340,'upload','com.melawai.ppuc.model.Upload@77383c3c',NULL,'ADM','2014-07-13 01:07:57'),(446,1341,'tgl_create','Sat Jul 12 23:34:01 ICT 2014',NULL,'ADM','2014-07-13 01:07:57'),(446,1342,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:07:57'),(446,1343,'subdiv_kd','ACC',NULL,'ADM','2014-07-13 01:07:57'),(447,1344,'tgl_update','Sat Jul 12 23:34:35 ICT 2014',NULL,'ADM','2014-07-13 01:08:03'),(447,1345,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:08:05'),(447,1346,'jam_update','23:34',NULL,'ADM','2014-07-13 01:08:05'),(447,1347,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-07-13 01:08:05'),(447,1348,'subdiv_kd','MLW',NULL,'ADM','2014-07-13 01:08:05'),(447,1349,'dept_kd','1',NULL,'ADM','2014-07-13 01:08:05'),(447,1350,'user_create','ADM',NULL,'ADM','2014-07-13 01:08:05'),(447,1351,'upload','com.melawai.ppuc.model.Upload@33e4c776',NULL,'ADM','2014-07-13 01:08:05'),(447,1352,'tgl_create','Sat Jul 12 23:34:02 ICT 2014',NULL,'ADM','2014-07-13 01:08:05'),(447,1353,'user_update','ADM',NULL,'ADM','2014-07-13 01:08:05'),(447,1354,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:08:05'),(448,1355,'upload','com.melawai.ppuc.model.Upload@6a4f0229',NULL,'ADM','2014-07-13 01:08:11'),(448,1356,'user_create','ADM',NULL,'ADM','2014-07-13 01:08:11'),(448,1357,'divisi_kd','HOL',NULL,'ADM','2014-07-13 01:08:11'),(448,1358,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:08:11'),(448,1359,'tgl_create','Sun Jul 13 01:05:53 ICT 2014',NULL,'ADM','2014-07-13 01:08:11'),(448,1360,'tgl_update','Sun Jul 13 01:06:08 ICT 2014',NULL,'ADM','2014-07-13 01:08:11'),(448,1361,'subdiv_kd','ACC',NULL,'ADM','2014-07-13 01:08:11'),(448,1362,'dept_kd','AQU',NULL,'ADM','2014-07-13 01:08:11'),(448,1363,'user_update','ADM',NULL,'ADM','2014-07-13 01:08:11'),(448,1364,'jam_update','01:06',NULL,'ADM','2014-07-13 01:08:11'),(448,1365,'dept_nm','AWUA',NULL,'ADM','2014-07-13 01:08:11'),(449,1366,'upload','com.melawai.ppuc.model.Upload@1abc265e',NULL,'ADM','2014-07-13 01:08:17'),(449,1367,'tgl_create','Sat Jul 12 23:34:01 ICT 2014',NULL,'ADM','2014-07-13 01:08:17'),(449,1368,'subdiv_kd','EYE',NULL,'ADM','2014-07-13 01:08:17'),(449,1369,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:08:17'),(449,1370,'dept_kd','1',NULL,'ADM','2014-07-13 01:08:17'),(449,1371,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:08:17'),(449,1372,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-07-13 01:08:17'),(449,1373,'user_create','ADM',NULL,'ADM','2014-07-13 01:08:17'),(452,1374,'upload','com.melawai.ppuc.model.Upload@129056bd',NULL,'ADM','2014-07-13 01:08:38'),(452,1375,'dept_nm','UMUM',NULL,'ADM','2014-07-13 01:08:38'),(452,1376,'dept_kd','Z',NULL,'ADM','2014-07-13 01:08:38'),(452,1377,'tgl_create','Sun Jul 13 01:08:38 ICT 2014',NULL,'ADM','2014-07-13 01:08:38'),(452,1378,'divisi_kd','HOL',NULL,'ADM','2014-07-13 01:08:38'),(452,1379,'user_create','ADM',NULL,'ADM','2014-07-13 01:08:38'),(452,1380,'subdiv_kd','ACC',NULL,'ADM','2014-07-13 01:08:38'),(452,1381,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:08:38'),(453,1382,'tgl_create','Sun Jul 13 01:08:38 ICT 2014',NULL,'ADM','2014-07-13 01:08:38'),(453,1383,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-07-13 01:08:39'),(453,1384,'user_create','ADM',NULL,'ADM','2014-07-13 01:08:39'),(453,1385,'subdiv_kd','EYE',NULL,'ADM','2014-07-13 01:08:39'),(453,1386,'dept_kd','1',NULL,'ADM','2014-07-13 01:08:39'),(453,1387,'upload','com.melawai.ppuc.model.Upload@459f6ed1',NULL,'ADM','2014-07-13 01:08:39'),(453,1388,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:08:39'),(453,1389,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:08:39'),(454,1390,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:08:39'),(454,1391,'tgl_create','Sun Jul 13 01:08:39 ICT 2014',NULL,'ADM','2014-07-13 01:08:39'),(454,1392,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-07-13 01:08:39'),(454,1393,'user_create','ADM',NULL,'ADM','2014-07-13 01:08:39'),(454,1394,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:08:39'),(454,1395,'upload','com.melawai.ppuc.model.Upload@48503eee',NULL,'ADM','2014-07-13 01:08:39'),(454,1396,'subdiv_kd','MLW',NULL,'ADM','2014-07-13 01:08:39'),(454,1397,'dept_kd','1',NULL,'ADM','2014-07-13 01:08:39'),(455,1398,'dept_kd','Z',NULL,'ADM','2014-07-13 01:11:27'),(455,1399,'divisi_kd','HOL',NULL,'ADM','2014-07-13 01:11:28'),(455,1400,'upload','com.melawai.ppuc.model.Upload@4182a1e7',NULL,'ADM','2014-07-13 01:11:28'),(455,1401,'user_create','ADM',NULL,'ADM','2014-07-13 01:11:28'),(455,1402,'subdiv_kd','ACC',NULL,'ADM','2014-07-13 01:11:28'),(455,1403,'dept_nm','UMUM',NULL,'ADM','2014-07-13 01:11:28'),(455,1404,'tgl_create','Sun Jul 13 01:08:38 ICT 2014',NULL,'ADM','2014-07-13 01:11:28'),(455,1405,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:11:28'),(456,1406,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-07-13 01:11:34'),(456,1407,'tgl_create','Sun Jul 13 01:08:39 ICT 2014',NULL,'ADM','2014-07-13 01:11:34'),(456,1408,'dept_kd','1',NULL,'ADM','2014-07-13 01:11:34'),(456,1409,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:11:34'),(456,1410,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:11:34'),(456,1411,'user_create','ADM',NULL,'ADM','2014-07-13 01:11:34'),(456,1412,'subdiv_kd','MLW',NULL,'ADM','2014-07-13 01:11:34'),(456,1413,'upload','com.melawai.ppuc.model.Upload@15222b98',NULL,'ADM','2014-07-13 01:11:34'),(457,1414,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-07-13 01:11:41'),(457,1415,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:11:42'),(457,1416,'upload','com.melawai.ppuc.model.Upload@3aa1eab',NULL,'ADM','2014-07-13 01:11:42'),(457,1417,'subdiv_kd','EYE',NULL,'ADM','2014-07-13 01:11:42'),(457,1418,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:11:42'),(457,1419,'tgl_create','Sun Jul 13 01:08:38 ICT 2014',NULL,'ADM','2014-07-13 01:11:42'),(457,1420,'dept_kd','1',NULL,'ADM','2014-07-13 01:11:42'),(457,1421,'user_create','ADM',NULL,'ADM','2014-07-13 01:11:42'),(459,1422,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:11:55'),(459,1423,'dept_nm','UMUM',NULL,'ADM','2014-07-13 01:11:55'),(459,1424,'dept_kd','Z',NULL,'ADM','2014-07-13 01:11:55'),(459,1425,'tgl_create','Sun Jul 13 01:11:55 ICT 2014',NULL,'ADM','2014-07-13 01:11:55'),(459,1426,'user_create','ADM',NULL,'ADM','2014-07-13 01:11:55'),(459,1427,'subdiv_kd','ACC',NULL,'ADM','2014-07-13 01:11:55'),(459,1428,'upload','com.melawai.ppuc.model.Upload@51eded67',NULL,'ADM','2014-07-13 01:11:55'),(459,1429,'divisi_kd','HOL',NULL,'ADM','2014-07-13 01:11:55'),(460,1430,'tgl_create','Sun Jul 13 01:11:55 ICT 2014',NULL,'ADM','2014-07-13 01:11:55'),(460,1431,'user_create','ADM',NULL,'ADM','2014-07-13 01:11:56'),(460,1432,'dept_kd','1',NULL,'ADM','2014-07-13 01:11:56'),(460,1433,'subdiv_kd','EYE',NULL,'ADM','2014-07-13 01:11:56'),(460,1434,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:11:56'),(460,1435,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-07-13 01:11:56'),(460,1436,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:11:56'),(460,1437,'upload','com.melawai.ppuc.model.Upload@b558806',NULL,'ADM','2014-07-13 01:11:56'),(461,1438,'upload','com.melawai.ppuc.model.Upload@6c6f3a15',NULL,'ADM','2014-07-13 01:11:56'),(461,1439,'dept_kd','1',NULL,'ADM','2014-07-13 01:11:56'),(461,1440,'user_create','ADM',NULL,'ADM','2014-07-13 01:11:56'),(461,1441,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-13 01:11:56'),(461,1442,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-07-13 01:11:56'),(461,1443,'subdiv_kd','MLW',NULL,'ADM','2014-07-13 01:11:56'),(461,1444,'tgl_create','Sun Jul 13 01:11:56 ICT 2014',NULL,'ADM','2014-07-13 01:11:56'),(461,1445,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:11:56'),(463,1446,'propinsi','DKI Jakarta',NULL,'ADM','2014-07-13 01:29:26'),(463,1447,'lok_kd','SNYN',NULL,'ADM','2014-07-13 01:29:26'),(463,1448,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:29:26'),(463,1449,'user_create','ADM',NULL,'ADM','2014-07-13 01:29:26'),(463,1450,'lok_nm','Senayan',NULL,'ADM','2014-07-13 01:29:26'),(463,1451,'email','brai@ada/ada',NULL,'ADM','2014-07-13 01:29:26'),(463,1452,'dept_kd','1',NULL,'ADM','2014-07-13 01:29:26'),(463,1453,'tgl_create','Sun Jul 13 01:29:26 ICT 2014',NULL,'ADM','2014-07-13 01:29:26'),(463,1454,'kota','Jakarta Barat',NULL,'ADM','2014-07-13 01:29:26'),(463,1455,'subdiv_kd','EYE',NULL,'ADM','2014-07-13 01:29:26'),(463,1456,'serialVersionUID','7503294106098421175',NULL,'ADM','2014-07-13 01:29:26'),(463,1457,'upload','com.melawai.ppuc.model.Upload@1b49af42',NULL,'ADM','2014-07-13 01:29:26'),(464,1458,'jam_update','01:36',NULL,'ADM','2014-07-13 01:36:52'),(464,1459,'user_update','ADM',NULL,'ADM','2014-07-13 01:36:54'),(464,1460,'user_create',NULL,'ADM','ADM','2014-07-13 01:36:54'),(464,1461,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:36:54'),(464,1462,'propinsi','Aceh','DKI Jakarta','ADM','2014-07-13 01:36:54'),(464,1463,'upload','com.melawai.ppuc.model.Upload@4c132270','com.melawai.ppuc.model.Upload@7c3a0e14','ADM','2014-07-13 01:36:54'),(464,1464,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:36:54'),(464,1465,'kota','BANDA ACEH','Jakarta Barat','ADM','2014-07-13 01:36:54'),(464,1466,'tgl_update','Sun Jul 13 01:36:52 ICT 2014',NULL,'ADM','2014-07-13 01:36:54'),(465,1467,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:40:23'),(465,1468,'f_tutup','1',NULL,'ADM','2014-07-13 01:40:23'),(465,1469,'upload','com.melawai.ppuc.model.Upload@1b31c507','com.melawai.ppuc.model.Upload@607e5c83','ADM','2014-07-13 01:40:23'),(465,1470,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:40:23'),(465,1471,'user_create',NULL,'ADM','ADM','2014-07-13 01:40:23'),(465,1472,'jam_update','01:40','01:36','ADM','2014-07-13 01:40:23'),(465,1473,'tgl_update','Sun Jul 13 01:40:23 ICT 2014','Sun Jul 13 01:36:52 ICT 2014','ADM','2014-07-13 01:40:23'),(466,1474,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:40:35'),(466,1475,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:40:35'),(466,1476,'user_create',NULL,'ADM','ADM','2014-07-13 01:40:35'),(466,1477,'upload','com.melawai.ppuc.model.Upload@52339a41','com.melawai.ppuc.model.Upload@39d44b90','ADM','2014-07-13 01:40:35'),(466,1478,'tgl_update','Sun Jul 13 01:40:35 ICT 2014','Sun Jul 13 01:40:23 ICT 2014','ADM','2014-07-13 01:40:35'),(466,1479,'f_tutup',NULL,'1','ADM','2014-07-13 01:40:35'),(467,1480,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:43:30'),(467,1481,'upload','com.melawai.ppuc.model.Upload@3a3623c3','com.melawai.ppuc.model.Upload@3865f510','ADM','2014-07-13 01:43:30'),(467,1482,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:43:30'),(467,1483,'user_create',NULL,'ADM','ADM','2014-07-13 01:43:30'),(467,1484,'jam_update','01:43','01:40','ADM','2014-07-13 01:43:30'),(467,1485,'tgl_update','Sun Jul 13 01:43:29 ICT 2014','Sun Jul 13 01:40:35 ICT 2014','ADM','2014-07-13 01:43:30'),(467,1486,'f_tutup','0','1','ADM','2014-07-13 01:43:30'),(468,1487,'user_create',NULL,'ADM','ADM','2014-07-13 01:43:43'),(468,1488,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:43:43'),(468,1489,'f_tutup','1','0','ADM','2014-07-13 01:43:43'),(468,1490,'upload','com.melawai.ppuc.model.Upload@c9c9279','com.melawai.ppuc.model.Upload@7b544c66','ADM','2014-07-13 01:43:43'),(468,1491,'tgl_update','Sun Jul 13 01:43:43 ICT 2014','Sun Jul 13 01:43:29 ICT 2014','ADM','2014-07-13 01:43:43'),(468,1492,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:43:43'),(469,1493,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:52:28'),(469,1494,'user_create',NULL,'ADM','ADM','2014-07-13 01:52:28'),(469,1495,'tgl_update','Sun Jul 13 01:52:28 ICT 2014','Sun Jul 13 01:43:43 ICT 2014','ADM','2014-07-13 01:52:28'),(469,1496,'upload','com.melawai.ppuc.model.Upload@53ed8bc','com.melawai.ppuc.model.Upload@61072154','ADM','2014-07-13 01:52:28'),(469,1497,'f_tutup','0','1','ADM','2014-07-13 01:52:28'),(469,1498,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:52:28'),(469,1499,'jam_update','01:52','01:43','ADM','2014-07-13 01:52:28'),(470,1500,'user_create',NULL,'ADM','ADM','2014-07-13 01:54:10'),(470,1501,'upload','com.melawai.ppuc.model.Upload@5970801b','com.melawai.ppuc.model.Upload@68ea9a7c','ADM','2014-07-13 01:54:10'),(470,1502,'tgl_update','Sun Jul 13 01:54:10 ICT 2014','Sun Jul 13 01:52:28 ICT 2014','ADM','2014-07-13 01:54:10'),(470,1503,'jam_update','01:54','01:52','ADM','2014-07-13 01:54:10'),(470,1504,'itemId','SNYN/1/EYE/OPT',NULL,'ADM','2014-07-13 01:54:10'),(470,1505,'email','brai@ad.ada','brai@ada/ada','ADM','2014-07-13 01:54:11'),(470,1506,'tgl_create',NULL,'Sun Jul 13 01:29:26 ICT 2014','ADM','2014-07-13 01:54:11'),(471,1507,'lok_nm','adadad',NULL,'ADM','2014-07-13 01:56:18'),(471,1508,'propinsi','Banten',NULL,'ADM','2014-07-13 01:56:18'),(471,1509,'upload','com.melawai.ppuc.model.Upload@67a475c3',NULL,'ADM','2014-07-13 01:56:18'),(471,1510,'divisi_kd','OPT',NULL,'ADM','2014-07-13 01:56:18'),(471,1511,'serialVersionUID','7503294106098421175',NULL,'ADM','2014-07-13 01:56:18'),(471,1512,'f_tutup','0',NULL,'ADM','2014-07-13 01:56:18'),(471,1513,'user_create','ADM',NULL,'ADM','2014-07-13 01:56:18'),(471,1514,'subdiv_kd','EYE',NULL,'ADM','2014-07-13 01:56:18'),(471,1515,'email','adada@ada.aa',NULL,'ADM','2014-07-13 01:56:18'),(471,1516,'dept_kd','1',NULL,'ADM','2014-07-13 01:56:18'),(471,1517,'tgl_create','Sun Jul 13 01:56:18 ICT 2014',NULL,'ADM','2014-07-13 01:56:18'),(471,1518,'kota','CENGKARENG',NULL,'ADM','2014-07-13 01:56:18'),(471,1519,'lok_kd','ADDDD',NULL,'ADM','2014-07-13 01:56:18'),(476,1520,'f_tutup',NULL,'0','ADM','2014-07-13 02:12:09'),(476,1521,'user_create',NULL,'ADM','ADM','2014-07-13 02:12:09'),(476,1522,'upload','com.melawai.ppuc.model.Upload@550d7440','com.melawai.ppuc.model.Upload@664b415f','ADM','2014-07-13 02:12:09'),(476,1523,'tgl_update','Sun Jul 13 02:12:09 ICT 2014',NULL,'ADM','2014-07-13 02:12:09'),(476,1524,'jam_update','02:12',NULL,'ADM','2014-07-13 02:12:09'),(476,1525,'user_update','ADM',NULL,'ADM','2014-07-13 02:12:09'),(476,1526,'tgl_tutup','Sun Jul 13 02:00:24 ICT 2014',NULL,'ADM','2014-07-13 02:12:09'),(477,1527,'f_tutup',NULL,'0','ADM','2014-07-13 02:12:09'),(477,1528,'tgl_update','Sun Jul 13 02:12:09 ICT 2014','Sun Jul 13 01:54:10 ICT 2014','ADM','2014-07-13 02:12:09'),(477,1529,'upload','com.melawai.ppuc.model.Upload@18521cb4','com.melawai.ppuc.model.Upload@36da79e5','ADM','2014-07-13 02:12:09'),(477,1530,'user_create',NULL,'ADM','ADM','2014-07-13 02:12:09'),(477,1531,'jam_update','02:12','01:54','ADM','2014-07-13 02:12:09'),(479,1532,'upload','com.melawai.ppuc.model.Upload@1f0fb1e2','com.melawai.ppuc.model.Upload@3f677c5c','ADM','2014-07-13 02:24:34'),(479,1533,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:24:34'),(479,1534,'user_create',NULL,'ADM','ADM','2014-07-13 02:24:34'),(479,1535,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:24:34'),(479,1536,'tgl_update','Sun Jul 13 02:24:34 ICT 2014','Sun Jul 13 02:12:09 ICT 2014','ADM','2014-07-13 02:24:34'),(479,1537,'jam_update','02:24','02:12','ADM','2014-07-13 02:24:34'),(479,1538,'tgl_tutup',NULL,'Sun Jul 13 02:00:24 ICT 2014','ADM','2014-07-13 02:24:34'),(480,1539,'user_create',NULL,'ADM','ADM','2014-07-13 02:24:49'),(480,1540,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:24:49'),(480,1541,'f_tutup','1','0','ADM','2014-07-13 02:24:49'),(480,1542,'upload','com.melawai.ppuc.model.Upload@55104de7','com.melawai.ppuc.model.Upload@216aa452','ADM','2014-07-13 02:24:49'),(480,1543,'tgl_update','Sun Jul 13 02:24:49 ICT 2014','Sun Jul 13 02:24:34 ICT 2014','ADM','2014-07-13 02:24:49'),(480,1544,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:24:49'),(482,1545,'tgl_update','Sun Jul 13 02:29:43 ICT 2014','Sun Jul 13 02:24:49 ICT 2014','ADM','2014-07-13 02:29:43'),(482,1546,'user_create',NULL,'ADM','ADM','2014-07-13 02:29:43'),(482,1547,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:29:43'),(482,1548,'jam_update','02:29','02:24','ADM','2014-07-13 02:29:43'),(482,1549,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:29:43'),(482,1550,'tgl_tutup','Fri Jul 11 00:00:00 ICT 2014',NULL,'ADM','2014-07-13 02:29:43'),(482,1551,'upload','com.melawai.ppuc.model.Upload@14e7707b','com.melawai.ppuc.model.Upload@6689a5f5','ADM','2014-07-13 02:29:43'),(483,1552,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:30:10'),(483,1553,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:30:10'),(483,1554,'f_tutup','0','1','ADM','2014-07-13 02:30:10'),(483,1555,'jam_update','02:30','02:29','ADM','2014-07-13 02:30:10'),(483,1556,'tgl_update','Sun Jul 13 02:30:10 ICT 2014','Sun Jul 13 02:29:43 ICT 2014','ADM','2014-07-13 02:30:10'),(483,1557,'upload','com.melawai.ppuc.model.Upload@7cbcb31','com.melawai.ppuc.model.Upload@548901f6','ADM','2014-07-13 02:30:10'),(483,1558,'user_create',NULL,'ADM','ADM','2014-07-13 02:30:10'),(484,1559,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:30:33'),(484,1560,'upload','com.melawai.ppuc.model.Upload@108c9386','com.melawai.ppuc.model.Upload@7d294ae6','ADM','2014-07-13 02:30:33'),(484,1561,'tgl_update','Sun Jul 13 02:30:33 ICT 2014','Sun Jul 13 02:30:10 ICT 2014','ADM','2014-07-13 02:30:33'),(484,1562,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:30:33'),(484,1563,'tgl_tutup',NULL,'Fri Jul 11 00:00:00 ICT 2014','ADM','2014-07-13 02:30:33'),(484,1564,'user_create',NULL,'ADM','ADM','2014-07-13 02:30:33'),(485,1565,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:30:47'),(485,1566,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:30:47'),(485,1567,'tgl_tutup','Thu Jul 24 00:00:00 ICT 2014',NULL,'ADM','2014-07-13 02:30:47'),(485,1568,'user_create',NULL,'ADM','ADM','2014-07-13 02:30:47'),(485,1569,'f_tutup','1','0','ADM','2014-07-13 02:30:47'),(485,1570,'upload','com.melawai.ppuc.model.Upload@4b284bc','com.melawai.ppuc.model.Upload@6428550c','ADM','2014-07-13 02:30:47'),(485,1571,'tgl_update','Sun Jul 13 02:30:47 ICT 2014','Sun Jul 13 02:30:33 ICT 2014','ADM','2014-07-13 02:30:47'),(486,1572,'user_create',NULL,'ADM','ADM','2014-07-13 02:31:01'),(486,1573,'f_tutup','0','1','ADM','2014-07-13 02:31:01'),(486,1574,'upload','com.melawai.ppuc.model.Upload@4403a302','com.melawai.ppuc.model.Upload@4ac2fb30','ADM','2014-07-13 02:31:01'),(486,1575,'tgl_create',NULL,'Sun Jul 13 01:56:18 ICT 2014','ADM','2014-07-13 02:31:01'),(486,1576,'tgl_tutup',NULL,'Thu Jul 24 00:00:00 ICT 2014','ADM','2014-07-13 02:31:01'),(486,1577,'tgl_update','Sun Jul 13 02:31:01 ICT 2014','Sun Jul 13 02:30:47 ICT 2014','ADM','2014-07-13 02:31:01'),(486,1578,'itemId','ADDDD/1/EYE/OPT',NULL,'ADM','2014-07-13 02:31:01'),(486,1579,'jam_update','02:31','02:30','ADM','2014-07-13 02:31:01'),(488,1580,'user_create',NULL,'ADM','ADM','2014-07-13 02:32:25'),(488,1581,'jam_update','02:32','02:31','ADM','2014-07-13 02:32:25'),(488,1582,'tgl_update','Sun Jul 13 02:32:25 ICT 2014','Sun Jul 13 02:31:01 ICT 2014','ADM','2014-07-13 02:32:25'),(488,1583,'tgl_tutup','Sun Jul 13 02:00:24 ICT 2014',NULL,'ADM','2014-07-13 02:32:25'),(488,1584,'f_tutup','1','0','ADM','2014-07-13 02:32:25'),(488,1585,'upload','com.melawai.ppuc.model.Upload@10e43b06','com.melawai.ppuc.model.Upload@77df0f93','ADM','2014-07-13 02:32:25'),(489,1586,'upload','com.melawai.ppuc.model.Upload@6cafef26','com.melawai.ppuc.model.Upload@127ddf89','ADM','2014-07-13 02:32:25'),(489,1587,'tgl_update','Sun Jul 13 02:32:25 ICT 2014','Sun Jul 13 02:12:09 ICT 2014','ADM','2014-07-13 02:32:25'),(489,1588,'user_create',NULL,'ADM','ADM','2014-07-13 02:32:25'),(489,1589,'jam_update','02:32','02:12','ADM','2014-07-13 02:32:25');
/*!40000 ALTER TABLE `sys_audittrail_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_counter`
--

DROP TABLE IF EXISTS `sys_counter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_counter` (
  `sys_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_nm_counter` varchar(50) DEFAULT NULL,
  `sys_counter` int(11) DEFAULT NULL,
  `sys_max` int(11) DEFAULT NULL,
  `sys_last_periode` date DEFAULT NULL,
  `sys_keterangan` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table untuk counter';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_counter`
--

LOCK TABLES `sys_counter` WRITE;
/*!40000 ALTER TABLE `sys_counter` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_counter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log_email`
--

DROP TABLE IF EXISTS `sys_log_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log_email` (
  `sys_id_log_email` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id table log_sms',
  `sys_divisi_kd` varchar(3) DEFAULT NULL,
  `sys_subdiv_kd` varchar(3) DEFAULT NULL,
  `sys_dept_kd` varchar(3) DEFAULT NULL,
  `sys_lok_kd` varchar(5) DEFAULT NULL,
  `sys_no_ppuc` varchar(9) DEFAULT NULL,
  `sys_tgl_ppuc` date DEFAULT NULL,
  `sys_send_date` datetime DEFAULT NULL,
  `sys_send_time` varchar(8) DEFAULT NULL,
  `sys_status` varchar(1) DEFAULT NULL COMMENT 'PENDING DELIVERED FAILED',
  `sys_isi_email` text,
  `sys_mail_maker` varchar(100) DEFAULT NULL,
  `sys_mail_approval` varchar(100) DEFAULT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_id_log_email`),
  KEY `fk_logemail_divisi_idx` (`sys_divisi_kd`),
  KEY `fk_logemail_subdivisi_idx` (`sys_subdiv_kd`),
  KEY `fk_logemail_departmen_idx` (`sys_dept_kd`),
  KEY `fk_logemail_lok_idx` (`sys_lok_kd`),
  CONSTRAINT `fk_logemail_departmen` FOREIGN KEY (`sys_dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_logemail_divisi` FOREIGN KEY (`sys_divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_logemail_lok` FOREIGN KEY (`sys_lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_logemail_subdivisi` FOREIGN KEY (`sys_subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log_email`
--

LOCK TABLES `sys_log_email` WRITE;
/*!40000 ALTER TABLE `sys_log_email` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log_sms`
--

DROP TABLE IF EXISTS `sys_log_sms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log_sms` (
  `sys_id_log_sms` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id table log_sms',
  `sys_divisi_kd` varchar(3) DEFAULT NULL,
  `sys_subdiv_kd` varchar(3) DEFAULT NULL,
  `sys_dept_kd` varchar(3) DEFAULT NULL,
  `sys_lok_kd` varchar(5) DEFAULT NULL,
  `sys_no_ppuc` varchar(9) DEFAULT NULL,
  `sys_tgl_ppuc` date DEFAULT NULL,
  `sys_send_date` datetime DEFAULT NULL,
  `sys_send_time` varchar(8) DEFAULT NULL,
  `sys_status` varchar(1) DEFAULT NULL COMMENT 'PENDING DELIVERED FAILED',
  `sys_isi_sms` text,
  `sys_hp_maker` varchar(25) DEFAULT NULL,
  `sys_hp_approval` varchar(25) DEFAULT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_id_log_sms`),
  KEY `fk_logsms_subdivisi_idx` (`sys_subdiv_kd`),
  KEY `fk_logsms_divisi_idx` (`sys_divisi_kd`),
  KEY `fk_logsms_dept_idx` (`sys_dept_kd`),
  KEY `fk_logsms_lokid_idx` (`sys_lok_kd`),
  CONSTRAINT `fk_logsms_dept` FOREIGN KEY (`sys_dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_logsms_divisi` FOREIGN KEY (`sys_divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_logsms_lokid` FOREIGN KEY (`sys_lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_logsms_subdivisi` FOREIGN KEY (`sys_subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log_sms`
--

LOCK TABLES `sys_log_sms` WRITE;
/*!40000 ALTER TABLE `sys_log_sms` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log_sms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL COMMENT 'username login',
  `password` varchar(100) DEFAULT NULL COMMENT 'password \nencrypted by spring security \ntidak  bisa di decrypt manual',
  `user_name` varchar(100) DEFAULT NULL COMMENT 'Nama User',
  `no_hp` varchar(25) DEFAULT NULL COMMENT 'no hp user\ntidak boleh sama',
  `email` varchar(100) DEFAULT NULL COMMENT 'email user \ntidak boleh sama',
  `kd_fungsi` varchar(5) DEFAULT NULL COMMENT 'kode fungsi dari table m-fungsi',
  `group_kd` varchar(5) NOT NULL COMMENT 'kode group dari table group-user',
  `f_aktif` int(1) DEFAULT '1' COMMENT 'flag aktif user\ndefault 1 = aktif\n0 = non aktif',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user-id creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal create user',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam create user',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user update terakhir dari table user',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tanggal update terakhir ',
  `sys_jam_update` varchar(8) DEFAULT NULL COMMENT 'jam update terakhir',
  `sys_user_nonaktif` varchar(50) DEFAULT NULL COMMENT 'user-id yang menonaktifkan',
  `sys_tgl_nonaktif` datetime DEFAULT NULL COMMENT 'tgl non aktif',
  `sys_jam_nonaktif` varchar(8) DEFAULT NULL COMMENT 'jam  non aktif',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`user_id`),
  UNIQUE KEY `no-hp_UNIQUE` (`no_hp`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_groupuser_idx` (`group_kd`),
  KEY `fk_user_fungsi_idx` (`kd_fungsi`),
  CONSTRAINT `fk_user_fungsi` FOREIGN KEY (`kd_fungsi`) REFERENCES `m_fungsi` (`kd_fungsi`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_groupuser` FOREIGN KEY (`group_kd`) REFERENCES `group_user` (`group_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','66056926298b5fff0042cfd219737c2d6ea50fbebfd758a33fbe00517b2036d3','a','+1','aad@asad.ada','APV','USR',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ADM','9be362919e715f797266a63f9a18f492ad8da073d52b51c3244645a16fabe2e2','ADMIN','+911313','Admin@admin.ca','APV','ADMIN',1,'',NULL,'','',NULL,'','',NULL,'');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_divisi`
--

DROP TABLE IF EXISTS `user_divisi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_divisi` (
  `sys_id_user_divisi` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL COMMENT 'user id dari table user',
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdiv',
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode dept dari table departmen',
  `lok_kd` varchar(5) DEFAULT NULL COMMENT 'kode lokasi dari table lokasi\ntidak mandatory',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal creator ',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam creator HH24:mm:ss',
  PRIMARY KEY (`sys_id_user_divisi`,`user_id`,`divisi_kd`,`subdiv_kd`,`dept_kd`),
  KEY `fk_ud_user_idx` (`user_id`),
  CONSTRAINT `fk_ud_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='table user divisi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_divisi`
--

LOCK TABLES `user_divisi` WRITE;
/*!40000 ALTER TABLE `user_divisi` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_divisi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-13  2:37:16
