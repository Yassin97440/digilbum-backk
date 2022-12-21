package com.digilbum.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digilbum.app.controllers.IPictureController;
import com.digilbum.app.models.Picture;

@RestController
@RequestMapping("/pictures")
public class PictureService {

    @Autowired
    IPictureController pictureController;

    public List<Picture> saveNewPictures(@RequestBody List<Picture> picturesToSave) {
        return pictureController.saveNewPictures(picturesToSave);
    }

    public List<Picture> loadAllPicturesForAlbum() {
        return null;
    }

}
