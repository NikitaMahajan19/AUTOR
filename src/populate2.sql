INSERT INTO Service_Center (
  	CenterID, 
	Center_Name, 
	Center_Address, 
	Center_Tele ) 
VALUES (
	'30001', 
	'sc1', 
	'3921 Western Blvd, Raleigh, NC 27606',
	'3392601234' );

INSERT INTO Service_Center (
  	CenterID, 
	Center_Name, 
	Center_Address, 
	Center_Tele ) 
VALUES (
	'30002', 
	'sc2', 
	'4500 Preslyn Dr Suite 103, Raleigh, NC 27616',
	'8576890280' );

INSERT INTO Service_Center (
  	CenterID, 
	Center_Name, 
	Center_Address, 
	Center_Tele ) 
VALUES (
	'30003', 
	'sc3', 
	'9515 Chapel Hill Rd, Morrisville, NC 27560',
	'8576890280' );

INSERT INTO Employee (
  	EmpID, 
	Emp_Name, 
	Emp_email, 
	Emp_address,
	Emp_phone,
	Emp_start_date ) 
VALUES (
	'123456789', 
	'John Doe', 
	'jdoe@gmail.com',
	'1378 University Woods, Raleigh, NC 27612',
	'8576890280',
	'Jan 2020' );

INSERT INTO Employee (
  	EmpID, 
	Emp_Name, 
	Emp_email, 
	Emp_address,
	Emp_phone,
	Emp_start_date ) 
VALUES (
	'987654321', 
	'Rachel Brooks', 
	'rbrooks@ymail.com',
	'2201 Gorman Parkwood, Raleigh, NC 27618',
	'8972468552',
	'March 2021' );

INSERT INTO Employee (
  	EmpID, 
	Emp_Name, 
	Emp_email, 
	Emp_address,
	Emp_phone,
	Emp_start_date ) 
VALUES (
	'987612345', 
	'Caleb Smith', 
	'csmith@yahoo.com',
	'1538 Red Bud Lane, Morrisville, NC 27560',
	'8547963210',
	'Feb 2015' );

INSERT INTO Customer (
  	CustID, 
	Cust_Name, 
	Cust_email, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10001', 
	'30001',
	'Peter Parker',
	'PP Lane, Morrisville, NC 27560',
	'1111111111' );

INSERT INTO Customer (
  	CustID, 
	Cust_Name, 
	Cust_email, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10002', 
	'30001',
	'Diana Prince', 
	'DP Lane, Morrisville, NC 27560',
	'2222222222' );

INSERT INTO Customer (
  	CustID,
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10053', 
	'30002',
	'Billy Batson', 
	'BB Lane, Morrisville, NC 27560',
	'3333333333' );

INSERT INTO Customer (
  	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10010', 
	'30002',
	'Bruce Wayne', 
	'BW Lane, Morrisville, NC 27560',
	'4444444444' );


INSERT INTO Customer (
  	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10001', 
	'30002',
	'Steve Rogers', 
	'SR Lane, Morrisville, NC 27560',
	'5555555555' );


INSERT INTO Customer (
  	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10035', 
	'30002',
	'Happy Hogan', 
	'HH Lane, Morrisville, NC 27560',
	'6666666666' );

INSERT INTO Customer (
  	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10002', 
	'30003',
	'Tony Stark', 
	'TS Lane, Morrisville, NC 27560',
	'7777777777' );

INSERT INTO Customer (
  	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10003', 
	'30003',
	'Natasha Romanoff', 
	'NR Lane, Morrisville, NC 27560',
	'8888888888' );

INSERT INTO Customer (
  	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10011', 
	'30003',
	'Harvey Bullock', 
	'HB Lane, Morrisville, NC 27560',
	'9999999999' );

INSERT INTO Customer (
	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10062', 
	'30003',
	'Sam Wilson', 
	'SW Lane, Morrisville, NC 27560',
	'1111122222' );

INSERT INTO Customer (
	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10501', 
	'30003',
	'Wanda Maximoff', 
	'WM Lane, Morrisville, NC 27560',
	'1111133333' );

INSERT INTO Customer (
	CustID, 
	CenterID,
	Cust_Name, 
	Cust_address,
	Cust_Phone ) 
