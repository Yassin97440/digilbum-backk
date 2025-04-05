package com.digilbum.app.controller;

import com.digilbum.app.dto.UserGroupMappingDto;
import com.digilbum.app.service.UserGroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2/user-group")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @GetMapping("/users")
    public List<UserGroupMappingDto> loadMembers(@RequestParam Integer groupId) {
        return userGroupService.loadMembers(groupId);
    }
}
