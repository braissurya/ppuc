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
  PRIMARY KEY (`dept_kd`),
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
INSERT INTO `group_user` VALUES ('ADMIN','ADMIN',NULL,'',NULL,'');
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
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`kd_group`,`kd_biaya`),
  KEY `fk_hbiaya_divisi_idx` (`divisi_kd`),
  KEY `fk_hbiaya_subdiv_idx` (`subdiv_kd`),
  KEY `fk_hbiaya_groupbiaya_idx` (`kd_group`),
  KEY `fk_hbiaya_detbiaya_idx` (`kd_biaya`),
  CONSTRAINT `fk_hbiaya_detbiaya` FOREIGN KEY (`kd_biaya`) REFERENCES `detail_biaya` (`kd_biaya`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_groupbiaya` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbiaya_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `img_ppuc_h`
--

DROP TABLE IF EXISTS `img_ppuc_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `img_ppuc_h` (
  `divisi_kd` varchar(3) NOT NULL,
  `subdiv_kd` varchar(3) NOT NULL,
  `dept_kd` varchar(3) NOT NULL,
  `lok_kd` varchar(5) NOT NULL,
  `sys_no_ppuc` varchar(9) NOT NULL,
  `tgl_ppuc` date NOT NULL,
  `no_batch` varchar(10) DEFAULT NULL,
  `no_realisasi` varchar(25) NOT NULL,
  `tgl_realisasi` date DEFAULT NULL,
  `user_entry` varchar(50) DEFAULT NULL,
  `tgl_entry` datetime DEFAULT NULL,
  `jam_entry` varchar(8) DEFAULT NULL,
  `f_confirm` int(1) DEFAULT NULL,
  `sys_user_confirm` varchar(50) DEFAULT NULL,
  `sys_tgl_confirm` datetime DEFAULT NULL,
  `sys_jam_confirm` varchar(8) DEFAULT NULL,
  `file_image` longblob,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  `sys_file_name` varchar(100) DEFAULT NULL COMMENT 'kalau mau taruh di server',
  `sys_file_ext` varchar(45) DEFAULT NULL,
  `sys_file_no` varchar(10) DEFAULT NULL COMMENT 'no file pakai counter aja',
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`dept_kd`,`lok_kd`,`sys_no_ppuc`,`tgl_ppuc`,`no_realisasi`),
  KEY `fk_img_ppuc_divisi_idx` (`divisi_kd`),
  KEY `fk_img_ppuc_subdiv_idx` (`subdiv_kd`),
  KEY `fk_img_ppuc_dept_idx` (`dept_kd`),
  KEY `fk_img_ppuc_lokasi_idx` (`lok_kd`),
  CONSTRAINT `fk_img_ppuc_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_img_ppuc_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_img_ppuc_lokasi` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_img_ppuc_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table image ppuc-h\n\n**KETERANGAN BELUM LENGKAP';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img_ppuc_h`
--

LOCK TABLES `img_ppuc_h` WRITE;
/*!40000 ALTER TABLE `img_ppuc_h` DISABLE KEYS */;
/*!40000 ALTER TABLE `img_ppuc_h` ENABLE KEYS */;
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
  `f_tutup` varchar(45) DEFAULT NULL COMMENT '??',
  `tgl_tutup` datetime DEFAULT NULL COMMENT 'tanggal tutup',
  `counter` int(11) DEFAULT '0' COMMENT 'untuk counter di ppuc no\n4 digit',
  `max_counter` int(11) DEFAULT '9999' COMMENT 'max counter',
  `sys_user_update` varchar(50) DEFAULT NULL COMMENT 'user-id terakhir update dari table user',
  `sys_tgl_update` datetime DEFAULT NULL COMMENT 'tanggal update terakhir',
  `sys_jam_update` varchar(8) DEFAULT NULL COMMENT 'jam terakhir update format HH24:mm:ss',
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`lok_kd`),
  KEY `fk_lokasi_divisi_idx` (`divisi_kd`),
  KEY `fk_lokasi_subdiv_idx` (`subdiv_kd`),
  KEY `fk_lokasi_dept_idx` (`dept_kd`),
  CONSTRAINT `fk_lokasi_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lokasi_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lokasi_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table lokasi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lokasi`
--

