package com.digilbum.app.controllers;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PictureControllerImpl implements IPictureController{

    private final String folderPath = "C:\\Users\\yassi\\Desktop\\album digital";
    @Override
    public Picture saveNewPictureFile(File file) {
        return null;
    }

    @Override
    public File getPictureFile(Picture picture) {
        return null;
    }

    @Override
    public List<Picture> saveNewPicturesFiles(Album album, List<File> files) {
        List<Picture> pictures= new ArrayList<>();
        for (File file:files) {
            File bi = new File("folderPath"+"/"+album.getName()+"/"+file.getName());
//        ImageIO.write()

        }
        return null;
    }

    @Override
    public List<File> getPictureFiles(List<Picture> pictures) {
        List<File> pictureFiles= new ArrayList<>();
        try {
            for (Picture pic: pictures) {
                BufferedImage image = ImageIO.read(new File(folderPath+pic.getPathFile()));

//                pictureFiles.add(image);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deletePictureFile(Picture picture) {
        return false;
    }
}
