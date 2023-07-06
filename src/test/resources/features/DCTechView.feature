Feature: EC view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "DC" view

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 150-250
    Given Search for 0 to 10 employee in DC view of OrgChart