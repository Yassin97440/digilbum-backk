package com.digilbum.app.repositorys;

import com.digilbum.app.dto.EventDto;
import com.digilbum.app.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("select new com.digilbum.app.dto.EventDto(" +
            "e.id, e.name, e.startedAt, e.endedAt) " +
            "from Event e" +
            " where e.createdBy.id = ?1")
    List<EventDto> findForUser(Integer userId);

    @Override
    Optional<Event> findById(Integer integer);

    @Transactional
    @Modifying
    @Query("update Event e set e.name = ?1, e.startedAt = ?2, e.endedAt = ?3 where e.id = ?4")
    int update(String name, LocalDate startedAt, LocalDate endedAt, Integer id);
}