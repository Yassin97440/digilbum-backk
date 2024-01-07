package com.digilbum.app.dto;

import com.digilbum.app.models.Album;

public record PictureDto(Integer id, String pathFile, Integer albumId) {
}
