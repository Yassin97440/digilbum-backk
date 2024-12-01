package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Table
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ColumnDefault("'not specified'")
    @Column( nullable = false, length = 150)
    private String name;

    private LocalDate startedAt;

    private LocalDate endedAt;

    private Integer favoritePicture;

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    @ManyToOne
    private User createdBy;

}