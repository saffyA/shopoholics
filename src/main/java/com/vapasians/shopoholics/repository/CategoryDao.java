package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.Category;
import com.vapasians.shopoholics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Integer> {
    List<Category> findAllByOrderByCategoryIdAsc();
}
