package com.digilbum.app.controller;

import com.digilbum.app.service.IAlbumService;
import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;

import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v2/album")
//@CrossOrigin(origins = "loca")
public class AlbumController {
    AlbumRepository albumRepository;
    UserRepository userRepository;
    IAlbumService albumController;

    public AlbumController(AlbumRepository albumRepository,
                           UserRepository userRepository,
                           IAlbumService albumController) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.albumController = albumController;
    }

    @PostMapping("/new")
    public Album addNewAlbum(@RequestBody Album album) {
        Optional<User> user = userRepository.findById(1);
        user.ifPresent(album::setUser);
        return albumRepository.save(album);
    }

    @GetMapping("/getAll")
    public Iterable<Album> loadAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/albumsWithPictures")
    public ResponseEntity<Iterable<Album>> loadAlbumsWithPictures() {
        System.out.println(HttpStatus.OK);
        return new ResponseEntity<>(albumController.loadAlbumsWithPictures(), HttpStatus.OK);
    }

}
