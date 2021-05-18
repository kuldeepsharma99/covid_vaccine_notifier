package com.vaccine.notifier.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationScheduler {

	@Autowired
	ResponseProcessorService responseProcessorService;

	@Scheduled(fixedDelay = 600000)
	public void checkVaccineSlots() throws InterruptedException {
		System.out.println("Vaccine Availability Check Performed At " + new Date());
		if (responseProcessorService.getVaccineNotification()) {
			System.out.println("Email Sent. Will check now after 20 minutes");
			Thread.sleep(20 * 60000);
			System.out.println("Performing new Availability check");
		}

	}
}
