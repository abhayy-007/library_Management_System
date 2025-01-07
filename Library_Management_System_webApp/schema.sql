create database lmsdb;
use lmsdb;

create table Admin(
User_ID Varchar(50) Primary Key,
Name Varchar(50) not null,
Password Varchar(50) not null unique,
Contact Varchar(10) not null,
);

Insert into Admin values('xyz@123', 'XYZ', '@xyz123', '0123456789');

create table Books(
Book_ID Varchar(50) Primary Key,
Category Varchar(50) not null,
Name Varchar(50) not null,
Author Varchar(10) not null,
Copies Integer,
);

create table Staffs(
Staff_ID Varchar(50) Primary Key,
Name Varchar(50) not null,
Password Varchar(50) not null unique,
Contact Varchar(10) not null,
);

CREATE TABLE Rent (
Rent_ID INT AUTO_INCREMENT PRIMARY KEY,
Book_ID VARCHAR(50),
Borrower_Name VARCHAR(100),
Borrower_Contact VARCHAR(50),
Rent_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
