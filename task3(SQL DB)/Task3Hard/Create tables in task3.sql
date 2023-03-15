USE task3
CREATE TABLE logins(
    id int IDENTITY PRIMARY KEY,
    Application_ varchar(30) NOT NULL,
    AppAccountName varchar(50),
    IsActive varchar(30),
	JobTitle varchar(80),
	Department varchar(80)
);
CREATE TABLE postings(
    id int IDENTITY PRIMARY KEY,
    MatDoc varchar(30),
    Item varchar(20),
    DocDate varchar(20),
	PstngDate varchar(20),
	MaterialDesc varchar(100),
	Quantity varchar(20),
	BUn varchar(20),
	AmountLC varchar(20),
	Crcy varchar(20),
	UserName varchar(50),
	Autorized varchar(80)
);
