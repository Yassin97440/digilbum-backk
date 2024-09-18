package com.digilbum.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.Group;
import com.digilbum.app.models.GroupType;
import com.digilbum.app.models.UserGroupMapping;
import com.digilbum.app.models.UserGroupMappingId;
import com.digilbum.app.repositorys.GroupRepository;
import com.digilbum.app.repositorys.UserGroupMappingRepository;
import com.digilbum.app.security.user.Role;
import com.digilbum.app.security.user.User;
import jakarta.persistence.EntityNotFoundException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GroupService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GroupServiceDiffblueTest {
    @MockBean
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @MockBean
    private UserGroupMappingRepository userGroupMappingRepository;

    /**
     * Method under test: {@link GroupService#create(GroupDto, User)}
     */
    @Test
    void testCreate() {
        // Arrange
        Group group = new Group();
        group.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group.setId(1);
        group.setJoinCode("Join Code");
        group.setName("Name");
        group.setType(GroupType.Familly);
        when(groupRepository.save(Mockito.<Group>any())).thenReturn(group);

        Group group2 = new Group();
        group2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group2.setId(1);
        group2.setJoinCode("Join Code");
        group2.setName("Name");
        group2.setType(GroupType.Familly);

        UserGroupMappingId id = new UserGroupMappingId();
        id.setGroupId(1);
        id.setUserId(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setId(1);
        user.setLastname("Doe");
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());

        UserGroupMapping userGroupMapping = new UserGroupMapping();
        userGroupMapping.setAdmin(true);
        userGroupMapping.setGroup(group2);
        userGroupMapping.setId(id);
        userGroupMapping.setJoinedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        userGroupMapping.setUser(user);
        when(userGroupMappingRepository.save(Mockito.<UserGroupMapping>any())).thenReturn(userGroupMapping);
        GroupDto groupToCreate = new GroupDto(1, GroupType.Familly, "Name",
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), "");

        User creator = new User();
        creator.setEmail("jane.doe@example.org");
        creator.setFirstname("Jane");
        creator.setId(1);
        creator.setLastname("Doe");
        creator.setPassword("iloveyou");
        creator.setRole(Role.USER);
        creator.setTokens(new ArrayList<>());

        // Act
        GroupDto actualCreateResult = groupService.create(groupToCreate, creator);

        // Assert
        verify(groupRepository).save(isA(Group.class));
        verify(userGroupMappingRepository).save(isA(UserGroupMapping.class));
        assertEquals("Name", actualCreateResult.name());
        assertEquals(0L, actualCreateResult.createdAt().getEpochSecond());
        assertEquals(1, actualCreateResult.id().intValue());
        assertEquals(GroupType.Familly, actualCreateResult.type());
    }

    /**
     * Method under test: {@link GroupService#create(GroupDto, User)}
     */
    @Test
    void testCreate2() {
        // Arrange
        Group group = new Group();
        group.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group.setId(1);
        group.setJoinCode("Join Code");
        group.setName("Name");
        group.setType(GroupType.Familly);
        when(groupRepository.save(Mockito.<Group>any())).thenReturn(group);
        when(userGroupMappingRepository.save(Mockito.<UserGroupMapping>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        GroupDto groupToCreate = new GroupDto(1, GroupType.Familly, "Name",
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), "");

        User creator = new User();
        creator.setEmail("jane.doe@example.org");
        creator.setFirstname("Jane");
        creator.setId(1);
        creator.setLastname("Doe");
        creator.setPassword("iloveyou");
        creator.setRole(Role.USER);
        creator.setTokens(new ArrayList<>());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> groupService.create(groupToCreate, creator));
        verify(groupRepository).save(isA(Group.class));
        verify(userGroupMappingRepository).save(isA(UserGroupMapping.class));
    }

    /**
     * Method under test: {@link GroupService#create(GroupDto, User)}
     */
    @Test
    void testCreate3() {
        // Arrange
        Group group = new Group();
        group.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group.setId(1);
        group.setJoinCode("Join Code");
        group.setName("Name");
        group.setType(GroupType.Familly);
        when(groupRepository.save(Mockito.<Group>any())).thenReturn(group);

        Group group2 = new Group();
        group2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group2.setId(1);
        group2.setJoinCode("Join Code");
        group2.setName("Name");
        group2.setType(GroupType.Familly);

        UserGroupMappingId id = new UserGroupMappingId();
        id.setGroupId(1);
        id.setUserId(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setId(1);
        user.setLastname("Doe");
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());

        Group group3 = new Group();
        group3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group3.setId(1);
        group3.setJoinCode("Join Code");
        group3.setName("Name");
        group3.setType(GroupType.Familly);
        UserGroupMapping userGroupMapping = mock(UserGroupMapping.class);
        when(userGroupMapping.getGroup()).thenReturn(group3);
        doNothing().when(userGroupMapping).setAdmin(Mockito.<Boolean>any());
        doNothing().when(userGroupMapping).setGroup(Mockito.<Group>any());
        doNothing().when(userGroupMapping).setId(Mockito.<UserGroupMappingId>any());
        doNothing().when(userGroupMapping).setJoinedAt(Mockito.<Instant>any());
        doNothing().when(userGroupMapping).setUser(Mockito.<User>any());
        userGroupMapping.setAdmin(true);
        userGroupMapping.setGroup(group2);
        userGroupMapping.setId(id);
        userGroupMapping.setJoinedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        userGroupMapping.setUser(user);
        when(userGroupMappingRepository.save(Mockito.<UserGroupMapping>any())).thenReturn(userGroupMapping);
        GroupDto groupToCreate = new GroupDto(1, GroupType.Familly, "Name",
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), "");

        User creator = new User();
        creator.setEmail("jane.doe@example.org");
        creator.setFirstname("Jane");
        creator.setId(1);
        creator.setLastname("Doe");
        creator.setPassword("iloveyou");
        creator.setRole(Role.USER);
        creator.setTokens(new ArrayList<>());

        // Act
        GroupDto actualCreateResult = groupService.create(groupToCreate, creator);

        // Assert
        verify(userGroupMapping).getGroup();
        verify(userGroupMapping).setAdmin(eq(true));
        verify(userGroupMapping).setGroup(isA(Group.class));
        verify(userGroupMapping).setId(isA(UserGroupMappingId.class));
        verify(userGroupMapping).setJoinedAt(isA(Instant.class));
        verify(userGroupMapping).setUser(isA(User.class));
        verify(groupRepository).save(isA(Group.class));
        verify(userGroupMappingRepository).save(isA(UserGroupMapping.class));
        assertEquals("Name", actualCreateResult.name());
        assertEquals(0L, actualCreateResult.createdAt().getEpochSecond());
        assertEquals(1, actualCreateResult.id().intValue());
        assertEquals(GroupType.Familly, actualCreateResult.type());
    }

    /**
     * Method under test: {@link GroupService#create(GroupDto, User)}
     */
    @Test
    void testCreate4() {
        // Arrange
        Group group = new Group();
        group.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group.setId(1);
        group.setJoinCode("Join Code");
        group.setName("Name");
        group.setType(GroupType.Familly);
        when(groupRepository.save(Mockito.<Group>any())).thenReturn(group);

        Group group2 = new Group();
        group2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group2.setId(1);
        group2.setJoinCode("Join Code");
        group2.setName("Name");
        group2.setType(GroupType.Familly);

        UserGroupMappingId id = new UserGroupMappingId();
        id.setGroupId(1);
        id.setUserId(1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setId(1);
        user.setLastname("Doe");
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        Group group3 = mock(Group.class);
        when(group3.getType()).thenReturn(GroupType.Familly);
        when(group3.getId()).thenReturn(1);
        when(group3.getName()).thenReturn("Name");
        when(group3.getCreatedAt()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        doNothing().when(group3).setCreatedAt(Mockito.<Instant>any());
        doNothing().when(group3).setId(Mockito.<Integer>any());
        doNothing().when(group3).setJoinCode(Mockito.<String>any());
        doNothing().when(group3).setName(Mockito.<String>any());
        doNothing().when(group3).setType(Mockito.<GroupType>any());
        group3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        group3.setId(1);
        group3.setJoinCode("Join Code");
        group3.setName("Name");
        group3.setType(GroupType.Familly);
        UserGroupMapping userGroupMapping = mock(UserGroupMapping.class);
        when(userGroupMapping.getGroup()).thenReturn(group3);
        doNothing().when(userGroupMapping).setAdmin(Mockito.<Boolean>any());
        doNothing().when(userGroupMapping).setGroup(Mockito.<Group>any());
        doNothing().when(userGroupMapping).setId(Mockito.<UserGroupMappingId>any());
        doNothing().when(userGroupMapping).setJoinedAt(Mockito.<Instant>any());
        doNothing().when(userGroupMapping).setUser(Mockito.<User>any());
        userGroupMapping.setAdmin(true);
        userGroupMapping.setGroup(group2);
        userGroupMapping.setId(id);
        userGroupMapping.setJoinedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        userGroupMapping.setUser(user);
        when(userGroupMappingRepository.save(Mockito.<UserGroupMapping>any())).thenReturn(userGroupMapping);
        GroupDto groupToCreate = new GroupDto(1, GroupType.Familly, "Name",
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), "");

        User creator = new User();
        creator.setEmail("jane.doe@example.org");
        creator.setFirstname("Jane");
        creator.setId(1);
        creator.setLastname("Doe");
        creator.setPassword("iloveyou");
        creator.setRole(Role.USER);
        creator.setTokens(new ArrayList<>());

        // Act
        GroupDto actualCreateResult = groupService.create(groupToCreate, creator);

        // Assert
        verify(group3).getCreatedAt();
        verify(group3).getId();
        verify(group3).getName();
        verify(group3).getType();
        verify(group3).setCreatedAt(isA(Instant.class));
        verify(group3).setId(eq(1));
        verify(group3).setJoinCode(eq("Join Code"));
        verify(group3).setName(eq("Name"));
        verify(group3).setType(eq(GroupType.Familly));
        verify(userGroupMapping).getGroup();
        verify(userGroupMapping).setAdmin(eq(true));
        verify(userGroupMapping).setGroup(isA(Group.class));
        verify(userGroupMapping).setId(isA(UserGroupMappingId.class));
        verify(userGroupMapping).setJoinedAt(isA(Instant.class));
        verify(userGroupMapping).setUser(isA(User.class));
        verify(groupRepository).save(isA(Group.class));
        verify(userGroupMappingRepository).save(isA(UserGroupMapping.class));
        assertEquals("Name", actualCreateResult.name());
        assertEquals(0L, actualCreateResult.createdAt().getEpochSecond());
        assertEquals(1, actualCreateResult.id().intValue());
        assertEquals(GroupType.Familly, actualCreateResult.type());
    }
}
