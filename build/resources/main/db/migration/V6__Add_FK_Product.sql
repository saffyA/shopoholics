Alter table Product
ADD constraint FK_Category Foreign Key (categoryid) references category (categoryid);