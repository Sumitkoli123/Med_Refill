package com.medevantage.medrefill.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

import com.medevantage.medrefill.base.BaseClass;

public class SendMailWithAttachment extends BaseClass {

	public static void sendEmailToUser() {

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication("testellen471@gmail.com", "jrhvcpwnwatrqyej");

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("sumit.koli@compumatrice.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("sumit.koli@compumatrice.com , testellen471@gmail.com"));
			// Add the subject link
			message.setSubject("Crystal Run Med_Refill Automation Results");

			// Create object to add Multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email

			messageBodyPart1.setText("Hello Everyone," 
			                + "\r\n" 
					        + "\r\n               Please Find The Attached Report File."
							+ "\r\n" 
					        + "\r\n" 
							+ "\r\nThanks & Regards..!");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			MimeBodyPart messageBodyPart3 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename1 = System.getProperty("user.dir") + "\\Extent_Report\\Test_Report.html";

			// Mention the file which you want to send
			String filename2 = System.getProperty("user.dir") + "\\Extent_Report\\Console_Log\\Console_Log_Output.txt";

			// Create data source and pass the filename
			DataSource source1 = new FileDataSource(filename1);
			DataSource source2 = new FileDataSource(filename2);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source1));
			messageBodyPart3.setDataHandler(new DataHandler(source2));

			// Set current system timeStamp
			String timeStamp = new SimpleDateFormat("dd-MM-yyyy ").format(new Date());

			// set the file
			messageBodyPart2.setFileName(timeStamp + source1.getName());
			messageBodyPart3.setFileName(source2.getName());

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 2 and 3
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart3);

			// add body part 1
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("\r\n" + "========== Email Sent To User ========== ");

		} catch (MessagingException e) {

			System.out.println(e);

			throw new RuntimeException(e);
		}
	}


	@Test

	public void testReport() {

		System.out.println("check Email");

	}

}
