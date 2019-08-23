package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {


}
