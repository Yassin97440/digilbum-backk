package com.digilbum.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digilbum.app.models.Picture;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class PictureDaoImpl implements IPictureDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public Iterable<Picture> loadAllPictoreForAlbum(int albumId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Picture> criteriaQuery = criteriaBuilder.createQuery(Picture.class);
        Root<Picture> picture = criteriaQuery.from(Picture.class);
        Predicate picture4albumPredicate = criteriaBuilder.equal(picture.get("album").get("id"), albumId);

        criteriaQuery.where(picture4albumPredicate);

        TypedQuery<Picture> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
