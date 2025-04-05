package com.digilbum.app.controller;

import java.io.IOException;
import java.util.List;

import com.digilbum.app.dto.PictureDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.digilbum.app.service.IPictureService;

@RestController
@RequestMapping("/api/v2/pictures")
public class PictureController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final IPictureService pictureService;

    public PictureController(IPictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping(path = "/writeAndSavePictures")
    ResponseEntity<List<PictureDto>> writeAndSavePictures(
            @RequestPart("pictures") List<MultipartFile> pictures,
            @RequestParam String albumId) {

        try {
            return new ResponseEntity<>(
                    pictureService.writeAndSave(pictures, Integer.parseInt(albumId)),
                    HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Failed to write pictures for this album : " + albumId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findForAlbum")
    public List<PictureDto> findPicturesForAlbumId(@RequestParam Integer albumId) {
        return pictureService.loadForAlbum(albumId);
    }

}
