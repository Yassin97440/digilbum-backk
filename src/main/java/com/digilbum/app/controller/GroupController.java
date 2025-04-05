package com.digilbum.app.controller;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.security.user.User;
import com.digilbum.app.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/api/v2/group")
public class GroupController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/")
    public GroupDto create(@RequestBody GroupDto groupDto) {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authUser.getPrincipal();
        return groupService.create(groupDto, user);
    }

    @PostMapping("/addMember")
    public GroupDto addMember(@RequestBody String joinCode) {
        return groupService.addMember(joinCode);
    }

    @GetMapping("/byJoinCode")
    public ResponseEntity<GroupDto> findByJoinCode(@RequestParam String joinCode) {
        try {
            return ResponseEntity.ok(
                    groupService.toDto(
                            groupService.findByJoinCode(joinCode)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    List<GroupDto> loadForUser() {
        return groupService.loadForUser();
    }

    @GetMapping("/{groupId}")
    public GroupDto findById(@PathVariable Integer groupId) {
        return groupService.findById(groupId);
    }
}
