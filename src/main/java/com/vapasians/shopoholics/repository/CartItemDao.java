package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemDao extends JpaRepository<CartItem,Integer> {
    @Query("from CartItem where userid=?1")
    List<CartItem> findCartItemByUserId(int userId);
}
