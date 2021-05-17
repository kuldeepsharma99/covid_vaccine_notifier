package com.vaccine.notifier.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${vaccine.email.to}")
	String email_to;
	
	@Autowired
	private JavaMailSender javaMailSender;
	 
	public void sendEmail(String message) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo(email_to);

        helper.setSubject("COVID Vaccine Notification");

        helper.setText(message, true);

        javaMailSender.send(msg);
        
        System.out.println("Email Sent");
	}
}
