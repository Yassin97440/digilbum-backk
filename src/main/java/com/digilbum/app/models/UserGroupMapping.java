package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class UserGroupMapping {
    @EmbeddedId
    private UserGroupMappingId id = new UserGroupMappingId();

    public Group getGroup(){
        return id.getGroup();
    }

    public User getUser(){
        return id.getUser();
    }

    @ColumnDefault("0")
    @Column( nullable = false)
    private Boolean admin = false;

    @CreatedDate
    @Column(name = "joinedAt")
    private Instant joinedAt;


}