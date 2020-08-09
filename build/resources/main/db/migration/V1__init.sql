create table user(id int auto_increment primary key,
    name varchar(50),
    password varchar(50),
    phone varchar(50),
    email varchar(50)
);

create table sys(id int auto_increment primary key,
    temperature int,
    liter int,
    time time,
    enabled boolean NOT NULL,
    user_id int,
    foreign key (user_id) references user (id)
);