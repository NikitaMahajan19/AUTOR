CREATE TABLE Service_Center (
    CenterID varchar(8) NOT NULL PRIMARY KEY,
    Center_Name varchar(20) NOT NULL,
    Center_Address varchar(100),
    Center_Tele varchar(15),
    Min_Wage int,
    Max_Wage int,
    Hourly_Wage int,
    CONSTRAINT Wage_Within_Range CHECK (Hourly_Wage>=Min_Wage AND Hourly_Wage<=Max_Wage)
);

CREATE TABLE Customer (
    CustID int,
    CenterID varchar(8),
    Cust_Name varchar(30),
    Cust_address varchar(50),
    Cust_Phone number(15),
    CONSTRAINT PRIMARY_Customer PRIMARY KEY (CustID, CenterID),
    CONSTRAINT FK_Customer FOREIGN KEY (CenterID) REFERENCES Service_Center(CenterID) ON  DELETE CASCADE
);



CREATE TABLE Employee (
EmpID varchar(15) PRIMARY KEY,
Emp_Name varchar(30),
Emp_email varchar(40) NOT NULL UNIQUE,
Emp_address varchar(50),
Emp_Phone varchar(20),
Emp_start_date varchar(50)
);


CREATE TABLE Contract_Emp (
EmpID varchar(15),
Salary int,
PRIMARY KEY (EmpID),
CONSTRAINT FK_Contract_Emp FOREIGN KEY (EmpID)
REFERENCES Employee(EmpID) ON  DELETE CASCADE
);

CREATE TABLE Hourly_Emp (
EmpID varchar(15),
Wages int,
PRIMARY KEY (EmpID),
CONSTRAINT FK_Hourly_Emp FOREIGN KEY (EmpID)
REFERENCES Employee(EmpID) ON  DELETE CASCADE
);


CREATE TABLE Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Engine_Repair_Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Engine_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Exhaust_Repair_Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Exhaust_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Electrical_Repair_Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Electrical_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Transmission_Repair_Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Transmission_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Tire_Repair_Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Tire_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Hvac_Repair_Service (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Hvac_Service PRIMARY KEY ( ServiceID)
);

CREATE TABLE Price (
    StoreId varchar(15),
    Manufacturer varchar(20),
    PriceTier int,
    Price int
);



CREATE TABLE Mechanic_Emp
(
EmpID varchar(15) PRIMARY KEY,
Wages int,
Hours_Worked int CHECK(Hours_Worked<=50),
CONSTRAINT FK_EMPID FOREIGN KEY (EmpID)
REFERENCES Employee(EmpID) ON  DELETE CASCADE
);

CREATE TABLE Swaps
(
    RequestId varchar(15) PRIMARY KEY,
    MechanicId varchar(15),
    SelfSwapWeek int,
    SelfSwapDay int,
    SelfSwapStartSlot int,
    SelfSwapEndSlot int,
    RequestedMechanicId varchar(15),
    RequestedSwapWeek int,
    RequestedSwapDay int,
    RequestedSwapStartSlot int,
    RequestedSwapEndSlot int,
    CONSTRAINT FK_MECHID FOREIGN KEY (MechanicId)
    REFERENCES Mechanic_Emp(EmpID) ON DELETE CASCADE,
    CONSTRAINT FK_REQMECHID FOREIGN KEY (RequestedMechanicId)
    REFERENCES Mechanic_Emp(EmpID) ON DELETE CASCADE
);

CREATE TABLE Cars
(
VIN  varchar(20) PRIMARY KEY,
Car_Type varchar(40),
Date_Purchase varchar(15),
Last_Mileage int,
Type_Recent_Service varchar(40),
Date_Recent_Service varchar(15)
);



CREATE TABLE Maintenance
(
ServiceName varchar(40),
ServiceType varchar(5)
);

CREATE TABLE Repair (
ServiceID varchar(10),
ServiceName varchar(40),
PriceTier int,
Hours int,
CONSTRAINT PK_Repair PRIMARY KEY ( ServiceID)
);


