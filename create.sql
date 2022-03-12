# logs db
CREATE TABLE `log_info` (
                            `id` int(11) NOT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `content` longtext,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# order db
CREATE TABLE `order_info` (
                              `id` INT ( 11 ) NOT NULL,
                              `money` DOUBLE NOT NULL,
                              `user_id` VARCHAR ( 20 ) DEFAULT NULL,
                              `address` VARCHAR ( 200 ) DEFAULT NULL,
                              `create_time` datetime DEFAULT NULL,
                              PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;