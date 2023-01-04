package com.digilbum.app.controllers;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.PictureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class PictureControllerImpl implements IPictureController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String folderPath = "Pictures/digilbum";
    private final String BASE_PATH = "C:/Users/yassi/Pictures/digilbum";

    @Autowired
    PictureRepository pictureRepository;

    /*
     * pour save un fichier, il faut construire le path du fichier
     * quand on recoit une picture volatile, le pathFile contient le nom du fichier
     * pour construire cet url on va utiliser le nom de l'album :
     * PATH/images/album/pictureName
     * puis plus tard :
     * PATH/pictures/familly/event/album/pictureName
     */
    @Override
    public List<Picture> saveNewPictures(List<Picture> pictures) {

        for (Picture picture : pictures) {
            generatePicturePathFile(picture);
        }

        return (List<Picture>) pictureRepository.saveAll(pictures);
    }

    // public Picture savePicture(Picture picture) {
    // generatePicturePathFile(picture);
    // return pictureRepository.save(picture);
    // }

    private void generatePicturePathFile(Picture picture) {
        String path = folderPath + picture.getAlbum().getName() + "/" + picture.getPathFile();
        picture.setPathFile(path);
    }

    @Override
    public void deletePictureFile(Picture picture) {
        pictureRepository.delete(picture);
    }

    @Override
    public List<Picture> writeAndSavePictures(List<MultipartFile> pictures, Album album) {
        List<Picture> newPictures = new ArrayList<>();
        int i = 0;
        try {
            for (MultipartFile pic : pictures) {
                String fileName = album.getName() + Calendar.getInstance().getTimeInMillis() + ".png";
                Path path = Paths.get(BASE_PATH, "/", fileName);
                pic.transferTo(path);
                System.out.println(path);
                Picture newPic = new Picture();
                newPic.setAlbum(album);
                newPic.setPathFile(folderPath + "/" + fileName);
                newPictures.add(newPic);
                pictureRepository.save(newPic);
                return newPictures;
            }
        } catch (Exception e) {
            logger.error("erreur pour photos de cette album : " + album.toString(), e);
        }
        return null;
    }
}
