package org.example.mapper;

import org.example.dto.UserResponseDTO;
import org.example.model.User;

public class UserMapper {
    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
