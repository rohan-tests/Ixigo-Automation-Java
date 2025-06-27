@Flight
Feature: Flight Booking Feature
@OneWayFlight
  Scenario: Book a one-way flight from Delhi to Aurangabad
    Given I navigate to the ixigo website
    When I select "One Way" as the trip type
    And I enter "Delhi" as the departure city
    And I enter "Aurangabad" as the destination city
    And I select "December 23, 2024" as the departure date
    And I click on the Search button
    Then I should see a list of available flights
    And I should be able to select flight
    And I should enter passenger details
    Then I should visit payment page
    
@RoundTrip
	Scenario: Book a round trip flight
    Given I navigate to the ixigo website
    When I select "Round Trip" as the trip type
    And I enter "Bengaluru" as the departure city
    And I enter "Pune" as the destination city
    And I select "December 23, 2024" as the departure date
    And I select "December 27, 2024" as the return date
    And I click on the Search button
    Then I should see a list of available flights for round trip
    And I should be able to select flights for both legs of the journey
    And I should enter passenger details for round trip
    Then I should visit payment page
  
@ExcelInput
 	Scenario: Search flight by taking input from excel file
 	Given I navigate to the ixigo website
    When I select "One Way" as the trip type
    And I enter flight details from excel file
    And I click on the Search button
    Then I should see the results in excel file  
  
@NoFlights
	Scenario: Search for flights with no available flights
    Given I navigate to the ixigo website
    When I select "One Way" as the trip type
    And I enter "Latur" as the departure city
    And I enter "Kolhapur" as the destination city
    And I select "December 25, 2024" as the departure date
    And I click on the Search button
    Then I should see a message stating "No flights found!"

@DirectFlightsOnly
	Scenario: Apply filter for direct flights only
    Given I navigate to the ixigo website
    When I select "One Way" as the trip type
    And I enter "Delhi" as the departure city
    And I enter "Aurangabad" as the destination city
    And I select "December 25, 2024" as the departure date
    And I click on the Search button
    And I apply the "Non-Stop" filter
    Then I should see only direct flights
    
@DifferentPassengers
	Scenario Outline: Book a flight with different numbers of passengers
    Given I navigate to the ixigo website
    When I select "One Way" as the trip type
    And I enter "<departure_city>" as the departure city
    And I enter "<destination_city>" as the destination city
    And I select "<departure_date>" as the departure date
    And I select "<passenger_count>" as the number of passengers
    And I click on the Search button
    Then I should see a list of available flights
    And I should be able to proceed with the booking for <passenger_count> passengers

    Examples:
      | departure_city | destination_city | departure_date 		  | passenger_count |
      | Delhi     	   | Mumbai           | December 30, 2024     | 3               |
      | Delhi     	   | Aurangabad       | December 28, 2024     | 2               |
 
 
    