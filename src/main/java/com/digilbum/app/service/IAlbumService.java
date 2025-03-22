package com.digilbum.app.service;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.models.Album;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface IAlbumService {

    List<AlbumDto> getDtos();

    List<AlbumDto> loadDtosForOwner();

    AlbumDto create(AlbumDto newAlbum);

    void update(AlbumDto albumDto);

    void deleteAlbum(Integer albumId);

    List<AlbumDto> loadSharedAlbumsForUser();

    AlbumDto getDtoById(Integer id);

    List<AlbumDto> loadAlbumsForEvent(Integer eventId);

    Album getAtomicAlbum(PictureServiceImpl pictureServiceImpl, Integer albumId);

}
