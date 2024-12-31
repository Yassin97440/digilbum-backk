package com.digilbum.app.repositorys;

import com.digilbum.app.models.Album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("SELECT a FROM Album a where a.user.id = ?1")
    List<Album> findDtoByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("update Album a set a.name = :name, a.startDate = :startDate, a.endDate = :endDate where a.id = :id")
    void update(@Param("name") String name, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("id") Integer id);

    Optional<List<Album>> findALlByEvent_Id(Integer id);
}