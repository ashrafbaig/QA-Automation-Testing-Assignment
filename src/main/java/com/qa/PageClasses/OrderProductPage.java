package com.qa.PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import BaseClass.BaseClass;

public class OrderProductPage extends BaseClass {
	Actions action = new Actions(driver);
	String OrderComplete;
	String Totalprice;
	String PriceOrderTable;
	@FindBy(id = "filter")
	WebElement FilterBox;

	@FindBy(linkText = "Women")
	WebElement Women;

	@FindBy(id = "selectProductSort")
	WebElement SortBy;

	@FindBy(id = "fancybox-frame1585479933808")
	WebElement switchframe;

	@FindBy(id = "quantity_wanted")
	WebElement Quantity;

	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	WebElement AddtoCart;

	@FindBy(xpath = "//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='quick-view']")
	WebElement QuickView;

	@FindBy(xpath = "//div[@class='fancybox-wrap fancybox-desktop fancybox-type-iframe fancybox-opened']")
	WebElement fancybox;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement ProceedCheckout;

	@FindBy(xpath = "//parent::h2//i")
	WebElement successfullText;

	@FindBy(id = "total_price")
	WebElement TotalPrice;

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement ProceddButton;

	@FindBy(xpath = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	WebElement AddressProceed;

	@FindBy(xpath = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	WebElement ShippingProceed;

	@FindBy(id = "cgv")
	WebElement TNC;

	@FindBy(xpath = "//a[@class='bankwire']")
	WebElement Payment;

	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	WebElement OrderConfirm;

	@FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
	WebElement OrderDone;

	@FindBy(xpath = "//a[@class='account']")
	WebElement Account;

	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")
	WebElement OrderHistory;

	@FindBy(id = "order-list")
	WebElement OrderList;

	public OrderProductPage() {

		PageFactory.initElements(driver, this);
	}

	public void ProductOrder() throws Exception {
		jse.executeScript("arguments[0].scrollIntoView(true);", Women);
		Women.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", SortBy);
		/// Clicking on QUICK VIEW //////////////
		List<WebElement> ImageHover = driver.findElements(By.xpath("//parent::div[@class='product-image-container']"));
		int ImageHover_size = ImageHover.size();
		for (int i = 0; i < ImageHover_size; i++) {
			if (i == 0) {
				action.moveToElement(ImageHover.get(i)).build().perform();
				// QuickView.click();
				wait.until(ExpectedConditions.elementToBeClickable(QuickView)).click();
				break;
			}
		}
		/////// Adding 2 Quantity for the product to cart////////
		wait.until(ExpectedConditions.visibilityOf(fancybox));
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		Quantity.clear();
		Quantity.sendKeys(pr.getProperty("Quantity").trim());
		AddtoCart.click();
		driver.switchTo().defaultContent();

		ProceedCheckout.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", TotalPrice);
		///////// Verifying the total cost of product//////////////////////////
		Totalprice = TotalPrice.getText();
		System.out.println("Total cost of product" + ":-" + Totalprice);
		jse.executeScript("arguments[0].scrollIntoView(true);", ProceddButton);
		ProceddButton.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", AddressProceed);
		AddressProceed.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", ShippingProceed);
		if (!TNC.isSelected()) {
			TNC.click();
		}
		ShippingProceed.click();
		//// Complete the checkout process by completing the payment flow ///
		Payment.click();
		OrderConfirm.click();
		OrderComplete = OrderDone.getText();
		Assert.assertEquals(OrderComplete, "Your order on My Store is complete.");
		jse.executeScript("arguments[0].scrollIntoView(true);", Account);
		Account.click();
		OrderHistory.click();
		jse.executeScript("arguments[0].scrollIntoView(true);", OrderList);
		List<WebElement> PriceList = driver.findElements(By.xpath("//tr//span[@class='price']"));
		int PriceList_size = PriceList.size();
		for (int i = 0; i < PriceList_size; i++) {
			if (i == 0) {
				PriceOrderTable = PriceList.get(i).getText();
				break;
			}
		}
		jse.executeScript("window.scrollTo(document.body.scrollHeight, 0)"); // scrolling to the top of the page
		// Verifying the amount of order under ORDER HISTORYs
		Assert.assertEquals(PriceOrderTable, Totalprice);

	}

}
