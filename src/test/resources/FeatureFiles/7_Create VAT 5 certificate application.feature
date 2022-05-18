Feature: Create Vat Five Project Application

  Background:
    Given User navigates to the login page
    Then Enters the username "tripsuser" and password "Passw0rd" to login

  @CREATE @vat_five
  Scenario: Verify The Process of Create Vat Five Project Application
    And Click on Vat Free Projects > Create Vat five tax certificate application
    Then Enter purchaser details with tin "C0106479"
    Then Enter supplier details for VAT certificate "C0103221"
    Then Enter Vat free project number
    Then Select bill of quantities as goods for vat five
    Then Click add under bill of quantities for vat five
    Then Enter Vat free goods and services details for vat five
    Then Enter procedue code number
    Then Enter exclusive use for
    Then Enter place of use
    Then Enter physical address
    Then Enter amount paid
    Then Enter receipt number
    Then Select bills of quantities certified for vat five
    Then Select bills of quantities certified by "C0103632"
    Then Add attachment details for vat five
    Then Enter details of person authorizing application
    Then Enter details of person making application for vat five
    Then Submit vat five project application
    Then Verify success message "Processing Completed - Reference Number -"
    Then Obtain reference number for VAT FIVE "Processing Completed - Reference Number"


