package com.digilbum.app.repositorys;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
    public List<Picture> findByAlbum(Album album);
}