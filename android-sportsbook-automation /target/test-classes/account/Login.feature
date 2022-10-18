Feature: Login

  Background:
    Given user opens Android Sportsbook app

  @Regression @Smoke
  Scenario: Log in to sportsbook as valid user
    When user logs in with "correct credentials"
    Then user should be logged into their account

  @Regression
  Scenario: User denied due to invalid credentials
    When user logs in with "incorrect credentials"
    Then user should receive an login error message of "That email and password combination is not valid." not allowing them to login

  @Regression
  Scenario: Suspended user attempts to log in to sportsbook
    When user logs in with "suspended account"
    Then user should receive an login error message of "Your account has been suspended by Fubo Sportsbook. You are now unable to place a wager, deposit funds and withdraw funds. In order to remedy the situation please contact Fubo Sportbook customer service here." not allowing them to login

  @Regression
  Scenario: User with deactivated account tries to log in
    When user logs in with "deactivated account"
    Then user should receive an login error message of "Something went wrong, please try again later" not allowing them to login

  @Regression
  Scenario: User attempts to log in with only pass word
    When user logs in with "just an email"
    Then the login button should not be clickable

  @Regression
  Scenario: User attempts to lg in with only password
    When user logs in with "just an password"
    Then the login button should not be clickable

  @Regression
  Scenario: user logs in with a TV account though sign up button
    When user logs in with "a TV account"
    Then user should be logged into their account