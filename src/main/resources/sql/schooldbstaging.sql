-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: schooldbstaging
-- ------------------------------------------------------
-- Server version	8.0.41

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

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cities_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Αθήνα'),(3,'Βόλος'),(7,'Δράμα'),(10,'Ηράκλειο'),(5,'Θεσσαλονίκη'),(9,'Καλαμάτα'),(6,'Κέρκυρα'),(4,'Λάρισσα'),(2,'Πάτρα'),(8,'Πύργος'),(11,'Χανιά');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `vat` varchar(45) NOT NULL,
  `father_name` varchar(45) NOT NULL,
  `phone_num` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `street_num` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  `zipcode` varchar(45) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_students_cities_city_id_idx` (`city_id`),
  KEY `idx_students_lastname` (`lastname`) /*!80000 INVISIBLE */,
  KEY `idx_students_vat` (`vat`) /*!80000 INVISIBLE */,
  KEY `idx_students_uuid` (`uuid`),
  CONSTRAINT `fk_students_cities_city_id` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Γεώργιος','Παναγιώτου','56783920','Στέφανος','6987654321','george@gmail.com','Σταδίου','55',2,'11223','c3b9d8e4-72fd-4d9f-bf32-1bde576f38aa','2025-03-11 14:52:00','2025-03-11 14:52:00'),(2,'Μαρία','Κολοκοτρώνη','908172635','Θεόδωρος','6976543210','maria@gmail.com','Αθήνας','34',6,'88990','d5e8f3a1-93c2-41cb-8f54-3a68d6a1b7d8','2025-03-11 14:52:00','2025-03-11 14:52:00'),(3,'Παναγιώτης','Σωτηρόπουλος','34215678','Δημήτρης','6932185476','panos@gmail.com','Λεωφόρος Συγγρού','88',4,'66778','e5f7d1c9-2bf6-4803-8f21-bdf987c1a2e9','2025-03-11 14:52:00','2025-03-11 14:52:00'),(4,'Ευαγγελία','Μαυρομιχάλη','12987465','Ιωάννης','6923456789','eva@gmail.com','Ομόνοιας','22',7,'23456','a9d3c4e1-73f1-45c6-8eb9-4d9f67a1b3c8','2025-03-11 14:52:00','2025-03-11 14:52:00'),(5,'Στέφανος','Καραγιάννης','78345612','Αντώνιος','6909876543','stefanos@gmail.com','Κηφισίας','101',3,'77788','b1c2d3e4-82b3-49f6-a9c2-3e7d8f1a9b6c','2025-03-11 14:52:00','2025-03-11 14:52:00'),(6,'Αικατερίνη','Βασιλείου','21436587','Νικόλαος','6987654098','katerina@gmail.com','Πανεπιστημίου','66',5,'54321','f9e8d7c6-5b4a-4321-9c87-6d5e4c3b2a1f','2025-03-11 14:52:00','2025-03-11 14:52:00'),(7,'Δημήτριος','Λεβέντης','90874532','Μιχαήλ','6935678901','dimitris@gmail.com','Βασιλίσσης Σοφίας','45',6,'33221','d3b7c8f9-1e2a-47f6-9c8b-5a4d6e7f2c1b','2025-03-11 14:52:00','2025-03-11 14:52:00'),(8,'Νικόλαος','Σταματίου','78234165','Πέτρος','6978123456','nikos@gmail.com','Ακαδημίας','29',2,'99887','c4d7e8f9-53b2-47c1-a9d8-6e5f4a3b2c1d','2025-03-11 14:52:00','2025-03-11 14:52:00'),(9,'Χριστίνα','Αναγνωστοπούλου','32456789','Εμμανουήλ','6956781234','christina@gmail.com','Λιοσίων','77',4,'76543','a7b9c8d3-2f1e-46a5-8d7c-4e3f6b2a1c9d','2025-03-11 14:52:00','2025-03-11 14:52:00'),(10,'Απόστολος','Διαμαντόπουλος','11223344','Σπυρίδων','6934567890','apostolos@gmail.com','Χαριλάου Τρικούπη','98',1,'22110','b3c9d7e8-41f2-49a6-a5d7-6e8f4c2b1a3d','2025-03-11 14:52:00','2025-03-11 14:52:00');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `vat` varchar(45) NOT NULL,
  `father_name` varchar(45) NOT NULL,
  `phone_num` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `street_num` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  `zipcode` varchar(45) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_teachers_cities_city_id_idx` (`city_id`),
  KEY `idx_teachers_lastname` (`lastname`) /*!80000 INVISIBLE */,
  KEY `idx_teachers_vat` (`vat`) /*!80000 INVISIBLE */,
  KEY `idx_teachers_uuid` (`uuid`),
  CONSTRAINT `fk_teachers_cities_city_id` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'Αθανάσιος','Ανδρούτσος','87654321','Ανδρέας','6935565765','a8ana@gmail.com','Πατησίων','76',7,'10434','5d24deb8-601d-451d-94f7-b89bf64b78e6','2025-03-10 12:32:45','2025-03-10 12:32:45'),(2,'Άννα','Γιαννούτσου','12345678','Κώ','12345678','anna@gmail.com','Γεωργούτσου','12',5,'67856','8ae1db31-7e12-4677-8097-961a26f6b664','2025-03-10 12:32:45','2025-03-10 12:32:45'),(3,'Μάκης','Καπέτης','987654321','Ευάγγελος','6935465768','makis@gmail.com','Πατησίων','76',1,'10434','619cdd10-2dfe-4eca-8d7f-abba6db5f625','2025-03-10 12:32:45','2025-03-10 12:32:45'),(4,'Νίκη','Γιαννούτσου','918273645','Αθανάσιος','6934564890','niki@gmail.com','Λαμπρούτσου','12',7,'65098','7c51ea7f-5c8b-43d0-a570-e9b10b04abc3','2025-03-10 12:32:45','2025-03-10 12:32:45'),(5,'Κωνσταντίνος','Ρούμπας','456782341','Κλεάνθης','69987678767','kostis@gmail.com','Φράγκου','37',3,'34567','f37c9f54-2156-42a3-8165-4f8ee692d221','2025-03-10 12:32:45','2025-03-10 12:32:45'),(6,'Ελένη','Λαμπρούτσου','9078563411','Λάμπρος','2111309876','eleni@aueb.gr','Αδριανής','12',7,'98076','1edc6e35-877f-4b9f-a73a-57d2e71c2a29','2025-03-10 12:32:45','2025-03-10 12:32:45'),(7,'Κυριάκος','Νικολαϊδης','76859401','Νίκος','90235674','kyriakos@gmail.com','Πατησίων','76',5,'89750','3fccd18d-667d-4733-aade-8e8c89df53db','2025-03-10 12:32:45','2025-03-10 12:32:45'),(8,'Ανδρέας','Ανδρούτσος','9812002345','Αθανάσιος','2103098765','andreas@gmail.com','Ανακούς','119',5,'10434','066aa896-448d-4cab-ac8a-4dccf2f2c71d','2025-03-10 12:32:45','2025-03-10 12:32:45'),(9,'Ηφαιστίων','Αλεξανδρής','656565637','Γρηγόριος','2109876567','ifaist@gmail.com','Ανακούς','77',1,'14341','f13d19d8-846f-47d9-bee0-add1ed1ad012','2025-03-10 12:32:45','2025-03-10 12:32:45'),(10,'Ανδρέας','Παπαδόπουλος','09090876','Ανδρέας','2109090908','andreas@gmail.com','Πατησίων','76',3,'10434','602305af-a5fe-4a65-bc20-57fc979dec14','2025-03-11 14:51:56','2025-03-11 14:51:56');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 17:52:09
