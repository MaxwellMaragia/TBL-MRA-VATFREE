Feature: Approve VAT 5 certificate by supervisor

  @CREATE @vat_five
  Scenario: Vat five approval by supervisor
    Given Open CRM URL Module as "TaxpayerServOff1"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters VAT FIVE certificate reference number in search results
    And click checkbox in case number
    Then click pick button dropdown
    Then switch to frame0
    Then Click on reference number
    Then VAT FREE status should be "Pending for Approval"
    Then switch to frame1
    And wait for plan to load for vat five "VAT Free Project No"
    Then switch to frame1
    Then Approve vat five certificate application
    And Click on Save button
    Then VAT FREE status should be "Approved"