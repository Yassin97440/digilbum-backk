package com.digilbum.app.repositorys;

import com.digilbum.app.security.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Permission, String> {
}