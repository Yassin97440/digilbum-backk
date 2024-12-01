package com.digilbum.app.service;

import com.digilbum.app.dto.EventDto;
import com.digilbum.app.models.Event;
import com.digilbum.app.repositorys.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    Event create(EventDto eventDto) {
        return eventRepository.save(toEntity(eventDto));
    }

    Event toEntity(EventDto dto){
        Event event = new Event();
        event.setName(dto.name());
        event.setStartedAt(dto.startedAt());
        event.setEndedAt(dto.endedAt());
        return event;

    }
    EventDto toDto(Event event){
        return new EventDto(event.getId(),
                event.getName(),
                event.getStartedAt(),
                event.getEndedAt()
        );
    }
}
