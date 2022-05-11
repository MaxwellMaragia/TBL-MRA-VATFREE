Feature: Authorization by Deputy commissioner

  @CREATE
  Scenario: Deputy commissioner authorization
    Given Open CRM URL Module as "Vatfreeprojdctech1"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters VAT FREE reference number in search results
    Then click pick button dropdown
    Then Click on reference number
    Then VAT FREE status should be "Awaiting Authorisation"
    Then switch to frame1
    And wait for plan to load "Project Owner TIN"
    Then Enter deputy commissioner notes and recommendations
    Then switch to frame1
    Then Select status as send for approval
    And Click on Save button
    Then VAT FREE status should be "Awaiting Approval"