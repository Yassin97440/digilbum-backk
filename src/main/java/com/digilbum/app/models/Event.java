package com.digilbum.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Table
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ColumnDefault("not specified")
    @Column( nullable = false, length = 150)
    private String name;

    private LocalDate startedAt;

    private LocalDate endedAt;

    private Integer favoritePicture;

    private Instant createdAt;

    private Integer createdBy;

}