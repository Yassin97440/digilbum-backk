package com.digilbum.app.service;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.models.Album;

import java.util.List;

public interface IAlbumService {

    List<AlbumDto> getDtos();

    List<AlbumDto> loadDtosForOwner();

    AlbumDto create(AlbumDto newAlbum);

    void update(AlbumDto albumDto);

    void delete(Integer albumId);

    List<AlbumDto> loadSharedAlbumsForUser();

    AlbumDto getDtoById(Integer id);

    List<AlbumDto> loadForEvent(Integer eventId);

    Album getAtomicAlbum(PictureServiceImpl pictureServiceImpl, Integer albumId);

}
