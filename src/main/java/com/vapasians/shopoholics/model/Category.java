package com.vapasians.shopoholics.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="categoryid")
    private int categoryId;

    @Column(name="categoryname")
    private String categoryName;

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category" , cascade = CascadeType.ALL)
    @OrderBy("productName ASC")
    private Set<Product> productSet;

    public Category(){}

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }


    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
