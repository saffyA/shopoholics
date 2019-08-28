package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.Category;
import com.vapasians.shopoholics.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail,Integer> {
}
