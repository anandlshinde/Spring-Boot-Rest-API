package com.devhelper.eventregistrationservice.service;

import com.devhelper.eventregistrationservice.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);

    List<Event> fetchAllEvents();

    Optional<Event> fetchEventEventId(Long eventId);

    Optional<Event> fetchEventByEventName(String eventName);

    Event updateEvent(Event event,Long eventId);

    String deleteEvent(Long eventId);

}
