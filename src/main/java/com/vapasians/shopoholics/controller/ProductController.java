package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
@Autowired
   private ProductService productService;

@GetMapping("/getProducts")
    public String showProductList(Model model)
{
     model.addAttribute("products",productService.findAll());
     return "home";

}




}
