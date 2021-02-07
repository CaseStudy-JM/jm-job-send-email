package com.payoneer.cs.job;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payoneer.cs.client.Job;
import com.payoneer.cs.error.JobException;
import com.payoneer.cs.job.model.EmailData;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SendEmailJob extends Job {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private EmailContentService emailContentService;

	@Override
	protected void process(String jobId, String... args) throws JobException {
		String filePath = args.length > 1 ? args[1] : "data/emails.csv";
		List<EmailData> emailDatas = emailContentService.getEmailDatasFromFile(filePath);
		emailDatas.stream().forEach(emailContent -> {
			try {
				notificationService.sendEmail(emailContent);
			} catch (MessagingException messagingException) {
				log.error("Exception Occured While Sending Email: {}", messagingException);
			}
		});
	}
}