LOCK TABLES `lokasi` WRITE;
/*!40000 ALTER TABLE `lokasi` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='table list menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,0,'ROOT',NULL,0,0,'0.0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'MASTER',NULL,1,1,'0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1,'TRANSAKSI',NULL,1,2,'0.1.2',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
SET NEW.sys_path = CONCAT(IFNULL((select sys_path from menu where sys_menu_id = NEW.parent), '0'), '.', New.urut) */;;
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
SET NEW.sys_path = CONCAT(IFNULL((select sys_path from menu where sys_menu_id = NEW.parent), '0'), '.', New.urut) */;;
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
  `sys_id_template_detail` int(11) NOT NULL AUTO_INCREMENT,
  `id_template` int(11) NOT NULL,
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
  `keterangan` text,
  `qty_real_cbg` int(11) DEFAULT NULL,
  `harga_real_cbg` double DEFAULT NULL,
  `total_real_cbg` double DEFAULT NULL,
  `qty_real_oc` int(11) DEFAULT NULL,
  `harga_real_oc` double DEFAULT NULL,
  `total_real_oc` double DEFAULT NULL,
  `nilai_charge` double DEFAULT NULL,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`divisi_kd`,`subdiv_kd`,`dept_kd`,`lok_kd`,`kd_biaya`,`sys_no_ppuc`,`tgl_ppuc`),
  KEY `fk_ppucd_divisi_idx` (`divisi_kd`),
  KEY `fk_ppucd_subdiv_idx` (`subdiv_kd`),
  KEY `fk_ppucd_dept_idx` (`dept_kd`),
  KEY `fk_ppucd_lokasi_idx` (`lok_kd`),
  KEY `fk_ppucd_groupbiaya_idx` (`kd_group`),
  KEY `fk_ppucd_detailbiaya_idx` (`kd_biaya`),
  CONSTRAINT `fk_ppucd_dept` FOREIGN KEY (`dept_kd`) REFERENCES `departmen` (`dept_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_detailbiaya` FOREIGN KEY (`kd_biaya`) REFERENCES `detail_biaya` (`kd_biaya`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_groupbiaya` FOREIGN KEY (`kd_group`) REFERENCES `group_biaya` (`kd_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_lokasi` FOREIGN KEY (`lok_kd`) REFERENCES `lokasi` (`lok_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ppucd_subdiv` FOREIGN KEY (`subdiv_kd`) REFERENCES `subdivisi` (`subdiv_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
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
  PRIMARY KEY (`subdiv_kd`),
  KEY `fk_subdiv_divisi_idx` (`divisi_kd`),
  CONSTRAINT `fk_subdiv_divisi` FOREIGN KEY (`divisi_kd`) REFERENCES `divisi` (`divisi_kd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table subdivisi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subdivisi`
--

LOCK TABLES `subdivisi` WRITE;
/*!40000 ALTER TABLE `subdivisi` DISABLE KEYS */;
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
  `sys_type_audit` varchar(10) DEFAULT NULL,
  `sys_model` varchar(50) DEFAULT NULL,
  `sys_model_id` varchar(50) DEFAULT NULL,
  `sys_ip` varchar(100) DEFAULT NULL,
  `sys_info` text,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_id_audittrail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table audit trail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audittrail`
--

LOCK TABLES `sys_audittrail` WRITE;
/*!40000 ALTER TABLE `sys_audittrail` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_audittrail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_audittrail_detail`
--

DROP TABLE IF EXISTS `sys_audittrail_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_audittrail_detail` (
  `sys_id_audittrail_detail` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_id_audittrail` bigint(20) NOT NULL,
  `sys_field` varchar(50) DEFAULT NULL,
  `sys_before` text,
  `sys_after` text,
  `sys_user_create` varchar(50) DEFAULT NULL,
  `sys_tgl_create` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_id_audittrail_detail`),
  KEY `fk_auditraildet_audittrail_idx` (`sys_id_audittrail`),
  CONSTRAINT `fk_auditraildet_audittrail` FOREIGN KEY (`sys_id_audittrail`) REFERENCES `sys_audittrail` (`sys_id_audittrail`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audittrail_detail`
--

LOCK TABLES `sys_audittrail_detail` WRITE;
/*!40000 ALTER TABLE `sys_audittrail_detail` DISABLE KEYS */;
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
INSERT INTO `user` VALUES ('ADM','bf16aecb54c55aaacdb760ec47caa6726046c0464d38536cbdf79a1540c1cf1f','ADMIN','','','APV','ADMIN',1,'',NULL,'','',NULL,'','',NULL,'');
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
  PRIMARY KEY (`id_user_divisi`),
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

-- Dump completed on 2014-06-24  1:28:47
