package com.digilbum.app.service;

import com.digilbum.app.dto.AlbumDto;

import java.util.List;

public interface IAlbumService {

    List<AlbumDto> getDtos();

    List<AlbumDto> loadDtosForOwner();

    AlbumDto create(AlbumDto newAlbum);

    void update(AlbumDto albumDto);

    void deleteAlbum(Integer albumId);

    List<AlbumDto> loadSharedAlbumsForUser();

    AlbumDto getDtoById(Integer id);

    List<AlbumDto> loadAlbumsForEvent(Integer eventId);
}
