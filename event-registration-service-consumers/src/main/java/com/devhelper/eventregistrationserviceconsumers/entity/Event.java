package com.devhelper.eventregistrationserviceconsumers.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event{
    private Long eventId;
    @Size(min = 2,message = "Event name should be the min 2 character")
    private String eventName;
    @Future(message = "Event date should be the future date and time")
    private LocalDateTime dateTime;
    private String location;
    private String organizer;
    private String speaker;
    private int seatLimit;
    public List<CustomLink> links;
}
