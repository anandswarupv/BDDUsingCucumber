package com.cucumber.automation.bdt.stepDefinitions;

import java.util.List;

import com.cucumber.automation.mobile.ios.pages.HomePageXP;
import com.cucumber.automation.mobile.ios.pages.PaymentPage;
import com.cucumber.automation.mobile.ios.pages.SwipePage;
import com.cucumber.automation.mobile.ios.pages.TransactionComplete;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class XebiaPayCloudSD {
	
	HomePageXP hp = new HomePageXP();
	PaymentPage pp = new PaymentPage();
	SwipePage sp = new SwipePage();
	TransactionComplete tc = new TransactionComplete();
	
	@Given("^user is on Pay Cloud Home Page$")
	public void user_is_on_Pay_Cloud_Home_Page() throws InterruptedException  {
		hp.verifyHomePage();	
	}
	
	@Then("^user logins to application with follwoing details$")
	public void user_logins_to_application_with_follwoing_details(DataTable loginDetails)  {
	   
		List<List<String>> login = loginDetails.raw();
		hp.login(login.get(1).get(0), login.get(1).get(1));
	    
	}

	@Then("^user is on Payment Page$")
	public void user_is_on_Payment_Page() throws InterruptedException  {
	   pp.verifyPaymentPage();
	    
	}

	@When("^user enter payment \"(.*?)\"$")
	public void user_enter_payment(String amount)  {
	   pp.enterCheckoutAmount(amount);
	    
	}
	
	@Then("^user see the amount entered as \"(.*?)\"$")
	public void user_see_the_amount_entered_as(String amount) {
		pp.verifyEnteredAmount(amount);
	}

	@When("^selects to Checkout$")
	public void selects_to_Checkout()  {
	   pp.clickCheckout();
	}

	@Then("^user is on Swipe Page$")
	public void user_is_on_Swipe_Page() throws InterruptedException  {
	   sp.verifySwipeScreen();
	}

	@When("^user selects to pay$")
	public void user_selects_to_pay()  {
	   sp.clickPay();
	    
	}

	@Then("^user is on Transaction Complete Page$")
	public void user_is_on_Transaction_Complete_Page() throws InterruptedException  {
	    tc.verifyTransactionPage();
	}

	@Then("^user sees the amount paid is \"(.*?)\"$")
	public void user_sees_the_amount_paid_is_$(String value)  {
	   tc.verifyTransactionValue(value);
	    
	}

}
