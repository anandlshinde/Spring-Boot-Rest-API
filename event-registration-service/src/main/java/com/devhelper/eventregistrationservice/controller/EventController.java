package com.devhelper.eventregistrationservice.controller;

import com.devhelper.eventregistrationservice.entity.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

public interface EventController {

   ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, @RequestHeader Map<String,String> headers);

   ResponseEntity<List<Event>> fetchAllEvents(@RequestHeader Map<String,String> headers);

   ResponseEntity<Event> fetchEventById(@PathVariable String eventId,@RequestHeader Map<String,String> headers);

   ResponseEntity<Event> fetchEventByName(@PathVariable String eventName,@RequestHeader Map<String,String> headers);

   ResponseEntity<Event> updateEvent(@RequestBody Event event,@PathVariable Long eventId,@RequestHeader Map<String,String> headers);

   ResponseEntity<String> deleteEvent(@PathVariable Long eventId,@RequestHeader Map<String,String> headers);
}
