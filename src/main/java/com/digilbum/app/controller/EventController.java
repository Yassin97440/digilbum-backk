package com.digilbum.app.controller;

import com.digilbum.app.dto.EventDto;
import com.digilbum.app.service.EventService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2/event")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;


    @PostMapping("/")
    EventDto create(EventDto newEvent) {
        return eventService.create(newEvent);
    }

    @GetMapping("/")
    List<EventDto> loadForUser(){
        return eventService.loadForUser();
    }
}
