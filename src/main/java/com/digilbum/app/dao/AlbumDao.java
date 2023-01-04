package com.digilbum.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digilbum.app.models.Album;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class AlbumDao {

    @Autowired
    EntityManager entityManager;

    public Album loadAlbumByName(String albumName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Album> criteriaQuery = criteriaBuilder.createQuery(Album.class);
        Root<Album> album = criteriaQuery.from(Album.class);
        Predicate albumNamePredicate = criteriaBuilder.equal(album.get("name"), albumName);

        criteriaQuery.where(albumNamePredicate);

        TypedQuery<Album> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();

    }
}
