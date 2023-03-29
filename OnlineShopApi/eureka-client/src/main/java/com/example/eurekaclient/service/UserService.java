package com.example.eurekaclient.service;


import com.example.eurekaclient.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    void save(User user);

    void delete(Long id);

    List<User> getAllUsers();

    boolean existUserById(Long id);
}
