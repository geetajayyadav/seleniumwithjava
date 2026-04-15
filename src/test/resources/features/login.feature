@TEST_AJAY-8

Feature: Login Test

Scenario: Valid Login
Given I open browser
When I enter username "Admin" and password "admin123"
And I click login button
Then I should see dashboard
