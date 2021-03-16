CREATE DATABASE `userdb` ;
USE `userdb`;



--
-- Table structure for table `buyer`
--


 
CREATE TABLE `buyer` (
  `BUYERID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PHONENUMBER` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ISPRIVILEGED` varchar(1) DEFAULT NULL,
  `REWARDPOINTS` varchar(11) DEFAULT NULL,
  `ISACTIVE` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`BUYERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `buyer`
--
insert into buyer(BUYERID, NAME, EMAIL, PHONENUMBER, PASSWORD, ) values(1,'saurabh kumar','saurabh@gmail.com','7777766666','Kumar@w*3996');
insert into buyer(BUYERID, NAME, EMAIL, PHONENUMBER, PASSWORD) values(2,'Akash kumar','akash@gmail.com','7777766555','Akash@w*3996');

insert into buyer values(1,'saurabh kumar','saurabh@gmail.com','7777766666','Kumar@w*3996','N','1000','Y');
insert into buyer values(2,'Akash kumar','akash@gmail.com','7777766555','Akash@w*3996','N','0','Y');
--
-- Table structure for table `cart`
--


CREATE TABLE `cart` (
  `BUYERID` int(11) NOT NULL,
  `PRODID` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  PRIMARY KEY (`BUYERID`,`PRODID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `cart`
--






--
-- Table structure for table `seller`
--



CREATE TABLE `seller` (
  `SELLERID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PHONENUMBER` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ISACTIVE` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SELLERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




--
-- Dumping data for table `seller`
--

insert into seller(SELLERID, NAME, EMAIL, PHONENUMBER, PASSWORD) values(10,'Roshan kumar','mroshan@gmail.com','8484886578','Roshan@w*2338');
insert into seller(SELLERID, NAME, EMAIL, PHONENUMBER, PASSWORD) values(20,'Jack','jack@gmail.com','9898989898','Don@w*7777');



--
-- Table structure for table `wishlist`
--




CREATE TABLE `wishlist` (
  `BUYERID` int(11) NOT NULL,
  `PRODID` int(11) NOT NULL,
  PRIMARY KEY (`BUYERID`,`PRODID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `wishlist`
--

select * from buyer;
select * from seller;
select * from wishlist;
select * from cart;

insert into wishlist values (1,1);
insert into wishlist values (1,2);
insert into wishlist values (2,1);
insert into wishlist values (2,2);

insert into cart values (1,1,10);
insert into cart values (1,2,10);
