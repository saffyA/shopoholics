package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public ModelAndView adminHome(HttpSession session)
    {
        return new ModelAndView("adminHome");
    }

    @GetMapping("/admin/removeUnavailableProducts")
    public String removeUnavailableProducts(Model model)
    {
        model.addAttribute("products",productService.findAll());
        return "adminproducts";
    }

    @PostMapping("/admin/removeUnavailableProducts")
    public ModelAndView markProductUnavailable(@RequestParam("pid") int pid)
    {
        productService.markProductUnavailable(pid);
        return new ModelAndView("redirect:/admin/removeUnavailableProducts");
    }

    @GetMapping("/admin/addProducts")
    public String addProducts(Model model)
    {
        Product product;
        model.addAttribute( "product" , new Product(  ));
        return "adminaddproducts";
    }

    @PostMapping("/admin/addproducts")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product, Model model)
    {
        System.out.println(" Product name  -------" + product.getProductName());
        productService.saveProduct(product);
        return new ModelAndView("redirect:/admin");
    }
}
