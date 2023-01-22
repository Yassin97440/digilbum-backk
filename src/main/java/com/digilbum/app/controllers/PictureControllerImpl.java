package com.digilbum.app.controllers;

import com.digilbum.app.dao.IPictureDao;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.PictureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class PictureControllerImpl implements IPictureController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String folderPath = "pictures/";
    private final String BASE_PATH = "~/digilbum/" + folderPath;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    IPictureDao pictureDao;

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

    private String getTypePictureFile(String originalFileName) {
        String[] nameSpleted = originalFileName.split("\\.");
        return nameSpleted[1];
    }

    @Override
    public List<Picture> writeAndSavePictures(List<MultipartFile> pictures, Album album) {
        logger.info("call : writeAndSavePictures");
        List<Picture> newPictures = new ArrayList<>();
        int i = 0;
        try {
            for (MultipartFile pic : pictures) {
                String fileName = album.getName() + Calendar.getInstance().getTimeInMillis() + "."
                        + getTypePictureFile(pic.getOriginalFilename());
                Path path = Paths.get(BASE_PATH, "/");

                // on créer u nouveau fichier
                File picfile = path.toFile();
                picfile.createNewFile();
                // on créer un output stream pour écrire le nouveau fichier
                FileOutputStream fileOutputStream = new FileOutputStream(picfile);
                // on donne le contenu de ce qu'on a recu
                fileOutputStream.write(pic.getBytes());
                fileOutputStream.close();
                // copy(pic.getInputStream(), path.resolve(fileName.trim()));
                // pic.transferTo(path);
                System.out.println(path);
                // on sauvarde la photo en bdd
                Picture newPic = new Picture();
                newPic.setAlbum(album);
                newPic.setPathFile(picfile.getAbsolutePath()); // folderPath + "/" + fileName
                newPictures.add(newPic);
                pictureRepository.save(newPic);
            }
            return newPictures;
        } catch (Exception e) {
            logger.error("erreur pour photos de cette album : " + album.toString(), e);
        }
        return null;
    }

    @Override
    public Iterable<Picture> loadAllPictoreForAlbum(int albumId) {
        return pictureDao.loadAllPictoreForAlbum(albumId);
    }
}
