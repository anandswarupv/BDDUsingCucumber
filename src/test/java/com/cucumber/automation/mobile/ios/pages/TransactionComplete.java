package com.cucumber.automation.mobile.ios.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

public class TransactionComplete extends DriverFactory{
	
	
	By doneButton = By.name("Done");
	By title = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]");
	
	By value = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[8]");
	
	public void verifyTransactionPage() throws InterruptedException
	{
		Thread.sleep(5000);
		//wait for next screen:
		 waitVar.until(ExpectedConditions.elementToBeClickable(doneButton));
		 //verify screen title:
		 assertEquals("Transaction Complete", driver.findElement(title).getText());
	}
	 
	public void verifyTransactionValue(String Value)
	{
		//verify other attributes:
		 assertEquals(Value, driver.findElement(value).getText());
//		 assertEquals("Xebia PayCloud", driver.findElement(By.name("Xebia PayCloud")).getText());
//		 assertEquals("Smart Card", driver.findElement(By.name("Smart Card")).getText());
//		 assertEquals("Subtotal", driver.findElement(By.name("Subtotal")).getText());
//		 assertEquals("Total", driver.findElement(By.name("Total")).getText());
	}
	 

}
