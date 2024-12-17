package com.digilbum.app.service;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.models.GroupType;
import com.digilbum.app.security.user.User;
import com.digilbum.app.security.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    GroupService groupService;
    @Autowired
    UserRepository userRepository;
    static User userTest;

    @BeforeAll
    static void createUserTest(){
        userTest = User.builder()
                .id(7)
                .firstname("john")
                .lastname("doe")
                .email("yassinabdulla7@gmail.com")
                .build();
    }

    @Test
    void create() {
        GroupDto result = groupService.create(
                new GroupDto(
                        null,
                        GroupType.Familly,
                        "TEST UNIT",
                        Instant.now(),
                        groupService.generateJoinCode()
                ),
                userTest
        );

        Assertions.assertThat(result.id()).isNotNull();
        Assertions.assertThat(result.groupType()).isEqualTo(GroupType.Familly);
        Assertions.assertThat(result.joinCode()).isNotNull();
    }

@Test
void generateJoinCode_shouldGenerateCodeWithLengthOf8() {
    String joinCode = groupService.generateJoinCode();
    
    Assertions.assertThat(joinCode).hasSize(8);
    Assertions.assertThat(joinCode).matches("[A-Z0-9]{8}");
}

@Test
void generateJoinCode_shouldProduceDifferentCodesOnConsecutiveCalls() {
    String firstCode = groupService.generateJoinCode();
    String secondCode = groupService.generateJoinCode();

    Assertions.assertThat(firstCode).isNotEqualTo(secondCode);
    Assertions.assertThat(firstCode).hasSize(8);
    Assertions.assertThat(secondCode).hasSize(8);
    Assertions.assertThat(firstCode).matches("[A-Z0-9]{8}");
    Assertions.assertThat(secondCode).matches("[A-Z0-9]{8}");
}
}
