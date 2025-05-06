package com.digilbum.app.security.auth;

import com.digilbum.app.repositorys.RoleRepository;
import com.digilbum.app.security.exception.RoleNotFoundException;
import com.digilbum.app.security.user.Role;
import com.digilbum.app.security.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorizationServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthorizationService authorizationService;

    private User user;
    private Role basicRole;

    @BeforeEach
    void setUp() {
        user = new User();
        basicRole = new Role();
    }

    @Test
    void giveUserBasicRole_WhenRoleExists_ShouldAddRoleToUser() {
        // Arrange
        when(roleRepository.findById("ROLE_USER")).thenReturn(Optional.of(basicRole));

        // Act
        authorizationService.giveUserBasicRole(user);

        // Assert
        assertTrue(user.getAuthorities().contains(basicRole));
        verify(roleRepository, times(1)).findById("ROLE_USER");
    }

    @Test
    void giveUserBasicRole_WhenRoleDoesNotExist_ShouldThrowException() {
        // Arrange
        when(roleRepository.findById("ROLE_USER")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RoleNotFoundException.class, () -> {
            authorizationService.giveUserBasicRole(user);
        });
        verify(roleRepository, times(1)).findById("ROLE_USER");
    }
}