Feature: Create Customer Feature
  Verify new customer can be created.

  Scenario: Create new customer
    Given I logged in to home page successfully to create customer
    When I click on "New Customer" link
    Then "Add New Customer" page will displays
    When I leave all input fields and click Submit customer
    Then The alert text will displays as "please fill all fields" on Customer page
    When I enter invalid values into some fields
    Then The validation messages will display beside associate fields
    When I enter valid values into all fields
    And Click Submit customer button
    Then Success message and New customer ID will display


