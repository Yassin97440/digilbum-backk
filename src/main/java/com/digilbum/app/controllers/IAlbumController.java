package com.digilbum.app.controllers;

import com.digilbum.app.models.Album;

public interface IAlbumController {

    public Iterable<Album> loadAlbumsWithPictures();

}
