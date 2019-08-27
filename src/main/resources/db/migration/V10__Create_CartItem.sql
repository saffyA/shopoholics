CREATE TABLE CartItem
(
    CartItemId serial PRIMARY KEY,
    UserId integer references UserTable(UserId),
    ProductId integer references Product(ProductId)

);