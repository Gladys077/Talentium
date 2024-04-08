-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.29 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS `homeservice`;
-- Volcando estructura de base de datos para homeservice
CREATE DATABASE IF NOT EXISTS `homeservice` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `homeservice`;

-- Volcando estructura para tabla homeservice.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `cliente_usuario_id` int DEFAULT NULL,
  `cliente_direccion_id` int DEFAULT NULL,
  `clasificacion` tinyint DEFAULT NULL,
  `compra_id` int DEFAULT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`),
  KEY `cliente_usuario_id_idx` (`cliente_usuario_id`),
  KEY `cliente_direccion_id_idx` (`cliente_direccion_id`),
  KEY `compra_id_idx` (`compra_id`),
  CONSTRAINT `cliente_direccion_id` FOREIGN KEY (`cliente_direccion_id`) REFERENCES `direcciones` (`id`),
  CONSTRAINT `cliente_usuario_id` FOREIGN KEY (`cliente_usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `compra_id` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.compras
CREATE TABLE IF NOT EXISTS `compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `compra_producto_id` int DEFAULT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `compra_producto_id_idx` (`compra_producto_id`),
  CONSTRAINT `compra_producto_id` FOREIGN KEY (`compra_producto_id`) REFERENCES `compras_productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.compras_productos
CREATE TABLE IF NOT EXISTS `compras_productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `producto_id` int DEFAULT NULL,
  `cantidad` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `producto_id_idx` (`producto_id`),
  CONSTRAINT `producto_id` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.direcciones
CREATE TABLE IF NOT EXISTS `direcciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `localidad` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.ordenes
CREATE TABLE IF NOT EXISTS `ordenes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) NOT NULL,
  `profesional_id` int DEFAULT NULL,
  `descripcion_profesional` varchar(1500),
  `precio` decimal(18,2) DEFAULT NULL,
  `fecha` date NOT NULL,
  `ESTADO` enum('Inicial','Pendiente','Aprobada','Finalizada') NOT NULL DEFAULT 'Inicial',
  `cliente_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `profesional_id_idx` (`profesional_id`),
  KEY `cliente_id` (`cliente_id`),
  CONSTRAINT `ordenes_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `profesional_id` FOREIGN KEY (`profesional_id`) REFERENCES `profesionales` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `precio` decimal(18,2) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `cantidad` int NOT NULL DEFAULT '1',
  `foto` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='		';

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.profesionales
CREATE TABLE IF NOT EXISTS `profesionales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `cuit` varchar(45) NOT NULL,
  `profesional_usuario_id` int DEFAULT NULL,
  `PROFESION` enum('OTHERS', 'PLUMBER', 'ELECTRICIAN', 'CONSTRUCTION_WORKER', 'PAINTER', 'GARDENER', 'GASMAN', 'TEACHER', 'PROGRAMMER', 'DESIGNER', 'NANNY') NOT NULL DEFAULT 'OTHERS',
  `profesional_direccion_id` int DEFAULT NULL,
  `profesional_producto_id` int DEFAULT NULL,
  `cbu` varchar(45) NOT NULL,
  `clasificacion` int DEFAULT NULL,
  `profesional_orden_id` int DEFAULT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  `billetera` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cuit` (`cuit`),
  KEY `profesional_usuario_id_idx` (`profesional_usuario_id`),
  KEY `profesional_direccion_id_idx` (`profesional_direccion_id`),
  KEY `profesional_producto_id_idx` (`profesional_producto_id`),
  KEY `profesional_orden_id_idx` (`profesional_orden_id`),
  CONSTRAINT `profesional_direccion_id` FOREIGN KEY (`profesional_direccion_id`) REFERENCES `direcciones` (`id`),
  CONSTRAINT `profesional_orden_id` FOREIGN KEY (`profesional_orden_id`) REFERENCES `ordenes` (`id`),
  CONSTRAINT `profesional_producto_id` FOREIGN KEY (`profesional_producto_id`) REFERENCES `profesionales_productos` (`id`),
  CONSTRAINT `profesional_usuario_id` FOREIGN KEY (`profesional_usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.profesionales_productos
CREATE TABLE IF NOT EXISTS `profesionales_productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prof_producto_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prof_producto_id_idx` (`prof_producto_id`),
  CONSTRAINT `prof_producto_id` FOREIGN KEY (`prof_producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla homeservice.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `avatar` TEXT DEFAULT 'url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
sys

