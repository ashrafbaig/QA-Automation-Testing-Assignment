package com.qa.TestClasses;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.PageClasses.CreateAccountPage;
import com.qa.Utilities.Utilities;

import BaseClass.BaseClass;

public class CreateAccountTest extends BaseClass {
	Logger log = Logger.getLogger(CreateAccountTest.class);
	CreateAccountPage createpage;
	Utilities utility;

	public CreateAccountTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {
		log.info(
				"****************************** Starting test cases execution  *****************************************");
		initialization();
		createpage = new CreateAccountPage();
		utility = new Utilities();

	}

	@Test
	public void CreateAccount() {
		log.info(
				"****************************** Creation of New Account into the Application  *****************************************");
		createpage.AccountCreation();

	}

	@AfterMethod
	public void teardown() throws Exception {
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
			driver.quit();

		}
	}

}
