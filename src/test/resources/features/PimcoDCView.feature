Feature: Pimco DC view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "Pimco Dc" view

    @test123
  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 0-20
    Given Search for 0 to 20 employee in PIMCODC view of OrgChart

      @test123
  Scenario: Orgchart, Open employee branch in EC View and verify if correct information is displayed or not 20-40
    Given Search for 20 to 40 employee in PIMCODC view of OrgChart