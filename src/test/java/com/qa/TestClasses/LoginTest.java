package com.qa.TestClasses;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.PageClasses.LoginPage;
import com.qa.Utilities.Utilities;

import BaseClass.BaseClass;

public class LoginTest extends BaseClass {
	Logger log = Logger.getLogger(LoginTest.class);
	LoginPage loginpage;
	Utilities utility;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {
		log.info(
				"****************************** Starting test cases execution  *****************************************");
		initialization();
		loginpage = new LoginPage();
		utility = new Utilities();
	}

	@Test
	public void YourLogoLogin() {
		log.info("****************************** Login into the Application *****************************************");
		loginpage.LoginYourLogo();

	}

	@AfterMethod
	public void teardown() throws Throwable {
		log.info(
				"****************************** Logout from the Application  *****************************************");
		Thread.sleep(2000);
		utility.yourLogo_logout();
		log.info("****************************** Closing the Browser *****************************************");
		try {
			if (driver.getCurrentUrl().equals(pr.getProperty("LoginURL").trim())) {
				driver.quit();
			} else {
				driver.quit();
			}
		} catch (Throwable e) {
			

		}
	}

}
