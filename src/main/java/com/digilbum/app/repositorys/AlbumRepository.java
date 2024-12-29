package com.digilbum.app.repositorys;

import com.digilbum.app.models.Album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("SELECT a FROM Album a where a.user.id = ?1")
    List<Album> findDtoByUserId(Integer userId);

}