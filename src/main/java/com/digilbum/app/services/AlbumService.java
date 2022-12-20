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

//    @Autowired
//    IPictureController pictureController;

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

    /**
     * @return
     */
    @GetMapping("/getpicTest")
    public @ResponseBody ResponseEntity<byte[]> getThePictureTest( ) {
        InputStream in = getClass()
                .getResourceAsStream("C:\\Users\\yassin.abdulla\\Pictures\\bgpng.png");
        System.out.println("inputStream"+in);
        var imgFile = new ClassPathResource("C:\\Users\\yassin.abdulla\\Pictures\\bgpng");
        // try {
        //     System.out.println("imgFIles with classpatchrsrc : "+imgFile.getInputStream());
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        byte[] bytes;
        try {
            bytes = StreamUtils.copyToByteArray(in);
            // System.out.println(bytes.toString());
//            bytes = IOUtils.toByteArray(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);


//        String path = "C:\\Users\\yassi\\Desktop\\album digital\\app\\src\\main\\java\\com\\digilbum\\app";
//        InputStream pic = getClass().getResourceAsStream(path);
//        BufferedImage image;
//        try {
//           image = ImageIO.read(new URL("https://www.pngall.com/wp-content/uploads/2016/05/Nature-Free-PNG-Image.png"));
//           ImageInputStream iis = ImageIO.createImageInputStream(image);
//           InputStreamResource isr = new InputStreamResource((InputStream) iis);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        ResponseEntity<InputStreamResource> response = ResponseEntity.ok()
//                .contentType(contentType)
//                .body(new InputStreamResource());



    }
}
