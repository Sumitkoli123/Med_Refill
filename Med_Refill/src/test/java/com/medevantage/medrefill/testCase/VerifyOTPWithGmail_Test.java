package com.medevantage.medrefill.testCase;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.Store;
import org.testng.annotations.Test;
import com.medevantage.medrefill.base.BaseClass;
import com.medevantage.medrefill.ui.VerifyOTP;
import com.testing.framework.EmailUtils;

public class VerifyOTPWithGmail_Test extends BaseClass {
	VerifyOTP EnterOTP;

	@Test(dependsOnMethods= {"patientVerification"}, ignoreMissingDependencies= true)
	public void enterYourOTP() throws Exception {

		EnterOTP = new VerifyOTP(driver);
		Thread.sleep(3000);

		EmailUtils emailutils = new EmailUtils();
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GmailOTP.properties");
		prop.load(file);
		Store connection = emailutils.connectToGmail(prop);
		emailutils.getUnreadMessages(connection, "Inbox");

		@SuppressWarnings("unchecked")
		List<String> emailText = emailutils.getUnreadMessageByFromEmail(emailutils.connectToGmail(prop), "Inbox","support@medevantage.com", "Your Medevantage verification code is");

		if (emailText.size() < 1)
			throw new Exception("No Email Received");
		else {
			String OTPText = (String) emailText.get(0);
			System.out.println(OTPText);

			String[] OTPEmail = OTPText.split(":");
			@SuppressWarnings("unused")
			String text = OTPEmail[0];
			String OTP = OTPEmail[1];
			// System.out.println(OTP);
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
}
