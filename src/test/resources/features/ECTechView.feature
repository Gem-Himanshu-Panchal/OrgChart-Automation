@testEC
Feature: EC view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "EC" view

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 0-200
    Given Search for 0 to 200 employee in EC view of OrgChart

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 200-400
    Given Search for 200 to 400 employee in EC view of OrgChart

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 400-600
    Given Search for 400 to 600 employee in EC view of OrgChart

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 600-800
    Given Search for 600 to 800 employee in EC view of OrgChart

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 800-1000
    Given Search for 800 to 1000 employee in EC view of OrgChart

  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 1000-1137
    Given Search for 1000 to 1137 employee in EC view of OrgChart

