-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.20 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for mrbs
DROP DATABASE IF EXISTS `mrbs`;
CREATE DATABASE IF NOT EXISTS `mrbs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mrbs`;

-- Dumping structure for table mrbs.book_record
DROP TABLE IF EXISTS `book_record`;
CREATE TABLE IF NOT EXISTS `book_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `meeting_room_id` int(11) DEFAULT NULL,
  `start_time_index` int(11) DEFAULT NULL COMMENT '时间id',
  `date` date DEFAULT NULL COMMENT '预定日期',
  `end_time_index` int(11) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='预定记录';

-- Dumping data for table mrbs.book_record: ~4 rows (approximately)
/*!40000 ALTER TABLE `book_record` DISABLE KEYS */;
INSERT INTO `book_record` (`id`, `user_id`, `meeting_room_id`, `start_time_index`, `date`, `end_time_index`, `is_delete`) VALUES
	(14, 2, 1, 1, '2018-04-26', 1, 0),
	(15, 2, 1, 2, '2018-04-26', 6, 0),
	(16, 2, 2, 6, '2018-04-26', 11, 0),
	(17, 2, 2, 3, '2018-04-26', 4, 0);
/*!40000 ALTER TABLE `book_record` ENABLE KEYS */;

-- Dumping structure for table mrbs.meeting_room
DROP TABLE IF EXISTS `meeting_room`;
CREATE TABLE IF NOT EXISTS `meeting_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='会议室';

-- Dumping data for table mrbs.meeting_room: ~4 rows (approximately)
/*!40000 ALTER TABLE `meeting_room` DISABLE KEYS */;
INSERT INTO `meeting_room` (`id`, `name`, `site_id`) VALUES
	(1, 'no1', 1),
	(2, 'no2', 1),
	(3, 'no3', 2),
	(4, 'no4', 2),
	(5, 'no5', 3);
/*!40000 ALTER TABLE `meeting_room` ENABLE KEYS */;

-- Dumping structure for table mrbs.site
DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table mrbs.site: ~2 rows (approximately)
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` (`id`, `name`) VALUES
	(1, 'site1'),
	(2, 'site2'),
	(3, 'site3');
/*!40000 ALTER TABLE `site` ENABLE KEYS */;

-- Dumping structure for table mrbs.time
DROP TABLE IF EXISTS `time`;
CREATE TABLE IF NOT EXISTS `time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Dumping data for table mrbs.time: ~16 rows (approximately)
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` (`id`, `start_time`, `end_time`, `index`) VALUES
	(1, '08:01:00', '08:30:00', 1),
	(2, '08:31:00', '09:00:00', 2),
	(3, '09:01:00', '09:30:00', 3),
	(4, '09:31:00', '10:00:00', 4),
	(5, '10:01:00', '10:30:00', 5),
	(6, '10:31:00', '11:00:00', 6),
	(7, '11:01:00', '11:30:00', 7),
	(8, '11:31:00', '12:00:00', 8),
	(9, '14:31:00', '15:00:00', 12),
	(10, '15:01:00', '15:30:00', 13),
	(11, '15:31:00', '16:00:00', 14),
	(12, '16:01:00', '16:30:00', 15),
	(13, '16:31:00', '17:00:00', 16),
	(14, '13:01:00', '13:30:00', 9),
	(15, '13:31:00', '14:00:00', 10),
	(16, '14:01:00', '14:30:00', 11);
/*!40000 ALTER TABLE `time` ENABLE KEYS */;

-- Dumping structure for table mrbs.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `is_expired` tinyint(4) DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table mrbs.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `is_expired`, `site_id`) VALUES
	(2, 'nice', '123456', 0, 1),
	(3, 'hello', '123456', 0, 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
