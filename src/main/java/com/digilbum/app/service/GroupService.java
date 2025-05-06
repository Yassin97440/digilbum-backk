package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.Group;
import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.repositorys.GroupRepository;
import com.digilbum.app.security.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserGroupService userGroupService;
    final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Group save(GroupDto groupToSave) {

        return groupRepository.save(
                Group.builder()
                        .name(groupToSave.groupName())
                        .type(groupToSave.groupType())
                        .build());
    }

    public GroupDto create(GroupDto groupToCreate, User creator) {
        Group group = Group.builder()
                .name(groupToCreate.groupName())
                .type(groupToCreate.groupType())
                .joinCode(generateJoinCode())
                .build();
        final Group gr = groupRepository.save(group);

        return toDto(
                userGroupService.createAdminAssociation(creator, gr).getGroup());
    }

    public GroupDto addMember(String joinCode) {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User newMember = (User) authUser.getPrincipal();
        Group group = findByJoinCode(joinCode);
        return addMember(newMember, group);

    }

    public GroupDto addMember(User newMember, Group group) {

        return toDto(
                userGroupService.createBasicAssociation(newMember, group).getGroup());
    }

    public Group findByJoinCode(String joinCode) throws EntityNotFoundException {
        return groupRepository.findByJoinCode(joinCode);
    }

    public String generateJoinCode() {
        StringBuilder joinCode = new StringBuilder();
        Random random = new Random();
        int codeLength = 8;

        do {
            joinCode.setLength(0);
            for (int i = 0; i < codeLength; i++) {
                joinCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
        } while (groupRepository.existsByJoinCode(joinCode.toString()));

        return joinCode.toString();
    }

    public GroupDto toDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getType(),
                group.getName(),
                group.getCreatedAt(),
                group.getJoinCode());
    }

    public Group toEntity(GroupDto dto) {
        return Group.builder()
                .id(dto.id())
                .name(dto.groupName())
                .type(dto.groupType())
                .joinCode(dto.joinCode())
                .build();
    }

    public List<GroupDto> loadForUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authUser.getPrincipal();
        List<GroupDto> groupsUserDto = new ArrayList<>();
        for (UserGroupMapping mapping : userGroupService.loadUserGroupMappingsForUser(user.getId())) {
            groupsUserDto.add(toDto(mapping.getGroup()));
        }
        return groupsUserDto;
    }

    public GroupDto findById(Integer groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent())
            return toDto(group.get());

        throw new EntityNotFoundException("Group not found for id : " + groupId);
    }
}
