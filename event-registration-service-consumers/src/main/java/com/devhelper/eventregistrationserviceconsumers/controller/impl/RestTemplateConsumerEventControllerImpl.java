package com.devhelper.eventregistrationserviceconsumers.controller.impl;

import com.devhelper.eventregistrationserviceconsumers.controller.RestTemplateConsumerEventController;
import com.devhelper.eventregistrationserviceconsumers.entity.Event;
import com.devhelper.eventregistrationservice.exception.EventNotFoundException;
import com.devhelper.eventregistrationserviceconsumers.service.RestTemplateEventService;
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
public class RestTemplateConsumerEventControllerImpl implements RestTemplateConsumerEventController {

    private final RestTemplateEventService eventService;
    @PostMapping("/rest-template/event")
    @Override
    public ResponseEntity<Event> createEvent(Event event, Map<String,String> headers) {
        Event eventResponse = eventService.createEvent(event);
        return ResponseEntity.created(null).header(TID, headers.get(TID)).body(eventResponse);
    }

    @GetMapping("/rest-template/events")
    @Override
    public ResponseEntity<List<Event>> restTemplateFetchAllEvents(Map<String, String> headers) {
        return ResponseEntity.ok(eventService.fetchAllEvents());
    }

    @GetMapping("/rest-template/event/eventId/{eventId}")
    @Override
    public ResponseEntity<Event> fetchEventById(String eventId, Map<String, String> headers) {
        Optional<Event> event = eventService.fetchEventEventId(Long.valueOf(eventId));
        if(!event.isPresent()) {
            throw new EventNotFoundException("Event not found with eventId :"+eventId);
        }
        return ResponseEntity.ok(event.get());
    }

    @GetMapping("/rest-template/event/eventName/{eventName}")
    @Override
    public ResponseEntity<Event> fetchEventByName(String eventName, Map<String, String> headers) {
        Optional<Event> event = eventService.fetchEventByEventName(eventName);
        return ResponseEntity.ok(event.get());
    }

    @PatchMapping("/rest-template/event/{eventId}")
    @Override
    public ResponseEntity<Event> updateEvent(Event event, Long eventId, Map<String, String> headers) {
        Event updatedEvent = eventService.updateEvent(event, eventId);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/rest-template/event/{eventId}")
    @Override
    public ResponseEntity<String> deleteEvent(Long eventId, Map<String, String> headers) {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
}
