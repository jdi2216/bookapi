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
	'����������� ������������ ����������� �����������',
    '����'
);
INSERT INTO tbl_faculty
(
	title,
    short_title,
    university_id
)
VALUES
(
	'��������� �������������� ���������� � �������������',
    '����',
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
	'����������� ����������� �������������� ������ � ����������',
    '�����',
    1
);

-- insert sample data to category table
INSERT INTO tbl_category(category_name) VALUES ('���� ������');
INSERT INTO tbl_category(category_name) VALUES ('��������� ����������������');

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
    '���� ������', 
    '���������, �. �.',
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
    '��������� ����������������', 
    '��������, �. �.',
    'assets/images/books/text-101.png',
    'assets/books/text-101.pdf',
    2, 
    NOW(),
    1
);

    
	

