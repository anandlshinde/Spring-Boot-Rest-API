package com.devhelper.eventregistrationserviceconsumers.controller.impl;

import com.devhelper.eventregistrationservice.exception.EventNotFoundException;
import com.devhelper.eventregistrationserviceconsumers.controller.WebClientConsumerEventController;
import com.devhelper.eventregistrationserviceconsumers.entity.Event;
import com.devhelper.eventregistrationserviceconsumers.service.RestTemplateEventService;
import com.devhelper.eventregistrationserviceconsumers.service.WebClientEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.devhelper.eventregistrationserviceconsumers.constant.HeaderConstant.TID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class WebClientConsumerEventControllerImpl implements WebClientConsumerEventController {

    private final WebClientEventService eventService;
    @PostMapping("/web-client/event")
    @Override
    public ResponseEntity<Event> webClientCreateEvent(Event event, Map<String,String> headers) {
        Event eventResponse = eventService.createEvent(event);
        return ResponseEntity.created(null).header(TID, headers.get(TID)).body(eventResponse);
    }

    @GetMapping("/web-client/events")
    @Override
    public ResponseEntity<List<Event>> webClientFetchAllEvents(Map<String, String> headers) {
        List<Event> events = eventService.fetchAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/web-client/event/eventId/{eventId}")
    @Override
    public ResponseEntity<Event> webClientFetchEventById(String eventId, Map<String, String> headers) {
        Optional<Event> event = eventService.fetchEventEventId(Long.valueOf(eventId));
        if(!event.isPresent()) {
            throw new EventNotFoundException("Event not found with eventId :"+eventId);
        }
        return ResponseEntity.ok(event.get());
    }

    @GetMapping("/web-client/event/eventName/{eventName}")
    @Override
    public ResponseEntity<Event> webClientFetchEventByName(String eventName, Map<String, String> headers) {
        Optional<Event> event = eventService.fetchEventByEventName(eventName);
        return ResponseEntity.ok(event.get());
    }

    @PatchMapping("/web-client/event/{eventId}")
    @Override
    public ResponseEntity<Event> webClientUpdateEvent(Event event, Long eventId, Map<String, String> headers) {
        Event updatedEvent = eventService.updateEvent(event, eventId);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/web-client/event/{eventId}")
    @Override
    public ResponseEntity<String> webClientDeleteEvent(Long eventId, Map<String, String> headers) {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
}
