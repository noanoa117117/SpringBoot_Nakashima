INSERT INTO employee(id,name,age)
VALUES('1','TOM',30);

/*ユーザーマスター*/
INSERT INTO m_user(
user_id
,password
,user_name
,birthday
,age
,gender
,department_id
,role
)VALUES
('system@co.jp','$2a$10$NHt1b6JwnKarWYMz1qnW9eSPNMOr102NckcKEeHkNBdZPByTHRNhu','システム管理者','2000-01-01',21,1,1,'ROLE_ADMIN')
,('user@co.jp','$2a$10$NHt1b6JwnKarWYMz1qnW9eSPNMOr102NckcKEeHkNBdZPByTHRNhu','ユーザー1','2000-01-01',21,2,2,'ROLE_GENERAL')
;

/*部署マスター*/
INSERT INTO m_department(
　department_id
,department_name
)VALUES
(1,'システム管理部')
,(2,'営業部')
;

/*給料テーブル*/
INSERT INTO t_salary(
user_id
,year_month
,salary
)VALUES
('user@co.jp','2020/11',280000)
,('user@co.jp','2020/12',290000)
,('user@co.jp','2021/1',300000)
