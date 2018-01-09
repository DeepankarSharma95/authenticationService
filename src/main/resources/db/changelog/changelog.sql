-- liquibase formatted sql
-- comment Hello World

-- changeset deepankar.sharma:PR-1-1
-- comment Create API_KEY table
DROP TABLE IF EXISTS `api_key`;
CREATE TABLE `api_key` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_name` VARCHAR(50) NOT NULL,
  `api_key` VARCHAR(50) NOT NULL,
  `token_validity` INT(3) NOT NULL,
  `when_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tlm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `application_name_UNIQUE` (`application_name` ASC));

-- changeset deepankar.sharma:PR-1-2
-- comment Create AUTHENTICATION table
DROP TABLE IF EXISTS `authentication`;
CREATE TABLE `authentication` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `auth_token` VARCHAR(50) NOT NULL,
  `auth_token_validity` DATETIME NOT NULL,
  `when_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tlm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

-- changeset deepankar.sharma:PR-1-3
-- comment Create MEMBER table
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `authentication_id` INT(10) UNSIGNED NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `when_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tlm` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_member_1_idx` (`authentication_id` ASC),
  CONSTRAINT `fk_member_1`
  FOREIGN KEY (`authentication_id`)
  REFERENCES `authentication` (`id`));

-- changeset deepankar.sharma:PR-1-4
-- comment insert the api key
INSERT INTO api_key (`application_name`, `api_key`, `token_validity`)
VALUES ("UI5 Client", "abcdefghijklmno", "30");

-- changeset deepankar.sharma:PR-1-5
-- comment insert the api key
INSERT INTO api_key (`application_name`, `api_key`, `token_validity`)
VALUES ("Model Training Service", "zxcvbnmasdfghjk", "30");