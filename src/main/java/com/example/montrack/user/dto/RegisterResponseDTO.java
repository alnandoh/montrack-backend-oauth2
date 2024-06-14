package com.example.montrack.user.dto;

import com.example.montrack.user.entity.User;
import lombok.Data;

@Data
public class RegisterResponseDTO {
    private String username;
    private String email;

    public static RegisterResponseDTO fromEntity (User user){
        RegisterResponseDTO dto = new RegisterResponseDTO();
        dto.setUsername(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
