package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.dto.UserGroupMappingDto;
import com.digilbum.app.models.Group;
import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.models.UserGroupMappingId;
import com.digilbum.app.repositorys.UserGroupMappingRepository;
import com.digilbum.app.security.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserGroupService {

    private final UserGroupMappingRepository userGroupMappingRepository;
    private final UserService userService;

    Iterable<? extends UserGroupMapping> loadUserGroupMappingsForUser(Integer userId) {
        return userGroupMappingRepository.findById_User_Id(userId);
    }

    UserGroupMapping createAdminAssociation(User user, Group group) {
        return userGroupMappingRepository.save(createAssociation(user, group, true));
    }

    UserGroupMapping createBasicAssociation(User user, Group group) {
        return userGroupMappingRepository.save(createAssociation(user, group, false));
    }

    private UserGroupMapping createAssociation(User user, Group gr, boolean admin) {

        return new UserGroupMapping(
                new UserGroupMappingId(user, gr),
                admin,
                Instant.now());

    }

    public List<UserGroupMappingDto> loadMembers(Integer groupId) {
        List<UserGroupMapping> associations = userGroupMappingRepository.findById_Group_Id(groupId);
        return associations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    UserGroupMappingDto toDto(UserGroupMapping toParse) {
        return new UserGroupMappingDto(
                userService.toDto(
                        toParse.getId().getUser()),
                new GroupDto(
                        toParse.getId().getGroup().getId(),
                        toParse.getId().getGroup().getType(),
                        toParse.getId().getGroup().getName(),
                        toParse.getId().getGroup().getCreatedAt(),
                        toParse.getId().getGroup().getJoinCode()),
                toParse.getAdmin(),
                toParse.getJoinedAt());
    }
}
