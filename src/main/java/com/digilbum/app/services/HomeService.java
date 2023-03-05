package com.digilbum.app.services;

import com.digilbum.app.models.Album;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v2")
public class HomeService {


    @GetMapping("/hello/{name}")
    public @ResponseBody String helloWorld(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/userdir")
    public @ResponseBody String sysUserdir() {
        return System.getProperty("user.home");
    }

    @PostMapping("/post")
    public @ResponseBody String postMethodTest(@RequestBody Album album) {
        System.out.println(album.getName());
        return "Hello " + album.getName();
    }

}
