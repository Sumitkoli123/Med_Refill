package com.medevantage.medrefill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RequestPrescriptionRefill {

	public WebDriver driver;

	public RequestPrescriptionRefill(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By selectPreRefill = By.xpath("//button[text()='Request Prescription Refill']");

	By clickOnMed = By.xpath("(//div[@class='col-sm-6 ng-star-inserted'])[1]");

	By selectMed1_enterComment = By.xpath("//textarea[@placeholder='Alternate Pharmacy / Medication Comment']");

	By saveButton = By.xpath("//button[text()='Save']");

	By selectMed2 = By.xpath("//input[@id='medicationlbl1']");

	By selectMed3 = By.xpath("//input[@id='medicationlbl2']");

	By selectMed4 = By.xpath("//input[@id='medicationlbl3']");

	By requestRefillButton1 = By.xpath("//button[@class='btn btn-primary btn-sm px-5 m-1']");

	By requestRefillButton2 = By.xpath("(//button[@class='btn btn-primary btn-sm px-5 m-1'])");

	By sentSuccessfully = By.xpath("//h3[text()='Sent Successfully!']");

	By goToHomePage = By.xpath("//button[text()='Go To Home Page']");

	
	
	public void selectPreRefill() {
		driver.findElement(selectPreRefill).click();
	}

	public void clickOnMed() {
		driver.findElement(clickOnMed).click();
	}

	public void selectMed1_enterComment() {
		WebElement comment = driver.findElement(selectMed1_enterComment);
		           comment.click();
		           comment.sendKeys("Test Comment from Medevantage Side");
	}

	public void saveButton() {
		driver.findElement(saveButton).click();
	}

	public void selectMed2() {
		driver.findElement(selectMed2).click();
	}

	public void selectMed3() {
		driver.findElement(selectMed3).click();
	}

	public void selectMed4() {
		driver.findElement(selectMed4).click();
	}

	public void requestRefillButton1() {
		driver.findElement(requestRefillButton1).click();
	}

	public void requestRefillButton2() {
		driver.findElement(requestRefillButton2).click();
	}

	public void sentSuccessfully() {
		System.out.println("Medication Refill Request : " + driver.findElement(sentSuccessfully).getText());
	}

	public void goToHomePage() {
		driver.findElement(goToHomePage).click();
	}
}
