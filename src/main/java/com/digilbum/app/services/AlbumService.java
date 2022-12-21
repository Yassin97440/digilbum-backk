package com.digilbum.app.services;

import com.digilbum.app.controllers.IPictureController;
import com.digilbum.app.models.Album;
import com.digilbum.app.models.User;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/new")
    public void addNewAlbum(@RequestBody Album album) {
        System.out.println(album.getName());
        System.out.println(album);
        Optional<User> user = userRepository.findById(1);
        album.setUser(user.get());
        albumRepository.save(album);
    }

}
