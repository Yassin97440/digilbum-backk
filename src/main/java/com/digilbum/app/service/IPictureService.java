package com.digilbum.app.service;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    public void deletePictureFile(Picture picture);

    public List<Picture> writeAndSavePictures(List<MultipartFile> pictures, Integer albumId);

    public List<Picture> loadPicturesForAlbum(Album album);

    public List<Album> addWebPathForPictures(List<Album> albums);

}
