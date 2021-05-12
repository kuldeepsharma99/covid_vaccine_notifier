package com.vaccine.notifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vaccine.notifier.service.ResponseProcessorService;

@RestController
public class VaccineNotifierController {

	@Autowired
	ResponseProcessorService responseProcessorService;
	
	@GetMapping(value = "/getVaccineAvailability")
	public ResponseEntity<Void> getVaccineAvailability(){
		responseProcessorService.getVaccineNotification();
		return ResponseEntity.ok().build();
	}
}
