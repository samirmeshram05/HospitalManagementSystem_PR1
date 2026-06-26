package com.hospital.service;

import java.util.List;

import com.hospital.entity.User;

public interface UserService {

    User registerUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

}