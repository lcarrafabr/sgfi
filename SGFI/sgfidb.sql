# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: sgfidb
# ------------------------------------------------------
# Server version 5.4.1-beta-community

DROP DATABASE IF EXISTS `sgfidb`;
CREATE DATABASE `sgfidb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sgfidb`;

#
# Source for table comunidade
#

DROP TABLE IF EXISTS `comunidade`;
CREATE TABLE `comunidade` (
  `codComunidade` int(11) NOT NULL AUTO_INCREMENT,
  `nomeComunidade` varchar(80) DEFAULT NULL,
  `cnpj` varchar(19) DEFAULT NULL,
  `insc_Estadual` varchar(20) DEFAULT NULL,
  `endereco` varchar(80) DEFAULT NULL,
  `bairro` varchar(30) DEFAULT NULL,
  `cidade` varchar(15) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `uf` char(2) DEFAULT NULL,
  `telefone1` varchar(14) DEFAULT NULL,
  `telefone2` varchar(14) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `observacao` text,
  PRIMARY KEY (`codComunidade`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Dumping data for table comunidade
#

LOCK TABLES `comunidade` WRITE;
/*!40000 ALTER TABLE `comunidade` DISABLE KEYS */;
INSERT INTO `comunidade` VALUES (9,'Igreja Presbiteriana','111.111.111/1111-11','1111111111','Rua Dr° Lobato, 469','centro','Pinheiros','29.980-000','ES','(27) 3765-1234','(27) 9876-5432','igreja@email.com.br','Todos esses dados são ficticios, foram usados aqui apenas para teste');
/*!40000 ALTER TABLE `comunidade` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table contas
#

DROP TABLE IF EXISTS `contas`;
CREATE TABLE `contas` (
  `codConta` int(11) NOT NULL AUTO_INCREMENT,
  `codComunidade` int(11) DEFAULT NULL,
  `banco` varchar(50) DEFAULT NULL,
  `agencia` int(15) DEFAULT NULL,
  `digitoAgencia` int(4) DEFAULT NULL,
  `numeroConta` int(11) DEFAULT NULL,
  `digitoConta` varchar(4) DEFAULT NULL,
  `ativo` varchar(3) DEFAULT NULL,
  `conta` varchar(50) DEFAULT NULL,
  `numeroBanco` int(15) DEFAULT NULL,
  `observacao` text,
  PRIMARY KEY (`codConta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Dumping data for table contas
#

LOCK TABLES `contas` WRITE;
/*!40000 ALTER TABLE `contas` DISABLE KEYS */;
INSERT INTO `contas` VALUES (7,9,'Banco do Brasil',2451,1,12835,'1','Sim','Conta Poupança',154,'Conta bancária');
INSERT INTO `contas` VALUES (8,9,'Caixa I',0,0,0,'','Sim','Dizimo',0,'Caixa urna, onde fica o dinheiro antes deir para o banco');
INSERT INTO `contas` VALUES (9,9,'Banco Sicoob I',3009,1,13508,'8','Não','Conta Corrente',1004,'segunda conta bancária\nteste setAlterar');
/*!40000 ALTER TABLE `contas` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table livro_caixa
#

DROP TABLE IF EXISTS `livro_caixa`;
CREATE TABLE `livro_caixa` (
  `codLanc` int(11) NOT NULL AUTO_INCREMENT,
  `codConta` int(11) DEFAULT NULL,
  `codPessoa` int(11) DEFAULT NULL,
  `codComunidade` int(11) DEFAULT NULL,
  `numDoc` int(11) DEFAULT NULL,
  `tipoDoc` varchar(50) DEFAULT NULL,
  `historico` varchar(100) DEFAULT NULL,
  `dataLanc` date DEFAULT NULL,
  `anoRef` int(11) DEFAULT NULL,
  `valor` double(10,2) DEFAULT NULL,
  `mesRef` int(11) DEFAULT NULL,
  PRIMARY KEY (`codLanc`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

#
# Dumping data for table livro_caixa
#

LOCK TABLES `livro_caixa` WRITE;
/*!40000 ALTER TABLE `livro_caixa` DISABLE KEYS */;
INSERT INTO `livro_caixa` VALUES (1,7,41,0,0,'Deposito','Deposito ','2011-05-08',NULL,100,NULL);
INSERT INTO `livro_caixa` VALUES (2,7,42,0,0,'Deposito','Deposito Testando deposito','2011-05-08',NULL,100,NULL);
INSERT INTO `livro_caixa` VALUES (3,7,42,0,0,'Deposito','Deposito testando centavos','2011-05-08',NULL,100.52,NULL);
INSERT INTO `livro_caixa` VALUES (4,8,43,0,0,'Deposito','Deposito Dizimo','2011-05-08',NULL,450,NULL);
INSERT INTO `livro_caixa` VALUES (5,8,41,0,0,'Deposito','Deposito ','2011-05-08',NULL,100,NULL);
INSERT INTO `livro_caixa` VALUES (6,7,41,9,0,'Deposito','Deposito Dizimo','2011-05-10',NULL,2000,NULL);
INSERT INTO `livro_caixa` VALUES (7,7,42,9,0,'Deposito','Deposito Teste','2011-05-10',NULL,1500,NULL);
INSERT INTO `livro_caixa` VALUES (8,7,41,9,0,'Deposito','Deposito teste numDoc','2011-05-10',NULL,100,NULL);
INSERT INTO `livro_caixa` VALUES (9,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,0.5,NULL);
INSERT INTO `livro_caixa` VALUES (10,7,41,9,0,'Deposito','Deposito teste numDoc','2011-05-10',NULL,5,NULL);
INSERT INTO `livro_caixa` VALUES (11,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,1,NULL);
INSERT INTO `livro_caixa` VALUES (12,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,1,NULL);
INSERT INTO `livro_caixa` VALUES (13,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,5000,NULL);
INSERT INTO `livro_caixa` VALUES (14,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,1,NULL);
INSERT INTO `livro_caixa` VALUES (15,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,1,NULL);
INSERT INTO `livro_caixa` VALUES (16,7,41,9,0,'Deposito','Deposito ','2011-05-10',NULL,14,NULL);
INSERT INTO `livro_caixa` VALUES (17,7,41,9,0,'Deposito','Deposito ','2011-05-12',NULL,55,NULL);
INSERT INTO `livro_caixa` VALUES (18,7,41,9,145874,'Deposito','Deposito ','2011-05-12',NULL,1,NULL);
INSERT INTO `livro_caixa` VALUES (19,7,41,9,1458,'Nota Fiscal','Deposito ','2011-05-12',NULL,1,NULL);
INSERT INTO `livro_caixa` VALUES (20,8,41,9,12544,'Recibo','Deposito teste','2011-05-12',NULL,500,NULL);
INSERT INTO `livro_caixa` VALUES (21,8,42,9,1455,'Prolabore','Deposito ','2011-05-12',NULL,500,NULL);
/*!40000 ALTER TABLE `livro_caixa` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table pessoas
#

DROP TABLE IF EXISTS `pessoas`;
CREATE TABLE `pessoas` (
  `codPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `pessoa` varchar(40) DEFAULT NULL,
  `codComunidade` int(11) DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  `endereco` varchar(80) DEFAULT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `bairro` varchar(30) DEFAULT NULL,
  `tipoDizimo` varchar(20) DEFAULT NULL,
  `observacao` text,
  `cidade` varchar(15) DEFAULT NULL,
  `uf` char(2) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`codPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

#
# Dumping data for table pessoas
#

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES (41,'Luciano Carrafa Benfica',9,'2011-05-06','Rua henrique Ayres','469','Centro','Individual','teste','pinheiros','ES','(27) 9278-6473','vitiazze@gmail.com');
INSERT INTO `pessoas` VALUES (42,'Bybbyana Lissani Carrafa',9,'2011-05-06','Rua monte alverner','469','galileia','Familiar','cadastro de bya','pinheiros','ES','(27) 9278-6473','vitiazze@gmail.com');
INSERT INTO `pessoas` VALUES (43,'Jessica batista martins',9,'2011-05-06','Rua dr lobato','765','Centro','Individual','cadastro jessica','Pinheiros','ES','(27) 9988-5566','jessica@jessica.com.br');
INSERT INTO `pessoas` VALUES (44,'Cristiano Carrafa Benfica',9,'2011-05-06','Rua Monte alverner','469','Galileia','Individual','','Pinheiros','ES','(27) 9966-8855','');
INSERT INTO `pessoas` VALUES (45,'Geucinéia Carrafa',9,'2011-05-07','Rua Monte alverner ','165','Centro','Individual','teste setAlterar','pinheiros','ES','(27) 9632-5874','sem e-mail');
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
