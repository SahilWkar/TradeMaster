DROP TABLE IF EXISTS TRADE_LOG;
CREATE TABLE TRADE_LOG
(
   tradeid varchar(100),
   version varchar(100),
   counterpartyid varchar(100),
   bookId varchar(100),
   maturitydate date,
   createddate date,
   expired varchar(100)   
);