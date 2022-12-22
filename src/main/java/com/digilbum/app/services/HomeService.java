package com.digilbum.app.services;

import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.PictureRepository;
import com.digilbum.app.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v2")
public class HomeService {

    @Autowired
    AlbumRepository albumRepo;
    @Autowired
    PictureRepository pictureRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/hello/{name}")
    public @ResponseBody String helloWorld(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/post")
    public @ResponseBody String postMethodTest(@RequestBody Album album) {
        System.out.println(album.getName());
        return "Hello " + album.getName();
    }
}
