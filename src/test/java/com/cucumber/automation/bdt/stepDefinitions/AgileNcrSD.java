package com.cucumber.automation.bdt.stepDefinitions;

import com.cucumber.automation.mobile.android.pages.AgendaPage;
import com.cucumber.automation.mobile.android.pages.HomePage;
import com.cucumber.automation.mobile.android.pages.SpeakersPage;
import com.cucumber.automation.utils.DriverFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AgileNcrSD {
	
	DriverFactory df = new DriverFactory();
	HomePage hp = new HomePage();
	AgendaPage ag = new AgendaPage();
	SpeakersPage sp = new SpeakersPage();
	
	@Given("^user is on AgileNCR Home Page$")
	public void user_is_on_AgileNCR_Home_Page()  {
	    hp.verifyHomePage();
	}

	@Then("^user gets an option to choose Agenda, Speakers, Locaton and My sechedule$")
	public void user_gets_an_option_to_choose_Agenda_Speakers_Locaton_and_My_sechedule() {
		hp.verifyHomePageOptions();
	}

	@When("^user selects Agenda$")
	public void user_selects_Agenda() {
		hp.clickAgenda();
	}
	@Then("^user is on Agenda Screen$")
	public void user_is_on_Agenda_Screen() {
		ag.verifyAgendaPage();
	}
	@When("^user chooses to go back$")
	public void user_chooses_to_go_back() {
		hp.clickBack();
	}
	@When("^user selects Speakers$")
	public void user_selects_Speakers() {
		hp.clickSpeakers();
	}
	@Then("^user is on Speakers Screen$")
	public void user_is_on_Speakers_Screen() {
		sp.verifySpeakersPage();
	}
	
	@Then("^i want to include scerenshot also$")
	public void i_want_to_include_scerenshot_also() {
		hp.screenShot();
	
	}
}
