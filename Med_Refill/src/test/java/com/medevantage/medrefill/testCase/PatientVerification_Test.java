package com.medevantage.medrefill.testCase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.medevantage.medrefill.base.BaseClass;
import com.medevantage.medrefill.ui.PatientVerification;
import com.medevantage.medrefill.utility.ConfigReader;

@Listeners(com.medevantage.medrefill.utility.ListenerTest.class)
public class PatientVerification_Test extends BaseClass {

	PatientVerification verify;
	ConfigReader setData;

	@Test
	public void patientVerification() throws IOException, InterruptedException {
		verify = new PatientVerification(driver);
		setData = new ConfigReader();

		driver.findElement(By.xpath("//button[text()='Go']")).click();

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
		Thread.sleep(2000);

		List<WebElement> patientNotFound = driver.findElements(By.xpath("//h5[text()='Patient record not found']"));

		if (patientNotFound.size() < 0 || patientNotFound.size() > 0) {
			System.out.println("=========== Patient record not found ==========");
			Thread.sleep(3000);
			verify.goToPatientForm();
		}

		List<WebElement> patienContactDetailstNotFound = driver.findElements(By.xpath("//h5[text()='Patient contact details not found']"));

		if (patienContactDetailstNotFound.size() < 0 || patienContactDetailstNotFound.size() > 0) {
			System.out.println("========= Patient contact details not found ==========");
			Thread.sleep(3000);
			verify.goToPatientForm();

		}

		List<WebElement> patienVerified = driver.findElements(By.xpath("//label[text()='Enter Your OTP ']"));

		if (patienVerified.size() < 0 || patienVerified.size() > 0) {
			System.out.println("========= Patient Verified Successfully ==========");
		}
		
	}
}
