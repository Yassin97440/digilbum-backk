package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "`groups`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer id;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private GroupType type;

    @ColumnDefault("not specified")
    @Column( nullable = false, length = 150)
    private String name;

    @CreatedDate
    private Instant createdAt;

    @CreatedBy()
    private User createdBy;

    @Column( nullable = false, length = 250, unique = true)
    private String joinCode;

}