@wip
Feature: Xebia Pay Cloud
	In order to test Xebia Pay Cloud
	As a Registered user
	I want to specify the flow for Xebia pay Cloud app

@ios
Scenario: Pay Cloud Payment Test
	Given user is on Pay Cloud Home Page
	Then user logins to application with follwoing details
	|username| password|
	|shankar|  shankar|
	Then user is on Payment Page
	When user enter payment "2"
	Then user see the amount entered as "$0.02"
	And selects to Checkout
	Then user is on Swipe Page
	When user selects to pay
	Then user is on Transaction Complete Page
	And user sees the amount paid is "$0.02"
