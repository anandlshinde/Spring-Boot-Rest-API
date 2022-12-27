package com.devhelper.eventregistrationserviceconsumers.controller;

import com.devhelper.eventregistrationserviceconsumers.entity.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

public interface WebClientConsumerEventController {

   ResponseEntity<Event> webClientCreateEvent(@Valid @RequestBody Event event, @RequestHeader Map<String,String> headers);

   ResponseEntity<List<Event>> webClientFetchAllEvents(@RequestHeader Map<String,String> headers);

   ResponseEntity<Event> webClientFetchEventById(@PathVariable String eventId,@RequestHeader Map<String,String> headers);

   ResponseEntity<Event> webClientFetchEventByName(@PathVariable String eventName,@RequestHeader Map<String,String> headers);

   ResponseEntity<Event> webClientUpdateEvent(@RequestBody Event event,@PathVariable Long eventId,@RequestHeader Map<String,String> headers);

   ResponseEntity<String> webClientDeleteEvent(@PathVariable Long eventId,@RequestHeader Map<String,String> headers);
}
