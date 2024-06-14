package com.example.montrack.user.service;

import com.example.montrack.exceptions.ApplicationException;
import com.example.montrack.user.dto.RegisterRequestDTO;
import com.example.montrack.user.dto.RegisterResponseDTO;
import com.example.montrack.user.repository.UserRepository;
import com.example.montrack.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
    }

    @Override
    public User profile(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ApplicationException("Email is registered");
        }
        User newUser = user.toEntity();
        var password = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(password);
        User savedUser = userRepository.save(newUser);
        var response = new RegisterResponseDTO();
        response.setEmail(newUser.getEmail());
        return RegisterResponseDTO.fromEntity(savedUser);
    }

    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setUpdatedAt(Instant.now());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        Optional<User> currentUser = userRepository.findById(id);
        if(currentUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found") ;
        }
        userRepository.deleteById(id);
    }
}
