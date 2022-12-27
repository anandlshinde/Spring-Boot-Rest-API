package com.devhelper.eventregistrationservice.repository;

import com.devhelper.eventregistrationservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

   Event findByEventName(String eventName);
}
