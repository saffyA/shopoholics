CREATE TABLE ordermaster( orderid SERIAL PRIMARY KEY, userid INTEGER REFERENCES usertable(userid), orderdate DATE DEFAULT CURRENT_DATE);