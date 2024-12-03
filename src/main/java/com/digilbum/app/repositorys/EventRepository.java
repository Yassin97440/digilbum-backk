package com.digilbum.app.repositorys;

import com.digilbum.app.dto.EventDto;
import com.digilbum.app.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("select new com.digilbum.app.dto.EventDto(" +
            "e.id, e.name, e.startedAt, e.endedAt) " +
            "from Event e" +
            " where e.createdBy.id = ?1")
    List<EventDto> findForUser(Integer userId);
}