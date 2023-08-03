Feature: Gemini view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
#    And Switch to "Gemini" view


  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 0-100
    Given Search for 0 to 1 employee in OrgChart

    Scenario: Verify any duplicate data in OrgChart employee view
      Given Search for any duplicate employee in OrgChart Gemini view