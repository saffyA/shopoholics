package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.model.OrderDetail;
import com.vapasians.shopoholics.model.OrderMaster;
import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.repository.CartItemDao;
import com.vapasians.shopoholics.repository.OrderDetailDao;
import com.vapasians.shopoholics.repository.OrderMasterDao;
import com.vapasians.shopoholics.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    OrderMasterDao orderMasterDao;

    @Autowired
    CartService cartService;

    @Autowired
    ProductDao productDao;

    @Autowired
    CartItemDao cartItemDao;

    public int placeFinalOrder(int userId,HttpSession session)
    {
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setUserId(userId);
        //orderMaster.setOrderDate(new Date());
        System.out.println("Order Id  :" +orderMaster.getOrderId()+" UserId : "+ orderMaster.getUserId()+" Date : "+orderMaster.getOrderDate());
        orderMasterDao.save(orderMaster);

       for(CartItem cartItem: cartService.getCartItemObjects(session))
       {
           OrderDetail orderDetail=new OrderDetail();
           orderDetail.setOrderId(orderMaster.getOrderId());
           orderDetail.setProductId(cartItem.getProductId());
           orderDetailDao.save(orderDetail);
           Optional<Product> product = productDao.findById(cartItem.getProductId());
           if(product.isPresent() && product.get().getQuantity()!=0)
           {
               product.get().setQuantity(product.get().getQuantity()-1);
               productDao.save(product.get());
           }
           cartItemDao.deleteById(cartItem.getCartItemId());

       }


        System.out.println(" Order id : "+orderMaster.getOrderId());
       return orderMaster.getOrderId();
    }
}
