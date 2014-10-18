package com.cucumber.automation.web.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import com.cucumber.automation.utils.DriverFactory;

public class SearchPage extends DriverFactory{
	
	By eleHeaderSubtitle = By.cssSelector("h1.trb_masthead_subtitle");

	public void verifyTitle() {
		assertTrue(driver.getTitle().equals("Search - Los Angeles Times"));
		
	}

	public void verifyHeaderSubTitle() {
		assertTrue(driver.findElement(eleHeaderSubtitle).isDisplayed());
		
	}
	
	

}
