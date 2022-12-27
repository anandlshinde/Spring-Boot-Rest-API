package com.devhelper.eventregistrationservice.service.impl;

import com.devhelper.eventregistrationservice.entity.Event;
import com.devhelper.eventregistrationservice.repository.EventRepository;
import com.devhelper.eventregistrationservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> fetchAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> fetchEventEventId(Long eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public Optional<Event> fetchEventByEventName(String eventName) {
        return Optional.of(eventRepository.findByEventName(eventName));
    }

    @Override
    public Event updateEvent(Event event, Long eventId) {
        Optional<Event> eventDetails=eventRepository.findById(eventId);
        if(eventDetails.isPresent()){
          Event eventObj=new Event(eventId,event.getEventName(),event.getDateTime(),
                                    event.getLocation(),event.getOrganizer(),event.getSpeaker(),
                                    event.getSeatLimit());
            eventRepository.save(eventObj);
            return eventObj;
        }
    return event;
    }

    @Override
    public String deleteEvent(Long eventId) {
        Optional<Event> eventDetails=eventRepository.findById(eventId);
        if(eventDetails.isPresent()) {
            eventRepository.deleteById(eventId);
            return "Event deleted successfully";
        }
        return "";
    }
}
