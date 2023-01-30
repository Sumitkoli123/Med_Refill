package com.medevantage.medrefill.testCase;

import java.io.IOException;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.medevantage.medrefill.base.BaseClass;
import com.medevantage.medrefill.ui.VerifyOTP;
import com.medevantage.medrefill.utility.ConfigReader;

@Listeners(com.medevantage.medrefill.utility.ListenerTest.class)
public class VerifyOTP_Test extends BaseClass {

	VerifyOTP EnterOTP;
	ConfigReader setData;

	@Test
	public void enterYourOTP() throws IOException, InterruptedException {
		setData = new ConfigReader();
		EnterOTP = new VerifyOTP(driver);

		String enterOTPPage = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);

		driver.get(setData.getYopmail());
		EnterOTP.enterEmail(setData.getEmail());
		EnterOTP.checkInbox();
		driver.switchTo().frame("ifinbox");
		EnterOTP.openOTPEmail();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ifmail");
		String OTPText = EnterOTP.getOTPText();
		System.out.println(OTPText);
		driver.switchTo().defaultContent();

		String[] OTPEmail = OTPText.split(":");
		@SuppressWarnings("unused")
		String text = OTPEmail[0];
		String OTP = OTPEmail[1];
		
		// String number = String.valueOf(string2);
		// char[] digits1 = number.toCharArray();
		// char digit1 = digits1[2];

		String[] number = OTP.split("(?<=.)");

		String digit1 = number[1];
		String digit2 = number[2];
		String digit3 = number[3];
		String digit4 = number[4];
		String digit5 = number[5];
		String digit6 = number[6];

		Thread.sleep(1000);
		driver.switchTo().window(enterOTPPage);

		Thread.sleep(1000);
		EnterOTP.firstOtp(digit1);
		EnterOTP.secondOtp(digit2);
		EnterOTP.thirdOtp(digit3);
		EnterOTP.fourthOtp(digit4);
		EnterOTP.fifthOtp(digit5);
		EnterOTP.sixthOtp(digit6);
		Thread.sleep(1000);
		EnterOTP.nextButton();
		Thread.sleep(2000);
	}
}
