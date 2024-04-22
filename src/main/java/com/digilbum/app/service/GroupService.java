package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.Group;
import com.digilbum.app.repositorys.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupDto save(GroupDto groupToSave){

        return toDto(
                    groupRepository.save(
                        Group.builder()
                                .name(groupToSave.name())
                                .type(groupToSave.type())
                                .build()
                    )
        );
    }

    public GroupDto findByJoinCode(String joinCode){
        return toDto(groupRepository.findByJoinCode(joinCode));
    }

    public GroupDto toDto(Group group){
        return new GroupDto(
                group.getId(),
                group.getType(),
                group.getName(),
                group.getCreatedAt()
        );
    }
}
