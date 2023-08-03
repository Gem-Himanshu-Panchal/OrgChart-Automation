Feature: Gemini view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "DC" view

  Scenario: Orgchart, Open all the modals in
    Given Open modals in Tata Aig