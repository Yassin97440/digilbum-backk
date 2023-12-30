package com.digilbum.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;

@Controller
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    IPictureService pictureController;

    @Override
    public Iterable<Album> loadAlbumsWithPictures() {
        List<Album> albums = albumRepository.findAll();
        return pictureController.addWebPathForPictures(albums);
    }

}
