package com.digilbum.app.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digilbum.app.controllers.IPictureController;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.PictureRepository;

@RestController
@RequestMapping("/pictures")
public class PictureService {

    public final String BASE_PATH = "file:///C:/Users/yassi/";

    @Autowired
    IPictureController pictureController;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    AlbumRepository albumRepository;

    public List<Picture> saveNewPictures(@RequestBody List<Picture> picturesToSave) {
        return pictureController.saveNewPictures(picturesToSave);
    }

    public List<Picture> loadAllPicturesForAlbum() {
        return null;
    }

    @PostMapping(path = "/writeAndSavePictures", consumes = "multipart/form-data")
    public void writeAndSavePictures(@RequestPart List<MultipartFile> pictures, Album album) {
        System.out.println("call postMethodMultidata");
        pictureController.writeAndSavePictures(pictures, album);
        // album.setPictures((Set<Picture>) newPictures);
        System.out.println("on save les pics");
        // pictureRepository.saveAll(newPictures);

    }

}
