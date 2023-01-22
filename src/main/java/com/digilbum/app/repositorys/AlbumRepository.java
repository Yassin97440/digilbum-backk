package com.digilbum.app.repositorys;

import com.digilbum.app.models.Album;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}