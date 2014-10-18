package com.cucumber.automation.mobile.ios.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

public class SwipePage extends DriverFactory{
	
	By payButton = By.name("Pay");
	By title = By.xpath("//UIANavigationBar[1]/UIAStaticText[1]");
	
	public void verifySwipeScreen() throws InterruptedException
	{
		Thread.sleep(5000);
		//wait for next screen:
		 waitVar.until(ExpectedConditions.elementToBeClickable(payButton));
		 //verify screen title:
		 assertEquals("Payment", driver.findElement(title).getText());
	}
	
	public void clickPay()
	{
		 //click Pay:
		 driver.findElement(payButton).click();
	}


}
