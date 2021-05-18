package com.vaccine.notifier.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vaccine.notifier.model.Center;
import com.vaccine.notifier.model.Root;
import com.vaccine.notifier.model.Session;

@Service
public class ResponseProcessorService {

	@Autowired
	CowinAPI cowinAPI;

	@Value("${vaccine.age}")
	Integer minimum_age;

	@Value("#{${vaccine.pincode}}")
	List<String> pincodes;

	@Autowired
	EmailService emailService;

	public static final String NEWLINE = "<br/>";
	public static final String BOLD_OPEN = "<b>";
	public static final String BOLD_CLOSE = "</b>";

	public Boolean getVaccineNotification() {
		List<Root> roots = pincodes.stream().map(i -> cowinAPI.getResponse(i)).collect(Collectors.toList());

		List<Center> centers = roots.stream().flatMap(i -> i.getCenters().stream()).collect(Collectors.toList());

		StringBuilder sb = new StringBuilder();

		for (Center center : centers) {
			List<Session> sessions = center.getSessions().stream().filter(i -> i.getMin_age_limit() == minimum_age)
					.filter(i -> i.getAvailable_capacity() > 0).collect(Collectors.toList());

			if (!sessions.isEmpty()) {
				sb.append("<H3>Vaccine available at below centers for Age Group " + minimum_age + "</H3>");
				boolean printHeaderOnlyOneTime = true;
				for (Session session : sessions) {
					if (printHeaderOnlyOneTime) {
						sb.append(NEWLINE);
						sb.append(BOLD_OPEN + "CENTER NAME: " + BOLD_CLOSE).append(center.getName()).append(NEWLINE);
						sb.append(BOLD_OPEN + "ADDRESS: " + BOLD_CLOSE).append(center.getAddress()).append(NEWLINE);
						sb.append(BOLD_OPEN + "FEE: " + BOLD_CLOSE).append(center.getFee_type()).append(NEWLINE);
						printHeaderOnlyOneTime = false;
					}
					sb.append(NEWLINE);
					sb.append(BOLD_OPEN + "DATE: " + BOLD_CLOSE).append(session.getDate()).append(NEWLINE);
					sb.append(BOLD_OPEN + "AGE LIMIT: " + BOLD_CLOSE).append(session.getMin_age_limit())
							.append(NEWLINE);
					sb.append(BOLD_OPEN + "VACCINE: " + BOLD_CLOSE).append(session.getVaccine()).append(NEWLINE);
					sb.append(BOLD_OPEN + "SLOTS SCHEDULE: " + BOLD_CLOSE).append(session.getSlots()).append(NEWLINE);
					sb.append(BOLD_OPEN + "SLOTS AVAILABLE: " + BOLD_CLOSE).append(session.getAvailable_capacity())
							.append(NEWLINE);
				}
				sb.append("<hr>");
			}
		}

		try {
			if (!sb.isEmpty()) {
				emailService.sendEmail(sb.toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
