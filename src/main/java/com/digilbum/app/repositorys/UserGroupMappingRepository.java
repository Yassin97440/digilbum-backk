package com.digilbum.app.repositorys;

import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.models.UserGroupMappingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, UserGroupMappingId> {
}