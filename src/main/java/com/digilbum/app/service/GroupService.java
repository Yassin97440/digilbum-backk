package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.Group;
import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.repositorys.GroupRepository;
import com.digilbum.app.repositorys.UserGroupMappingRepository;
import com.digilbum.app.security.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;

    public Group save(GroupDto groupToSave){

        return groupRepository.save(
                        Group.builder()
                                .name(groupToSave.name())
                                .type(groupToSave.type())
                                .build()
                    )
        ;
    }

    public GroupDto create(GroupDto groupToCreate, User creator){
        final Group gr = groupRepository.save(
                Group.builder()
                        .name(groupToCreate.name())
                        .type(groupToCreate.type())
                        .joinCode(generateJoinCode())
                        .build()
        );
        return toDto(
                userGroupMappingRepository.save(
                        createAssociation(creator, gr, true)
                ).getGroup()
        );
    }

    public GroupDto addMember(String joinCode, User newMember) {
        Group group = findByJoinCode(joinCode);
        createAssociation(newMember, group, false);

        return toDto(
                userGroupMappingRepository.save(
                        createAssociation(newMember, group, false)
                ).getGroup()
        );

    }

    private UserGroupMapping createAssociation(User user, Group gr, boolean admin) {
        return UserGroupMapping.builder()
                .user(user)
                .group(gr)
                .joinedAt(Instant.now())
                .admin(admin)
                .build();

    }

    public Group findByJoinCode(String joinCode)  throws EntityNotFoundException {
        return groupRepository.findByJoinCode(joinCode);
    }

    public String generateJoinCode(){

        return "YOUPI786";
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
}
