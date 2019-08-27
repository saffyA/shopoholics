package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.repository.CartItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemDao cartItemDao;

    public void saveCartItem(CartItem cartItem)
    {
        cartItemDao.save(cartItem);
    }

    public int getCartItemCount(HttpSession session)
    {
        List<CartItem> items=cartItemDao.findCartItemByUserId(((User)session.getAttribute("loggedInUserThisController")).getUserId());
        return items.size();
    }

}
