package com.digilbum.app.service;

import com.digilbum.app.dto.UserDto;
import com.digilbum.app.security.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail()
        );
    }
}
