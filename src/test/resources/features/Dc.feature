Feature: DC data validations
  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard

    Scenario: Validate DC data
      Given Switch to "DC" view
      Then Open DC branch
      And Go to "Accounts" tab