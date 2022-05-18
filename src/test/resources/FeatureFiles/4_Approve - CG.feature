Feature: Approval by CG

  @CREATE
  Scenario: CG Approval
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
    Then Select status as approved and enter expiry date
    And Click on Save button
    Then VAT FREE status should be "Approved"

