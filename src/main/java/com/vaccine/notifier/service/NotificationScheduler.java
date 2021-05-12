package com.vaccine.notifier.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationScheduler {

	@Autowired
	ResponseProcessorService responseProcessorService;
	
	@Scheduled(fixedRate = 600000)
	public void checkVaccineSlots() {
		responseProcessorService.getVaccineNotification();
		
		System.out.println("Vaccine Availability Check Performed At "+new Date());
	}
}
