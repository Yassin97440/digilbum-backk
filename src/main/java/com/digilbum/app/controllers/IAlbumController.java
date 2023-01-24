package com.digilbum.app.controllers;

import com.digilbum.app.models.Album;

public interface IAlbumController {

    @Deprecated
    public Iterable<Album> loadAlbumsWithPictures();
}
