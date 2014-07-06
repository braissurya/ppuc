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
INSERT INTO `departmen` VALUES ('1','OPT','EYE','OUTLET OPTIK EYEVOLUTION',NULL,NULL,NULL,'ADM','2014-06-30 22:10:15'),('1','OPT','MLW','OUTLET OPTIK melawai2',NULL,NULL,NULL,'ADM','2014-06-30 22:10:15'),('2','OPT','MLW','TEST','',NULL,'','ADM','2014-06-30 22:11:25'),('3','OPT','MLW','OK','',NULL,'','ADM','2014-06-30 22:13:27'),('OIK','HOL','ACC','OK DEH',NULL,NULL,NULL,'ADM','2014-07-06 23:30:20'),('Z','HOL','ACC','UMUM',NULL,NULL,NULL,'ADM','2014-06-30 22:10:14');
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
INSERT INTO `divisi` VALUES ('HOL','HOLDING','ADM','2014-06-29 05:15:09','05:15','ADM','2014-06-29 03:56:44'),('OPT','OPTIK',NULL,NULL,NULL,'ADM','2014-06-29 05:15:10');
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
INSERT INTO `group_user` VALUES ('ADMIN','ADMIN',1,'',NULL,''),('USER','USER',2,'',NULL,'');
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
INSERT INTO `lokasi` VALUES ('OKA','HOL','ACC','Z','123 ada','Jawa Barat','CIREBON','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ADM','2014-07-06 23:27:40');
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
INSERT INTO `m_fungsi` VALUES ('APV','APROVAL',1,'',NULL,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='table list menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,0,'ROOT',NULL,0,1,'0.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'Admin',NULL,1,1,'0.1.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(3,2,'Audit Trail','master/audittrail',2,1,'0.1.1.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(4,2,'Log Email','master/logemail',2,2,'0.1.1.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(5,2,'Log SMS','master/logsms',2,3,'0.1.1.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(6,1,'Master',NULL,1,2,'0.1.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(7,6,'Divisi','master/divisi',2,1,'0.1.2.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(8,6,'Group User','master/groupuser',2,2,'0.1.2.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(9,6,'Sub Divisi','master/subdivisi',2,3,'0.1.2.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(10,6,'Departmen','master/departmen',2,4,'0.1.2.4',1,NULL,NULL,NULL,NULL,NULL,NULL),(11,6,'Lokasi','master/lokasi',2,5,'0.1.2.5',1,NULL,NULL,NULL,NULL,NULL,NULL),(12,6,'Menu','master/menu',2,6,'0.1.2.6',1,NULL,NULL,NULL,NULL,NULL,NULL),(13,6,'Fungsi User','master/mfungsi',2,7,'0.1.2.7',1,NULL,NULL,NULL,NULL,NULL,NULL),(14,6,'Group User','master/groupuser',2,8,'0.1.2.8',1,NULL,NULL,NULL,NULL,NULL,NULL),(15,6,'User','master/user',2,9,'0.1.2.9',1,NULL,NULL,NULL,NULL,NULL,NULL),(16,6,'User Divisi','master/userdivisi',2,10,'0.1.2.10',1,NULL,NULL,NULL,NULL,NULL,NULL),(17,6,'Group Biaya','master/groupbiaya',2,11,'0.1.2.11',1,NULL,NULL,NULL,NULL,NULL,NULL),(18,6,'Detail Biaya','master/detailbiaya',2,12,'0.1.2.12',1,NULL,NULL,NULL,NULL,NULL,NULL),(19,6,'Hak Biaya','master/hakbiaya',2,13,'0.1.2.13',1,NULL,NULL,NULL,NULL,NULL,NULL),(20,6,'Hak Approve','master/hakapprove',2,14,'0.1.2.14',1,NULL,NULL,NULL,NULL,NULL,NULL),(21,1,'Transaksi',NULL,1,3,'0.1.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(22,21,'Entry PPUC',NULL,2,1,'0.1.3.1',1,NULL,NULL,NULL,NULL,NULL,NULL),(23,21,'Confirm PPUC',NULL,2,2,'0.1.3.2',1,NULL,NULL,NULL,NULL,NULL,NULL),(24,21,'Approval Divisi',NULL,2,3,'0.1.3.3',1,NULL,NULL,NULL,NULL,NULL,NULL),(25,21,'Realisasi PPUC untuk Cabang',NULL,2,4,'0.1.3.4',1,NULL,NULL,NULL,NULL,NULL,NULL),(26,21,'Batal PPUC',NULL,2,5,'0.1.3.5',1,NULL,NULL,NULL,NULL,NULL,NULL),(27,21,'Confirm Realisasi PPUC untuk Cabang',NULL,2,6,'0.1.3.6',1,NULL,NULL,NULL,NULL,NULL,NULL),(28,21,'Entry PPUCVerifikasi Realisasi PPUC oleh OC',NULL,2,7,'0.1.3.7',1,NULL,NULL,NULL,NULL,NULL,NULL),(29,21,'Transfer Finance',NULL,2,8,'0.1.3.8',1,NULL,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `subdivisi` VALUES ('ACC','HOL','ACCOUNTING',NULL,NULL,NULL,'ADM','2014-06-29 11:35:29'),('EYE','OPT','OPTIK REVOLUTION',NULL,NULL,NULL,'ADM','2014-06-29 11:35:30'),('MLW','OPT','OPTIK MELAWAI',NULL,NULL,NULL,'ADM','2014-06-29 11:35:32');
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
INSERT INTO `sys_akses_menu` VALUES ('ADMIN',1,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',2,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',3,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',4,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',5,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',6,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',7,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',8,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',9,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',10,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',11,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',12,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',13,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',14,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',15,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',16,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',17,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',18,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',19,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',20,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',21,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',22,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',23,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',24,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',25,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',26,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',27,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',28,'SYSTEM','2014-06-30 23:15:42'),('ADMIN',29,'SYSTEM','2014-06-30 23:15:42');
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
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8 COMMENT='table audit trail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audittrail`
--

LOCK TABLES `sys_audittrail` WRITE;
/*!40000 ALTER TABLE `sys_audittrail` DISABLE KEYS */;
INSERT INTO `sys_audittrail` VALUES (112,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-29 03:56:43'),(113,'TRANS','ADD','Divisi','HOL','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-06-29 03:56:44'),(114,'TRANS','ADD','Divisi','OPT','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-06-29 03:56:45'),(115,'TRANS','DELETE','Divisi','OPT','0:0:0:0:0:0:0:1','DELETE DIVISI','ADM','2014-06-29 03:56:57'),(116,'EXIM','SUCCESS','Divisi','divisi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-29 05:15:09'),(117,'TRANS','UPDATE','Divisi','HOL','0:0:0:0:0:0:0:1','UPDATE DIVISI','ADM','2014-06-29 05:15:09'),(118,'TRANS','ADD','Divisi','OPT','0:0:0:0:0:0:0:1','ADD DIVISI','ADM','2014-06-29 05:15:10'),(119,'EXIM','SUCCESS','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-29 11:35:29'),(120,'TRANS','ADD','Subdivisi','ACC/HOL','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-06-29 11:35:30'),(121,'TRANS','ADD','Subdivisi','EYE/OPT','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-06-29 11:35:31'),(122,'TRANS','ADD','Subdivisi','MLW/OPT','0:0:0:0:0:0:0:1','ADD SUB DIVISI','ADM','2014-06-29 11:35:32'),(123,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:40:51'),(124,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:47:35'),(125,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:49:37'),(126,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 11:50:52'),(127,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>]','ADM','2014-06-29 17:02:42'),(128,'EXIM','FAILED','Subdivisi','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n pada baris ke-5<br/>, No DIVISI KD [ABC] found with this id.]','ADM','2014-06-29 17:08:17'),(129,'EXIM','FAILED','Subdivisi',NULL,'0:0:0:0:0:0:0:1','[File Upload is required]','ADM','2014-06-29 17:08:57'),(130,'EXIM','FAILED','Subdivisi',NULL,'0:0:0:0:0:0:0:1','[File Upload is required]','ADM','2014-06-29 17:09:33'),(131,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:30:40'),(132,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:31:58'),(133,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:32:22'),(134,'EXIM','FAILED','Departmen','divisi.csv','0:0:0:0:0:0:0:1','[ (filename= divisi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:36:42'),(135,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 21:36:58'),(136,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 22:02:59'),(137,'EXIM','FAILED','Departmen','subdivi.csv','0:0:0:0:0:0:0:1','[ (filename= subdivi.csv)\n Jumlah kolom pada baris ke-2 tidak sama dengan requirement]','ADM','2014-06-30 22:04:24'),(138,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-30 22:04:48'),(141,'EXIM','SUCCESS','Departmen','departmen.csv','0:0:0:0:0:0:0:1','IMPORT DATA SUCCESS','ADM','2014-06-30 22:10:14'),(142,'TRANS','ADD','Departmen','Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:10:14'),(143,'TRANS','ADD','Departmen','1/EYE/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:10:15'),(144,'TRANS','ADD','Departmen','1/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:10:15'),(145,'TRANS','ADD','Departmen','2/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:11:25'),(146,'TRANS','ADD','Departmen','3/MLW/OPT','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-06-30 22:13:27'),(147,'TRANS','ADD','Lokasi','OKA/Z/ACC/HOL','0:0:0:0:0:0:0:1','ADD Lokasi','ADM','2014-07-06 23:27:41'),(148,'TRANS','ADD','Departmen','OIK/ACC/HOL','0:0:0:0:0:0:0:1','ADD DEPARTMENT','ADM','2014-07-06 23:30:20');
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
) ENGINE=InnoDB AUTO_INCREMENT=406 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audittrail_detail`
--

LOCK TABLES `sys_audittrail_detail` WRITE;
/*!40000 ALTER TABLE `sys_audittrail_detail` DISABLE KEYS */;
INSERT INTO `sys_audittrail_detail` VALUES (113,279,'upload','com.melawai.ppuc.model.Upload@3a2bb9b6',NULL,'ADM','2014-06-29 03:56:44'),(113,280,'user_create','ADM',NULL,'ADM','2014-06-29 03:56:44'),(113,281,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 03:56:44'),(113,282,'tgl_create','Sun Jun 29 03:56:44 ICT 2014',NULL,'ADM','2014-06-29 03:56:45'),(113,283,'divisi_kd','HOL',NULL,'ADM','2014-06-29 03:56:45'),(113,284,'divisi_nm','HOLDING',NULL,'ADM','2014-06-29 03:56:45'),(114,285,'upload','com.melawai.ppuc.model.Upload@11afd356',NULL,'ADM','2014-06-29 03:56:45'),(114,286,'tgl_create','Sun Jun 29 03:56:45 ICT 2014',NULL,'ADM','2014-06-29 03:56:46'),(114,287,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 03:56:46'),(114,288,'divisi_nm','OPTIK',NULL,'ADM','2014-06-29 03:56:46'),(114,289,'user_create','ADM',NULL,'ADM','2014-06-29 03:56:46'),(114,290,'divisi_kd','OPT',NULL,'ADM','2014-06-29 03:56:46'),(115,291,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 03:56:57'),(115,292,'upload','com.melawai.ppuc.model.Upload@15e3592b',NULL,'ADM','2014-06-29 03:56:57'),(115,293,'divisi_nm','OPTIK',NULL,'ADM','2014-06-29 03:56:57'),(115,294,'divisi_kd','OPT',NULL,'ADM','2014-06-29 03:56:57'),(115,295,'user_create','ADM',NULL,'ADM','2014-06-29 03:56:57'),(115,296,'tgl_create','Sun Jun 29 03:56:45 ICT 2014',NULL,'ADM','2014-06-29 03:56:57'),(117,297,'upload','com.melawai.ppuc.model.Upload@3899bc9a','com.melawai.ppuc.model.Upload@752d3f7d','ADM','2014-06-29 05:15:09'),(117,298,'jam_update','05:15',NULL,'ADM','2014-06-29 05:15:09'),(117,299,'user_create',NULL,'ADM','ADM','2014-06-29 05:15:09'),(117,300,'user_update','ADM',NULL,'ADM','2014-06-29 05:15:09'),(117,301,'tgl_update','Sun Jun 29 05:15:09 ICT 2014',NULL,'ADM','2014-06-29 05:15:09'),(118,302,'serialVersionUID','4205222752833921897',NULL,'ADM','2014-06-29 05:15:10'),(118,303,'tgl_create','Sun Jun 29 05:15:10 ICT 2014',NULL,'ADM','2014-06-29 05:15:10'),(118,304,'divisi_nm','OPTIK',NULL,'ADM','2014-06-29 05:15:10'),(118,305,'user_create','ADM',NULL,'ADM','2014-06-29 05:15:10'),(118,306,'upload','com.melawai.ppuc.model.Upload@59162f2f',NULL,'ADM','2014-06-29 05:15:10'),(118,307,'divisi_kd','OPT',NULL,'ADM','2014-06-29 05:15:10'),(120,308,'divisi_kd','HOL',NULL,'ADM','2014-06-29 11:35:30'),(120,309,'upload','com.melawai.ppuc.model.Upload@4df80836',NULL,'ADM','2014-06-29 11:35:30'),(120,310,'subdiv_nm','ACCOUNTING',NULL,'ADM','2014-06-29 11:35:30'),(120,311,'subdiv_kd','ACC',NULL,'ADM','2014-06-29 11:35:30'),(120,312,'user_create','ADM',NULL,'ADM','2014-06-29 11:35:30'),(120,313,'tgl_create','Sun Jun 29 11:35:29 ICT 2014',NULL,'ADM','2014-06-29 11:35:30'),(121,314,'upload','com.melawai.ppuc.model.Upload@4defbda8',NULL,'ADM','2014-06-29 11:35:31'),(121,315,'subdiv_nm','OPTIK REVOLUTION',NULL,'ADM','2014-06-29 11:35:31'),(121,316,'tgl_create','Sun Jun 29 11:35:30 ICT 2014',NULL,'ADM','2014-06-29 11:35:31'),(121,317,'user_create','ADM',NULL,'ADM','2014-06-29 11:35:31'),(121,318,'subdiv_kd','EYE',NULL,'ADM','2014-06-29 11:35:32'),(121,319,'divisi_kd','OPT',NULL,'ADM','2014-06-29 11:35:32'),(122,320,'user_create','ADM',NULL,'ADM','2014-06-29 11:35:32'),(122,321,'tgl_create','Sun Jun 29 11:35:32 ICT 2014',NULL,'ADM','2014-06-29 11:35:32'),(122,322,'subdiv_kd','MLW',NULL,'ADM','2014-06-29 11:35:32'),(122,323,'subdiv_nm','OPTIK MELAWAI',NULL,'ADM','2014-06-29 11:35:32'),(122,324,'upload','com.melawai.ppuc.model.Upload@486aee33',NULL,'ADM','2014-06-29 11:35:32'),(122,325,'divisi_kd','OPT',NULL,'ADM','2014-06-29 11:35:32'),(142,342,'dept_nm','UMUM',NULL,'ADM','2014-06-30 22:10:14'),(142,343,'tgl_create','Mon Jun 30 22:10:14 ICT 2014',NULL,'ADM','2014-06-30 22:10:14'),(142,344,'divisi_kd','HOL',NULL,'ADM','2014-06-30 22:10:14'),(142,345,'dept_kd','Z',NULL,'ADM','2014-06-30 22:10:14'),(142,346,'user_create','ADM',NULL,'ADM','2014-06-30 22:10:14'),(142,347,'upload','com.melawai.ppuc.model.Upload@4c693c42',NULL,'ADM','2014-06-30 22:10:14'),(142,348,'subdiv_kd','ACC',NULL,'ADM','2014-06-30 22:10:14'),(142,349,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:10:14'),(143,350,'dept_kd','1',NULL,'ADM','2014-06-30 22:10:15'),(143,351,'subdiv_kd','EYE',NULL,'ADM','2014-06-30 22:10:15'),(143,352,'user_create','ADM',NULL,'ADM','2014-06-30 22:10:15'),(143,353,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:10:15'),(143,354,'tgl_create','Mon Jun 30 22:10:15 ICT 2014',NULL,'ADM','2014-06-30 22:10:15'),(143,355,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:10:15'),(143,356,'upload','com.melawai.ppuc.model.Upload@44d0dc1d',NULL,'ADM','2014-06-30 22:10:15'),(143,357,'dept_nm','OUTLET OPTIK EYEVOLUTION',NULL,'ADM','2014-06-30 22:10:15'),(144,358,'upload','com.melawai.ppuc.model.Upload@634284f3',NULL,'ADM','2014-06-30 22:10:15'),(144,359,'dept_nm','OUTLET OPTIK melawai2',NULL,'ADM','2014-06-30 22:10:15'),(144,360,'tgl_create','Mon Jun 30 22:10:15 ICT 2014',NULL,'ADM','2014-06-30 22:10:15'),(144,361,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:10:15'),(144,362,'subdiv_kd','MLW',NULL,'ADM','2014-06-30 22:10:15'),(144,363,'user_create','ADM',NULL,'ADM','2014-06-30 22:10:15'),(144,364,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:10:15'),(144,365,'dept_kd','1',NULL,'ADM','2014-06-30 22:10:15'),(145,366,'user_update','',NULL,'ADM','2014-06-30 22:11:25'),(145,367,'jam_update','',NULL,'ADM','2014-06-30 22:11:26'),(145,368,'upload','com.melawai.ppuc.model.Upload@207dd291',NULL,'ADM','2014-06-30 22:11:26'),(145,369,'dept_nm','TEST',NULL,'ADM','2014-06-30 22:11:26'),(145,370,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:11:26'),(145,371,'tgl_create','Mon Jun 30 22:11:25 ICT 2014',NULL,'ADM','2014-06-30 22:11:26'),(145,372,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:11:26'),(145,373,'dept_kd','2',NULL,'ADM','2014-06-30 22:11:26'),(145,374,'subdiv_kd','MLW',NULL,'ADM','2014-06-30 22:11:26'),(145,375,'user_create','ADM',NULL,'ADM','2014-06-30 22:11:26'),(146,376,'user_update','',NULL,'ADM','2014-06-30 22:13:27'),(146,377,'user_create','ADM',NULL,'ADM','2014-06-30 22:13:27'),(146,378,'subdiv_kd','MLW',NULL,'ADM','2014-06-30 22:13:27'),(146,379,'tgl_create','Mon Jun 30 22:13:27 ICT 2014',NULL,'ADM','2014-06-30 22:13:27'),(146,380,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-06-30 22:13:27'),(146,381,'jam_update','',NULL,'ADM','2014-06-30 22:13:27'),(146,382,'divisi_kd','OPT',NULL,'ADM','2014-06-30 22:13:27'),(146,383,'dept_kd','3',NULL,'ADM','2014-06-30 22:13:27'),(146,384,'dept_nm','OK',NULL,'ADM','2014-06-30 22:13:27'),(146,385,'upload','com.melawai.ppuc.model.Upload@2a0a81fe',NULL,'ADM','2014-06-30 22:13:27'),(147,386,'email','',NULL,'ADM','2014-07-06 23:27:41'),(147,387,'propinsi','Jawa Barat',NULL,'ADM','2014-07-06 23:27:42'),(147,388,'user_create','ADM',NULL,'ADM','2014-07-06 23:27:42'),(147,389,'serialVersionUID','7503294106098421175',NULL,'ADM','2014-07-06 23:27:42'),(147,390,'tgl_create','Sun Jul 06 23:27:40 ICT 2014',NULL,'ADM','2014-07-06 23:27:42'),(147,391,'lok_kd','OKA',NULL,'ADM','2014-07-06 23:27:42'),(147,392,'upload','com.melawai.ppuc.model.Upload@52415085',NULL,'ADM','2014-07-06 23:27:42'),(147,393,'kota','CIREBON',NULL,'ADM','2014-07-06 23:27:42'),(147,394,'lok_nm','123 ada',NULL,'ADM','2014-07-06 23:27:42'),(147,395,'dept_kd','Z',NULL,'ADM','2014-07-06 23:27:42'),(147,396,'divisi_kd','HOL',NULL,'ADM','2014-07-06 23:27:42'),(147,397,'subdiv_kd','ACC',NULL,'ADM','2014-07-06 23:27:42'),(148,398,'dept_nm','OK DEH',NULL,'ADM','2014-07-06 23:30:20'),(148,399,'user_create','ADM',NULL,'ADM','2014-07-06 23:30:20'),(148,400,'subdiv_kd','ACC',NULL,'ADM','2014-07-06 23:30:20'),(148,401,'divisi_kd','HOL',NULL,'ADM','2014-07-06 23:30:20'),(148,402,'tgl_create','Sun Jul 06 23:30:20 ICT 2014',NULL,'ADM','2014-07-06 23:30:20'),(148,403,'dept_kd','OIK',NULL,'ADM','2014-07-06 23:30:20'),(148,404,'upload','com.melawai.ppuc.model.Upload@3af93f2a',NULL,'ADM','2014-07-06 23:30:20'),(148,405,'serialVersionUID','4539182142457484816',NULL,'ADM','2014-07-06 23:30:20');
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
INSERT INTO `user` VALUES ('ADM','bf16aecb54c55aaacdb760ec47caa6726046c0464d38536cbdf79a1540c1cf1f','ADMIN','+911313','Admin@admin.c','APV','ADMIN',1,'',NULL,'','',NULL,'','',NULL,''),('USER','c67fad3cdf157b0f92e23bbd26419834af2ae0a29defec8d65285fa4dc3e1274','User Biasa','+62911313','brais@ada.da','APV','ADMIN',NULL,'',NULL,'','',NULL,'','',NULL,'');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_divisi`
--

DROP TABLE IF EXISTS `user_divisi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_divisi` (
  `id_user_divisi` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id table user-divisi',
  `user_id` varchar(50) NOT NULL COMMENT 'user id dari table user',
  `divisi_kd` varchar(3) NOT NULL COMMENT 'kode divisi dari table divisi',
  `subdiv_kd` varchar(3) NOT NULL COMMENT 'kode subdiv dari table subdiv',
  `dept_kd` varchar(3) NOT NULL COMMENT 'kode dept dari table departmen',
  `lok_kd` varchar(5) DEFAULT NULL COMMENT 'kode lokasi dari table lokasi\ntidak mandatory',
  `sys_user_create` varchar(50) DEFAULT NULL COMMENT 'user creator dari table user',
  `sys_tgl_create` datetime DEFAULT NULL COMMENT 'tanggal creator ',
  `sys_jam_create` varchar(8) DEFAULT NULL COMMENT 'jam creator HH24:mm:ss',
  PRIMARY KEY (`id_user_divisi`,`user_id`,`divisi_kd`,`subdiv_kd`,`dept_kd`),
  KEY `fk_userdiv_divisi_idx` (`divisi_kd`),
  KEY `fk_userdiv_subdiv_idx` (`subdiv_kd`),
  KEY `fk_userdiv_dept_idx` (`dept_kd`),
  KEY `fk_userdiv_lokasi_idx` (`lok_kd`),
  KEY `fk_userdiv_user_idx` (`user_id`),
  CONSTRAINT `fk_userdiv_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userdiv_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userdiv_lokasi` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userdiv_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userdiv_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table user divisi';
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

-- Dump completed on 2014-07-06 23:50:51
