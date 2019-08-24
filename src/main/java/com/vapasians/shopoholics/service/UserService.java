package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vapasians.shopoholics.model.User;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> authenticateUser(String username, String password)
    {
        return userRepository.findUserByLoginNameAndLoginPwd(username,password);
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }
}