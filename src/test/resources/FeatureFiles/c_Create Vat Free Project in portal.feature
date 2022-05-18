Feature: Create Vat Free Project Application in Portal

  Background:
    Given User navigates to portal login screen
    Then Enters portal username "tripsuser" and password "Password@" to login

  @CREATE @edit
  Scenario: Verify The Process of Create Vat Free Project Application in Portal
    And Navigate to my tax > Vat Free Project Application
    Then Enter portal project owner details with tin "C0106479"
    Then Enter project owner name as "DR Maxipain KMn"
    Then Enter project owner address as "Kenema, Lilongwe, Central Region, Malawi"
    Then Enter project owner contact number as "254686777756"
    Then Enter project owner email address as "margiewambui11@gmail.com"
    Then Enter project financier details for portal
    Then Enter Contractor tin as ""
    Then Enter Contractor names as ""
    Then Enter Contractor address as ""
    Then Enter Contractor contact number as ""
    Then Enter Contractor email address as ""
    Then Enter project details portal
    Then Upload goods and services details template