Feature: Create Vat Free Project Application

  Background:
    Given User navigates to the login page
    Then Enters the username "tripsuser" and password "Passw0rd" to login

  @CREATE @edit
  Scenario: Verify The Process of Create Vat Free Project Application
    And Click on Vat Free Projects > Vat Free Project Application
    Then Enter project owner details with tin "C0106479"
    Then Enter project financier details
    Then Add contractor details with tin "C0106451"
    Then Add project details
    Then Select bill of quantities as goods
    Then Click add under bill of quantities
    Then Enter Vat free goods and services details
    Then Select bill of quantities certified
    Then Enter supplier details with tin "C0103221"
    Then Enter details of authorizing officials with tin "C0103632"
    Then Add attachment for VAT free project application
    Then Enter details of person making application
    Then Submit application
    Then Verify success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"

    




