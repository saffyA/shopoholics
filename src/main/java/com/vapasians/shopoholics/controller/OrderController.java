package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.model.User;
import com.vapasians.shopoholics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/placeOrder")
    public String placeOrder(HttpSession session, Model model) {
        if (session.getAttribute("loggedInUser") == null)
            return "home";

        int orderId;
        orderId = orderService.placeFinalOrder(((User) session.getAttribute("loggedInUser")).getUserId(),session);
        model.addAttribute("orderid", orderId);



        System.out.println("Order Id : "+orderId);
        return "orderconfirmation";


    }
}