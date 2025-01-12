package com.digilbum.app.repository;

import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {
        // Arrange
        String email = "test@example.com";
        User user = User.builder()
                .firstname("Test")
                .lastname("User")
                .email(email)
                .password("password123")
                .build();
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByEmail(email);

        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEmailDoesNotExist() {
        // Arrange
        String nonExistentEmail = "nonexistent@example.com";

        // Act
        boolean exists = userRepository.existsByEmail(nonExistentEmail);

        // Assert
        assertThat(exists).isFalse();
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExistsWithDifferentCase() {
        // Arrange
        String email = "Test@Example.com";
        User user = User.builder()
                .firstname("Test")
                .lastname("User")
                .email(email)
                .password("password123")
                .build();
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByEmail("test@example.com");

        // Assert
        assertThat(exists).isTrue();
    }
}
