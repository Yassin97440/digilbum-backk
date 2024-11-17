package com.digilbum.app.security.auth;

import com.digilbum.app.repositorys.RoleRepository;
import com.digilbum.app.security.user.Role;
import com.digilbum.app.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService {

    RoleRepository roleRepository;

    public void giveUserBasicRole(User user) {
        Role role = roleRepository.findById("USER").orElseThrow(() -> new RuntimeException("Basic role not found"));
        user.getAuthorities().add(role);
    }
}
