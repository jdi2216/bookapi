use bookapi;

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
),

(
	'Робототенические системы',
    'РТС',
    1
),

(
	'Электропривод и автоматизация промышленных установок и технологических комплексов',
    'ЭАПУиТК',
    1
),

(
	'Техническая физика',
    'ТФ',
    1
),

(
	'Высшая математика',
    'ВМ',
    1
);

INSERT INTO tbl_role (id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_AUTHOR'), (3, 'ROLE_ADMIN');


INSERT INTO tbl_user
(
	full_name,
	email,
    password,
    username
)
VALUES
(
	'Админ',
	'admin@bntu.by',
    '123456',
    'admin'
),
(
	'Однолько Д.С.',
    'author11@bntu.by',
    '123456',
    'author11'
),
(
	'Прихожий А.А.',
    'prihozhiy@bntu.by',
    '123456',
    'prihozhiyaa'
),
(
	'Васильев Д.С.',
    'vasilyev@bntu.by',
    '123456',
    'vasilyev'
),
(
	'Опейко О.Ф.',
    'opeyko@bntu.by',
    '123456',
    'opeykoof'
),
(
	'Прибыльская Н.М',
    'pribylskayanm@bntu.by',
    '123456',
    'PribylskayaNM'
),
(
	'Бородуля А.В.',
    'borodulya@bntu.by',
    '123456',
    'borodulya'
),
(
	'Разорёнов Н.А.',
    'razoryonov@bntu.by',
    '123456',
    'RazoryonovNA'
);

INSERT INTO tbl_user_roles (user_id, role_id) VALUES (1, 3), (2, 2), (3, 2), (4, 2), (5, 2), 
	(6, 2), (7, 2);
    
INSERT INTO tbl_category (category_name) VALUES ('Техническое обеспечение интегрированных САПР'), 
	('Операционные системы и системное программирование'), ('Теория электропривода'),
    ('Распределённая и параллельная обработка данных'),
    ('Наладка и диагностика систем управления электроприводами'), 
    ('Основы математического моделирования'),
    ('Программирование сетевых приложений'),
    ('Микропроцессорные средства в автоматизированном электроприводе');
    
INSERT INTO tbl_book 
(
	isbn,
	title,
    description,
    year,
    date_created,
    last_updated,
    image_url,
    book_url,
    department_id,
    category_id
)
VALUES
(
	'978-985-525-640-4',
    'Техническое обеспечение интегрированных САПР. В 3 ч. Ч. 2',
    ''
	







    
	


	