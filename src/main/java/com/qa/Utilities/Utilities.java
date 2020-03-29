package com.qa.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import BaseClass.BaseClass;

public class Utilities extends BaseClass {
	Select select;
	public static long Page_Load = 50;
	public static long Implicit_Time = 50;

	////////// Logout/////////////////////
	@FindBy(linkText = "Sign out")
	 WebElement logout;
	
//////////LogIn/////////////////////
	@FindBy(partialLinkText = "Sign")
	WebElement Sign;
	///////////////// Beneficiary Error Message///////////

	@FindBy(id = "ContentPlaceHolder1_lblError")
	WebElement Error;

	@FindBy(id = "ContentPlaceHolder1_btnHide")
	WebElement cancel;

	public Utilities() {

		PageFactory.initElements(driver, this);
	}

	///////////////// Your Logo logout/////////////////////////
	public  void yourLogo_logout() {
		jse.executeScript("arguments[0].style.border='3px solid red'", logout);
		jse.executeScript("arguments[0].click();", logout);
		wait.until(ExpectedConditions.visibilityOf(Sign));

	}

	public static void getScreenshot() throws IOException {
		// WebDriver driver, String screenshotName
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// return destination;
	}

	

}
