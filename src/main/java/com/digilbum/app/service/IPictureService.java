package com.digilbum.app.service;

import com.digilbum.app.dto.PictureDto;
import com.digilbum.app.models.Album;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    List<PictureDto> writeAndSave(List<MultipartFile> pictures, Integer albumId) throws IOException;

    List<PictureDto> loadForAlbum(Integer albumId);

    void deletePictures(Album album);
}
