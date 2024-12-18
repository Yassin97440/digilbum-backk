package com.digilbum.app.service;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.security.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private IPictureService pictureService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AlbumSharingService albumSharingService;

    private AlbumServiceImpl albumService;

    @BeforeEach
    void setUp() {
        albumService = new AlbumServiceImpl(
                albumRepository,
                pictureService,
                userRepository,
                "http://localhost",
                "/pictures",
                albumSharingService);
    }

    @Test
    void getSharedAlbumsForUser_ShouldReturnAccessibleAlbums() {
        // Arrange
        Integer userId = 1;
        List<AlbumDto> expectedAlbums = Arrays.asList(
                new AlbumDto(1, "Album 1", null, null, null),
                new AlbumDto(2, "Album 2", null, null, null));
        when(albumRepository.findAccessibleAlbumsByUserId(userId))
                .thenReturn(expectedAlbums);

        // Act
        List<AlbumDto> result = albumService.getSharedAlbumsForUser(userId);

        // Assert
        assertEquals(expectedAlbums, result);
        verify(albumRepository).findAccessibleAlbumsByUserId(userId);
    }


}