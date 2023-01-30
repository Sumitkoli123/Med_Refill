package com.medevantage.medrefill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MessagYourProvider {

	public WebDriver driver;

	public MessagYourProvider(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By messageYourProvider = By.xpath("//button[text()='Message Your Provider']");

	By searchProvider = By.xpath("//input[@aria-autocomplete='list']");

	By selectProvider = By.xpath("(//div[@class='small ng-star-inserted'])[1]");

	By textArea = By.xpath("//textarea[@name='messageBody']");

	By submitButton1 = By.xpath("//button[text()='Submit']");

	By submitButton2 = By.xpath("//button[text()='Submit']");

	By sentSuccessfully = By.xpath("//h3[text()='Sent Successfully!']");

	By goToHomePage = By.xpath("//button[text()='Go To Home Page']");
	

	public void messageYourProvider() {
		driver.findElement(messageYourProvider).click();
	}

	public void searchProvider() {
		WebElement providerMess = driver.findElement(searchProvider);
	               providerMess.click();
		           providerMess.sendKeys("Gre");
	}

	public void selectProvider() {
		driver.findElement(selectProvider).click();
	}

	public void textArea() {
		driver.findElement(textArea).sendKeys("Test Message to Provider from Medevantage");
	}

	public void submitButton1() {
		driver.findElement(submitButton1).click();
	}

	public void submitButton2() {
		driver.findElement(submitButton2).click();
	}

	public void sentSuccessfully() {
		System.out.println("Message to Your Provider : " + driver.findElement(sentSuccessfully).getText());
	}

	public void goToHomePage() {
		driver.findElement(goToHomePage).click();
	}
}
