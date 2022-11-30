CREATE DATABASE  IF NOT EXISTS `gestao_epi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gestao_epi`;
-- MySQL dump 10.16  Distrib 10.1.28-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: gestao_epi
-- ------------------------------------------------------
-- Server version	10.1.28-MariaDB

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
-- Table structure for table `entregas`
--

DROP TABLE IF EXISTS `entregas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entregas` (
  `id_entrega` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_funcionario` int(11) NOT NULL,
  `id_epi` int(11) NOT NULL,
  `data_entrega` date NOT NULL,
  `data_devolucao` date DEFAULT NULL,
  `quant_entrega` int(11) NOT NULL,
  `code_entrega` varchar(10) DEFAULT NULL,
  `confirma_entrega` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_entrega`),
  UNIQUE KEY `code_entrega` (`code_entrega`),
  KEY `id_user` (`id_user`),
  KEY `id_funcionario` (`id_funcionario`),
  KEY `id_epi` (`id_epi`),
  CONSTRAINT `entregas_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `entregas_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id_funcionario`),
  CONSTRAINT `entregas_ibfk_3` FOREIGN KEY (`id_epi`) REFERENCES `epis` (`id_epi`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregas`
--

LOCK TABLES `entregas` WRITE;
/*!40000 ALTER TABLE `entregas` DISABLE KEYS */;
INSERT INTO `entregas` VALUES (36,1,1,1,'2022-11-20',NULL,1,'RQ3320',0),(37,1,1,2,'2022-11-23',NULL,1,'AS4369',0),(38,1,1,1,'2022-11-24',NULL,1,'NS3081',0),(39,1,2,1,'2022-11-24',NULL,1,'PO6185',0),(40,1,1,1,'2022-11-24',NULL,1,'UO8574',0),(41,1,8,1,'2022-11-24',NULL,1,'UR1394',0),(42,1,8,1,'2022-11-28','2022-11-28',1,NULL,1),(43,2,12,6,'2022-11-30',NULL,1,'SN5536',0),(44,2,12,7,'2022-11-30',NULL,1,'PV4818',0),(45,2,12,7,'2022-11-30',NULL,5,'TP5142',0);
/*!40000 ALTER TABLE `entregas` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER remove_quant_epi AFTER INSERT ON entregas FOR EACH ROW
BEGIN
	UPDATE epis SET quant_epi = quant_epi - new.quant_entrega WHERE id_epi = new.id_epi; 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER clear_code BEFORE UPDATE ON entregas FOR EACH ROW
BEGIN
	IF new.confirma_entrega IS TRUE THEN
		SET new.code_entrega = NULL;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `epis`
--

DROP TABLE IF EXISTS `epis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `epis` (
  `id_epi` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `nome_epi` varchar(250) NOT NULL,
  `ca_epi` varchar(25) DEFAULT NULL,
  `marca_epi` varchar(100) NOT NULL,
  `vida_util_epi` int(11) NOT NULL,
  `data_validade_epi` date NOT NULL,
  `quant_epi` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_epi`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `epis_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epis`
--

LOCK TABLES `epis` WRITE;
/*!40000 ALTER TABLE `epis` DISABLE KEYS */;
INSERT INTO `epis` VALUES (1,1,'LUVA DE COURO','5542','3M',10,'2023-10-29',12),(2,1,'ABAFADOR DE RUIDOS','23464','MUFFLIN',20,'2024-11-04',49),(3,1,'CAPACETE DE PROTEÇÃO','778854687','3M',365,'2029-11-14',109),(4,1,'BOTAS DE PROTEÇÃO','99555666','NIKE',100,'2022-12-31',10),(5,1,'AVENTAL DE BORRACHA','35798','MULTI',15,'2022-11-28',80),(6,2,'PROTETOR AURICULAR','3442','3M',80,'2025-11-05',29),(7,2,'MASCARA DE SEGURANÇA','1123','3M',2,'2025-11-05',94),(8,2,'KIT ALCOOL GEL','5422','GENERICO',70,'2022-11-16',10);
/*!40000 ALTER TABLE `epis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionarios` (
  `id_funcionario` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `reg` varchar(25) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `id_setor` int(11) NOT NULL,
  `func_email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_funcionario`),
  KEY `id_user` (`id_user`),
  KEY `id_setor` (`id_setor`),
  CONSTRAINT `funcionarios_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `funcionarios_ibfk_2` FOREIGN KEY (`id_setor`) REFERENCES `setores` (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
INSERT INTO `funcionarios` VALUES (1,1,'1','LEANDRO GARCIA',1,'leandrogarcia@gmail.com'),(2,1,'11','LUCAS DA SILVA',1,'lucasdasilvas@gmai.com'),(3,1,'15','EDUARDO DE ALCANTRA',1,'eduedu@gmail.com'),(4,1,'98','VINICIUS ALEGRE',1,'vivi0909@gmail.com'),(5,1,'56','ELIANDRO DA SILVA',1,'eliandro@gmail.com'),(6,1,'76','BANANA LIMPIO',5,'bananalimpio@gmail.com'),(7,1,'49','LETICIA DANIELA',2,'leticiadaniela@gmail.com'),(8,1,'1356757','MARCOS MAGALHÃES',1,'marcosvmagalhaes123@gmail.com'),(9,2,'1','ELIANA MOREIRA',6,'eliana.moreira@ifsp.edu.br'),(10,2,'2','SHEILA VENERO',6,'sheila.venero@ifsp.edu.br'),(11,2,'4','EVERTON SILVA',6,'everton.silva@ifsp.edu.br'),(12,2,'6','MARCOS MAGALHAES',7,'marcosvmagalhaes123@gmail.com');
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setores`
--

DROP TABLE IF EXISTS `setores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setores` (
  `id_setor` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `setor_codigo` varchar(25) NOT NULL,
  `setor_nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id_setor`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `setores_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setores`
--

LOCK TABLES `setores` WRITE;
/*!40000 ALTER TABLE `setores` DISABLE KEYS */;
INSERT INTO `setores` VALUES (1,1,'131','ALMOXARIFADO'),(2,1,'13','ADMINISTRAÇÃO'),(5,1,'36','LIMPEZA'),(6,2,'1','PROFESSORES'),(7,2,'2','ALUNOS');
/*!40000 ALTER TABLE `setores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `senha` varchar(250) NOT NULL,
  `empresa` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@admin.com','Admin','5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8','Empresa Teste'),(2,'marcos.magalhaes@aluno.ifsp.edu.br','Marcos Magalhães','5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8','IFSP Campinas');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gestao_epi'
--

--
-- Dumping routines for database 'gestao_epi'
--
/*!50003 DROP PROCEDURE IF EXISTS `confirm_entrega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `confirm_entrega`(codigo VARCHAR(10))
BEGIN
	DECLARE codigo_entrega VARCHAR(10);
    SELECT code_entrega FROM entregas WHERE code_entrega = codigo INTO codigo_entrega;
    
    IF codigo = codigo_entrega THEN
		SELECT entregas.id_entrega, funcionarios.nome, epis.nome_epi, epis.vida_util_epi, entregas.data_entrega, entregas.data_devolucao, entregas.quant_entrega, entregas.confirma_entrega
		FROM entregas, funcionarios, epis
		WHERE entregas.id_funcionario = funcionarios.id_funcionario AND entregas.id_epi = epis.id_epi AND entregas.code_entrega = codigo;
		UPDATE entregas SET confirma_entrega = TRUE WHERE code_entrega = codigo_entrega;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `entregas_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `entregas_user`(usuario INT)
BEGIN
	SELECT entregas.id_entrega, funcionarios.nome, epis.nome_epi, epis.vida_util_epi, entregas.data_entrega, entregas.data_devolucao, entregas.quant_entrega, entregas.confirma_entrega
	FROM entregas, funcionarios, epis
	WHERE entregas.id_funcionario = funcionarios.id_funcionario AND entregas.id_epi = epis.id_epi AND entregas.id_user = usuario AND entregas.data_devolucao IS NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `entregas_user_arquivadas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `entregas_user_arquivadas`(usuario INT, nome VARCHAR(100))
BEGIN
	SELECT entregas.id_entrega, funcionarios.nome, epis.nome_epi, epis.vida_util_epi, entregas.data_entrega, entregas.data_devolucao, entregas.quant_entrega, entregas.confirma_entrega
	FROM entregas, funcionarios, epis
	WHERE entregas.id_funcionario = funcionarios.id_funcionario AND entregas.id_epi = epis.id_epi AND entregas.id_user = usuario AND entregas.data_devolucao IS NOT NULL AND funcionarios.nome LIKE nome;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `entregas_week` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `entregas_week`(usuario INT, data_atual DATE)
BEGIN
	SELECT entregas.id_entrega, funcionarios.nome, epis.nome_epi, epis.vida_util_epi, entregas.data_entrega, entregas.data_devolucao, entregas.quant_entrega, entregas.confirma_entrega
	FROM entregas, funcionarios, epis
	WHERE entregas.id_funcionario = funcionarios.id_funcionario AND entregas.id_epi = epis.id_epi AND entregas.id_user = usuario AND entregas.data_devolucao IS NULL AND (epis.vida_util_epi * entregas.quant_entrega) - datediff(data_atual, entregas.data_entrega) <= 7;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `epis_to_venc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `epis_to_venc`(usuario INT, today DATE)
BEGIN
	SELECT * FROM epis WHERE id_user = usuario AND (quant_epi <= 30 OR datediff(data_validade_epi, today) <= 30);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-30  0:46:20
