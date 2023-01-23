package com.digilbum.app.services;

import com.digilbum.app.controllers.IAlbumController;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.User;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IAlbumController albumController;

    @PostMapping("/new")
    public Album addNewAlbum(@RequestBody Album album) {
        System.out.println(album.getName());
        System.out.println(album);
        Optional<User> user = userRepository.findById(1);
        album.setUser(user.get());
        album = albumRepository.save(album);
        return album;
    }

    @GetMapping("/getAll")
    public Iterable<Album> loadAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/albumsWithPictures")
    public @ResponseBody Iterable<Album> loadAlbumsWithPictures() {
        return albumController.loadAlbumsWithPictures();
    }

}
