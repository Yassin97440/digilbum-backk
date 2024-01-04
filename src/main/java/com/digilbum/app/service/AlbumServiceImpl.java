package com.digilbum.app.service;

import java.util.List;
import java.util.Optional;

import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;

@Controller
public class AlbumServiceImpl implements IAlbumService {


    private final AlbumRepository albumRepository;

    private final IPictureService pictureController;

    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository,
                            IPictureService pictureController,
                            UserRepository userRepository
    ) {
        this.albumRepository = albumRepository;
        this.pictureController = pictureController;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<Album> loadAlbumsWithPictures() {
        List<Album> albums = albumRepository.findAll();
        return pictureController.addWebPathForPictures(albums);
    }

    @Override
    public Album newAlbum(Album newAlbum) {

        Optional<User> user = userRepository.findById(1
//                ( (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()
        );
        user.ifPresent(newAlbum::setUser);
        return albumRepository.save(newAlbum);
    }

}
