package com.devhelper.eventregistrationservice.controller.impl;

import static com.devhelper.eventregistrationservice.constant.HeaderConstant.TID;
import com.devhelper.eventregistrationservice.controller.EventController;
import com.devhelper.eventregistrationservice.entity.Event;
import com.devhelper.eventregistrationservice.exception.EventNotFoundException;
import com.devhelper.eventregistrationservice.service.EventService;
import lombok.RequiredArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {

    private final EventService eventService;

    @PostMapping("/event")
    @Override
    public ResponseEntity<Event> createEvent(Event event, Map<String,String> headers) {
        Event eventReponseObj = eventService.createEvent(event);
        return ResponseEntity.created(null).header(TID, headers.get(TID)).body(eventReponseObj);
    }

    @GetMapping("/events")
    @Override
    public ResponseEntity<List<Event>> fetchAllEvents(Map<String, String> headers) {
        List<Event> eventsList = new ArrayList<>();
        eventService.fetchAllEvents().forEach(event -> {
            Link linkBuilder=linkTo(methodOn(this.getClass())
                    .fetchEventById(event.getEventId().toString(),headers)).withSelfRel();
        event.add(linkBuilder);
            eventsList.add(event);
        });
        return ResponseEntity.ok(eventsList);
    }

    @GetMapping("/event/eventId/{eventId}")
    @Override
    public ResponseEntity<Event> fetchEventById(String eventId, Map<String, String> headers) {
        Optional<Event> event = eventService.fetchEventEventId(Long.parseLong(eventId));
        if(!event.isPresent()) {
            throw new EventNotFoundException("Event not found with eventId :"+eventId);
        }
        Link link=WebMvcLinkBuilder.linkTo(methodOn(this.getClass())
                .fetchAllEvents(headers)).withSelfRel();
        Event eventResponse = event.get().add(link);
        return ResponseEntity.ok(eventResponse);
    }

    @GetMapping("/event/eventName/{eventName}")
    @Override
    public ResponseEntity<Event> fetchEventByName(String eventName, Map<String, String> headers) {
        Optional<Event> event = eventService.fetchEventByEventName(eventName);
        return ResponseEntity.ok(event.get());
    }

    @PatchMapping("/event/{eventId}")
    @Override
    public ResponseEntity<Event> updateEvent(Event event, Long eventId, Map<String, String> headers) {
        Event updatedEvent = eventService.updateEvent(event, eventId);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/event/{eventId}")
    @Override
    public ResponseEntity<String> deleteEvent(Long eventId, Map<String, String> headers) {
        String response = eventService.deleteEvent(eventId);
        return ResponseEntity.ok(response);
    }
}
