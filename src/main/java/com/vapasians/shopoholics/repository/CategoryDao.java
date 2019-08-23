package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.Category;
import com.vapasians.shopoholics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}
