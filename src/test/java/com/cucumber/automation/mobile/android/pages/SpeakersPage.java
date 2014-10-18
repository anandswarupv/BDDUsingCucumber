package com.cucumber.automation.mobile.android.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

public class SpeakersPage extends DriverFactory{

    // All the locators for Speaker page will be defined here
    By title = By.id("com.xebia.eventsapp:id/action_bar_custom_title");
    By SpeakersList = By.className("android.widget.RelativeLayout");

    // All the behavior of Speaker page will be defined here in functions
    public void verifySpeakersPage()
    {
    	waitVar.until(ExpectedConditions.presenceOfElementLocated(title));
		 
        assertEquals("Speakers",driver.findElement(title).getText());
        assertTrue(driver.findElements(SpeakersList).size()>=0);
    }

}
