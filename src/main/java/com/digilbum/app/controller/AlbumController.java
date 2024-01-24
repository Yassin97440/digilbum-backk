package com.digilbum.app.controller;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.service.IAlbumService;
import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;

import com.digilbum.app.security.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://159.89.0.150:3000")
@RequestMapping("/api/v2/album")
//@CrossOrigin(origins = "loca")
public class AlbumController {
    AlbumRepository albumRepository;
    UserRepository userRepository;
    IAlbumService albumService;

    public AlbumController(AlbumRepository albumRepository,
                           UserRepository userRepository,
                           IAlbumService albumController) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.albumService = albumController;
    }

    @PostMapping("/new")
    public Album addNewAlbum(@RequestBody Album album) {
        return albumService.newAlbum(album);
    }

    @GetMapping("/getAll")
    public Iterable<AlbumDto> loadAllAlbums() {

        Iterable<Album> albums=albumRepository.findAll();
        List<AlbumDto> returnList = new ArrayList<>();
        for (Album album : albums){
            returnList.add(new AlbumDto(album.getId(), album.getName()));
        }
        return returnList;
    }

    @GetMapping("/albumsWithPictures")
    public ResponseEntity<Iterable<Album>> loadAlbumsWithPictures() {
        return new ResponseEntity<>(albumService.loadAlbumsWithPictures(), HttpStatus.OK);
    }

}
