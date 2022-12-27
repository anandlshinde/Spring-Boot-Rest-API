package com.devhelper.eventregistrationserviceconsumers.service;

import com.devhelper.eventregistrationserviceconsumers.entity.Event;

import java.util.List;
import java.util.Optional;

public interface RestTemplateEventService {

    Event createEvent(Event event);

    List<Event> fetchAllEvents();

    Optional<Event> fetchEventEventId(Long eventId);

    Optional<Event> fetchEventByEventName(String eventName);

    Event updateEvent(Event event,Long eventId);

    String deleteEvent(Long eventId);

}
