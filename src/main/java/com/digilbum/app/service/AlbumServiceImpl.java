package com.digilbum.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.models.AlbumGroupMapping;
import com.digilbum.app.models.Picture;
import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.digilbum.app.models.Album;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.service.AlbumSharingService;

@Service
public class AlbumServiceImpl implements IAlbumService {

    private final AlbumRepository albumRepository;
    private final IPictureService pictureService;
    private final UserRepository userRepository;
    private final String picturesHost;
    private final String picturesFolders;
    private final AlbumSharingService albumSharingService;

    public AlbumServiceImpl(AlbumRepository albumRepository,
            IPictureService pictureService,
            UserRepository userRepository,
            @Value("${pictures.server.url}") String picturesHost,
            @Value("${pictures.folder.path}") String picturesFolders,
            AlbumSharingService albumSharingService) {
        this.albumRepository = albumRepository;
        this.pictureService = pictureService;
        this.userRepository = userRepository;
        this.picturesHost = picturesHost;
        this.picturesFolders = picturesFolders;
        this.albumSharingService = albumSharingService;
    }

    @Override
    public List<AlbumDto> getDtos() {
        Iterable<Album> albums = albumRepository.findAll();
        List<AlbumDto> returnList = new ArrayList<>();
        for (Album album : albums) {
            returnList.add(
                    toDto(album));
        }
        return returnList;
    }

    @Override
    public List<AlbumDto> loadDtosForOwner() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authUser.getPrincipal();
        return albumRepository.findDtoByUserId(user.getId());
    }

    @Override
    public Album newAlbum(AlbumDto newAlbumDto) {
        Album newAlbum = toEntity(newAlbumDto);
        Optional<User> user = userRepository.findById(1);
        user.ifPresent(newAlbum::setUser);
        return albumRepository.save(newAlbum);
    }

    @Override
    public void deleteAlbum(Integer albumId) {
        Album album = albumRepository.findById(albumId).get();
        pictureService.deletePictures(album);
        albumRepository.delete(album);
    }

    @Override
    public List<AlbumDto> getSharedAlbumsForUser(Integer userId) {
        List<AlbumDto> albumDtos = new ArrayList<>();
        List<AlbumGroupMapping> sharedAlbums = albumSharingService.findAlbumsByUserGroups(userId);
        for (AlbumGroupMapping albumGroupMapping : sharedAlbums) {
            albumDtos.add(toDto(albumGroupMapping.getAlbum()));
        }
        return albumDtos;
    }

    private AlbumDto toDto(Album album) {
        String coverPicPath = "";
        Optional<Picture> pic = album.getPictures().stream().findFirst();
        if (pic.isPresent()) {
            coverPicPath = pic.get().getPathFile();
            coverPicPath = picturesHost + picturesFolders + coverPicPath;
        }
        return new AlbumDto(
                album.getId(),
                album.getName(),
                coverPicPath,
                album.getStartDate(),
                album.getEndDate());

    }

    private Album toEntity(AlbumDto albumDto) {
        Album album = new Album();
        album.setName(albumDto.name());
        album.setStartDate(albumDto.startedAt());
        album.setEndDate(albumDto.endedAt());
        return album;
    }

}
