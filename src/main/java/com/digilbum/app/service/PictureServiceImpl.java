package com.digilbum.app.service;

import com.digilbum.app.dao.IPictureDao;
import com.digilbum.app.dto.PictureDto;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.PictureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class PictureServiceImpl implements IPictureService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String folderPath = "/pictures/";
    // private final String folderPath = "digilbum/";
    private final String BASE_PATH = System.getProperty("user.home") + folderPath;

    private final String WEB_PATH = "http://159.89.0.150/images/";
    // private final String BASE_PATH = "C:/Users/yassi/Pictures/" + folderPath;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    IPictureDao pictureDao;
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void deletePictureFile(Picture picture) {
        pictureRepository.delete(picture);
    }

    private String getTypePictureFile(String originalFileName) {
        String[] nameSpleted = originalFileName.split("\\.");
        return nameSpleted[1];
    }

    @Override
    public List<Picture> writeAndSavePictures(List<MultipartFile> pictures, Integer albumId) {
        logger.info("call : writeAndSavePictures");

        Album album =  albumRepository.findById(albumId).get();
        List<Picture> newPictures = new ArrayList<>();
        FileOutputStream fileOutputStream = null;
        String fileName = null;
        File picfile = null;
        try {
            for (MultipartFile pic : pictures) {
                fileName = generatePicturePathFile( album , pic);
                Path path = Paths.get(BASE_PATH, fileName);
                // on créer u nouveau fichier
                picfile = path.toFile();
                if (!picfile.createNewFile()) {
                    throw new IOException("Can't create new file for pictures"+path.toString());
                }
                // on créer un output stream pour écrire le nouveau fichier
                fileOutputStream = new FileOutputStream(picfile);
                // on donne le contenu de ce qu'on a recu
                fileOutputStream.write(pic.getBytes());
                fileOutputStream.close();
                // on sauvarde la photo en bdd

                Picture newPic = new Picture();
                newPic.setAlbum(album);
                newPic.setPathFile(fileName); // folderPath + "/" + fileName
                newPictures.add(newPic);
                pictureRepository.save(newPic);
            }
            return newPictures;
        } catch (Exception e) {
            logger.error("erreur pour photos de cette album : " + album.toString(), e);
        }
        return new ArrayList<>();
    }

    /**
     * pour save un fichier, il faut construire le path du fichier
     * quand on recoit une picture volatile, le pathFile contient le nom du fichier
     * pour construire cet url on va utiliser le nom de l'album :
     * /home/digilbum/pictures/
     * puis plus tard :
     * PATH/pictures/familly/event/album/pictureName
     */
    private String generatePicturePathFile(Album album, MultipartFile pic) {
        return album.getName().replaceAll(" ", "") + Calendar.getInstance().getTimeInMillis() + "."
                + getTypePictureFile(pic.getOriginalFilename());
    }

    @Override
    public List<Picture> loadPicturesForAlbum(Album album) {
        return pictureRepository.findByAlbum(album);
    }

    @Override
    public List<Album> addWebPathForPictures(List<Album> albums) {
        for (Album album : albums) {
            for (Picture picture : album.getPictures()) {
                picture.setPathFile(WEB_PATH + picture.getPathFile());
            }
        }
        return albums;
    }

    @Override
    public List<PictureDto> loadPicturesForAlbum(Integer albumId) {
        return addWebPathForPicturesDto(
                pictureRepository.findByAlbumId(albumId)
        );
    }


    public List<PictureDto> addWebPathForPicturesDto(List<Picture> pictures) {
        final List<PictureDto> finalDto = new ArrayList<>();
            for (Picture picture : pictures) {
                finalDto.add( new PictureDto(
                        picture.getId(),
                        WEB_PATH + picture.getPathFile(),
                        picture.getAlbum().getId()));
            }
        return finalDto;
    }
}
