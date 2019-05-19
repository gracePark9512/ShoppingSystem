/**
 * Author:  dinjo998
 * Created: 8/08/2017
 */

create table Product (
	Product_ID varchar(10) not null unique,
	Name varchar(50) not null,
	Description varchar(100) not null,
	Category varchar(30) not null,
	Price decimal(10, 2) not null,
	Quantity decimal(10, 2) not null,
	constraint Product_PK primary key (Product_ID)
);

create table Customer (
	Username varchar(15) not null unique,
	Password varchar(15) not null,
	Name varchar(50) not null,
	Address varchar(100) not null,
	Email varchar(50),
	CreditCard varchar(30) not null,
	constraint Customer_PK primary key (username)
);

