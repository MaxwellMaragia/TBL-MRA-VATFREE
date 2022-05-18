Feature: Review by Tech officer

  @CREATE
  Scenario: Tech officer review
    Given Open CRM URL Module as "Vatfreeprojtechoff1"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters VAT FREE reference number in search results
    And click checkbox in case number
    Then pick the case
    Then switch to frame0
    Then Click on reference number
    Then VAT FREE status should be "Pending Review"
    Then switch to frame1
    And wait for plan to load "Designation"
    Then Enter tech officer notes "Notes"
    Then switch to frame1
    Then Select status as send for authorization
    And Click on Save button
    Then VAT FREE status should be "Awaiting Authorisation"