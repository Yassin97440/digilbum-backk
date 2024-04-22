package com.digilbum.app.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`Group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GroupId", nullable = false)
    private Integer id;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false)
    private GroupType type;

    @ColumnDefault("not specified")
    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @CreatedDate
    @Column(name = "CreatedAt")
    private Instant createdAt;

    @Column(name = "JoinCode", nullable = false, length = 250)
    private String joinCode;

}