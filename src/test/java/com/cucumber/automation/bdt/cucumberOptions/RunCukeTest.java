package com.cucumber.automation.bdt.cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false,
		strict = true,
		features = "src/test/java/com/cucumber/automation/bdt/features",
		glue = "com.cucumber.automation.bdt.stepDefinitions",
		tags = { "@web,@android,@ios,@rest,@remote,@androidDevice","~@wip" },
		monochrome = true,
		format = { 
					"pretty",
					"html:target/cucumber",
					"json:target/json/cucumber.json",
					"junit:target/junit/cucumber.xml"
				} 
		)
public class RunCukeTest {
}
