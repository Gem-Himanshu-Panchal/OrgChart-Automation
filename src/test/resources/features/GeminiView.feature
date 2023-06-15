@test
Feature: Gemini view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
#    And Switch to "Gemini" view


  Scenario: Open employee branch in Gemini View and verify id correct information is displayed or not 0-10
    Given Search for 0 to 50 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify id correct information is displayed or not 50-100
    Given Search for 50 to 100 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify id correct information is displayed or not 100-150
    Given Search for 100 to 150 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify id correct information is displayed or not 150-200
    Given Search for 150 to 200 employee in OrgChart
