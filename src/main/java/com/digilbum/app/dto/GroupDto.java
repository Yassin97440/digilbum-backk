package com.digilbum.app.dto;

import com.digilbum.app.models.GroupType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.digilbum.app.models.Group}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GroupDto(Integer id, GroupType groupType, String groupName, Instant createdAt, String joinCode) implements Serializable {
}