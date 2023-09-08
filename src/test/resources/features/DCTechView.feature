@regression
Feature: DC view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "DC" view

    @testthis
  Scenario: Verify DC view for Accounts
    Given Open modals in "Accounts"
    Then Check employee in DC view for "Accounts" of OrgChart

  @testthis
  Scenario: Verify DC view for AFLI
    Given Open modals in "AFLI"
    Then Check employee in DC view for "AFLI" of OrgChart

  @testthis
  Scenario: Verify DC view for Beacon DevOps
    Given Open modals in "Beacon DevOps"
    Then Check employee in DC view for "Beacon DevOps" of OrgChart


  Scenario: Verify DC view for Discern
    Given Open modals in "Discern"
    Then Check employee in DC view for "Discern" of OrgChart

  @testthis
  Scenario: Verify DC view for DPLI/PLIL
    Given Open modals in "DPLI/PLIL"
    Then Check employee in DC view for "DPLI/PLIL" of OrgChart

@run @testthis
  Scenario: Verify DC view for Edward Jones Developer Platform
    Given Open modals in "Edward Jones Developer Platform"
    Then Check employee in DC view for "Edward Jones Developer Platform" of OrgChart


  Scenario: Verify DC view for Edward Jones QA
    Given Open modals in "Edward Jones QA"
    Then Check employee in DC view for "Edward Jones QA" of OrgChart

@run
  Scenario: Verify DC view for Hdfc Ergo
    Given Open modals in "Hdfc Ergo"
    Then Check employee in DC view for "Hdfc Ergo" of OrgChart


  Scenario: Verify DC view for Human Resource
    Given Open modals in "Human Resource"
    Then Check employee in DC view for "Human Resource" of OrgChart


  Scenario: Verify DC view for Internal Athena
    Given Open modals in "Internal Athena"
    Then Check employee in DC view for "Internal Athena" of OrgChart

@check
  Scenario: Verify DC view for Internal Catalyst
    Given Open modals in "Internal Catalyst"
    Then Check employee in DC view for "Internal Catalyst" of OrgChart


  Scenario: Verify DC view for Internal Contri-Neo-Retina
    Given Open modals in "Internal Contri-Neo-Retina"
    Then Check employee in DC view for "Internal Contri-Neo-Retina" of OrgChart


  Scenario: Verify DC view for Internal Gemini Hpipe_Simul
    Given Open modals in "Internal Gemini Hpipe_Simul"
    Then Check employee in DC view for "Internal Gemini Hpipe_Simul" of OrgChart


  Scenario: Verify DC view for Internal HRIS
    Given Open modals in "Internal HRIS"
    Then Check employee in DC view for "Internal HRIS" of OrgChart


  Scenario: Verify DC view for Internal Orgchart
    Given Open modals in "Internal Orgchart"
    Then Check employee in DC view for "Internal Orgchart" of OrgChart


  Scenario: Verify DC view for Internal QA
    Given Open modals in "Internal QA"
    Then Check employee in DC view for "Internal QA" of OrgChart


  Scenario: Verify DC view for Internal RnD
    Given Open modals in "Internal RnD"
    Then Check employee in DC view for "Internal RnD" of OrgChart

  @Dothis @check
  Scenario: Verify DC view for Internal Training
    Given Open modals in "Internal Training"
    Then Check employee in DC view for "Internal Training" of OrgChart

  @Dothis
  Scenario: Verify DC view for Internal-GemFin
    Given Open modals in "Internal-GemFin"
    Then Check employee in DC view for "Internal-GemFin" of OrgChart

  @Dothis
  Scenario: Verify DC view for Mosaic Smart Data
    Given Open modals in "Mosaic Smart Data"
    Then Check employee in DC view for "Mosaic Smart Data" of OrgChart

  @Dothis
  Scenario: Verify DC view for Sharemeister
    Given Open modals in "Sharemeister"
    Then Check employee in DC view for "Sharemeister" of OrgChart

  @Dothis @check
  Scenario: Verify DC view for Tata Aig
    Given Open modals in "Tata Aig"
    Then Check employee in DC view for "Tata Aig" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Analytics Support
    Given Open modals in "Pimco Analytics Support"
    Then Check employee in DC view for "Pimco Analytics Support" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Client Facing Tech
    Given Open modals in "Pimco Client Facing Tech"
    Then Check employee in DC view for "Pimco Client Facing Tech" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Credit Research / PARR
    Given Open modals in "Pimco Credit Research / PARR"
    Then Check employee in DC view for "Pimco Credit Research / PARR" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco CSA frontend & ABS Tech
    Given Open modals in "Pimco CSA frontend & ABS Tech"
    Then Check employee in DC view for "Pimco CSA frontend & ABS Tech" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Data Engineering
    Given Open modals in "Pimco Data Engineering"
    Then Check employee in DC view for "Pimco Data Engineering" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Data Science
    Given Open modals in "Pimco Data Science"
    Then Check employee in DC view for "Pimco Data Science" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco DevOps / Platform Engineering
    Given Open modals in "Pimco DevOps / Platform Engineering"
    Then Check employee in DC view for "Pimco DevOps / Platform Engineering" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco EMEA Tech
    Given Open modals in "Pimco EMEA Tech"
    Then Check employee in DC view for "Pimco EMEA Tech" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco FE Infrastructure
    Given Open modals in "Pimco FE Infrastructure"
    Then Check employee in DC view for "Pimco FE Infrastructure" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Infrastructure
    Given Open modals in "Pimco Infrastructure"
    Then Check employee in DC view for "Pimco Infrastructure" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Investment Data
    Given Open modals in "Pimco Investment Data"
    Then Check employee in DC view for "Pimco Investment Data" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Operations Tech
    Given Open modals in "Pimco Operations Tech"
    Then Check employee in DC view for "Pimco Operations Tech" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Portfolio Analytics Support
    Given Open modals in "Pimco Portfolio Analytics Support"
    Then Check employee in DC view for "Pimco Portfolio Analytics Support" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Puma
    Given Open modals in "Pimco Puma"
    Then Check employee in DC view for "Pimco Puma" of OrgChart

  @Dothis @check
  Scenario: Verify DC view for Pimco QA
    Given Open modals in "Pimco QA"
    Then Check employee in DC view for "Pimco QA" of OrgChart

  @Dothis @check
  Scenario: Verify DC view for Pimco Reference Data
    Given Open modals in "Pimco Reference Data"
    Then Check employee in DC view for "Pimco Reference Data" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco RiskOps
    Given Open modals in "Pimco RiskOps"
    Then Check employee in DC view for "Pimco RiskOps" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Sales & Marketing Tech
    Given Open modals in "Pimco Sales & Marketing Tech"
    Then Check employee in DC view for "Pimco Sales & Marketing Tech" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco Security
    Given Open modals in "Pimco Security"
    Then Check employee in DC view for "Pimco Security" of OrgChart

  @Dothis
  Scenario: Verify DC view for Pimco ServiceOps
    Given Open modals in "Pimco ServiceOps"
    Then Check employee in DC view for "Pimco ServiceOps" of OrgChart

  Scenario: Verify DC view for Hartron
    Given Open modals in "Hartron"
    Then Check employee in DC view for "Hartron" of OrgChart

  Scenario: Verify DC view for Piston
    Given Open modals in "Piston"
    Then Check employee in DC view for "Piston" of OrgChart

  Scenario: Verify DC view for Sports Performance Analytics
    Given Open modals in "Sports Performance Analytics"
    Then Check employee in DC view for "Sports Performance Analytics" of OrgChart