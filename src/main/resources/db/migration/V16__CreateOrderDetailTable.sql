create table OrderDetail
(
    OrderDetailId Serial PRIMARY KEY,
    OrderId int REFERENCES OrderMaster(OrderId),
    ProductId int references Product(ProductId)
);
