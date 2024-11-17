package com.digilbum.app.service;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.models.Album;

import java.util.List;

public interface IAlbumService {

    public Iterable<Album> loadAlbumsWithPictures();

    Iterable<Album> loadAlbumsWithPicturesForUser(Integer userId);

    List<AlbumDto> loadDtosForUser(Integer userId);

    public Album newAlbum(Album newAlbum);

    void deleteAlbum(Integer albumId);
}
