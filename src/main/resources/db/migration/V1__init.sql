create table user(id int auto_increment primary key,
    name varchar(50),
    password varchar(50),
    phone varchar(50),
    email varchar(50)
enabled boolean NOT NULL

);

CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)

  );


create table sys(id int auto_increment primary key,
    temperature int,
    liter int,
    time time,
    enabled boolean NOT NULL,
    user_id int,
    foreign key (user_id) references user (id)
);