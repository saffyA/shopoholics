package com.vapasians.shopoholics.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="ordermaster")
public class OrderMaster {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="orderid")
    private int orderId;

    @Column(name="userid")
    private int userId;

    @Column(name="orderdate")
    Date orderDate;

    public OrderMaster(){}

    public OrderMaster(int userId, Date orderDate) {
        this.userId = userId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
