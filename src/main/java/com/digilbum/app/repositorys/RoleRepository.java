package com.digilbum.app.repositorys;

import com.digilbum.app.security.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}