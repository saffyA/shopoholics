package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.service.CartService;
import com.vapasians.shopoholics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String showProductList(Model model,HttpSession session)
    {
        System.out.println("here");
        model.addAttribute("products",productService.findAll());
        if(session.getAttribute("loggedInUser") != null) {
            model.addAttribute("cartitems", cartService.getCartItems(session));
            model.addAttribute("count", cartService.getCartItemCount(session));
        }

        return "home";
    }
}