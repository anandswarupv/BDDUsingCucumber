package com.cucumber.automation.mobile.android.pages;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;

public class AgendaPage extends DriverFactory{
	
    // All the locators for Agenda page will be defined here
    By title = By.id("com.xebia.eventsapp:id/action_bar_custom_title");
    By AgendaList = By.className("android.widget.LinearLayout");

    // All the behavior of Agenda page will be defined here in functions

    public void verifyAgendaPage()
    {
        waitVar.until(ExpectedConditions.presenceOfElementLocated(title));
		 
        assertEquals("Agenda",driver.findElement(title).getText());
        assertTrue(driver.findElements(AgendaList).size()>=0);
    }

}
