package com.qa.PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import BaseClass.BaseClass;
import java.util.List;

public class CreateAccountPage extends BaseClass {

	Select select;

	@FindBy(partialLinkText = "Sign")
	WebElement Sign;

	@FindBy(id = "email_create")
	WebElement EmailCreate;

	@FindBy(id = "SubmitCreate")
	WebElement CreateAccount_Button;
	/////////////////////////// YOUR PERSONAL INFORMATION/////////////////
	@FindBy(id = "id_gender1")
	WebElement Title;

	@FindBy(id = "customer_firstname")
	WebElement customerfirstname;

	@FindBy(id = "customer_lastname")
	WebElement customerlastname;

	@FindBy(id = "email")
	WebElement email_id;

	@FindBy(id = "passwd")
	WebElement password;

	@FindBy(id = "days")
	WebElement days;

	@FindBy(id = "months")
	WebElement months;

	@FindBy(id = "years")
	WebElement years;

	//////////////////////// YOUR ADDRESS/////////////////

	@FindBy(id = "firstname")
	WebElement FirstName;

	@FindBy(id = "lastname")
	WebElement LastName;

	@FindBy(id = "company")
	WebElement CompanyName;

	@FindBy(id = "address1")
	WebElement Address1;

	@FindBy(id = "address2")
	WebElement Address2;

	@FindBy(id = "city")
	WebElement City;

	@FindBy(id = "id_state")
	WebElement State;

	@FindBy(id = "postcode")
	WebElement Postcode;

	@FindBy(id = "id_country")
	WebElement Country;

	@FindBy(id = "other")
	WebElement Other;

	@FindBy(id = "phone_mobile")
	WebElement Mobile_Number;

	@FindBy(id = "alias")
	WebElement Alias;

	//////////////// Registration Button/////////////////
	@FindBy(xpath = "//span[contains(text(),'Register')]")
	WebElement Register;

	public CreateAccountPage() {

		PageFactory.initElements(driver, this);
	}

	public void AccountCreation() {

		Sign.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", EmailCreate);
		EmailCreate.sendKeys(pr.getProperty("EmailId").trim());
		CreateAccount_Button.click();
		List<WebElement> Title = driver.findElements(By.xpath("//input[@name='id_gender']"));
		int title_size = Title.size();
		for (int i = 0; i < title_size; i++) {
			if (i == Integer.parseInt(pr.getProperty("GenderTitle").trim())) {
				Title.get(i).click();
				break;
			}
		}
		customerfirstname.sendKeys(pr.getProperty("FirstName").trim());
		customerlastname.sendKeys(pr.getProperty("LastName").trim());
		try {
			if (email_id.getAttribute("value").isEmpty()) {
				jse.executeScript("arguments[0].value='" + pr.getProperty("EmailId").trim() + "';", email_id);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		password.sendKeys(pr.getProperty("Password").trim());
		try {
			select = new Select(days);
			select.selectByValue(pr.getProperty("Days"));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			select = new Select(months);
			select.selectByValue(pr.getProperty("Months"));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			select = new Select(years);
			select.selectByValue(pr.getProperty("Years"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FirstName.clear();
			FirstName.sendKeys(pr.getProperty("FirstName").trim());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			LastName.clear();
			LastName.sendKeys(pr.getProperty("LastName").trim());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		CompanyName.sendKeys(pr.getProperty("CompanyName").trim());
		Address1.sendKeys(pr.getProperty("Address1").trim());
		try {
			Address2.sendKeys(pr.getProperty("Address2").trim());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		City.sendKeys(pr.getProperty("City").trim());
		try {
			if (State.isDisplayed() || State.isEnabled()) {
				select = new Select(State);
				List<WebElement> State_list = select.getOptions();
				int No_State = State_list.size();
				for (int i = 0; i < No_State; i++) {
					if (i == 0) {

						continue;

					}
					if (Integer.parseInt(pr.getProperty("State").trim()) >= i) {
						select.selectByValue(pr.getProperty("State").trim());
						break;
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			Postcode.sendKeys(pr.getProperty("PostCode").trim());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			select = new Select(Country);
			List<WebElement> Country_list = select.getOptions();
			int No_Country = Country_list.size();
			for (int i = 0; i < No_Country; i++) {
				if (i == 0) {

					continue;

				}
				if (i == 1) {
					select.selectByIndex(i);
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Other.sendKeys(pr.getProperty("Other").trim());
		Mobile_Number.sendKeys(pr.getProperty("MobilePhone").trim());
		Alias.sendKeys(pr.getProperty("Alias").trim());
		Register.click();
		jse.executeScript("return document.readyState").equals("complete");
		Assert.assertEquals(driver.getCurrentUrl(), pr.getProperty("MyAccountURL").trim());

	}

}
