Feature: Approve update for VAT free project

  @CREATE
  Scenario: Tech officer level
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

  @CREATE
  Scenario: Deputy commissioner level
    Given Open CRM URL Module as "Vatfreeprojdctech1"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters VAT FREE reference number in search results
    And click checkbox in case number
    Then click pick button dropdown
    Then switch to frame0
    Then Click on reference number
    Then VAT FREE status should be "Awaiting Authorisation"
    Then switch to frame1
    And wait for plan to load "Project Owner TIN"
    Then Enter deputy commissioner notes and recommendations
    Then switch to frame1
    Then Select status as send for approval
    And Click on Save button
    Then VAT FREE status should be "Awaiting Approval"

  @CREATE
  Scenario: CG level
    Given Open CRM URL Module as "ComGen"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters VAT FREE reference number in search results
    And click checkbox in case number
    Then click pick button dropdown
    Then switch to frame0
    Then Click on reference number
    Then VAT FREE status should be "Awaiting Approval"
    Then switch to frame1
    And wait for plan to load "Project Owner TIN"
    Then Enter CG notes
    Then switch to frame1
    Then Select status as approved
    And Click on Save button
    Then VAT FREE status should be "Approved"