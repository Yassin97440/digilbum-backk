package com.digilbum.app.controller;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.service.IAlbumService;
import com.digilbum.app.models.Album;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v2/album")
public class AlbumController {

    Logger logger = Logger.getLogger(getClass().getName());

    IAlbumService albumService;

    public AlbumController(IAlbumService albumController) {
        this.albumService = albumController;
    }

    @PostMapping("/new")
    public Album addNew(@RequestBody AlbumDto album) {
        return albumService.newAlbum(album);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteOne")
    public void delete(@RequestParam Integer albumId) {
        logger.info("delete album");
        albumService.deleteAlbum(albumId);
    }

    @GetMapping("/getAll")
    public Iterable<AlbumDto> loadAll() {
        return albumService.getDtos();
    }

    @GetMapping("/forOwner")
    public List<AlbumDto> loadForOwner() {
        return albumService.loadDtosForOwner();
    }


}
