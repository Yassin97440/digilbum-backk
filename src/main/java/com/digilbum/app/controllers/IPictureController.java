package com.digilbum.app.controllers;

import com.digilbum.app.models.Picture;

import java.util.List;

public interface IPictureController {

    public List<Picture> saveNewPictures(List<Picture> pictures);

    public void deletePictureFile(Picture picture);
}
