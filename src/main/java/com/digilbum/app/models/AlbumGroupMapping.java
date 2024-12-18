package com.digilbum.app.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class AlbumGroupMapping {
    @EmbeddedId
    private AlbumGroupMappingId id = new AlbumGroupMappingId();

    public Group getGroup() {
        return id.getGroup();
    }

    public void setGroup(Group group) {
        id.setGroup(group);
    }

    public Album getAlbum() {
        return id.getAlbum();
    }

    public void setAlbum(Album album) {
        id.setAlbum(album);
    }

    @CreatedDate
    @Column(name = "sharedAt")
    private Instant sharedAt;
}