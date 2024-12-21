package com.digilbum.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.digilbum.app.models.UserGroupMapping}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserGroupMappingDto(UserDto user, GroupDto group, Boolean admin,
                                  Instant joinedAt) implements Serializable {
}