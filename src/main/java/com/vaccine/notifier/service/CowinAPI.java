package com.vaccine.notifier.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vaccine.notifier.model.Root;

@Service
public class CowinAPI {

	@Value("${vaccine.pincode}")
	String pincode;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${vaccine.baseuri}")
	String baseUri;

	public Root getResponse() {
		Date currentDate= new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		
		String date = df.format(currentDate);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri)
        		.queryParam("pincode", pincode)
        		.queryParam("date", date);
        
		ResponseEntity<Root> root = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity,Root.class);
		
		return root.getBody();
	}
}