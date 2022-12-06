package com.digilbum.app.services;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.User;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    UserRepository userRepository;
    @PostMapping("/new")
    public void addNewAlbum(@RequestBody Album album){
        System.out.println(album.getName());
        System.out.println(album);
        Optional<User> user = userRepository.findById(1);
        album.setUser(user.get());
        albumRepository.save(album);
    }
}