VALUES (
	'10010', 
	'30003',
	'Virginia Potts',
	'VP Lane, Morrisville, NC 27560',
	'1111144444' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
    '4Y1BL658',
    '10001',
    '30001' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '7A1ST264',
       '10002',
       '30001' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '5TR3K914',
       '10053',
       '30002' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '15DC9A87',
       '10010',
       '30002' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '18S5R2D8',
       '10001',
       '30002' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '9R2UHP54',
       '10035',
       '30002' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '88TSM888',
       '10002',
       '30003' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '71HK2D89',
       '10003',
       '30003' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '34KLE19D',
       '10011',
       '30003' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '29T56WC3',
       '10062',
       '30003' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       'P39VN198',
       '10501',
       '30003' );

INSERT INTO GoesTo (
    LicensePlateID,
    CustID,
    CenterID
)
VALUES (
       '39YVS415',
       '10010',
       '30003' );

INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
        '4Y1BL658',
        'Toyota',
        '2007',
        53000,
        'B' );

INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '7A1ST264',
       'Honda',
       '1999',
       117000,
       'A' );

INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '5TR3K914',
       'Nissan',
       '2015',
       111000,
       'C' );


INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '15DC9A87',
       'Toyota',
       '2020',
       21000,
       'A' );


INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '18S5R2D8',
       'Nissan',
       '2019',
       195500,
       'C' );


INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '9R2UHP54',
       'Honda',
       '2013',
       67900,
       'B' );


INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '88TSM888',
       'Honda',
       '2000',
       10000,
       'A' );


INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '71HK2D89',
       'Toyota',
       '2004',
       195550,
       'B' );


INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '34KLE19D',
       'Toyota',
       '2010',
       123800,
       'C' );

INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '29T56WC3',
       'Nissan',
       '2011',
       51300,
       'A' );

INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       'P39VN198',
       'Nissan',
       '2013',
       39500,
       'B' );

INSERT INTO Cars (
    LicensePlateID,
    Car_Type,
    Date_Purchase,
    Last_Mileage,
    Type_Recent_Service
)
VALUES (
       '39YVS415',
       'Honda',
       '2021',
       49900,
       'A' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
    '132457689',
    'James Williams',
    'jwilliams@gmail.com',
    '1400 Gorman St, Raleigh, NC 27606-2972 ',
    '4576312882',
    '7/2/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '314275869',
           'David Johnson',
           'djohnson@ymail.com',
           '686 Stratford Court, Raleigh, NC 27606',
           '9892321100',
           '8/25/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '241368579',
           'Maria Garcia',
           'mgarcia@yahoo.com',
           '1521 Graduate Lane, Raleigh, NC 27606',
           '4573459090',
           '8/26/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '423186759',
           'Ellie Clark',
           'eclark@gmail.com',
           '3125 Avent Ferry Road, Raleigh, NC 27605',
           '9892180921',
           '9/1/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '123789456',
           'Robert Martinez',
           'rmartinez@ymail.com',
           '1232 Tartan Circle, Raleigh, NC 27607',
           '7652304282',
           '10/11/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '789123456',
           'Charles Rodriguez',
           'crodriguez@yahoo.com',
            '218 Patton Lane, Raleigh, NC 27603',
           '9092234281',
           '10/11/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '125689347',
           'Jose Hernandez',
           'jhernandez@gmail.com',
            '4747 Dola Mine Road, Raleigh, NC 27609',
           '7653241714',
           '10/17/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '347812569',
           'Charlie Brown',
           'cbrown@ymail.com',
            '1 Rockford Mountain Lane, Morrisville, NC 27560',
           '9091237568',
           '10/18/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '123456780',
           'Jeff Gibson',
           'jgibson@yahoo.com',
            '900 Development Drive, Morrisville, NC 27560',
           '3390108899',
           '10/20/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '123456708',
           'Isabelle Wilder',
           'iwilder@gmail.com',
            '6601 Koppers Road, Morrisville, NC 27560',
           '3394561231',
           '10/22/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '123456078',
           'Peter Titus',
           'ptitus@ymail.com',
            '2860 Slater Road, Morrisville, NC 27560',
           '3396723418',
           '10/28/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '123450678',
           'Mark Mendez',
           'mmendez@yahoo.com',
           '140 Southport Drive, Morrisville, NC 27560',
           '3395792080',
           '10/31/2021' );

