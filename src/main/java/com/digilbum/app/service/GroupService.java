package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.Group;
import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.models.UserGroupMappingId;
import com.digilbum.app.repositorys.GroupRepository;
import com.digilbum.app.repositorys.UserGroupMappingRepository;
import com.digilbum.app.security.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;

    public Group save(GroupDto groupToSave){

        return groupRepository.save(
                        Group.builder()
                                .name(groupToSave.groupName())
                                .type(groupToSave.groupType())
                                .build()
                    )
        ;
    }

    public GroupDto create(GroupDto groupToCreate, User creator){
        Group group = Group.builder()
                .name(groupToCreate.groupName())
                .type(groupToCreate.groupType())
                .joinCode(generateJoinCode())
                .build();
        final Group gr = groupRepository.save(group);


        return toDto(
                userGroupMappingRepository.save(
                        createAssociation(creator, gr, true)
                ).getGroup()
        );
    }

    public GroupDto addMember(String joinCode, User newMember) {
        Group group = findByJoinCode(joinCode);
        return addMember(newMember, group);

    }

    public GroupDto addMember(User newMember, Group group) {
        createAssociation(newMember, group, false);

        return toDto(
                userGroupMappingRepository.save(
                        createAssociation(newMember, group, false)
                ).getGroup()
        );
    }

    private UserGroupMapping createAssociation(User user, Group gr, boolean admin) {

        return new UserGroupMapping(
                new UserGroupMappingId(user, gr),
                admin,
                Instant.now()
        );

    }

    public Group findByJoinCode(String joinCode)  throws EntityNotFoundException {
        return groupRepository.findByJoinCode(joinCode);
    }

    public String generateJoinCode(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder joinCode = new StringBuilder();
        Random random = new Random();
        int codeLength = 8;

        do {
            joinCode.setLength(0);
            for (int i = 0; i < codeLength; i++) {
                joinCode.append(characters.charAt(random.nextInt(characters.length())));
            }
        } while (groupRepository.existsByJoinCode(joinCode.toString()));

        return joinCode.toString();
    }
    public GroupDto toDto(Group group){
        return new GroupDto(
                group.getId(),
                group.getType(),
                group.getName(),
                group.getCreatedAt(),
                group.getJoinCode()
        );
    }

    public Group toEntity(GroupDto dto) {
        return Group.builder()
               .id(dto.id())
               .name(dto.groupName())
               .type(dto.groupType())
               .joinCode(dto.joinCode())
               .build();
    }

    public List<GroupDto> loadGroupsForUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authUser.getPrincipal();
        List<GroupDto> groupsUserDto = new ArrayList<>();
        for (UserGroupMapping mapping : getUserGroupMappingsForUser(user.getId())) {
            groupsUserDto.add(toDto(mapping.getGroup()));
        }
        return groupsUserDto;
    }

    private Iterable<? extends UserGroupMapping> getUserGroupMappingsForUser(Integer userId) {
        return userGroupMappingRepository.findById_User_Id(userId);
    }
}
