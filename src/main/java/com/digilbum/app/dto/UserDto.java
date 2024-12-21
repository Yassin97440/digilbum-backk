package com.digilbum.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * DTO for {@link com.digilbum.app.security.user.User}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(Integer id, String firstname, String lastname, String email) implements Serializable {
}