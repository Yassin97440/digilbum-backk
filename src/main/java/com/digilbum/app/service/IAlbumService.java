package com.digilbum.app.service;

import com.digilbum.app.dto.AlbumDto;

import java.util.List;

public interface IAlbumService {

    List<AlbumDto> getDtos();

    List<AlbumDto> loadDtosForOwner();

    public AlbumDto newAlbum(AlbumDto newAlbum);

    void deleteAlbum(Integer albumId);

    List<AlbumDto> loadSharedAlbumsForUser();
}
