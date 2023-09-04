@testPimcoDC
Feature: Pimco DC view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "Pimco Dc" view


  Scenario: Verify Pimco DC view for Pimco Analytics Support
    Given Open modals box in "Pimco Analytics Support"
    Then Check employee in PIMCODC view for "Pimco Analytics Support" of OrgChart


  Scenario: Verify Pimco DC view for Pimco Client Facing Tech
    Given Open modals box in "Pimco Client Facing Tech"
    Then Check employee in PIMCODC view for "Pimco Client Facing Tech" of OrgChart

#    pass
  Scenario: Verify Pimco DC view for Pimco Credit Research / PARR
    Given Open modals box in "Pimco Credit Research / PARR"
    Then Check employee in PIMCODC view for "Pimco Credit Research / PARR" of OrgChart

#    pass
  Scenario: Verify Pimco DC view for Pimco CSA frontend & ABS Tech
    Given Open modals box in "Pimco CSA frontend & ABS Tech"
    Then Check employee in PIMCODC view for "Pimco CSA frontend & ABS Tech" of OrgChart

#pass
  Scenario: Verify Pimco DC view for Pimco Data Engineering
    Given Open modals box in "Pimco Data Engineering"
    Then Check employee in PIMCODC view for "Pimco Data Engineering" of OrgChart

# pass
  Scenario: Verify Pimco DC view for Pimco Data Science
    Given Open modals box in "Pimco Data Science"
    Then Check employee in PIMCODC view for "Pimco Data Science" of OrgChart

#    pass
  Scenario: Verify Pimco DC view for Pimco DevOps / Platform Engineering
    Given Open modals box in "Pimco DevOps / Platform Engineering"
    Then Check employee in PIMCODC view for "Pimco DevOps / Platform Engineering" of OrgChart

#    pass
  Scenario: Verify Pimco DC view for Pimco EMEA Tech
    Given Open modals box in "Pimco EMEA Tech"
    Then Check employee in PIMCODC view for "Pimco EMEA Tech" of OrgChart

#pass
  Scenario: Verify Pimco DC view for Pimco FE Infrastructure
    Given Open modals box in "Pimco FE Infrastructure"
    Then Check employee in PIMCODC view for "Pimco FE Infrastructure" of OrgChart


  Scenario: Verify Pimco DC view for Pimco Infrastructure
    Given Open modals box in "Pimco Infrastructure"
    Then Check employee in PIMCODC view for "Pimco Infrastructure" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Investment Data
    Given Open modals box in "Pimco Investment Data"
    Then Check employee in PIMCODC view for "Pimco Investment Data" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Operations Tech
    Given Open modals box in "Pimco Operations Tech"
    Then Check employee in PIMCODC view for "Pimco Operations Tech" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Portfolio Analytics Support
    Given Open modals box in "Pimco Portfolio Analytics Support"
    Then Check employee in PIMCODC view for "Pimco Portfolio Analytics Support" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Puma
    Given Open modals box in "Pimco Puma"
    Then Check employee in PIMCODC view for "Pimco Puma" of OrgChart

  Scenario: Verify Pimco DC view for Pimco QA
    Given Open modals box in "Pimco QA"
    Then Check employee in PIMCODC view for "Pimco QA" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Reference Data
    Given Open modals box in "Pimco Reference Data"
    Then Check employee in PIMCODC view for "Pimco Reference Data" of OrgChart

  Scenario: Verify Pimco DC view for Pimco RiskOps
    Given Open modals box in "Pimco RiskOps"
    Then Check employee in PIMCODC view for "Pimco RiskOps" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Sales & Marketing Tech
    Given Open modals box in "Pimco Sales & Marketing Tech"
    Then Check employee in PIMCODC view for "Pimco Sales & Marketing Tech" of OrgChart

  Scenario: Verify Pimco DC view for Pimco Security
    Given Open modals box in "Pimco Security"
    Then Check employee in PIMCODC view for "Pimco Security" of OrgChart

  Scenario: Verify Pimco DC view for Pimco ServiceOps
    Given Open modals box in "Pimco ServiceOps"
    Then Check employee in PIMCODC view for "Pimco ServiceOps" of OrgChart