package com.cucumber.automation.web.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import com.cucumber.automation.utils.DriverFactory;

public class SubscribePage extends DriverFactory {

	public void verifyPageTitle() {
		assertTrue(driver.getTitle().equals("Los Angeles Times"));
		
	}

	public void verifyPageURL() {
		assertTrue(driver.getCurrentUrl().contains("https://myaccount2.latimes.com/dsssubscribe.aspx"));
		
	}

	public void verifyTextPresent(String text) {
		String pageText = driver.findElement(By.xpath("//html")).getText();
		System.out.println(pageText);
		assertTrue(pageText.contains(text));
		
	}

}
