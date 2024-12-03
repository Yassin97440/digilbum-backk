package com.digilbum.app.service;

import com.digilbum.app.dto.EventDto;
import com.digilbum.app.models.Event;
import com.digilbum.app.repositorys.EventRepository;
import com.digilbum.app.security.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventDto create(EventDto eventDto) {
        return toDto(
                eventRepository.save(toEntity(eventDto))
        );
    }

    public List<EventDto> loadForUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authUser.getPrincipal();
        return eventRepository.findForUser(user.getId());
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
