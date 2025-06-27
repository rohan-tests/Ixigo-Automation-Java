package com.stepdefinition;

import org.openqa.selenium.interactions.Actions;
import com.pages.FlightBooking;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightBookingSteps {
//	WebDriver driver;
	public static Actions actions;
	public static FlightBooking fb = new FlightBooking();
	@Given("I navigate to the ixigo website")
	public void i_navigate_to_the_ixigo_website() {
		fb.launchApplication();
	}

	@Given("I enter {string} as the departure city")
	public void i_enter_as_the_departure_city(String string) {
		fb.clickAndEnterSrc(string);
	}

	@Given("I enter {string} as the destination city")
	public void i_enter_as_the_destination_city(String string) throws InterruptedException {
		fb.clickAndEnterDest(string);
	}

	@When("I select {string} as the departure date")
	public void i_select_as_the_departure_date(String string) throws InterruptedException {
	    fb.enterDate(string);
	}
	
	@When("I select {string} as the return date")
	public void i_select_as_the_return_date(String string) throws InterruptedException {
	    fb.enterDate(string);
	}
	@When("I enter flight details from excel file")
	public void i_enter_flight_details_from_excel_file() throws Exception {
		fb.enterDataFromExcel();
	}

	@Given("I click on the Search button")
	public void i_click_on_the_Search_button() throws InterruptedException {
		fb.clickSearch();
	}

	@Then("I should see a list of available flights")
	public void i_should_see_a_list_of_available_flights() {
		fb.displayFlights();
	}
	
	@Then("I should see a list of available flights for round trip")
	public void i_should_see_a_list_of_available_flights_for_round_trip() {
		fb.displayRoundTripFlights();
	}
	@Then("I should see the results in excel file")
	public void i_should_see_the_results_in_excel_file() throws Exception {
	    fb.storeResultinExcel();
	}

	@Then("I should be able to select flight")
	public void i_should_be_able_to_select_flight() throws InterruptedException {
		fb.selectFlight();
	}

	@Then("I should enter passenger details")
	public void i_should_enter_passenger_details() throws Exception {
		fb.enterPassengerDetails();
	}
	@Then("I should enter passenger details for round trip")
	public void i_should_enter_passenger_details_for_round_trip() throws Exception {
//		fb.passengerDetailForRoundTrip();
		fb.enterPassengerDetails();
	}

	@Then("I should visit payment page")
	public void i_should_visit_payment_page() throws Exception {
		fb.goToPaymentPage();
	}

	@When("I select {string} as the trip type")
	public void i_select_as_the_trip_type(String string) {
	    fb.selectTripType(string);
	}



	@Then("I should be able to select flights for both legs of the journey")
	public void i_should_be_able_to_select_flights_for_both_legs_of_the_journey() throws InterruptedException {
	    fb.selectFlight();
	}

	@Then("I should see a message stating {string}")
	public void i_should_see_a_message_stating(String string) {
	    fb.displayFlights();
	}

	@Given("I apply the {string} filter")
	public void i_apply_the_filter(String string) {
	    fb.selectFilter(string);
	}

	@Then("I should see only direct flights")
	public void i_should_see_only_direct_flights() {
	    fb.displayFlights();
	}

	@Given("I select {string} as the number of passengers")
	public void i_select_as_the_number_of_passengers(String string) {
	    fb.selectNoOfPassengers(string);
	}


	@Then("I should be able to proceed with the booking for {int} passengers")
	public void i_should_be_able_to_proceed_with_the_booking_for_passengers(Integer int1) throws Exception {
	    fb.selectFlight();
	    fb.enterMultiplePassengerDetail(int1);
	    fb.goToPaymentPage2();
	}
	@AfterStep
	public void TakeSS_ifFailed(Scenario scenario){

		fb.addScreenshot_failed(scenario);
	}
}
