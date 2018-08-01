DROP DATABASE IF EXISTS `nose`;
CREATE DATABASE  IF NOT EXISTS `nose` ;
USE `nose`;


DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `ci` varchar(10) NOT NULL unique,
  `correo` varchar(50) DEFAULT NULL unique,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `user` varchar(50) NOT NULL unique,
  `clave` varchar(50) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `local`;
CREATE TABLE `local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `ruc` varchar(15) NOT NULL unique,
  `clave` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `cartera`;
CREATE TABLE `cartera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_local` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `saldo` decimal(6,2) NOT NULL,
  external_id varchar(100),
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_local` (`id_local`),
  CONSTRAINT `cartera_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `cartera_ibfk_2` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
);

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `id_local` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp,
  `updated_at` timestamp NULL DEFAULT current_timestamp,
  PRIMARY KEY (`id`),
  KEY `id_local` (`id_local`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
);

DROP TABLE IF EXISTS `registros`;
CREATE TABLE `registros` (
  `id` int(11) NOT NULL auto_increment,
  `id_cliente` int(11) NOT NULL,
  `id_menu` int(11) DEFAULT NULL,
  `cantidad` smallint(3) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT current_timestamp,
  `valor` decimal(6,2) NOT NULL,
  `external_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `registros_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `registros_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id`)
);


DROP TABLE IF EXISTS asociar;
CREATE TABLE `asociar` (
	id int primary key auto_increment,
    
    `created_at` timestamp NULL DEFAULT current_timestamp,
  `updated_at` timestamp NULL DEFAULT current_timestamp,
  estado boolean default true,
  `id_cliente` int(11) DEFAULT NULL,
  `id_local` int(11) DEFAULT NULL,
  KEY `id_local` (`id_local`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `sesion_ibfk_1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`),
  CONSTRAINT `sesion_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
);
