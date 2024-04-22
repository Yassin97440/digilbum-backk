package com.digilbum.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventId", nullable = false)
    private Integer id;

    @ColumnDefault("not specified")
    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name = "StarteddAt")
    private LocalDate starteddAt;

    @Column(name = "EndedAt")
    private LocalDate endedAt;

    @Column(name = "FavoritePicture")
    private Integer favoritePicture;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "createdBy")
    private Integer createdBy;

}