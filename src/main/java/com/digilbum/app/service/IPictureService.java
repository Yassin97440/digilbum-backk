package com.digilbum.app.service;

import com.digilbum.app.dto.PictureDto;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    List<Picture> writeAndSavePictures(List<MultipartFile> pictures, Integer albumId) throws IOException;

    List<Album> addWebPathForPictures(List<Album> albums);

    List<PictureDto> loadPicturesForAlbum(Integer albumId);

}
