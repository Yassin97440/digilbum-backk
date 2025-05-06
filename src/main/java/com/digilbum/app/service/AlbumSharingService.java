package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.*;
import com.digilbum.app.repositorys.AlbumGroupMappingRepository;
import com.digilbum.app.repositorys.AlbumRepository;
import com.digilbum.app.repositorys.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumSharingService {

    private final AlbumRepository albumRepository;
    private final GroupRepository groupRepository;
    private final AlbumGroupMappingRepository albumGroupMappingRepository;
    private final GroupService groupService;

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

    List<AlbumGroupMapping> findAlbumsByUserGroups(Integer userId) {
        return albumGroupMappingRepository.findAlbumsByUserGroups(userId);
    }

    public List<GroupDto> loadGroupsForSharedAlbum(Integer albumId) {
        Optional<List<AlbumGroupMapping>> albumGroupMappings = albumGroupMappingRepository
                .findAllById_Album_Id(albumId);
        if (!albumGroupMappings.isPresent()) {
            throw new EntityNotFoundException("AlbumGroupMapping");
        }
        List<Group> groups = new ArrayList<>();
        for (AlbumGroupMapping mapping : albumGroupMappings.get()) {
            groups.add(mapping.getId().getGroup());
        }
        return groups.stream()
                .map(groupService::toDto).toList();
    }
}