package com.wardy.wardy.auth;

import com.wardy.wardy.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String userName) {
        com.wardy.wardy.user.User user = userService.loadUserByUsername(userName);
        return new User(user.getName(), user.getPassword(), new ArrayList<>());
    }
}
