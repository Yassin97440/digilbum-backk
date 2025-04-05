package com.digilbum.app.controller;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.service.IAlbumService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public AlbumDto getAlbumById(@PathVariable Integer id) {
        return albumService.getDtoById(id);
    }

    @PostMapping("/new")
    public AlbumDto addNew(@RequestBody AlbumDto album) {
        return albumService.create(album);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteOne")
    public void delete(@RequestParam Integer albumId) {
        logger.info("delete album");
        albumService.delete(albumId);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody AlbumDto album) {
        try {
            albumService.update(album);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.severe("Error updating album  : " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/forOwner")
    public List<AlbumDto> loadForOwner() {
        return albumService.loadDtosForOwner();
    }

    @GetMapping("/forUser")
    List<AlbumDto> loadSharedAlbumsForUser() {
        try {
            return albumService.loadSharedAlbumsForUser();
        } catch (Exception e) {
            logger.severe("Error loading shared albums for user  : " + e.getMessage());
            throw e;
        }
    }

    @GetMapping("/forEvent/{eventId}")
    List<AlbumDto> loadAlbumsForEvent(@PathVariable Integer eventId) {
        try {
            return albumService.loadForEvent(eventId);
        } catch (Exception e) {
            logger.severe("Error loading albums for event : " + e.getMessage());
            throw e;
        }
    }

}
