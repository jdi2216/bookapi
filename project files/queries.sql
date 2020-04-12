use bookapi;

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

INSERT INTO tbl_role (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_AUTHOR'), (3, 'ROLE_ADMIN');

INSERT INTO tbl_user
(
	email,
    username,
    password
)
VALUES
(
	'ivanivanov@bntu.by',
    '������ �. �.',
    '12345'
),
(
	'admin@bntu.by',
    '�����',
    '12345admin'
);

INSERT INTO tbl_user_roles (user_id, roles_id) VALUES (1, 2), (2, 3);