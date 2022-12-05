package com.digilbum.app.repositorys;

import com.digilbum.app.models.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
}