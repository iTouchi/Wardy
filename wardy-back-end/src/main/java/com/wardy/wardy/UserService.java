package com.wardy.wardy;

import com.wardy.wardy.user.User;
import com.wardy.wardy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserService {

    @Autowired
    UserRepository repo;


    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = new User();
        user.setName(name);
        Example<User> example = Example.of(user);
        Optional<User> optionalUser = repo.findOne(example);

        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("Could not find user with name: " + name);
        }

        user = optionalUser.get();
        return user;
    }
}
