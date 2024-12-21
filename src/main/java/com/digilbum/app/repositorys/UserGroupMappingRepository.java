package com.digilbum.app.repositorys;

import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.models.UserGroupMappingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupMappingRepository
        extends JpaRepository<UserGroupMapping, UserGroupMappingId>
{
    List<UserGroupMapping> findById_User_Id(Integer id);

    List<UserGroupMapping> findById_Group_Id(Integer groupId);
}