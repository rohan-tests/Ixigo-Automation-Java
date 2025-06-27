package com.runner;

import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterSuite;

import com.setup.SetupDriver;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resource/Features",  // Path to your feature files
    glue = {"com.stepdefinition"},                // Path to your step definition classes
    plugin = {"pretty", 
    		"html:target/cucumber-reports.html",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
             monochrome = true,
             publish = true
//             tags = "@OneWayFlight"
//    tags = "@RoundTrip"
//    tags = "@NoFlights"
//    tags = "@DirectFlightsOnly"
//    tags= "@DifferentPassengers"
//    tags = "@ExcelInput"
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests{
	
	@AfterSuite
	public void teardown() throws Exception {
		SetupDriver.teardown();
	}
	
}
