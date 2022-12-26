package com.digilbum.app.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    final String BASE_PATH = "C:\\Users\\yassi\\Desktop\\albumdigital";

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

    @PostMapping(path = "/p", consumes = "multipart/form-data")
    public void postMethodMultidata(@RequestPart List<MultipartFile> pictures, Album album) {
        System.out.println("call postMethodMultidata");
        List<Picture> newPictures = new ArrayList<>();
        int i = 0;
        try {
            for (MultipartFile pic : pictures) {
                String fileName = "" + i++ + ".jpg";
                Path path = Paths.get(BASE_PATH, "/", fileName);
                pic.transferTo(path);
                System.out.println(path);
                Picture newPic = new Picture();
                newPic.setAlbum(album);
                newPic.setPathFile(BASE_PATH + "/" + fileName);
                // newPictures.add(newPic);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // album.setPictures((Set<Picture>) newPictures);
        System.out.println("on save les pics");
        // pictureRepository.saveAll(newPictures);

    }

}
