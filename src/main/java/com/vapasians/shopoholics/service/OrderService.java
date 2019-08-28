package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.model.OrderDetail;
import com.vapasians.shopoholics.model.OrderMaster;
import com.vapasians.shopoholics.repository.CartItemDao;
import com.vapasians.shopoholics.repository.OrderDetailDao;
import com.vapasians.shopoholics.repository.OrderMasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    OrderMasterDao orderMasterDao;

    @Autowired
    CartService cartService;

    public int placeFinalOrder(int userId,HttpSession session)
    {
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setUserId(userId);
        orderMaster.setOrderDate(new Date());
        System.out.println("Order Id  :" +orderMaster.getOrderId()+" UserId : "+ orderMaster.getUserId()+" Date : "+orderMaster.getOrderDate());
        orderMasterDao.save(orderMaster);

       for(int n: cartService.getCartItems(session))
       {
           OrderDetail orderDetail=new OrderDetail();
           orderDetail.setOrderId(orderMaster.getOrderId());
           orderDetail.setProductId(n);
           orderDetailDao.save(orderDetail);
       }


        System.out.println(" Order id : "+orderMaster.getOrderId());
       return orderMaster.getOrderId();
    }
}
