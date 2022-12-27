package com.devhelper.eventregistrationserviceconsumers.service.impl;

import com.devhelper.eventregistrationserviceconsumers.config.AppConfig;
import com.devhelper.eventregistrationserviceconsumers.constant.HeaderConstant;
import com.devhelper.eventregistrationserviceconsumers.entity.Event;
import com.devhelper.eventregistrationserviceconsumers.service.RestTemplateEventService;
import com.devhelper.eventregistrationserviceconsumers.service.WebClientEventService;
import com.devhelper.eventregistrationserviceconsumers.util.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class WebClientEventServiceImpl implements WebClientEventService {

    Logger logger=LoggerFactory.getLogger(WebClientEventServiceImpl.class);
    private RestTemplate restTemplate;
    @Autowired
    private AuthUtil authUtil;
    private WebClient webClient;

    private String baseurl;

    @Value("${event.auth.username}")
    private String userName;
    @Value("${event.auth.password}")
    private String password;

    public WebClientEventServiceImpl(@Autowired AppConfig appConfig,
                                     @Value("${event.baseurl}") String baseurl){
        WebClient webClientBuilder = appConfig.webClientBuilder(baseurl);
        this.webClient = webClientBuilder;
    }

    @Override
    public Event createEvent(Event event) {
        Event eventReponseObj = this.webClient.post().uri("/event")
                .header("Authorization",authUtil.basicAuth(this.userName,this.password))
                .header(HeaderConstant.TID,"et4et")
                .body(Mono.just(event), Event.class)
                .retrieve()
                .bodyToMono(Event.class)
                .block();
        return eventReponseObj;
    }

    @Override
    public List<Event> fetchAllEvents() {
        List events = this.webClient.get().uri("/events")
                .header("Authorization", authUtil.basicAuth(this.userName,this.password))
                .header(HeaderConstant.TID, "23333")
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return events;
    }

    @Override
    public Optional<Event> fetchEventEventId(Long eventId) {
        Event event = this.webClient.get().uri("/event/eventId/"+eventId)
                .header("Authorization", authUtil.basicAuth(this.userName,this.password))
                .header(HeaderConstant.TID, "455565")
                .retrieve()
                .bodyToMono(Event.class)
                .block();
        return Optional.of(event);
    }

    @Override
    public Optional<Event> fetchEventByEventName(String eventName) {
        Event event = this.webClient.get().uri("/event/eventName/"+eventName)
                .header("Authorization", authUtil.basicAuth(this.userName,this.password))
                .header(HeaderConstant.TID, "455565")
                .retrieve()
                .bodyToMono(Event.class)
                .block();
        return Optional.of(event);
    }

    @Override
    public Event updateEvent(Event event, Long eventId) {
        Event updatedEvent = this.webClient.patch().uri("/event/"+eventId)
                .header("Authorization", authUtil.basicAuth(this.userName, this.password))
                .header(HeaderConstant.TID, "46564564")
                .body(Mono.just(event), Event.class)
                .retrieve()
                .bodyToMono(Event.class)
                .block();
        return updatedEvent;
    }

    @Override
    public String deleteEvent(Long eventId) {
        String response = this.webClient.delete().uri("/event/" + eventId)
                .header("Authorization", authUtil.basicAuth(this.userName, this.password))
                .header(HeaderConstant.TID, "46564564")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
