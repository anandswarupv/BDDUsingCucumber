package com.cucumber.automation.bdt.stepDefinitions;

import java.net.MalformedURLException;

import com.cucumber.automation.utils.DriverFactory;
import com.cucumber.automation.webservices.RestFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	DriverFactory df = new DriverFactory();
	RestFactory rf = new RestFactory();

	@Before("@android")
	public void beforeHookAndroid() throws MalformedURLException{
		df.appiumDriverandroid();
	}
	
	@Before("@androidDevice") 
	public void beforeHookAndroidDevice() throws MalformedURLException{
		df.appiumDriverandroidDevice();
	}
	
	@Before("@ios")
	public void beforeHookIos() throws MalformedURLException{
		df.appiumDriveriOS();
	}
	
	@Before("@web")
	public void beforeHookWeb() throws MalformedURLException, InterruptedException{
		df.createDriver();
	}
	
	@Before
	public void saveScenarioName(Scenario scenario){
		df.saveScenarioName(scenario.getName());
	}
	
	@After
	public void embedScreenshot(Scenario scenario) {
		if(scenario.isFailed()) {
			if((DriverFactory.driver!=null)&& (!scenario.getName().contains("Request"))){
				scenario.embed(df.returnScreenShot(), "image/png");
			}
			if((DriverFactory.driver==null) && (scenario.getName().contains("Request"))){
				scenario.embed(rf.returnResponse(), "text/plain");
			}
	    }
		
		df.teardown();
	}

}
