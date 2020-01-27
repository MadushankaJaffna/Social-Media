CREATE TABLE `adminNote` (
  `note` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `adminNote` VALUES ('Welcome All the Members to Liker');


CREATE TABLE `civilstatus` (
  `civilState` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `civilstatus` VALUES ('Single'),('In a relationship'),('Engaged'),('Married'),('In an open relationship'),('separated'),('Divorced'),('Widowed');


CREATE TABLE `complain` (
  `senderId` varchar(10) NOT NULL,
  `blockerId` varchar(10) NOT NULL,
  PRIMARY KEY (`blockerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `educationState` (
  `education` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `educationState` VALUES ('No formal Education'),('Student of School'),('Under Grade 11'),('Pass Ordinery Level'),('Pass Advanced Level'),('Deploma Level'),('Undergraduate'),('Graduated'),('Master Degree'),('PhD');

CREATE TABLE `user` (
`uId` varchar(100) NOT NULL,
`name` varchar(30) NOT NULL,
`gender` varchar(8) NOT NULL,
`email` varchar(20) NOT NULL,
`phoneNo` int(10) NOT NULL,
`civilStatus` varchar(50) NOT NULL,
`descrip` varchar(50) DEFAULT NULL,
`proPic` longblob,
`address` varchar(30) NOT NULL,
`homeTown` varchar(30) NOT NULL,
`birthDay` date NOT NULL,
`working` varchar(40) DEFAULT NULL,
`education` varchar(30) NOT NULL,
PRIMARY KEY (`uId`),
UNIQUE KEY `user_email_uindex` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `user` VALUES ('RE:001','Admin','Male','oska.coc@gmail.com',712385700,'In a relationship','Never Ever Give Up',NULL,'Balangoda','Balangoda','1993-07-07','Software engineer','Graduated');



CREATE TABLE `login` (
  `uid` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `roll` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`),
  KEY `login_user_uId_fk_2` (`uid`),
  CONSTRAINT `login_user_uId_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `login_user_uId_fk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `login` VALUES ('RE:001','oska.coc@gmail.com','MzIxOTowMTAyOTgxMzk1NjM2MDg3NzRA',1);


CREATE TABLE `notification` (
  `senderId` varchar(10) NOT NULL,
  `userID` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `postId` varchar(20) NOT NULL,
  PRIMARY KEY (`postId`,`senderId`),
  KEY `notification_user_uId_fk` (`senderId`),
  CONSTRAINT `notification_user_uId_fk` FOREIGN KEY (`senderId`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `payment` (
  `uId` varchar(100) NOT NULL,
  `name` varchar(30) NOT NULL,
  `cardNo` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` double(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `photoWall` (
  `userID` varchar(100) NOT NULL,
  `postID` varchar(20) NOT NULL,
  `image` longblob NOT NULL,
  `reactCount` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`postID`),
  UNIQUE KEY `photoWall_postID_uindex` (`postID`),
  KEY `photoWall_user_uId_fk_2` (`userID`),
  CONSTRAINT `photoWall_user_uId_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `photoWall_user_uId_fk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `wallPost` (
  `postId` varchar(20) NOT NULL,
  `reactionCount` int(11) DEFAULT NULL,
  `userId` varchar(100) NOT NULL,
  `desceription` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`postId`),
  UNIQUE KEY `wallPost_postId_uindex` (`postId`),
  KEY `wallPost_user_uId_fk_2` (`userId`),
  CONSTRAINT `wallPost_user_uId_fk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


