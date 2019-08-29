package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.model.CartItem;
import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.service.CartService;
import com.vapasians.shopoholics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/addToCart")
    public ModelAndView addItemToCart(@RequestParam("userId") int userId, @RequestParam("productId") int  productId, HttpSession session, Model model)
    {
        System.out.println("User Id :" +userId+" ProductId : "+productId);
        CartItem cartItem=new CartItem(userId,productId);
        cartService.saveCartItem(cartItem);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/viewcart")
    public ModelAndView viewUserIdCart(Model model, HttpSession session)
    {
        if((session.getAttribute("loggedInUser"))!=null)
        {
            model.addAttribute("products", cartService.getCartItemProduct(session));
            return new ModelAndView("cart");
        }
        return new ModelAndView("redirect:/login");
    }



    @PostMapping("/cartsubmit")
    public ModelAndView cartsubmit(@RequestParam("action") String action, @RequestParam("productId") int  productId, HttpSession session)
    {
        System.out.println(action +"nnnn");
        switch (action) {
            case "remove":
                //System.out.println(String.valueOf(productId));
                cartService.removeProductFromCart(session,productId);
                return new ModelAndView("redirect:/viewcart");
            case "checkout":
                System.out.println(action +"nnnn");
                return new ModelAndView("redirect:/checkout");
            default:
                return new ModelAndView(":/viewcart");

        }

    }

    @PostMapping("/checkout")
    public ModelAndView getOrderDetails(HttpSession session,Model model)
    {
        if((session.getAttribute("loggedInUser"))!=null)
        {
            List<Product> productsInUserCart = cartService.getCartItemProduct(session);
            float orderTotal = productService.getPriceOfProducts(productsInUserCart);
            model.addAttribute("orderTotal",orderTotal);
            return new ModelAndView("placeOrder");
        }
        return new ModelAndView("redirect:/login");
        //return new ModelAndView("placeOrder");
    }
}
