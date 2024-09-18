package com.digilbum.app.security.auth;

import com.digilbum.app.dto.GroupDto;

public record RegisterRequest(UserRegisterRequest user, GroupDto group) {
}
