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
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  
CREATE TABLE `nose`.`menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(100) NOT NULL,
  `precio` DECIMAL(6,2) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `external_id` VARCHAR(100) NOT NULL,
  `id_local` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_local_idx` (`id_local` ASC) VISIBLE,
  CONSTRAINT `id_local`
    FOREIGN KEY (`id_local`)
    REFERENCES `nose`.`local` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `nose`.`registros` (
  `id` INT NOT NULL,
  `id_user` INT(11) NOT NULL,
  `id_menu` INT(11) NULL,
  `cantidad` SMALLINT(3) NULL,
  `fecha` TIMESTAMP,
  `valor` DECIMAL(6,2) NOT NULL,
  `saldo_actual` DECIMAL(6,2) NULL,
  `saldo_final` DECIMAL(6,2) NULL,
  `external_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  INDEX `id_menu_idx` (`id_menu` ASC) VISIBLE,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `nose`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_menu`
    FOREIGN KEY (`id_menu`)
    REFERENCES `nose`.`menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `nose`.`login` (
  `id_cliente` INT(11) NULL,
  `id_local` INT(11) NULL,
  INDEX `id_local_idx` (`id_local` ASC) VISIBLE,
  INDEX `id_cliente_idx` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `id_local1`
    FOREIGN KEY (`id_local`)
    REFERENCES `nose`.`local` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_cliente1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `nose`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `cartera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_local` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `saldo` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_local_idx` (`id_local`),
  KEY `id_cliente_idx` (`id_cliente`),
  CONSTRAINT `id_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `id_local1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`)
) ;


