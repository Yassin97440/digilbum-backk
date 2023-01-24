package com.digilbum.app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PictureId", nullable = false)
    private Integer id;

    @Column(name = "PathFile", nullable = false)
    private String pathFile;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "AlbumId", nullable = false)
    @JsonBackReference
    private Album album;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}