create table TECHNICIAN(
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar NOT NULL ,
  `last_name` varchar NOT NULL ,
  `email` varchar NOT NULL ,
   PRIMARY KEY (`ID`));

insert into TECHNICIAN (first_name,last_name,email) values
('John','Smith','john@gmail.com'),
('Erik','test','erik@gmail.com');


create table ASSIGNMENT(
  `id` int NOT NULL AUTO_INCREMENT,
  `technician_id` int NOT NULL ,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
   PRIMARY KEY (`ID`),
   FOREIGN KEY (technician_id) REFERENCES TECHNICIAN(id));
   
insert into ASSIGNMENT (technician_id,start_time,end_time) values
(1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
(2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

