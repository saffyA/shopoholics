package com.vapasians.shopoholics.model;

import javax.persistence.*;

@Entity
@Table(name="orderdetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="orderdetailid")
    private int orderDetailId;

    @Column(name="orderid")
    private int orderId;

    @Column(name="productid")
    private int productId;

    public OrderDetail(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderDetail(){}

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
