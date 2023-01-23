package com.digilbum.app.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.digilbum.app.dao.IPictureDao;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.AlbumRepository;
import java.util.Set;

@Controller
public class AlbumControllerImpl implements IAlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    IPictureDao pictureDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.digilbum.app.controllers.IAlbumController#loadAlbumsWithPictures()
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.digilbum.app.controllers.IAlbumController#loadAlbumsWithPictures()
     */
    @Override
    public Iterable<Album> loadAlbumsWithPictures() {
        Iterable<Album> albums = albumRepository.findAll();

        for (Album album : albums) {
            final Set<Picture> list = new HashSet();
            album.setPictures(list);

            pictureDao.loadAllPictoreForAlbum(album.getId()).iterator().forEachRemaining(album.getPictures()::add);
        }

        return albums;
    }

}
