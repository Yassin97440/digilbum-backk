package com.digilbum.app.controller;

import java.util.List;

import com.digilbum.app.dto.PictureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.digilbum.app.service.IPictureService;
import com.digilbum.app.dao.AlbumDao;
import com.digilbum.app.models.Picture;


@RestController
@RequestMapping("/api/v2/pictures")
public class PictureController {

    public final String BASE_PATH = "file:///C:/Users/yassi/";

    @Autowired
    IPictureService pictureService;

    @Autowired
    AlbumDao albumDao;

    public List<Picture> loadAllPicturesForAlbum() {
        return null;
    }

    @PostMapping(path = "/writeAndSavePictures") //, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE}
    public void writeAndSavePictures(
            @RequestPart("pictures") List<MultipartFile> pictures,
            @RequestParam String albumId)
    {

        System.out.println("call postMethodMultidata");
        pictureService.writeAndSavePictures(pictures, Integer.parseInt(albumId));
        System.out.println("end postMethodMultidata");

    }
    @GetMapping("/findForAlbum")
    public List<PictureDto> findPicturesForAlbumId(@RequestParam Integer albumId){
        return pictureService.loadPicturesForAlbum(albumId);
    }

}
