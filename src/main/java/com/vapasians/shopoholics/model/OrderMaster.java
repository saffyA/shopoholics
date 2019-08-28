package com.vapasians.shopoholics.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="OrderMaster")
public class OrderMaster {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="orderid")
    private int orderId;

    @Column(name="userid")
    private int userId;

    @Column(name="orderdate")
    LocalDate orderDate;

    public OrderMaster(){}

    public OrderMaster(int userId, LocalDate orderDate) {
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
