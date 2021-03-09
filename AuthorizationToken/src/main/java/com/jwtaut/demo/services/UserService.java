package com.jwtaut.demo.services;

import com.jwtaut.demo.models.UserModel;
import com.jwtaut.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel foundUser = userRepository.findByUsername(username);
        if (foundUser == null) return null;

        String name = foundUser.getUsername();
        String password2 = foundUser.getPassword();

        return new User(name, password2, new ArrayList<>());
    }

}
