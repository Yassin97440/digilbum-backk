package com.digilbum.app.security.config;

import com.digilbum.app.security.user.Permission;
import com.digilbum.app.security.user.Role;
import com.digilbum.app.security.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private Permission permission1;
    @Mock
    private Permission permission2;
    @Mock
    private Permission permission3;
    @Mock
    private Role role1;
    @Mock
    private Role role2;
    @Mock
    private User user;

    @Test
    void getUserPermissions_ShouldReturnAllUniquePermissions() {
        // Given
        when(permission1.getAuthority()).thenReturn("READ");
        when(permission2.getAuthority()).thenReturn("WRITE");
        when(permission3.getAuthority()).thenReturn("DELETE");

        when(role1.getAllowedOperations()).thenReturn(List.of(permission1, permission2));
        when(role2.getAllowedOperations()).thenReturn(List.of(permission2, permission3));

        when(user.getAuthorities()).thenReturn(List.of(role1, role2));

        // When
        List<String> permissions = jwtService.getUserPermissions(user);

        // Then
        assertThat(permissions)
                .hasSize(3)
                .containsExactlyInAnyOrder("READ", "WRITE", "DELETE");
    }
}
