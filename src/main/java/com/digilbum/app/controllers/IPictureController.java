package com.digilbum.app.controllers;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.Picture;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public interface IPictureController {
    public Picture saveNewPictureFile(File file);
    public File getPictureFile(Picture picture);

    public List<Picture> saveNewPicturesFiles(Album album, List<File> files);
    public List<File> getPictureFiles(List<Picture>  picture);

    FileInputStream getPictureFromDisk(String pathFile);

    public boolean deletePictureFile(Picture picture);
}
