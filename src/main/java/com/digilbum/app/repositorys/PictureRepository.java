package com.digilbum.app.repositorys;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
    List<Picture> findByAlbum(Album album);

    @Query("select p from Picture p where p.album.id = ?1")
    List<Picture> findByAlbumId(@NonNull Integer id);

}