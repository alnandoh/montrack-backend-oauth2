package com.example.montrack.user.service;

import com.example.montrack.user.dto.RegisterRequestDTO;
import com.example.montrack.user.dto.RegisterResponseDTO;
import com.example.montrack.user.entity.User;

import java.util.List;

public interface UserService {
    RegisterResponseDTO register(RegisterRequestDTO user);
    List<User> findAll();
    User findById(Long id);
    User profile(String email);
    User updateUser (Long id, User user);
    void deleteUser (Long id);
}
