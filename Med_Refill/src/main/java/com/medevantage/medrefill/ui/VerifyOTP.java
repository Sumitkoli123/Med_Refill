package com.medevantage.medrefill.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class VerifyOTP {

	public WebDriver driver;

	public VerifyOTP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By enterEmail = By.xpath("//input[@id='login']");

	By checkInbox = By.xpath("(//i[@class='material-icons-outlined f36'])[1]");

	//By openOTPEmail = By.xpath("(//span[@class='lmf'][contains(text(),'Medevantage Patient Verification')])[1]"); //Test env path
	
	By openOTPEmail = By.xpath("(//span[@class='lmf'][normalize-space()='Crystal Run Patient Verification OTP'])[1]");  //Dev env path

	//By getOTPText = By.xpath("//tbody//tr//center//p[1]"); // Test env
	
	By getOTPText = By.xpath("//span[contains(text(),'Your Medevantage OTP Service verification code is:')]"); //Dev env 

	By firstOtp = By.xpath("//input[@id='ngx-otp-input-0']"); 

	By secondOtp = By.xpath("//input[@id='ngx-otp-input-1']"); 

	By thirdOtp = By.xpath("//input[@id='ngx-otp-input-2']");  

	By fourthOtp = By.xpath("//input[@id='ngx-otp-input-3']");  

	By fifthOtp = By.xpath("//input[@id='ngx-otp-input-4']");  

	By sixthOtp = By.xpath("//input[@id='ngx-otp-input-5']"); 

	By nextButton = By.xpath("//button[text()='Next']");

	
	public void enterEmail(String email) {
		WebElement usereMail = driver.findElement(enterEmail);
		           usereMail.clear();
		           usereMail.sendKeys(email);
	}

	public void checkInbox() {
		driver.findElement(checkInbox).click();
	}

	public void openOTPEmail() {
		driver.findElement(openOTPEmail).click();
	}

	public String getOTPText() {
		return driver.findElement(getOTPText).getText();
	}

	public void firstOtp(String digit1) {
		driver.findElement(firstOtp).sendKeys(digit1);
	}

	public void secondOtp(String digit2) {
		driver.findElement(secondOtp).sendKeys(digit2);
	}

	public void thirdOtp(String digit3) {
		driver.findElement(thirdOtp).sendKeys(digit3);
	}

	public void fourthOtp(String digit4) {
		driver.findElement(fourthOtp).sendKeys(digit4);
	}

	public void fifthOtp(String digit5) {
		driver.findElement(fifthOtp).sendKeys(digit5);
	}

	public void sixthOtp(String digit6) {
		driver.findElement(sixthOtp).sendKeys(digit6);
	}

	public void nextButton() {
		driver.findElement(nextButton).click();
	}

}
