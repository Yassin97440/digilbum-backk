package com.digilbum.app.controllers;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureController {

    public List<Picture> saveNewPictures(List<Picture> pictures);

    public void deletePictureFile(Picture picture);

    public List<Picture> writeAndSavePictures(List<MultipartFile> pictures, Album album);

    public List<Picture> loadPicturesForAlbum(Album album);

}
