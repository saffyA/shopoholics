package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.vapasians.shopoholics.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
//@SessionAttributes("loggedInUserThisController")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ProductController productController;

//    @ModelAttribute("loggedInUser")
//    public User createLoggedInSessionObject()
//    {
//        return new User();
//    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(Model model,RedirectAttributes redirectAttributes, HttpSession session, @ModelAttribute("loggedInUserThisController") User loggedInUserThisController)
    {
        if(session.getAttribute("loggedInUser") != null)
        {
            redirectAttributes.addFlashAttribute("loggedInUser",session.getAttribute("loggedInUser"));
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("user",new User());
        return new ModelAndView("/login");
    }

    @GetMapping(value="/signUp")
    public String signup(Model model){
        model.addAttribute("user",new User());
        return "signup";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user)
    {
        userService.saveUser(user);
        return "home";
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("user") User user,RedirectAttributes redirectAttributes,HttpSession session,@ModelAttribute("loggedInUser") User loggedInUserThisController) {
        if(session.getAttribute("loggedInUser") != null)
        {
            redirectAttributes.addFlashAttribute("loggedInUser",session.getAttribute("loggedInUser"));
            return new ModelAndView("redirect:/");
        }
        else
        {
           Optional<User> validUserOrNull = userService.authenticateUser(user.getLoginName(), user.getLoginPwd());
            if (!validUserOrNull.isPresent())
                return new ModelAndView("/login");
            else
            {
                //System.out.println(validUserOrNull.get());
                session.setAttribute("loggedInUser",validUserOrNull.get());
                redirectAttributes.addFlashAttribute("loggedInUser",validUserOrNull.get());
                //System.out.println(session.getAttribute("theUser"));
                return new ModelAndView("redirect:/");
                //return productController.showProductList(model,(User)session.getAttribute("loggedInUserThisController"));
            }
        }
    }
}