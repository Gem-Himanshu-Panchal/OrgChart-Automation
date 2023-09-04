Feature: Gemini view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
#    And Switch to "Gemini" view

  Scenario: Orgchart, Open all the modals in
    Given Open hierarchy in Gemini View



  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 0-100
    Given Search for 346 to 1200 employee in OrgChart