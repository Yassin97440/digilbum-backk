package com.digilbum.app.service;

import com.digilbum.app.dto.UserDto;
import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail());
    }
}
