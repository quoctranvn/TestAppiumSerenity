Feature: Create Account Feature
  Verify to create new account based on the customer just created above

  Scenario: Create new customer based on the existing customer
    Given I logged in to home page successfully to create account
    When I click on "New Account" link
    Then "Add New Account" page will displays
    When I leave all input fields and click Submit account
    Then The alert text will displays as "Please fill all fields" on Account page
    When I enter invalid values into "Customer id" and "Initial deposit"
    Then The validation messages will display beside these fields
    When I enter valid values into "Customer id" and "Initial deposit"
    And Click Submit account button
    Then Success message and New Account ID will display
