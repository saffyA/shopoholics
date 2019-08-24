package com.vapasians.shopoholics.controller;

import com.vapasians.shopoholics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.vapasians.shopoholics.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
//@SessionAttributes("loggedInUserForUserController")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ProductController productController;

//    @ModelAttribute("loggedInUserForUserController")
//    public User createLoggedInSessionObject()
//    {
//        return new User();
//    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(Model model,RedirectAttributes redirectAttributes, HttpSession session)
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
    public ModelAndView loginUser(@ModelAttribute("user") User user,RedirectAttributes redirectAttributes,HttpSession session) {
        if(isAnyLoggedInUserInSession(session))
        {
            System.out.println("found loggedin user");
            redirectAttributes.addFlashAttribute("loggedInUser",session.getAttribute("loggedInUser"));
            return new ModelAndView("redirect:/");
        }
        else
        {
            String usernameFromLoginForm = user.getLoginName();
            String passwordFromLoginForm = user.getLoginPwd();

            Optional<User> userExistsOrNull = userService.isUserValidByUsername(usernameFromLoginForm);
            if(!userExistsOrNull.isPresent())
                return new ModelAndView("login");

            User validUser = userExistsOrNull.get();
            if(!userService.isUserAuthenticated(validUser,passwordFromLoginForm))
                return new ModelAndView("login");
            else
            {
                session.setAttribute("loggedInUser",validUser);
                redirectAttributes.addFlashAttribute("loggedInUser",validUser);
                return new ModelAndView("redirect:/");
                //return productController.showProductList(model,(User)session.getAttribute("loggedInUserThisController"));
            }
        }
    }

    public boolean isAnyLoggedInUserInSession(HttpSession session)
    {
        return session.getAttribute("loggedInUser") != null;
    }
}