package com.qa.TestClasses;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.PageClasses.LoginPage;
import com.qa.PageClasses.OrderProductPage;
import com.qa.Utilities.Utilities;

import BaseClass.BaseClass;

public class OrderProductTest extends BaseClass {
	Logger log = Logger.getLogger(OrderProductTest.class);
	LoginPage loginpage;
	Utilities utility;
	OrderProductPage order;

	public OrderProductTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {
		log.info(
				"****************************** Starting test cases execution  *****************************************");
		initialization();
		loginpage = new LoginPage();
		utility = new Utilities();
		order = new OrderProductPage();
		log.info("****************************** Login into the Application *****************************************");

		loginpage.LoginYourLogo();

	}

	@Test
	public void CreateAccount() throws Exception {
		log.info(
				"****************************** Placing the Order into the Application *****************************************");
		order.ProductOrder();

	}

	@AfterMethod
	public void teardown() throws Exception {
		log.info(
				"****************************** Logout from the Application  *****************************************");
		Thread.sleep(2000);
		utility.yourLogo_logout();
		log.info("****************************** Closing the Browser *****************************************");
		try {
			if (driver.getCurrentUrl().equalsIgnoreCase(pr.getProperty("LoginURL").trim())) {
				driver.quit();
			} else {
				driver.quit();
			}
		} catch (Throwable e) {

		}
	}

}
