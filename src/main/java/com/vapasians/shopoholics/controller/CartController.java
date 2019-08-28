package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;



    @PostMapping("/addToCart")
    public ModelAndView addItemToCart(@RequestParam("userId") int userId, @RequestParam("productId") int  productId, HttpSession session, Model model)
    {
        System.out.println("User Id :" +userId+" ProductId : "+productId);
        CartItem cartItem=new CartItem(userId,productId);
        cartService.saveCartItem(cartItem);

        //int count=cartService.getCartItemCount(session);

       // model.addAttribute("count",count);
//

        return new ModelAndView("redirect:/");
    }

    /*@ModelAttribute("count")
    public int getItemCount(HttpSession session){
        return cartService.getCartItemCount(session);
    }*/

    /*@RequestMapping(value="/cart/items/count", method= RequestMethod.GET)
    @ResponseBody

    public Map<String,Integer> getCartItemCount()
    {

    }*/

}
