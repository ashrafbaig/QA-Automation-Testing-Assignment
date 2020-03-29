package com.qa.PageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import BaseClass.BaseClass;

public class LoginPage extends BaseClass {

	@FindBy(partialLinkText = "Sign")
	WebElement Sign;

	@FindBy(id = "email")
	WebElement EmailID;

	@FindBy(id = "passwd")
	WebElement Password;

	@FindBy(id = "SubmitLogin")
	WebElement login_button;

	public LoginPage() {

		PageFactory.initElements(driver, this);
	}

	public void LoginYourLogo() {
		Sign.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", EmailID);

		EmailID.sendKeys(pr.getProperty("EmailId").trim());
		Password.sendKeys(pr.getProperty("Password").trim());
		login_button.click();
		jse.executeScript("return document.readyState").equals("complete");
		Assert.assertEquals(driver.getCurrentUrl(), pr.getProperty("MyAccountURL").trim());

	}

}
