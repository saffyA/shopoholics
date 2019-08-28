package com.vapasians.shopoholics.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "productid")
    private int id;

    @Column(name = "productname")
    private String productName;

    @Column(name = "price")
    private float price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "quantity")
    private int quantity;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Column(name = "available")
    private boolean available;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid" ,referencedColumnName = "categoryid" , insertable = false, updatable = false)
    private Category category;


    @Column(name = "categoryid")
    private int categoryId;

    @Column(name = "imageurl")
    private String imageUrl;

    public Product(){}

    public Product(int id, String productName, float price, int categoryId) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
