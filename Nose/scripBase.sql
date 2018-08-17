CREATE DATABASE  IF NOT EXISTS `id6826336_nose` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `id6826336_nose`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: id6826336_nose
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `ci` varchar(10) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `user` varchar(50) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ci` (`ci`),
  UNIQUE KEY `user` (`user`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--


/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Brayan Alejandro','Cobos Cabrera','1105399065','balec@g.com','Catamayo','0984986586','95076343-5e19-49bb-9e8f-484017192ffe','ale','cliente',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Table structure for table `local`
--

DROP TABLE IF EXISTS `local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `ruc` varchar(15) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ruc` (`ruc`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local`
--


/*!40000 ALTER TABLE `local` DISABLE KEYS */;
INSERT INTO `local` VALUES (1,'Mama Lore','Catamayo','1102456783001','local','0997678345','877ded47-cb34-4e8e-b90d-8383f636f624',1),(2,'Viejo Lucho','Catamayo','1102827076001','local','072678231','ffb4c7c7-334a-4c82-bf90-734b93d651d8',1);
/*!40000 ALTER TABLE `local` ENABLE KEYS */;


--
-- Table structure for table `asociar`
--

DROP TABLE IF EXISTS `asociar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asociar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` tinyint(1) DEFAULT '1',
  `external_id` varchar(100) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_local` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_local` (`id_local`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `sesion_ibfk_1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`),
  CONSTRAINT `sesion_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asociar`
--


/*!40000 ALTER TABLE `asociar` DISABLE KEYS */;
/*!40000 ALTER TABLE `asociar` ENABLE KEYS */;


--
-- Table structure for table `cartera`
--

DROP TABLE IF EXISTS `cartera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_local` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `saldo` decimal(6,2) NOT NULL,
  `external_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_local` (`id_local`),
  CONSTRAINT `cartera_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `cartera_ibfk_2` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartera`
--



--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `id_local` int(11) NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_local` (`id_local`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'DESAYUNO',2.50,'Cafe con Tamal','4df243d8-1a13-417c-ae5d-71e5a17d5595',1,1,'2018-08-03 14:56:37','2018-08-03 14:56:37'),(2,'ALMUERZO',3.50,'Encebollado','b7661f60-b603-48ca-93fe-1450d6045c00',1,1,'2018-08-03 14:57:24','2018-08-03 14:57:24'),(3,'CENA',2.50,'Seco De Pollo','9eaeed12-2fe5-40cf-b34d-a0674293378c',1,1,'2018-08-03 14:58:17','2018-08-03 14:58:17'),(4,'ALMUERZO',6.50,'Cecina','696130a6-f690-4b26-a800-074ef12c6104',2,1,'2018-08-03 14:59:10','2018-08-03 14:59:10'),(5,'DESAYUNO',3.50,'Continental','17b269cc-3749-40c7-abd3-504dfad639b9',1,1,'2018-08-03 17:11:53','2018-08-03 17:11:53');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Table structure for table `registros`
--

DROP TABLE IF EXISTS `registros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_menu` int(11) DEFAULT NULL,
  `cantidad` smallint(3) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `valor` decimal(6,2) NOT NULL,
  `external_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `registros_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `registros_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registros`
--


/*!40000 ALTER TABLE `registros` DISABLE KEYS */;
INSERT INTO `registros` VALUES (1,1,1,1,'2018-08-03 15:02:29',2.50,'77753eb3-f09d-429b-8dbc-09d31f6f9a98'),(3,1,2,1,'2018-08-03 15:09:26',2.50,'cad3d944-257e-47df-a3c4-d21d61e52e3a'),(4,1,2,1,'2018-08-03 15:22:00',7.00,'864e3266-9772-474e-8fbc-e348dd90a8bf'),(5,1,2,10,'2018-08-03 15:22:12',25.00,'4afd9c6d-bb66-4acc-a863-dfaedfe0094d');
/*!40000 ALTER TABLE `registros` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-03 18:30:19
