DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `oauth_client_details` VALUES ('adminapp',null,'{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',true,'admin,superadmin','authorization_code,password,refresh_token,implicit',NULL,NULL,900,3600,'{}',NULL);




DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `permission` VALUES (1,'delete_user','Can delete user',true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(2,'create_user','Can create user',true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(3,'update_user','Can update user',true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(4,'reate_user','Can reate user',true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0);



DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `role` VALUES (1,'admin',true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0,'Role admin'),(2,'user',true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0,'Role user');


DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `permission_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `enabled` bit(1) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `permission_role_fk2` (`role_id`),
  CONSTRAINT `permission_role_fk1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_role_fk2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `permission_role` VALUES (1,1,true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(2,1,true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(3,1,true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(4,1,true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(4,2,true,'2020-08-26 01:00:00','2020-08-26 01:00:00',0);



DROP TABLE IF EXISTS `permission_group`;
CREATE TABLE `permission_group` (
  `permission_id` bigint(20) unsigned NOT NULL,
  `group_id` bigint(20) unsigned NOT NULL,
  `enabled` bit(1) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`,`group_id`),
  KEY `permission_group_fk2` (`group_id`),
  CONSTRAINT `permission_group_fk1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_group_fk2` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone_number` varchar(14) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT 0,
  `pass_change_request` bit(1) NOT NULL DEFAULT 0,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_ix1` (`username`),
  UNIQUE KEY `user_ix2` (`email`),
  UNIQUE KEY `user_ix3` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES (1,'admin','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','0974006438','admin@example.com',true,'\0','\0','\0','\0','\0','2020-08-26 01:00:00','2020-08-26 01:00:00',0),(2,'user','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','0974006439','user@example.com',true,'\0','\0','\0','\0','\0','2020-08-26 01:00:00','2020-08-26 01:00:00',0);


DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `group_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `enabled` bit(1) NOT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `approver` bigint(20) DEFAULT NULL,
  `approved` bit(1) NOT NULL DEFAULT 1,
  `approved_on`datetime DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `group_user_fk2` (`user_id`),
  CONSTRAINT `group_user_fk1` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_user_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `role_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `enabled` bit(1) NOT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `approver` bigint(20) DEFAULT NULL,
  `approved` bit(1) NOT NULL DEFAULT 1,
  `approved_on`datetime DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `role_user_fk2` (`user_id`),
  CONSTRAINT `role_user_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_user_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `role_user` VALUES (1,1,true,null,null,true,null,'2020-08-26 01:00:00','2020-08-26 01:00:00',0),(2,2,true,null,null,true,null,'2020-08-26 01:00:00','2020-08-26 01:00:00',0);
