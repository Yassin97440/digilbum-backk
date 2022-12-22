package com.digilbum.app.controllers;

import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PictureControllerImpl implements IPictureController {

    private final String folderPath = "file:///C:/Users/yassi/Pictures/digilbum";

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

    public Picture savePicture(Picture picture) {
        generatePicturePathFile(picture);

        return pictureRepository.save(picture);

    }

    private void generatePicturePathFile(Picture picture) {
        String path = folderPath + picture.getAlbum().getName() + "/" + picture.getPathFile();
        picture.setPathFile(path);
    }

    @Override
    public void deletePictureFile(Picture picture) {
        pictureRepository.delete(picture);
    }
}
