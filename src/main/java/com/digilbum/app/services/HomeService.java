package com.digilbum.app.services;

import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.PictureRepository;
import com.digilbum.app.repositorys.UserRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(path = "/p", consumes = "multipart/form-data")
    public void postMethodMultidata(@RequestPart MultipartFile document) {
        Path path = Paths.get("C:\\Users\\yassi\\Desktop\\album digital\\photo.jpg");

        try {
            document.transferTo(path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
