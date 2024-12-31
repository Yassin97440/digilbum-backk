package com.digilbum.app.controller;

import com.digilbum.app.dto.AlbumDto;
import com.digilbum.app.dto.EventDto;
import com.digilbum.app.service.EventService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2/event")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;


    @GetMapping("/{id}")
    EventDto loadById(@PathVariable Integer id){
        return eventService.load(id);
    }
    @PostMapping("/")
        EventDto create(@RequestBody EventDto newEvent) {
        return eventService.create(newEvent);
    }

    @GetMapping("/")
    List<EventDto> loadForUser(){
        return eventService.loadForUser();
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody EventDto event) {
        try {
            eventService.update(event);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            logger.error("Error updating album ",e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete")
    public void delete(@RequestParam Integer eventId) {
        eventService.delete(eventId);
    }

}
