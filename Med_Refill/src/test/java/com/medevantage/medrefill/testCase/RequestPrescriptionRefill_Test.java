package com.medevantage.medrefill.testCase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.medevantage.medrefill.base.BaseClass;
import com.medevantage.medrefill.ui.RequestPrescriptionRefill;
import com.medevantage.medrefill.utility.ConfigReader;

@Listeners(com.medevantage.medrefill.utility.ListenerTest.class)
public class RequestPrescriptionRefill_Test extends BaseClass {

	RequestPrescriptionRefill refill;
	ConfigReader setData;

	@Test(dependsOnMethods= {"enterYourOTP"},ignoreMissingDependencies= true)
	public void requestPrescriptionRefill() throws InterruptedException, IOException {

		refill = new RequestPrescriptionRefill(driver);
		setData = new ConfigReader();
		Thread.sleep(1000);
		refill.selectPreRefill();
		Thread.sleep(2000);

		List<WebElement> refillReq = driver.findElements(By.xpath("//span[@class='text-info font-1_rem']"));

		if (refillReq.size() < 0 || refillReq.size() > 0) {
			
			System.out.println("========== Sorry! No active medication founds. ==========");
			Thread.sleep(2000);
			driver.get(setData.getUrl());
			Thread.sleep(1000);
			
		} else {
			
			System.out.println("========== Active Medication founds. ==========");

			Thread.sleep(1000);
			refill.clickOnMed();
			Thread.sleep(1000);
			refill.selectMed1_enterComment();
			Thread.sleep(1000);
			refill.saveButton();
			Thread.sleep(1000);
			refill.requestRefillButton1();
			
			System.out.println("========== verified Prescription Refill Details Successfully ==========");
			
			Thread.sleep(3000);
			refill.requestRefillButton2();
			refill.sentSuccessfully();
			Thread.sleep(3000);
			refill.goToHomePage();

		}

		/*
		refill.selectMed2();
		Thread.sleep(1000);
		refill.selectMed3();
		Thread.sleep(1000);
		refill.selectMed4();
		Thread.sleep(2000);
		refill.requestRefillButton1();
		*/	
	}
}
