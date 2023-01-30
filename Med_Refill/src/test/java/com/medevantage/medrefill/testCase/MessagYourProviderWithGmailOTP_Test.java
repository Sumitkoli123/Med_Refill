package com.medevantage.medrefill.testCase;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.medevantage.medrefill.base.BaseClass;
import com.medevantage.medrefill.ui.MessagYourProvider;
import com.medevantage.medrefill.ui.PatientVerification;
import com.medevantage.medrefill.ui.VerifyOTP;
import com.medevantage.medrefill.utility.ConfigReader;
import com.testing.framework.EmailUtils;

@Listeners(com.medevantage.medrefill.utility.ListenerTest.class)
public class MessagYourProviderWithGmailOTP_Test extends BaseClass {

	PatientVerification verify;
	VerifyOTP EnterOTP;
	MessagYourProvider message;
	ConfigReader setData;
	
	@Test(priority=1,dependsOnMethods= {"requestPrescriptionRefill"},ignoreMissingDependencies= true)
	public void patientVerification() throws IOException, InterruptedException {
		verify = new PatientVerification(driver);
		setData = new ConfigReader();
		
		driver.findElement(By.xpath("//button[normalize-space()='Go']")).click();
		Thread.sleep(1000);
		verify.firstName(setData.getFirstName());
		Thread.sleep(1000);
		verify.lastName(setData.getLastName());
		Thread.sleep(1000);
		verify.DOB(setData.getDOB());
		Thread.sleep(1000);
		verify.zipCode(setData.getZipCode());
		Thread.sleep(1000);
		verify.cellPhoneOnFile(setData.getCellPhoneOnFile());
		Thread.sleep(1000);
		verify.nextButton();
		
	}

	@Test(priority = 2 , dependsOnMethods= {"patientVerification"},ignoreMissingDependencies= true)
	public void enterYourOTP() throws Exception {
		setData = new ConfigReader();
		EnterOTP = new VerifyOTP(driver);
		Thread.sleep(1000);

		EmailUtils emailutils = new EmailUtils();
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GmailOTP.properties");
		prop.load(file);
		Store connection = emailutils.connectToGmail(prop);
		emailutils.getUnreadMessages(connection, "Inbox");

		@SuppressWarnings("unchecked")
		List<String> emailText = emailutils.getUnreadMessageByFromEmail(emailutils.connectToGmail(prop), "Inbox",
				"support@medevantage.com", "Your Medevantage verification code is");
		if (emailText.size() < 1)
			throw new Exception("No Email Received");
		else {
			String OTPText = (String) emailText.get(0);
			System.out.println(OTPText);

			String[] OTPEmail = OTPText.split(":");
			@SuppressWarnings("unused")
			String text = OTPEmail[0];
			String OTP = OTPEmail[1];
			System.out.println(OTP);
			String[] number = OTP.split("(?<=.)");

			String digit1 = number[2];
			String digit2 = number[3];
			String digit3 = number[4];
			String digit4 = number[5];
			String digit5 = number[6];
			String digit6 = number[7];

			Thread.sleep(1000);
			EnterOTP.firstOtp(digit1);
			EnterOTP.secondOtp(digit2);
			EnterOTP.thirdOtp(digit3);
			EnterOTP.fourthOtp(digit4);
			EnterOTP.fifthOtp(digit5);
			EnterOTP.sixthOtp(digit6);
			Thread.sleep(1000);
			EnterOTP.nextButton();
			Thread.sleep(3000);

		}
	}


	
	@Test(priority=3 , dependsOnMethods= {"enterYourOTP"},ignoreMissingDependencies= true)
	public void messageYourProvider() throws InterruptedException {

		message = new MessagYourProvider(driver);
		Thread.sleep(1000);
		message.messageYourProvider();
		Thread.sleep(2000);
		message.searchProvider();
		Thread.sleep(4000);
		message.selectProvider();
		Thread.sleep(1000);
		message.textArea();
		Thread.sleep(1000);
		message.submitButton1();

		List<WebElement> verifyMessage = driver.findElements(By.xpath("//h5[.='Verify Message Details']"));

		if (verifyMessage.size() < 0 || verifyMessage.size() > 0) {
			System.out.println("Verified Message Details Successfully");
			Thread.sleep(3000);
			message.submitButton2();
			Thread.sleep(3000);
			message.sentSuccessfully();

		} else {
			System.out.println("Verify Message Details Failed");
		}
	}

}

