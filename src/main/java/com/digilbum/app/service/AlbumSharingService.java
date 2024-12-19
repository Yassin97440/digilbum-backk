package com.digilbum.app.service;

import com.digilbum.app.models.*;
import com.digilbum.app.repositorys.AlbumGroupMappingRepository;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumSharingService {

    private final AlbumRepository albumRepository;
    private final GroupRepository groupRepository;
    private final AlbumGroupMappingRepository albumGroupMappingRepository;

    @Transactional
    public void shareAlbumWithGroup(Integer albumId, Integer[] groupIds) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album non trouvé"));
        for (Integer groupId : groupIds) {

            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

            AlbumGroupMapping mapping = new AlbumGroupMapping();
            AlbumGroupMappingId mappingId = new AlbumGroupMappingId();
            mappingId.setAlbum(album);
            mappingId.setGroup(group);
            mapping.setId(mappingId);
            albumGroupMappingRepository.save(mapping);
        }

    }

    @Transactional
    public void unshareAlbumWithGroup(Integer albumId, Integer groupId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album non trouvé"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        AlbumGroupMappingId mappingId = new AlbumGroupMappingId();
        mappingId.setAlbum(album);
        mappingId.setGroup(group);

        albumGroupMappingRepository.deleteById(mappingId);
    }

    List<AlbumGroupMapping> findAlbumsByUserGroups(Integer userId){
        return albumGroupMappingRepository.findAlbumsByUserGroups(userId);
    }
}