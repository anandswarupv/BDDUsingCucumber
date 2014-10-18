package com.cucumber.automation.mobile.ios.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

public class PaymentPage extends DriverFactory{
	
	By checkoutButton = By.name("Checkout");
	By title = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]");
	
	By amountText = By.xpath("//UIATextField[1]");
	By amountLabel = By.xpath("//UIAStaticText[2]");
	
	
	public void verifyPaymentPage() throws InterruptedException
	{
		Thread.sleep(5000);
		//wait for next page
		 waitVar.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
		 //verifying the title of the screen:
		 assertEquals("Take Payment", driver.findElement(title).getText());
	}
	
	public void enterCheckoutAmount(String amount)
	{
		//enter checkout amount:
		driver.findElement(amountText).clear();
		driver.findElement(amountText).sendKeys(amount);
	}
	
	public void verifyEnteredAmount(String amount)
	{
		//verify the text on screen:
		 assertEquals(amount, driver.findElement(amountLabel).getText());
	}
	
	public void clickCheckout()
	{
		//click on checkout
		 driver.findElement(checkoutButton).click();
	}
	
}
