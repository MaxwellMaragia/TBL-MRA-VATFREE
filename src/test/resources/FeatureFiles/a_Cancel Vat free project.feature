Feature: Cancel Vat Free Project

  Background:
    Given User navigates to the login page
    Then Enters the username "tripsuser" and password "Passw0rd" to login

  @CREATE @boom
  Scenario: Verify The Process of Cancelling Vat Free Project
    And Click on Vat Free Projects > Maintain Vat Free Project
    Then Enter project name and search
    Then Click cancel project
    Then Select cancellation reason as "Business Ceased"
    Then Submit application
    Then Obtain reference number "Processing Completed - Reference Number -"