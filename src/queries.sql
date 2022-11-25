select
    G.CenterId AS Service_Center_ID, count(G.CenterId) AS Customer_Count
from
    GoesTo G
group by
    G.CenterId
having
        count(G.CenterId)=( select
                                max(count(G.CenterId)) as count
from GoesTo G
group by
    G.CenterId );





select
    AVG(P.Price) AS Average_Honda_Evap_Price
from
    Price P
where
        P.manufacturer = 'Honda'
  and
        P.PriceTier=( select S.PriceTier
                      from
                          Service S
                      where
                              S.ServiceName='Evaporator Repair' );





select
    C.Cust_Name, C.CustId
from
    Customer C, Invoice I
where
        I.CustID=C.CustID
  and
        I.CenterID=C.CenterID
  and
        I.CenterID='30003'
  and
        I.status='Unpaid';




select
    servicename
from
    maintenance
where
        servicetype='mr';




select
    ABS(T1.total - T2.total) AS Absolute_Cost_Difference_30001_30002
from
    (select
         SUM(P1.Price) as total
     from
         Price P1
     where
             P1.StoreId='30001'
       and
             P1.manufacturer='Toyota'
       and
             P1.PriceTier IN
             (select
                  S1.PriceTier
              from
                  Service S1
              where
                      S1.ServiceName='Belt Replacement'
                 or
                      S1.ServiceName='A')) T1,
    (select
         SUM(P2.Price) as total
     from
         Price P2
     where
             P2.StoreId='30002'
       and
             P2.manufacturer='Toyota'
       and P2.PriceTier IN
           (select
                S2.PriceTier
            from
                Service S2
            where
                    S2.ServiceName='Belt Replacement'
               or
                    S2.ServiceName='A')) T2;




select
    CONCAT('Schedule ', C.Type_Recent_Service) AS Most_Recent_Service,
    CASE
        WHEN C.Type_Recent_Service='A' THEN 'Schedule B'
        WHEN C.Type_Recent_Service='B' THEN 'Schedule C'
        WHEN C.Type_Recent_Service='C' THEN 'Schedule A'
        ELSE 'Schedule A'
        END AS Next_Eligible_Service
from
    Cars C
where
        C.VIN='34KLE19D';