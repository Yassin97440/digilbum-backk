package com.digilbum.app.controller;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.security.user.User;
import com.digilbum.app.service.GroupService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v2/group")
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
    @GetMapping("/byJoinCode")
    public GroupDto findByJoinCode(@RequestParam String joinCode){
        return groupService.toDto(
                groupService.findByJoinCode(joinCode)
        );
    }
    @GetMapping("/")
    List<GroupDto> loadGroupsForUser(){
        return groupService.loadGroupsForUser();
    }
    @GetMapping("/{groupId}")
    public GroupDto findById(@PathVariable Integer groupId){
        return groupService.findById(groupId);
    }
}
