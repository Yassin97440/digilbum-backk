package com.digilbum.app.repositorys;

import com.digilbum.app.models.AlbumGroupMapping;
import com.digilbum.app.models.AlbumGroupMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumGroupMappingRepository extends JpaRepository<AlbumGroupMapping, AlbumGroupMappingId> {

    @Query("SELECT agm FROM AlbumGroupMapping agm WHERE agm.id.group.id IN " +
            "(SELECT ugm.id.group.id FROM UserGroupMapping ugm WHERE ugm.id.user.id = :userId)")
    List<AlbumGroupMapping> findAlbumsByUserGroups(Integer userId);
}