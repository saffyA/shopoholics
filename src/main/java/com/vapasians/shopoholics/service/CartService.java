package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.model.Category;
import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.repository.CartItemDao;
import com.vapasians.shopoholics.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

import java.util.stream.Collectors;


@Service
public class CartService {

    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private ProductDao productDao;

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




    public List<Product> getCartItemProduct(HttpSession session)
    {
        List<Product> products=new ArrayList<Product>();
        List<CartItem> items=cartItemDao.findCartItemByUserId(((User)session.getAttribute("loggedInUser")).getUserId());
        //System.out.println(items);
        for(CartItem cartitem : items) {
            Optional<Product> product = productDao.findById(cartitem.getProductId());
            products.add(product.get());
        }
        return products;
    }

}
