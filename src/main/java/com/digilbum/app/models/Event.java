package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ColumnDefault("'not specified'")
    @Column( nullable = false, length = 150)
    private String name;

    @Column
    private LocalDate startedAt;

    @Column
    private LocalDate endedAt;

    @Column
    private Integer favoritePicture;

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(nullable = true)
    private User createdBy;

}