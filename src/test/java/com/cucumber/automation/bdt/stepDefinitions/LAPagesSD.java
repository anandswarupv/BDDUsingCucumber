package com.cucumber.automation.bdt.stepDefinitions;

import com.cucumber.automation.utils.DriverFactory;
import com.cucumber.automation.web.pages.HomePage;
import com.cucumber.automation.web.pages.SearchPage;
import com.cucumber.automation.web.pages.SubscribePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LAPagesSD {
	
	DriverFactory df = new DriverFactory();
	HomePage hp = new HomePage();
	SearchPage searchPage;
	SubscribePage subcribePage;

	
    @Given("^user is on LA Times Home Page$")
	public void user_is_on_LA_Times_Home_Page() throws Throwable {
		hp.verifyHomePageTitle();
	}

	@Then("^user should be able to see Master Logo for Los Angeles Times$")
	public void user_should_be_able_to_see_Master_Logo_for_Lost_Angeles_Times() throws Throwable {
	    hp.verifyMasterLogoPresence();
	    
	}

	@Then("^user should be able to see Facebook Like link$")
	public void user_should_be_able_to_see_Facebook_Like_link() throws Throwable {
	    hp.verifyFBLinkPresence();
	    
	}

	@Then("^user shhould be able to see current Date$")
	public void user_shhould_be_able_to_see_current_Date() throws Throwable {
	    hp.verifyCurrentDateDisplay();
	    
	}

	@Then("^user should be able to see weather link$")
	public void user_should_be_able_to_see_weather_link() throws Throwable {
	    hp.verifyWeatherLinkDisplay();
	    
    }
	

	/////////////////////////////////////////////////////////////////////////
	
	
	@Given("^user enters 'Whitehouse' in search box$")
	public void user_enters_Whitehouse_in_search_box() throws Throwable {
		hp.typeSearchTerm("Whitehouse");
	}

	@Given("^user clicks on 'Search' button\\.$")
	public void user_clicks_on_Search_button() throws Throwable {
		searchPage = hp.clickSearchIcon();
	}

	@Then("^user should be taken to search page$")
	public void user_should_be_taken_to_search_page() throws Throwable {
		searchPage.verifyTitle();
	}

	@Then("^user should be able to see 'Search' in Header$")
	public void user_should_be_able_to_see_Search_in_Header() throws Throwable {
		searchPage.verifyHeaderSubTitle();
	}

	///////////////////////////////////////////////////////////////////
	
	
	@Given("^user enters clicks on 'Subscribe' link$")
	public void user_enters_clicks_on_Subscribe_link() throws Throwable {
	   subcribePage = hp.clickSubscribeLink();
	}

	@Then("^user should be on 'Subscriber' page Check url$")
	public void user_should_be_on_Subscriber_page_Check_url() throws Throwable {
		subcribePage.verifyPageTitle();
	}

	@Then("^user should be on 'Subscriber' page Check title$")
	public void user_should_be_on_Subscriber_page_Check_title() throws Throwable {
		subcribePage.verifyPageURL();
	}

	@Then("^user should be displayed 'Find Special Offers' text$")
	public void user_should_be_displayed_Find_Special_Offers_text() throws Throwable {
		subcribePage.verifyTextPresent("Find Special Offers");
	}
	
	///////////////////////////////////////////////////////////////////
	
}