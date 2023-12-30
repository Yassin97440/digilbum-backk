package com.digilbum.app.service;

import com.digilbum.app.models.Album;

public interface IAlbumService {

    public Iterable<Album> loadAlbumsWithPictures();

}
