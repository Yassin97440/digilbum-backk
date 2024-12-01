package com.digilbum.app.repositorys;

import com.digilbum.app.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}