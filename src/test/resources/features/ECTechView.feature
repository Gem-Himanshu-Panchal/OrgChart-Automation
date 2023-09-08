@regression
Feature: EC view data validations

  Background: Verify if user successfully log into Orgchart
    Given Navigate to OrgChart and login
    Then Verify if user is on OrgChart dashboard
    And Switch to "EC" view


  Scenario: Verify EC view for .Net
    Given Open modals box in EC View ".Net"
    Then Check employee in EC view for ".Net" of OrgChart


  Scenario: Verify EC view for Accounts
    Given Open modals box in EC View "Accounts"
    Then Check employee in EC view for "Accounts" of OrgChart


  Scenario: Verify EC view for Accounts
    Given Open modals box in EC View "Admin"
    Then Check employee in EC view for "Admin" of OrgChart


  Scenario: Verify EC view for Architect
    Given Open modals box in EC View "Architect"
    Then Check employee in EC view for "Architect" of OrgChart



  Scenario: Verify EC view for Asset Management
    Given Open modals box in EC View "Asset Management"
    Then Check employee in EC view for "Asset Management" of OrgChart


  Scenario: Verify EC view for Data Engineering
    Given Open modals box in EC View "Data Engineering"
    Then Check employee in EC view for "Data Engineering" of OrgChart


  Scenario: Verify EC view for Data Science/Quant/ML
    Given Open modals box in EC View "Data Science/Quant/ML"
    Then Check employee in EC view for "Data Science/Quant/ML" of OrgChart


  Scenario: Verify EC view for DesignBranding
    Given Open modals box in EC View "DesignBranding"
    Then Check employee in EC view for "DesignBranding" of OrgChart


  Scenario: Verify EC view for Devops
    Given Open modals box in EC View "Devops"
    Then Check employee in EC view for "Devops" of OrgChart



  Scenario: Verify EC view for Executive Office
    Given Open modals box in EC View "Executive Office"
    Then Check employee in EC view for "Executive Office" of OrgChart


  Scenario: Verify EC view for Full stack (Angular/ Node/ React)
    Given Open modals box in EC View "Full stack (Angular/ Node/ React)"
    Then Check employee in EC view for "Full stack (Angular/ Node/ React)" of OrgChart


  Scenario: Verify EC view for Human Resource
    Given Open modals box in EC View "Human Resource"
    Then Check employee in EC view for "Human Resource" of OrgChart


  Scenario: Verify EC view for Infrastructure/IT
    Given Open modals box in EC View "Infrastructure/IT"
    Then Check employee in EC view for "Infrastructure/IT" of OrgChart


  Scenario: Verify EC view for Insurance
    Given Open modals box in EC View "Insurance"
    Then Check employee in EC view for "Insurance" of OrgChart


  Scenario: Verify EC view for Java
    Given Open modals box in EC View "Java"
    Then Check employee in EC view for "Java" of OrgChart


  Scenario: Verify EC view for Python/C++
    Given Open modals box in EC View "Python/C++"
    Then Check employee in EC view for "Python/C++" of OrgChart


  Scenario: Verify EC view for QA
    Given Open modals box in EC View "QA"
    Then Check employee in EC view for "QA" of OrgChart


  Scenario: Verify EC view for Training
    Given Open modals box in EC View "Training"
    Then Check employee in EC view for "Training" of OrgChart