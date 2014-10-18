
Feature: Agile NCR App
	In order to look at Agile NCR Conference
	As a Registered user
	I want to specify the flow to Agenda and Speakers

@android
Scenario: Agenda
	Given user is on AgileNCR Home Page
	Then user gets an option to choose Agenda, Speakers, Locaton and My sechedule
	When user selects Agenda
	Then user is on Agenda Screen
	When user chooses to go back
	Then user is on AgileNCR Home Page
	And i want to include scerenshot also

#@android	
Scenario: Speakers
	Given user is on AgileNCR Home Page
	Then user gets an option to choose Agenda, Speakers, Locaton and My sechedule
	When user selects Speakers
	Then user is on Speakers Screen
	When user chooses to go back
	Then user is on AgileNCR Home Page
	And i want to include scerenshot also