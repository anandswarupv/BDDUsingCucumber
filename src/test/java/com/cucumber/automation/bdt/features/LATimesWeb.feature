
Feature: LA Times web testing
	In order to verify LA Times Home Page
	As a user	
	I want to specify test conditions for home page

@web
Scenario: Verification of Home Page attributes
	Given user is on LA Times Home Page
	Then user should be able to see Master Logo for Los Angeles Times
	Then user should be able to see Facebook Like link
	Then user shhould be able to see current Date
	Then user should be able to see weather link

@web
Scenario: Verification of Search on Home page
	Given user is on LA Times Home Page
	And user enters 'Whitehouse' in search box
	And user clicks on 'Search' button.
	Then user should be taken to search page
	Then user should be able to see 'Search' in Header
	
@web
Scenario: Verifcation of Subscribe Link on Home Page
	Given user is on LA Times Home Page
	And user enters clicks on 'Subscribe' link
	Then user should be on 'Subscriber' page Check url
	Then user should be on 'Subscriber' page Check title
	Then user should be displayed 'Find Special Offers' text
	




	
	
 
	