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
        model.addAttribute("user",new User());
        return new ModelAndView("/login");
    }

    @GetMapping(value="/signUp")
    public ModelAndView signup(Model model,HttpSession session){
        model.addAttribute("user",new User());
        return new ModelAndView("signup");
    }

    @PostMapping("/saveuser")
    public ModelAndView saveUser(User user, Model model)
    {
        Optional<User> userExistsOrNull=userService.isUserValidByUsername(user.getLoginName());
        if(userExistsOrNull.isPresent())
        {
            model.addAttribute("loginErrorMessage","User with this username already exists");
            return new ModelAndView("signup");
        }
        userService.saveUser(user);
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("user") User user, HttpSession session, Model model) {
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

        //login and redirect to home page
        return loginUser(session,validUser);
    }

    @GetMapping("/logout")
    public ModelAndView logoutUser(HttpSession session)
    {
        session.removeAttribute("loggedInUser");
        return new ModelAndView("redirect:/");
    }

    private ModelAndView loginUser(HttpSession session, User validUser)
    {
        storeAuthenticatedUserInSession(session,validUser);
        if(validUser.getRole()=='A')
            return new ModelAndView("redirect:/admin");
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