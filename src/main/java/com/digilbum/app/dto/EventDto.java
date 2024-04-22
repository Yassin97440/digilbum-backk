package com.digilbum.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.digilbum.app.models.Event}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EventDto(Integer id, String name, LocalDate starteddAt, LocalDate endedAt) implements Serializable {
}