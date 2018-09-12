Feature: Create Deposit Feature
  Verify deposit function work fine with the account just created

  Scenario: Create new deposit based on the existing account
    Given I logged in to home page successfully to create deposit
    When I click on Deposit link
    Then "Amount Deposit Form" page will displays
    When I leave all input fields and click Submit deposit
    Then The alert text will displays as "Please fill all fields" on Deposit page
    When I enter invalid values into "Account No" and Amount
    Then The validation messages will display beside these fields on Deposit page
    When I enter valid values into "Account No" and Amount
    And Click Submit deposit button
    Then Success message and New Transaction ID will display
