package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`group`")
@EntityListeners(AuditingEntityListener.class)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer id;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupType type;

    @ColumnDefault("'not specified'")
    @Column(nullable = false, length = 150)
    private String name;

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(nullable = true)
    private User createdBy;

    @Column(nullable = false, length = 250, unique = true)
    private String joinCode;

    @OneToMany(mappedBy = "id.group", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<AlbumGroupMapping> sharedAlbums = new HashSet<>();


}