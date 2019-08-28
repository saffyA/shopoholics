package com.vapasians.shopoholics.repository;

import com.vapasians.shopoholics.model.Category;
import com.vapasians.shopoholics.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterDao extends JpaRepository<OrderMaster,Integer> {

}
