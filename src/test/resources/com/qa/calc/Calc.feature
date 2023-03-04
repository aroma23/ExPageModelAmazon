@Calc
Feature: Calc

  Scenario Outline: Multiply
    Given Calc is launched
#    When Credentials: '<email>' and '<password>' passed to application
#    Then Error: 'Wrong email address or password' message popup
#
    Examples:
      | email               | password      |
      | stringify@gmail.com | password1234 |
