package com.digilbum.app.repositorys;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.models.Album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("SELECT new com.digilbum.app.dto.AlbumDto(a.id, a.name, null,a.startDate, a.endDate)" +
            "FROM Album  a where a.user.id = ?1")
    List<AlbumDto> findDtoByUserId(Integer userId);

    List<Album> findByUserId(Integer userId);
}