package com.digilbum.app.service;

import com.digilbum.app.models.Album;
import com.digilbum.app.models.AlbumGroupMapping;
import com.digilbum.app.models.Group;
import com.digilbum.app.repositorys.AlbumGroupMappingRepository;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumSharingServiceTest {

    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private AlbumGroupMappingRepository albumGroupMappingRepository;

    private AlbumSharingService albumSharingService;

    @BeforeEach
    void setUp() {
        albumSharingService = new AlbumSharingService(
                albumRepository,
                groupRepository,
                albumGroupMappingRepository);
    }

//    @Test
//    void shareAlbumWithGroup_ShouldCreateMapping() {
//        // Arrange
//        Integer albumId = 1;
//        Integer groupId = 1;
//        Album album = new Album();
//        album.setId(albumId);
//        Group group = new Group();
//        group.setId(groupId);
//
//        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
//        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
//
//        // Act
//        albumSharingService.shareAlbumWithGroup(albumId, groupId);
//
//        // Assert
//        ArgumentCaptor<AlbumGroupMapping> mappingCaptor = ArgumentCaptor.forClass(AlbumGroupMapping.class);
//        verify(albumGroupMappingRepository).save(mappingCaptor.capture());
//
//        AlbumGroupMapping capturedMapping = mappingCaptor.getValue();
//        assertEquals(album, capturedMapping.getAlbum());
//        assertEquals(group, capturedMapping.getGroup());
//    }
//
//    @Test
//    void shareAlbumWithGroup_WhenAlbumNotFound_ShouldThrowException() {
//        // Arrange
//        Integer albumId = 1;
//        Integer groupId = 1;
//        when(albumRepository.findById(albumId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(RuntimeException.class,
//                () -> albumSharingService.shareAlbumWithGroup(albumId, groupId));
//        verify(albumGroupMappingRepository, never()).save(any());
//    }

//    @Test
//    void unshareAlbumWithGroup_ShouldDeleteMapping() {
//        // Arrange
//        Integer albumId = 1;
//        Integer groupId = 1;
//        Album album = new Album();
//        album.setId(albumId);
//        Group group = new Group();
//        group.setId(groupId);
//
//        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
//        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
//
//        // Act
//        albumSharingService.unshareAlbumWithGroup(albumId, groupId);
//
//        // Assert
//        verify(albumGroupMappingRepository).deleteById(any());
//    }
}