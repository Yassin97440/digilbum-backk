package com.digilbum.app.dto;

import java.time.LocalDate;

public record AlbumDto(Integer id, String name, String coverImagePath, LocalDate startedAt, LocalDate endedAt) {
}
