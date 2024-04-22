package com.digilbum.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

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
    @Column(name = "Type", nullable = false)
    private String type;

    @ColumnDefault("not specified")
    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name = "CreatedAt")
    private Instant createdAt;

    @Column(name = "JoinCode", nullable = false, length = 250)
    private String joinCode;

}