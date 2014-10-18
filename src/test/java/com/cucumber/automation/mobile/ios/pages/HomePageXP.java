package com.cucumber.automation.mobile.ios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

import static org.junit.Assert.*;

public class HomePageXP extends DriverFactory{
	
	
	By signInButton =  By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");//By.name("Sign In");
	By signInImage = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAImage[2]");
	
	By userName = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]");
	By password = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]");
	By done = By.name("Done");
	
	public void verifyHomePage() throws InterruptedException
	{
		Thread.sleep(5000);
		waitVar.until(ExpectedConditions.elementToBeClickable(signInButton));
		waitVar.until(ExpectedConditions.presenceOfElementLocated(signInImage));
		
		assertTrue(driver.findElement(signInButton).isEnabled());
		assertTrue(driver.findElement(signInImage).isEnabled());
		
	}
	
	public void login(String Username, String Password)
	{
		 //enter data in username and password
		driver.findElement(userName).clear();
		 driver.findElement(userName).sendKeys(Username);
		 driver.findElement(password).clear();
		 driver.findElement(password).sendKeys(Password);
		 //click done to get the focus back to main window
		 driver.findElement(done).click();
		 waitVar.until(ExpectedConditions.presenceOfElementLocated(signInButton));
		 //click login
		 driver.findElement(signInButton).click();
	}
	

}
