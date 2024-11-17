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
                .firstname("Yassin")
                .lastname("Abdulla")
                .email("yassinabdulla7@gmail.com")
                .build();
    }

    @Test
    void create() {
        GroupDto result = groupService.create(
                new GroupDto(
                        null,
                        GroupType.Familly,
                        "THE TEST",
                        Instant.now(),
                        null
                ),
                userTest
        );

        Assertions.assertThat(result.id()).isNotNull();
        Assertions.assertThat(result.type()).isEqualTo(GroupType.Familly);
        Assertions.assertThat(result.joinCode()).isNotNull();
    }
}
