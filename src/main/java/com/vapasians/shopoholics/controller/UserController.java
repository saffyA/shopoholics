package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.Model.User;
import com.vapasians.shopoholics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
    public class UserController {

        @Autowired
        UserService userService;

        @ModelAttribute("user")
        public User getUser(){ return new User();};

        @GetMapping(value="/")
        public String signup(Model model){
            System.out.println("in controller");
           return "signup";
        }


        @PostMapping("/saveUser")
        public String saveUser(User user)
        {
            userService.saveUser(user);
            return "home";
        }
}