CREATE TABLE Login
(
LoginID varchar(30) PRIMARY KEY,
Pass varchar(30) NOT NULL
);

CREATE TABLE Admin
(
    AdminName varchar(30) PRIMARY KEY
);


CREATE TABLE GoesTo (
   VIN varchar(20),
   CustID int,
   CenterID varchar(15)
);



CREATE TABLE WorksAt(
EmpID varchar(15),
CenterID varchar(15),
CONSTRAINT PK_WorksAt PRIMARY KEY (EmpID, CenterID),
CONSTRAINT FK_WorksAt_EmpID FOREIGN KEY (EmpID) REFERENCES Employee(EmpID)
ON DELETE CASCADE,
CONSTRAINT FK_WorksAt_Service_CenterID FOREIGN KEY (CenterID) REFERENCES Service_Center(CenterID)
ON DELETE CASCADE
);


CREATE TABLE Invoice ( 
 Invoiceid int PRIMARY KEY, 
 CenterID varchar(15), 
 CustID int,
 VIN varchar(20),
 InvoiceDate varchar(20),
 InvoiceWeek int,
 InvoiceDay int,
 start_slot int, 
 end_slot int, 
 mechanic varchar2(30),
 mechanicId varchar(10),
 total_price int,
 status varchar2(30),
 Basic_services_used varchar(40),
 service_types varchar(40),
 cost_services varchar(40),
 CONSTRAINT CHECK_INVOICE_Check_No_Sundays CHECK (InvoiceDay<>7),
 CONSTRAINT FK_INVOICE_CenterID FOREIGN KEY ( CenterID) REFERENCES Service_Center( CenterID),
 CONSTRAINT FK_INVOICE_VIN FOREIGN KEY (VIN) REFERENCES Cars(VIN)
);

CREATE TABLE Appointment (
AppointmentId int PRIMARY KEY,
CenterID varchar(15),
ServiceId int,
CustID int,
AppointmentWeek int,
AppointmentDay int,
start_slot int,
end_slot int,
mechanicId varchar(10)
);

CREATE TABLE Orders (
OrderId int PRIMARY KEY,
CenterID varchar(15),
CustID int
);


CREATE TABLE Manager_Emp (
ManagerId varchar(15) PRIMARY KEY,
CenterID varchar(15),
ManagerName varchar(30),
CONSTRAINT FK_MANAGER_MANAGERID FOREIGN KEY ( ManagerId) REFERENCES Employee( EmpId)
);

CREATE TABLE Manages (
ManagerId varchar(15) PRIMARY KEY,
CenterID varchar(15),
CONSTRAINT FK_MANAGES_MANAGERID FOREIGN KEY ( ManagerId) REFERENCES Employee( EmpId)
);

CREATE TABLE Receptionist_Emp (
ReceptionistId varchar(15) PRIMARY KEY,
CenterID varchar(15),
ReceptionistName varchar(30),
CONSTRAINT FK_RECEPTIONIST_RECEPTIONISTID FOREIGN KEY ( ReceptionistId) REFERENCES Employee( EmpId)
);

create or replace procedure removeEmptyCustomers
AS
begin
delete from Customer where CustID NOT IN (SELECT CustID from GoesTo);
End;
/

CREATE OR REPLACE TRIGGER customerMustOwnVehicle
BEFORE INSERT on Customer
FOR EACH ROW
DECLARE value integer;
BEGIN
delete from Customer C1 where (C1.CustID<>:new.CustID) and (C1.CustID NOT IN (SELECT CustID from GoesTo));
END;
/

CREATE OR REPLACE TRIGGER noServiceDuringLunch
BEFORE INSERT on Invoice
FOR EACH ROW
DECLARE value integer;
BEGIN
delete from Invoice I1 where I1.start_slot=5 and I1.end_slot=6;
END;
/