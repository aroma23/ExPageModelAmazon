@Login
Feature: Login
  Description: The purpose of this feature is to login to https://pa.caesarsonline.com/login/

  Scenario Outline: Login
  Description:
    Given Login page is launched
    When Credentials: '<email>' and '<password>' passed to application
    Then Error: 'Wrong email address or password' message popup

    Examples:
      | email               | password      |
      | stringify@gmail.com | password1234 |
