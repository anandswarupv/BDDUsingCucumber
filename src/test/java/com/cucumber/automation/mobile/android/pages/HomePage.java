package com.cucumber.automation.mobile.android.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

public class HomePage extends DriverFactory{

    // All the locators for Home page will be defined here
    By homePageImage = By.id("com.xebia.eventsapp:id/home_banner_imageView");
    By agendaButton = By.id("com.xebia.eventsapp:id/home_agenda_title");
    By speakersButton = By.id("com.xebia.eventsapp:id/home_speakers_title");
    By myScheduleButton = By.id("com.xebia.eventsapp:id/home_mySchedule_title");
    By locationButton = By.id("com.xebia.eventsapp:id/home_location_title");
    
    By backButton = By.id("android:id/home");

    // All the behavior of home page will be defined here in functions
    public void verifyHomePage()
    {
        waitVar.until(ExpectedConditions.presenceOfElementLocated(homePageImage));
		assertTrue(driver.findElement(homePageImage).isDisplayed());
    }
    
    public void verifyHomePageOptions()
    {
    	 waitVar.until(ExpectedConditions.presenceOfElementLocated(homePageImage));
		 waitVar.until(ExpectedConditions.elementToBeClickable(agendaButton));
		 
		 assertTrue(driver.findElement(agendaButton).isDisplayed());
		 assertTrue(driver.findElement(speakersButton).isDisplayed());
		 assertTrue(driver.findElement(locationButton).isDisplayed());
		 assertTrue(driver.findElement(myScheduleButton).isDisplayed());
		 
		 //print all the options on HomePage
		 System.out.println(driver.findElement(agendaButton).getText());
		 System.out.println(driver.findElement(speakersButton).getText());
		 System.out.println(driver.findElement(myScheduleButton).getText());
		 System.out.println(driver.findElement(locationButton).getText());
    }
    
    public void clickAgenda()
    {
    	driver.findElement(agendaButton).click();
    }
   
    
    public void clickSpeakers()
    {
    	driver.findElement(speakersButton).click();
    }
    
    public void clickBack()
	{
	 	driver.findElement(backButton).click();
		waitVar.until(ExpectedConditions.elementToBeClickable(agendaButton));


		if (driver.findElement(agendaButton).isDisplayed()){
			System.out.println("Successfully reach to home page");
		}
		else
		{
			System.out.println("Did Not reach to home page");
		}
	}
    
    public void screenShot()
    {
    	assertTrue(false);
    }

}
