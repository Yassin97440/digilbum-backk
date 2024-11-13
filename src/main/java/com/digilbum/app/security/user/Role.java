package com.digilbum.app.security.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private String id;

    @ManyToMany
    private final List<Permission> allowedPermissions = new ArrayList<>();

    @Override
    public String getAuthority() {
        return id;
    }

    public Collection<Permission> getAllowedOperations() {
        return allowedPermissions;
    }
}

