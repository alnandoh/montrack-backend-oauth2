package com.example.montrack.user.controller;

import com.example.montrack.auth.helpers.Claims;
import com.example.montrack.responses.Response;
import com.example.montrack.user.dto.RegisterRequestDTO;
import com.example.montrack.user.entity.User;
import com.example.montrack.user.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllUsers(){
        return userService.findAll();
    }

//    @GetMapping("/profile")
//    public ResponseEntity<?> profile(){
//        var claims = Claims.getClaimsFromJwt();
//        var email = (String) claims.get("sub");
//
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO user){

        return Response.success("User register success",userService.register(user));
    }

    @DeleteMapping
    public String deleteUser(Long id){
        userService.deleteUser(id);
        return "User successfully deleted";
    }
}
