-- create database bookapi
DROP SCHEMA IF EXISTS `bookapi`;
CREATE SCHEMA `bookapi`;

-- select the database
USE `bookapi` ;

CREATE TABLE IF NOT EXISTS `bookapi`.`tbl_university` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) DEFAULT NULL,
  `short_title` VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `bookapi`.`tbl_faculty` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) DEFAULT NULL,
  `short_title` VARCHAR(255) DEFAULT NULL,
  `university_id` BIGINT(20) NOT NULL,
  FOREIGN KEY (`university_id`) REFERENCES `tbl_university` (`id`)
);

CREATE TABLE IF NOT EXISTS `bookapi`.`tbl_department` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) DEFAULT NULL,
  `short_title` VARCHAR(255) DEFAULT NULL,
  `faculty_id` BIGINT(20) NOT NULL,
  FOREIGN KEY (`faculty_id`) REFERENCES `tbl_faculty` (`id`)
);

-- create category table tbl_category
CREATE TABLE IF NOT EXISTS `bookapi`.`tbl_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `category_name` VARCHAR(255) NULL DEFAULT NULL);

-- create book table tbl_book
CREATE TABLE IF NOT EXISTS `bookapi`.`tbl_book` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) DEFAULT NULL,
  `author` VARCHAR(255) DEFAULT NULL,
  `image_url` VARCHAR(255) DEFAULT NULL,
  `book_url` VARCHAR(255) DEFAULT NULL,
   `date_created` DATETIME DEFAULT NULL,
  `last_updated` DATETIME DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  `department_id` BIGINT(20) NOT NULL,
  FOREIGN KEY (`category_id`) REFERENCES `tbl_category` (`id`),
  FOREIGN KEY (`department_id`) REFERENCES `tbl_department` (`id`)
);


INSERT INTO tbl_university
(
	title, 
    short_title
)
VALUES
(
	'Белорусский национальный технический университет',
    'БНТУ'
);
INSERT INTO tbl_faculty
(
	title,
    short_title,
    university_id
)
VALUES
(
	'Факультет информационных технологий и робототехники',
    'ФИТР',
    1
);

INSERT INTO tbl_department
(
	title,
    short_title,
    faculty_id
)
VALUES
(
	'Программное обеспечение информационных систем и технологий',
    'ПОСиТ',
    1
);

-- insert sample data to category table
INSERT INTO tbl_category(category_name) VALUES ('Базы данных');
INSERT INTO tbl_category(category_name) VALUES ('Системное программирование');

-- insert sample data to book table
INSERT INTO tbl_book 
(
	name, 
    author, 
    image_url, 
    book_url,
    category_id,
    date_created,
    department_id
)
VALUES 
( 
    'Базы данных', 
    'Бухвалова, И. А.',
    'assets/images/books/text-100.png',
    'assets/books/text-100.pdf',
    1, 
    NOW(),
    1
);

INSERT INTO tbl_book 
(
	name, 
    author, 
    image_url,
    book_url,
    category_id,
    date_created,
    department_id
)
VALUES 
(
    'Системное программирование', 
    'Разорёнов, Н. А.',
    'assets/images/books/text-101.png',
    'assets/books/text-101.pdf',
    2, 
    NOW(),
    1
);

    
	

