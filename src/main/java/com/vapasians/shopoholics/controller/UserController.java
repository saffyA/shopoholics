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
    public ModelAndView showLoginPage(Model model, HttpSession session)
    {
        if(isAnyLoggedInUserInSession(session))
            return new ModelAndView("redirect:/");
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
    public ModelAndView loginUser(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if(isAnyLoggedInUserInSession(session))
            return new ModelAndView("redirect:/");

        //Get data entered in login form
        String usernameFromLoginForm = user.getLoginName();
        String passwordFromLoginForm = user.getLoginPwd();

        //Redirect to login page if username does not exit
        Optional<User> userExistsOrNull = userService.isUserValidByUsername(usernameFromLoginForm);
        if(!userExistsOrNull.isPresent()) {
            model.addAttribute("loginErrorMessage","Username does not exist!");
            return new ModelAndView("login");
        }

        //Redirect to login page if password entered in form and in user record do not match
        User validUser = userExistsOrNull.get();
        if(!userService.isUserAuthenticated(validUser,passwordFromLoginForm)) {
            model.addAttribute("loginErrorMessage","Wrong Password!");
            return new ModelAndView("login");
        }

        //login
        return loginUserAndRedirectToHomePage(session,validUser);
    }

    private ModelAndView loginUserAndRedirectToHomePage(HttpSession session, User validUser)
    {
        storeAuthenticatedUserInSession(session,validUser);
        return new ModelAndView("redirect:/");
        //return productController.showProductList(model,(User)session.getAttribute("loggedInUserThisController"));
    }

    private void storeAuthenticatedUserInSession(HttpSession session, User validUser)
    {
        session.setAttribute("loggedInUser",validUser);
    }

    private boolean isAnyLoggedInUserInSession(HttpSession session)
    {
        return session.getAttribute("loggedInUser") != null;
    }
}