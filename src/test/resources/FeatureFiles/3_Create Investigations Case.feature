Feature: Create Investigations Case

  @CREATE
  Scenario: Verify the Process of  Create Investigations Case and submit
    Given Open CRM URL Module as "Investignoff1"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters Investigations reference number in search results
    Then Click on reference number
    Then Click create Investigations case
    Then Fill in background information and investigation objectives
    Then Submit Investigation case
    Then Investigation status should be "Pending Investigation Approval"



