create database nose;
use nose;

CREATE TABLE `nose`.`local` (
  `id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(200) NULL DEFAULT NULL,
  `RUC` VARCHAR(15) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(15) NULL,
  `external_id` VARCHAR(100) NOT NULL);


CREATE TABLE `nose`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `ci` VARCHAR(10) NOT NULL,
  `correo` VARCHAR(50) NULL,
  `direccion` VARCHAR(200) NULL,
  `telefono` VARCHAR(15) NULL,
  `external_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
    
    CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `external_id` varchar(100) NOT NULL,
  `id_local` int(11) NOT NULL,
  `fecha_crea` timestamp NULL DEFAULT NULL,
  `fecha_act` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
) ;
    
    CREATE TABLE `nose`.`registros` (
  `id` INT NOT NULL,
  `id_cliente` INT(11) NOT NULL,
  `id_menu` INT(11) NULL,
  `cantidad` SMALLINT(3) NULL,
  `fecha` TIMESTAMP,
  `valor` DECIMAL(6,2) NOT NULL,
  `saldo_actual` DECIMAL(6,2) NULL,
  `saldo_final` DECIMAL(6,2) NULL,
  `external_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),

    FOREIGN KEY (`id_cliente`)
    REFERENCES `nose`.`cliente` (`id`),
   
 
    FOREIGN KEY (`id_menu`)
    REFERENCES `nose`.`menu` (`id`)
    );
    
    CREATE TABLE `nose`.`sesion` (
  `id_cliente` INT(11) NULL,
  `id_local` INT(11) NULL,
  
    FOREIGN KEY (`id_local`)
    REFERENCES `nose`.`local` (`id`)
    ,
  
    FOREIGN KEY (`id_cliente`)
    REFERENCES `nose`.`cliente` (`id`)
    );
    
    CREATE TABLE `cartera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_local` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `saldo` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`),
 
  FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
) ;




