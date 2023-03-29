package com.example.eurekaclient.service;


import com.example.eurekaclient.model.User;
import com.example.eurekaclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceOwn implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User doesn't exists"));

        return UserDetailsOwn.fromUser(user);
    }

    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
    }

}
