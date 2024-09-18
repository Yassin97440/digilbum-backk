package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserGroupMapping {
    @EmbeddedId
    private UserGroupMappingId id = new UserGroupMappingId();

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @MapsId("groupId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GroupId", nullable = false)
    private Group group;

    @ColumnDefault("0")
    @Column(name = "Admin", nullable = false)
    private Boolean admin = false;

    @Column(name = "joinedAt")
    private Instant joinedAt;

}