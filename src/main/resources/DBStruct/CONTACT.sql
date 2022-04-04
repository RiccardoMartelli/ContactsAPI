CREATE TABLE CAOWT.CONTACT (
	id INT auto_increment NOT NULL,
	firstname varchar(100) NULL,
	lastname varchar(100) NULL,
	fullname varchar(100) NULL,
	address varchar(100) NULL,
	email varchar(100) NULL,
	phone_number varchar(100) NULL,
	unique_md5 varchar(32) GENERATED ALWAYS AS (md5(concat_ws('X',ifnull(`firstname`,0),ifnull(`lastname`,0),ifnull(`fullname`,0),ifnull(`address`,0),ifnull(`email`,0),ifnull(`phone_number`,0)))) VIRTUAL,
	CONSTRAINT CONTACT_PK PRIMARY KEY (id),
	CONSTRAINT unique_md5 UNIQUE KEY unique_md5 (unique_md5)
)
COMMENT='Contact Table';