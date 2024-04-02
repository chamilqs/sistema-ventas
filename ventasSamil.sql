-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ventasSamil
-- ------------------------------------------------------
-- Server version	8.0.34
DROP DATABASE IF EXISTS ventasSamil;
CREATE DATABASE ventasSamil;
USE ventasSamil;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` varchar(50) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nivelAcceso` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `caja`
--

DROP TABLE IF EXISTS `caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caja` (
  `id` int auto_increment,
  `monto_Actual` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precioU` float(10,2) DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  `prioridad` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `idProducto` varchar(50) NOT NULL,
  `entrada` int DEFAULT NULL,
  `salida` int DEFAULT NULL,
  `stock` int DEFAULT NULL,
  
PRIMARY KEY (`idProducto`),
CONSTRAINT `FK_Inventario_Producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id` varchar(50) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `telefonoproveedor`
--

DROP TABLE IF EXISTS `telefonoproveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefonoproveedor` (
	`id` int auto_increment,
  `idProveedor` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_TelefonoProveedor_Proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleproducto`
--

DROP TABLE IF EXISTS `detalleproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleproducto` (
	`id` int auto_increment,
  `idProducto` varchar(50) NOT NULL,
  `idProveedor` varchar(50) NOT NULL,
  primary key(`id`),
  KEY `FK_DetalleProducto_Proveedor` (`idProveedor`),
  CONSTRAINT `FK_DetalleProducto_Producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FK_DetalleProducto_Proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `id` varchar(50) NOT NULL,
  `idProveedor` varchar(50) NOT NULL,
  `total` float(10,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Compra_Proveedor` (`idProveedor`),
  CONSTRAINT `FK_Compra_Proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `compra_chk_1` CHECK ((`total` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `id` varchar(50) NOT NULL,
  `nombreCliente` varchar(100) DEFAULT NULL, -- Nuevo campo para el nombre del cliente
  `total` float(10,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `detalleventa`
--

DROP TABLE IF EXISTS `detalleventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleventa` (
	`id` int auto_increment,
  `idVenta` varchar(50) NOT NULL,
  `idProducto` varchar(50) NOT NULL,
  `cantidadP` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DetalleVenta_Venta` (`idVenta`),
  CONSTRAINT `FK_DetalleVenta_Venta` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuentaporcobrar`
--

DROP TABLE IF EXISTS `cuentaporcobrar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentaporcobrar` (
  `id` int auto_increment,
  `idVenta` varchar(50) not null,
  `deuda` float(10,2) DEFAULT NULL,
  `totalFaltante` float(10,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `descripcion` varchar(200) default null,

  PRIMARY KEY (`id`),
    KEY `FK_CuentaPorCobrar_Venta` (`idVenta`),
  CONSTRAINT `FK_CuentaPorCobrar_Venta` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuentaporpagar`
--

DROP TABLE IF EXISTS `cuentaporpagar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentaporpagar` (
  `id` int auto_increment,
  `idCompra` varchar(50),
  `idProveedor` varchar(50) NOT NULL,
  `deuda` float(10,2) DEFAULT NULL,
  `totalFaltante` float(10,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,  
  PRIMARY KEY (`id`),
  KEY `FK_CuentaPorPagar_Proveedor` (`idProveedor`),
  KEY `FK_CuentaPorPagar_Compra` (`idCompra`),
    CONSTRAINT `FK_CuentaPorPagar_Compra` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`id`),
  CONSTRAINT `FK_CuentaPorPagar_Proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detallecompra`
--

DROP TABLE IF EXISTS `detallecompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallecompra` (
	`id` int auto_increment,
  `idCompra` varchar(50) NOT NULL,
  `idProducto` varchar(50) NOT NULL,
  `cantidadP` int DEFAULT NULL,
  `precioCompra` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DetalleCompra_Compra` (`idCompra`),
  CONSTRAINT `FK_DetalleCompra_Compra` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `salidacaja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table SalidaCaja(
	id int primary key auto_increment,
	idCaja int not null,
    justificacion text default null,
    monto float(10,2),
    fecha datetime,
	KEY FK_salidaCaja_Caja (idCaja),
    constraint FK_salidaCaja_Caja foreign key (idCaja) references Caja(id)
);
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table Ingresos(
	id int primary key not null auto_increment,
    concepto text default null,
    monto float(10,2) not null,
	fecha datetime
);
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table Gastos(
	id int primary key not null auto_increment,
    concepto text default null,
    monto float(10,2) not null,
	fecha datetime
);
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table Historial(
	id int primary key not null auto_increment,
    idUsuario varchar(50) default null,
    login datetime default null,
    logout datetime default null,
    key FK_Historial_Usuario (idUsuario),
    constraint FK_Historial_Usuario foreign key (idUsuario) references Usuario(id)
);

DROP TABLE IF EXISTS `PagoCC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table PagoCC(
	id int primary key auto_increment,
    idVenta varchar(50) default null,
    monto float(10,2) default null,
    fecha datetime default null,
    key FK_PagoCC_Venta  (idVenta),
    constraint FK_PagoCC_Venta foreign key (idVenta) references Venta(id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `PagoCP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table PagoCP(
	id int primary key auto_increment,
    idCompra varchar(50) default null,
    monto float(10,2) default null,
    fecha datetime default null,
    key FK_PagoCP_Compra  (idCompra),
    constraint FK_PagoCP_Compra foreign key (idCompra) references Compra(id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `HistorialCaja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table HistorialCaja(
  `id` int auto_increment,
  `idCaja` int not null,
  `usuarioId` varchar(50) NOT NULL,
  `fecha_Apertura` datetime DEFAULT NULL,
  `monto_Apertura` decimal(10,2) DEFAULT NULL,
  `fecha_Cierre` datetime DEFAULT NULL,
  `monto_Cierre` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HistorialCaja_Usuario` (`usuarioId`),
  KEY `FK_HistorialCaja_Caja` (`idCaja`),
  CONSTRAINT `FK_HistorialCaja_Usuario` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_HistorialCaja_Caja` FOREIGN KEY (`idCaja`) REFERENCES `caja` (`id`)

);
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- TRIGEER DE Producto
DROP PROCEDURE IF EXISTS trg_Insert_Producto;
DELIMITER //

create trigger trg_Insert_Producto after insert on Producto
for each row
begin
	INSERT INTO inventario (idProducto,entrada,salida,stock)
    VALUES (NEW.id,0,0,0);
end;
//
DELIMITER ;

-- TRIGEER DE PAGOCC
DROP PROCEDURE IF EXISTS trg_Insert_PagoCC;
DELIMITER //

DELIMITER //

DELIMITER //
CREATE TRIGGER trg_Insert_PagoCC AFTER INSERT ON PagoCC
FOR EACH ROW
BEGIN
    DECLARE monto FLOAT(10,2);
    DECLARE resultante FLOAT(10,2);
    DECLARE faltante FLOAT(10,2);
    DECLARE conceptoT VARCHAR(100);
    DECLARE cliente VARCHAR(100);

    SET monto = NEW.monto;

    SELECT totalFaltante INTO faltante FROM cuentaporcobrar WHERE idVenta = NEW.idVenta;

    SET resultante = faltante - monto;

    UPDATE cuentaporcobrar SET totalFaltante = resultante WHERE idVenta = NEW.idVenta;

    SELECT nombreCliente INTO cliente FROM venta WHERE id = NEW.idVenta;

    SET conceptoT = CONCAT('Pago de ', cliente);

    INSERT INTO ingresos (concepto, monto, fecha) 
    VALUES (conceptoT, NEW.monto, NEW.fecha);
END;
//
DELIMITER ;


-- TRIGEER DE PAGOCP
DROP PROCEDURE IF EXISTS trg_Insert_PagoCP;
DELIMITER //

create trigger trg_Insert_PagoCP after insert on PagoCP
for each row
begin
		declare monto float(10,2);
		declare resultante float(10,2);
        declare proveedor varchar(50);
        declare conceptoT varchar (100);
		DECLARE faltante FLOAT(10,2);
		set monto = new.monto;
        
		SELECT totalFaltante INTO faltante FROM cuentaporpagar WHERE idCompra = NEW.idCompra;
        
        set resultante = faltante - monto;
        
        update cuentaporpagar set totalFaltante = resultante where idCompra = new.idCompra;
        
        SELECT p.nombre INTO proveedor FROM compra as c
        inner join proveedor as p on p.id = c.idProveedor
        WHERE c.id = NEW.idCompra;
        
        set conceptoT = concat('Pago a ', proveedor);

        
        insert into gastos(concepto,monto,fecha) 
        values(conceptoT,new.monto,new.fecha);
        
end;
//
DELIMITER ;

-- TRIGEER DE SalidaCaja
DROP PROCEDURE IF EXISTS trg_Insert_SalidaCaja;
DELIMITER //

create trigger trg_Insert_SalidaCaja after insert on SalidaCaja
for each row
begin
		declare montoV float(10,2);
		declare resultante float(10,2);
        declare cajaActual float(10,2);
        
		set montoV = new.monto;
        
        select monto_Actual into cajaActual from Caja where id = new.idCaja;
        
        set resultante = cajaActual - montoV;
        
        update caja set monto_Actual = resultante where id = new.idCaja;
        
        
end;
//
DELIMITER ;

-- TRIGEER DE detalleCompra
DROP PROCEDURE IF EXISTS trg_Insert_detalleCompra;
DELIMITER //

create trigger trg_Insert_detalleCompra after insert on detalleCompra
for each row
begin
		declare cantidad int;
        declare stockV int;
        declare resultante int;
        
        set cantidad = new.cantidadP;
        
        select stock into stockV from inventario where idProducto = new.idProducto;
        
        set resultante = stockV + cantidad;
        
        update inventario set stock = resultante, entrada = resultante where idProducto = new.idProducto;
     
end;
//
DELIMITER ;

-- TRIGEER DE detalleVenta
DROP PROCEDURE IF EXISTS trg_Insert_detalleVenta;
DELIMITER //

create trigger trg_Insert_detalleVenta after insert on detalleVenta
for each row
begin
		declare cantidad int;
        declare stockV int;
        declare salidaV int;
        declare resultanteStock int;
        declare resultanteSalida int;

        set cantidad = new.cantidadP;
        
        select stock into stockV from inventario where idProducto = new.idProducto;
		select salida into salidaV from inventario where idProducto = new.idProducto;

        set resultanteStock = stockV - cantidad;
         set resultanteSalida = salidaV + cantidad;

        update inventario set stock = resultanteStock, salida = resultanteSalida where idProducto = new.idProducto;
     
end;
//
DELIMITER ;

USE ventasSamil;


insert into Usuario(id,nombre,correo,password,nivelAcceso)
values('12','admin','admin@gmail.com','admin','admin');
insert into Usuario(id,nombre,correo,password,nivelAcceso)
values('13','user','user@gmail.com','user','user');
insert into Usuario(id,nombre,correo,password,nivelAcceso)
values('1','samil','samilq26@gmail.com','s','admin');


