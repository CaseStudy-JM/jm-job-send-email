package com.payoneer.cs.job;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.payoneer.cs.email.EmailService;
import com.payoneer.cs.job.model.EmailData;

@Service
public class NotificationService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private EmailService emailService;

	public void sendEmail(EmailData email) throws MessagingException {
		Context context = new Context();
		context.setVariable("emailContent", email.getContent());

		emailService.email(email.getToEmailIds(), "Subject : " + email.getSubject(),
				templateEngine.process("email", context));
	}
}
