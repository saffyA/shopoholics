package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.repository.CartItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartItemDao cartItemDao;

    public void saveCartItem(CartItem cartItem)
    {
        cartItemDao.save(cartItem);
    }


    public List<Integer> getCartItems(HttpSession session)
    {

      List<Integer> list= cartItemDao.findCartItemByUserId(((User)session.getAttribute("loggedInUser")).getUserId()).stream().map(CartItem::getProductId).collect(Collectors.toList());
      //for(int n:list)
        //  System.out.println(n);
      return list;

    }

    public int getCartItemCount(HttpSession session)
    {
        List<CartItem> items=cartItemDao.findCartItemByUserId(((User)session.getAttribute("loggedInUser")).getUserId());

        return items.size();
    }

}
