package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.vapasians.shopoholics.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean isUserAuthenticated(User validUser, String passwordFromLoginForm)
    {
        return validUser.getLoginPwd().equals(passwordFromLoginForm);
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public Optional<User> isUserValidByUsername(String username)
    {
        return userRepository.findUserByLoginName(username);
    }
}