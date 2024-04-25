package com.digilbum.app.controller;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.security.user.User;
import com.digilbum.app.service.GroupService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/group")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/")
    public GroupDto create(GroupDto groupDto, HttpSession session)   {
        User user = (User) session.getAttribute("user");
        return groupService.create(groupDto, user );
    }

    @PostMapping("/addMember")
    public GroupDto addMember(String joinCode, HttpSession session)   {
        User user = (User) session.getAttribute("user");
        return groupService.addMember(joinCode, user);
    }

}
