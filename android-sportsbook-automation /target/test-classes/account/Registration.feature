Feature: Registration Functionality

  Background:
    Given user opens Android Sportsbook app

  @Regression @Smoke
  Scenario: Sign up to the sportsbook
    Given user signs up as a new user using this information
      | Email           | Password        | Zip Code |
      | dechols@fubo.tv | FuboTV#yyyyMMdd | 60677    |
    And completes KYC with this information
      | First Name | Last Name  | Date Of Birth | House Number    | Street Name | City      | State    | Zip Code | Phone Number     | SSN           | Strong Auth | Promotion Code |
      | Android    | Automation | 4/13/1983     | 450 Test Avenue | Test        | Test Park | Illinois | 60677    | Generated Number | Generated SSN | No          | N/A            |
    Then the user should be registered but not verified to the sportsbook

  @Regression
  Scenario: User under 21 attempts to register to the sportsbook
    Given user signs up as a new user using this information
      | Email           | Password        | Zip Code |
      | dechols@fubo.tv | FuboTV#yyyyMMdd | 60677    |
    And completes KYC with date of birth information under twenty-one
      | First Name | Last Name  | Date Of Birth | House Number    | Street Name | City      | State    | Zip Code | Phone Number     | SSN           | Strong Auth | Promotion Code |
      | Android    | Automation | 4/13/2010     | 450 Test Avenue | Test        | Test Park | Illinois | 60677    | Generated Number | Generated SSN | No          | N/A            |
    Then the sportsbook should give a "Birth Date is invalid" error message and not allowed to sign up to the sportsbook

    @Regression
    Scenario: User attempts to sign up with an already registered account
      Given user attempts signs up with an already registered account
        | Email           | Password        | Zip Code |
        | dechols@fubo.tv | FuboTV#yyyyMMdd | 60677    |
      Then the sportsbook should give a "The email has already been registered, please sign in into your account." error message and can not continue to register

      @Regression
      Scenario: User attempts to sign up an already existing phone number
        Given user signs up as a new user using this information
          | Email           | Password        | Zip Code |
          | dechols@fubo.tv | FuboTV#yyyyMMdd | 60677    |
        And completes KYC with this information with an already existing phone number
          | First Name | Last Name  | Date Of Birth | House Number    | Street Name | City      | State    | Zip Code | Phone Number     | SSN           | Strong Auth | Promotion Code |
          | Android    | Automation | 4/13/1983     | 450 Test Avenue | Test        | Test Park | Illinois | 60677    | Generated Number | Generated SSN | No          | N/A            |
        Then the sportsbook should give a "Mobile number already exists. " error message and not allowed to sign up to the sportsbook