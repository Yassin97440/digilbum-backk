package com.digilbum.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.models.Picture;
import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.springframework.stereotype.Controller;

import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;

@Controller
public class AlbumServiceImpl implements IAlbumService {


    private final AlbumRepository albumRepository;

    private final IPictureService pictureService;

    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository,
                            IPictureService pictureService,
                            UserRepository userRepository
    ) {
        this.albumRepository = albumRepository;
        this.pictureService = pictureService;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<Album> loadAlbumsWithPictures() {

        List<Album> albums = albumRepository.findAll();
        return pictureService.addWebPathForPictures(albums);
    }

    @Override
    public List<AlbumDto> getDtos() {
        Iterable<Album> albums=albumRepository.findAll();
        List<AlbumDto> returnList = new ArrayList<>();
        for (Album album : albums){
            returnList.add(
                    toDto(album)
            );
        }
        return returnList;
    }

    @Override
    public List<AlbumDto> loadDtosForUser(Integer userId){
       return albumRepository.findDtoByUserId(userId);
    }

    @Override
    public Album newAlbum(Album newAlbum) {

        Optional<User> user = userRepository.findById(1
        );
        user.ifPresent(newAlbum::setUser);
        return albumRepository.save(newAlbum);
    }

    @Override
    public void deleteAlbum(Integer albumId) {
        albumRepository.deleteById(albumId);
    }

    private AlbumDto toDto(Album album)
    {
        String coverPicPath ="";
        Optional<Picture> pic = album.getPictures().stream().findFirst();
        if (pic.isPresent()) {
            coverPicPath = pic.get().getPathFile();
        }
        return new AlbumDto(
                album.getId(),
                album.getName(),
                coverPicPath
        );

    };
}
