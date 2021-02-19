CREATE DATABASE IF NOT EXISTS blogDB;
USE blogDB;
-- DROP TABLE IF EXISTS `entity`;
-- CREATE TABLE `entity` (
-- 	`entity_id` INT(11) NOT NULL AUTO_INCREMENT,
-- 	PRIMARY KEY (`entity_id`)
-- );

DROP TABLE IF EXISTS `tb_blog`;
CREATE TABLE `tb_blog` (
`entity_id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(255) DEFAULT NULL,
`likes` boolean, `body` varchar(255) DEFAULT NULL, 
PRIMARY KEY (`entity_id`),
FOREIGN KEY (entity_id) REFERENCES entity(entity_id)
);

DROP TABLE IF EXISTS `tb_comments`;
CREATE TABLE `tb_comments` (
`entity_id` int(11) NOT NULL,
`comments_id` int(11) NOT NULL AUTO_INCREMENT, 
`comment` varchar(255) DEFAULT NULL,
PRIMARY KEY (`comments_id`, `entity_id`),
FOREIGN KEY (entity_id) REFERENCES entity(entity_id)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `email` varchar(50)  NOT NULL,
    `password` varchar(100) NOT NULL,
    `username` varchar(20) NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    PRIMARY KEY(`id`)
);

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50)  NOT NULL,
    PRIMARY KEY(`id`)
);

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`(
	`user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY(`user_id`, `role_id`),
    FOREIGN KEY (role_id) REFERENCES roles(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);
-- insert into `entity` values (1), (2), (3), (4);
insert into `tb_comments` (`entity_id`, `comments_id`, `comment`) values('1', '1', 'This is a lame post');
insert into `tb_comments` (`entity_id`,`comments_id`, `comment`) values('2','2', 'Great work');
insert into `tb_comments` (`entity_id`,`comments_id`, `comment`) values('3', '3', 'Nice post');
insert into `tb_comments` (`entity_id`,`comments_id`, `comment`) values('4', '4', 'Why are you still posting');

insert into `tb_blog` (`entity_id`, `title`, `body`, `likes`) values('1','Work','Work is super lame', true);
insert into `tb_blog` (`entity_id`, `title`, `body`, `likes`) values('2','Soda','Soda is bad for you', true);
insert into `tb_blog` (`entity_id`, `title`, `body`, `likes`) values('3','Water', 'Water is super good for you', false);
insert into `tb_blog` (`entity_id`, `title`, `body`, `likes`) values('4','BasketBall', 'is not my sport', false);

-- insert into `users` (`id`, `email`, `password`, `username`, `first_name`, `last_name`) values('1', 'cheyenne@gmail.com', '$2y$12$5hTMOLd6pRl.tV3tp2JNJOd5ElhG7R1b8cGPvWB/DRwl1/mQEZRh6', 'cheyenne25', 'Cheyenne', 'Holland'); 
-- -- password = password123
-- insert into `users` (`id`, `email`, `password`, `username`, `first_name`, `last_name`) values('2', 'corey@gmail.com',  'root123', 'corey25', 'Corey', 'Castillo');
-- -- password = root123
-- insert into `users` (`id`, `email`, `password`, `username`, `first_name`, `last_name`) values('3', 'nicole@gmail.com',  'admin123', 'nicole25', 'Nicole', 'Becker');
-- -- password = admin123

INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');

-- insert into `user_roles` (`user_id`, `role_id`) values('1', '1');
-- insert into `user_roles` (`user_id`, `role_id`) values('2', '2');
-- insert into `user_roles` (`user_id`, `role_id`) values('3', '3');



select * from `users`