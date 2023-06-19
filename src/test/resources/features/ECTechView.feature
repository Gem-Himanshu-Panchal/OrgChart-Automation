Feature: Gemini view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "EC" view

    Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 0-10
      Given Search for 0 to 10 employee in EC view of OrgChart
