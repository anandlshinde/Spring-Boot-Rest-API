package com.devhelper.eventregistrationserviceconsumers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventRegistrationServiceConsumersApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventRegistrationServiceConsumersApplication.class, args);
	}

}
