@regression
Feature: Gemini view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
#    And Switch to "Gemini" view

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 0-100
    Given Search for 0 to 100 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 100-200
    Given Search for 100 to 200 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 200-300
    Given Search for 200 to 300 employee in OrgChart

    @runthissce
  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 300-400
    Given Search for 300 to 400 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 400-500
    Given Search for 400 to 500 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 500-600
    Given Search for 500 to 600 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 600-700
    Given Search for 600 to 700 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 700-800
    Given Search for 700 to 800 employee in OrgChart

  @runthissce
  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 800-900
    Given Search for 800 to 900 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 900-1000
    Given Search for 900 to 1000 employee in OrgChart

  Scenario: Open employee branch in Gemini View and verify if correct information is displayed or not 1000-End
    Given Search for 1100 to 1131 employee in OrgChart



#    Scenario: Verify any duplicate data in OrgChart employee view
#      Given Search for any duplicate employee in OrgChart Gemini view