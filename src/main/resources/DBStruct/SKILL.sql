CREATE TABLE CAOWT.SKILL (
	id INT auto_increment NOT NULL,
	name varchar(100) NULL,
	level varchar(100) NULL,
	unique_skill_md5 varchar(32) GENERATED ALWAYS AS (md5(concat_ws('X',ifnull(`name`,0),ifnull(`level`,0)))) VIRTUAL,
	CONSTRAINT SKILL_PK PRIMARY KEY (id),
	CONSTRAINT unique_skill_md5 UNIQUE KEY (unique_skill_md5)
)
COMMENT='Skill Table';