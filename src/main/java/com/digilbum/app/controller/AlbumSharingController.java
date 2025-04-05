package com.digilbum.app.controller;

import com.digilbum.app.dto.GroupDto;
import com.digilbum.app.service.AlbumSharingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/album-sharing")
@RequiredArgsConstructor
public class AlbumSharingController {

    private final AlbumSharingService albumSharingService;

    @GetMapping("/share")
    public ResponseEntity<Void> shareAlbum(
            @RequestParam Integer albumId,
            @RequestParam Integer... groupIds) {
        albumSharingService.shareAlbumWithGroup(albumId, groupIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unshare")
    public ResponseEntity<Void> unshareAlbum(
            @RequestParam Integer albumId,
            @RequestParam Integer groupId) {
        albumSharingService.unshareAlbumWithGroup(albumId, groupId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/groups")
    public ResponseEntity<List<GroupDto>> getGroupsForAlbumShared(@RequestParam Integer albumId) {
        return ResponseEntity.ok(albumSharingService.loadGroupsForSharedAlbum(albumId));
    }
}