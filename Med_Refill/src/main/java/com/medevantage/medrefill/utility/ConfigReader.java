package com.medevantage.medrefill.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties prop;
	String path = (System.getProperty("user.dir") + "\\src\\test\\resources\\TestData2.properties");

	public ConfigReader() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
	}

	public String getBrowser() {
		String browser = prop.getProperty("Browser");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("Browser Not Found");
	}

	public String getUrl() {
		String url = prop.getProperty("WebURL");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url Not Found");
	}

	public String getFirstName() {
		String firstName = prop.getProperty("First_Name");
		if (firstName != null)
			return firstName;
		else
			throw new RuntimeException("firstName Not Found");
	}

	public String getLastName() {
		String lastName = prop.getProperty("Last_Name");
		if (lastName != null)
			return lastName;
		else
			throw new RuntimeException("lastName Not Found");
	}

	public String getDOB() {
		String DOB = prop.getProperty("DOB");
		if (DOB != null)
			return DOB;
		else
			throw new RuntimeException("DOB Not Found");
	}

	public String getZipCode() {
		String ZipCode = prop.getProperty("Zip_Code");
		if (ZipCode != null)
			return ZipCode;
		else
			throw new RuntimeException("ZipCode Not Found");
	}

	public String getCellPhoneOnFile() {
		String cellPhoneOnFile = prop.getProperty("Cell_Phone_On_File");
		if (cellPhoneOnFile != null)
			return cellPhoneOnFile;
		else
			throw new RuntimeException("cellPhoneOnFile Not Found");
	}

	public String getYopmail() {
		String yopmail = prop.getProperty("Yopmail");
		if (yopmail != null)
			return yopmail;
		else
			throw new RuntimeException("yopmail Not Found");
	}

	public String getEmail() {
		String email = prop.getProperty("Email");
		if (email != null)
			return email;
		else
			throw new RuntimeException("email Not Found");
	}
}
