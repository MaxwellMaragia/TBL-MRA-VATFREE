Feature: Assign an Investigations Case to Investigations officer

  @CREATE
  Scenario: Verify the Process of Assign Investigations Case to officer
    Given Open CRM URL Module as "Investignmangr1"
    And Close Popup Window
    And Click on Case management dropdown
    And click on Queues
    Then switch to frame0
    And enters Investigations reference number in search results
    And click checkbox in case number
    And click assign button
    Then Assign pop up is displayed
    And search team to assign
    And selects the team "Investigations Officer"
    Then Switch to default
    Then Assign pop up is displayed
    And assigns to the team or user