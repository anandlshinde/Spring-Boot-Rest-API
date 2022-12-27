package com.devhelper.eventregistrationserviceconsumers.service.impl;

import com.devhelper.eventregistrationserviceconsumers.entity.Event;
import com.devhelper.eventregistrationserviceconsumers.service.RestTemplateEventService;
import com.devhelper.eventregistrationserviceconsumers.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestTemplateEventServiceImpl implements RestTemplateEventService {

    Logger logger=LoggerFactory.getLogger(RestTemplateEventServiceImpl.class);
    private final RestTemplate restTemplate;
    private final AuthUtil authUtil;
    @Value("${event.baseurl}")
    private String baseurl;

    @Value("${event.auth.username}")
    private String userName;
    @Value("${event.auth.password}")
    private String password;

    @Override
    public Event createEvent(Event event) {
        HttpEntity httpEntity=new HttpEntity(event);
        Event eventReponseObj = restTemplate.exchange(baseurl+"/event", HttpMethod.POST,httpEntity,Event.class).getBody();
        return eventReponseObj;
    }

    @Override
    public List<Event> fetchAllEvents() {
        HttpHeaders headers = authUtil.createHeaders(this.userName, this.password);
        ResponseEntity<List<Event>> exchange = restTemplate.exchange(baseurl + "/events", HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<Event>>() {});
        return exchange.getBody();
    }

    @Override
    public Optional<Event> fetchEventEventId(Long eventId) {
        HttpHeaders headers = authUtil.createHeaders(this.userName, this.password);
        Optional<Event> event = Optional.ofNullable(restTemplate.exchange(baseurl + "/event/eventId/{eventId}", HttpMethod.GET, new HttpEntity<>(headers), Event.class, eventId).getBody());
        return event;
    }

    @Override
    public Optional<Event> fetchEventByEventName(String eventName) {
        HttpHeaders headers = authUtil.createHeaders(this.userName, this.password);
        Optional<Event> event = Optional.ofNullable(restTemplate.exchange(baseurl + "/event/eventName/{eventName}", HttpMethod.GET, new HttpEntity<>(headers), Event.class, eventName).getBody());
        return event;
    }

    @Override
    public Event updateEvent(Event event, Long eventId) {
        HttpEntity httpEntity=new HttpEntity(event);
        Event updatedEvent =restTemplate.exchange(baseurl+"/event/{eventId}",HttpMethod.PATCH,httpEntity,Event.class,eventId).getBody();
        return updatedEvent;
    }

    @Override
    public String deleteEvent(Long eventId) {
        String response = restTemplate.exchange(baseurl + "/event/{eventId}", HttpMethod.DELETE, HttpEntity.EMPTY, String.class, eventId).getBody();
        return response;
    }
}
