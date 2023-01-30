package com.medevantage.medrefill.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v107.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.medevantage.medrefill.utility.ConfigReader;
import com.medevantage.medrefill.utility.SendMailWithAttachment;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	ConfigReader setData;

	@BeforeSuite
	public void setupBrowser() throws IOException {
		setData = new ConfigReader();
		String browserName = setData.getBrowser();

		if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// Get the devTools and create a session
		DevTools devTools = ((HasDevTools) driver).getDevTools();
		devTools.createSession();

		// Enable the console Logs
		devTools.send(Log.enable());

		// Add a listener for the console logs
		devTools.addListener(Log.entryAdded(), LogEnter -> {
			System.out.println("===============================================");
			System.out.println("Level : " + LogEnter.getLevel());
			System.out.println("Text  : " + LogEnter.getText());
			System.out.println("URL   : " + LogEnter.getUrl());
			System.out.println("===============================================");
		});

		// Load the Web site
		driver.get(setData.getUrl());
	}

	public String getScreenshotPath(String methodName, WebDriver driver) throws IOException {

		File screenshotFile = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir") + "\\Extent_Report\\" + methodName + ".png";
		File file = new File(destpath);
		FileUtils.copyFile(screenshotFile, file);
		return destpath;
	}

	@AfterSuite
	public void teardown() {
		driver.quit();
		SendMailWithAttachment.sendEmailToUser();
	}

}
