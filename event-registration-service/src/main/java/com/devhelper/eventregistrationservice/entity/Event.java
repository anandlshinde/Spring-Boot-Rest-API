package com.devhelper.eventregistrationservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.hateoas.RepresentationModel;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicUpdate
public class Event extends RepresentationModel<Event> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;
    @Size(min = 2,message = "Event name should be the min 2 character")
    private String eventName;
    @Future(message = "Event date should be the future date and time")
    private LocalDateTime dateTime;
    private String location;
    private String organizer;
    private String speaker;
    private int seatLimit;
}
