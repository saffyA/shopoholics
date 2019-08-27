package com.vapasians.shopoholics.model;

import javax.persistence.*;

@Entity
@Table(name="cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cartitemid")
    private int cartItemId;

    @Column(name = "userid")
    private int userId;

    @Column(name = "productid")
    private int productId;

    public CartItem()
    {

    }

    public CartItem(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
