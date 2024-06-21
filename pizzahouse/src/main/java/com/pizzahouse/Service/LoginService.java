package com.pizzahouse.Service;

import com.pizzahouse.Entity.User;
import com.pizzahouse.Repository.UserRepository;
import com.pizzahouse.Service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean signin(String email, String password) {
        User user = userRepository.findByEmail(email);
        return passwordEncoder.matches(password, user.getPassword());
    }




}
