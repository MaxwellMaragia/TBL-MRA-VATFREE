Feature: Update Vat Free Project Application

  Background:
    Given User navigates to the login page
    Then Enters the username "tripsuser" and password "Passw0rd" to login

  @CREATE @boom
  Scenario: Verify The Process of Updating Vat Free Project Application
    And Click on Vat Free Projects > Maintain Vat Free Project
    Then Enter project name and search
    Then Obtain project id
    Then Update attachment for VAT free project application
    Then Verify success message "Record Added"
    Then Select reason for edit
    Then Submit application
    Then Verify success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"







