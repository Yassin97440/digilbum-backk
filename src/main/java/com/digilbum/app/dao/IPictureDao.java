package com.digilbum.app.dao;

import com.digilbum.app.models.Picture;

public interface IPictureDao {

    public Iterable<Picture> loadAllPictoreForAlbum(int albumId);

}