INSERT INTO Employee (
    EmpID,
    Emp_Name,
    Emp_email,
    Emp_address,
    Emp_Phone,
    Emp_start_date
)
VALUES (
           '123405678',
           'Lisa Alberti',
           'lalberti@yahoo.com',
            '100 Valley Glen Drive, Morrisville, NC 27560',
           '3391126787',
           '11/01/2021' );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
        '132457689',
        35 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '314275869',
           35 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '241368579',
           35 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '423186759',
           25 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '123789456',
           25 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '789123456',
           25 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '125689347',
           25 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '347812569',
           22 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '123456780',
           22 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '123456708',
           22 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '123456078',
           22 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '123450678',
           22 );

INSERT INTO Mechanic_Emp (
    EmpID,
    Wages
)
VALUES (
           '123405678',
           22 );

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
        '132457689',
         '30001');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '314275869',
               '30001');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '241368579',
               '30001');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '423186759',
               '30002');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '123789456',
               '30002');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '789123456',
               '30002');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '125689347',
               '30002');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '347812569',
               '30003');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '123456780',
               '30003');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '123456708',
               '30003');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '123456078',
               '30003');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '123450678',
               '30003');

INSERT INTO WorksAt (
    EmpID,
    CenterID
)
VALUES (
           '123405678',
               '30003');

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
    '101',
    'Belt Replacement',
    2,
    1 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
       '102',
       'Engine Repair',
       3,
       5 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '103',
           'Exhaust Repair',
           4,
           4 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '104',
           'Muffler Repair',
           2,
           2 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '105',
           'Alternator Repair',
           3,
           4 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '106',
           'Power Lock Repair',
           3,
           5 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '107',
           'Axle Repair',
           5,
           7 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '108',
           'Brake Repair',
           3,
           3 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '109',
           'Tire Balancing',
           1,
           2 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '110',
           'Wheel Alignment',
           2,
           1 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '111',
           'Compressor Repair',
           4,
           3 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '112',
           'Evaporator Repair',
           3,
           4 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '113',
           'A',
           6,
           3 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '114',
           'B',
           7,
           6 );

INSERT INTO Service (
    ServiceID,
    ServiceName,
    PriceTier,
    Hours
)
VALUES (
           '115',
           'C',
           8,
           9 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
    '30001',
    'Honda',
    1,
    50 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
       '30001',
       'Nissan',
       1,
       70 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           1,
           60 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           2,
           140 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           2,
           175 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           1,
           160 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           3,
           400 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           3,
           500 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           3,
           450 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           4,
           590 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           4,
           700 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           4,
           680 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           5,
           1000 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           5,
           1200 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           5,
           1100 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           6,
           120 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           6,
           190 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           6,
           200 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           7,
           195 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           7,
           210 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           7,
           215 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           8,
           300 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           8,
           310 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           8,
           305 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           1,
           50 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           1,
           70 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           1,
           60 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           2,
           140 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           2,
           175 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           1,
           160 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           3,
           400 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           3,
           500 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           3,
           450 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           4,
           590 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           4,
           700 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           4,
           680 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           5,
           1000 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           5,
           1200 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           5,
           1100 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           6,
           120 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           6,
           190 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           6,
           200 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           7,
           195 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           7,
           210 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           7,
           215 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Honda',
           8,
           300 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Nissan',
           8,
           310 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30001',
           'Toyota',
           8,
           305 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           1,
           70 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           1,
           90 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           1,
           80 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           2,
           160 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           2,
           195 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           1,
           180 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           3,
           420 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           3,
           520 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           3,
           470 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           4,
           610 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           4,
           720 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           4,
           700 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           5,
           1020 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           5,
           1220 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           5,
           1120 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           6,
           125 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           6,
           195 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           6,
           205 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           7,
           200 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           7,
           215 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           7,
           220 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Honda',
           8,
           305 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Nissan',
           8,
           315 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30002',
           'Toyota',
           8,
           310 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           1,
           85 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           1,
           105 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           1,
           95 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           2,
           175 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           2,
           210 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           1,
           195 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           3,
           435 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           3,
           535 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           3,
           485 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           4,
           625 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           4,
           735 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           4,
           715 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           5,
           1035 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           5,
           1235 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           5,
           1135 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           6,
           140 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           6,
           180 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           6,
           195 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           7,
           210 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           7,
           220 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           7,
           215 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Honda',
           8,
           310 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Nissan',
           8,
           305 );

INSERT INTO Price (
    StoreId,
    Manufacturer,
    PriceTier,
    Price
)
VALUES (
           '30003',
           'Toyota',
           8,
           310 );
